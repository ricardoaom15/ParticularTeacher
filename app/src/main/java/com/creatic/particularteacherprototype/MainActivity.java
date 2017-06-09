package com.creatic.particularteacherprototype;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.creatic.particularteacherprototype.adapters.OfferAdapter;
import com.creatic.particularteacherprototype.adapters.SubjectAdapter;
import com.creatic.particularteacherprototype.database.OfferDao;
import com.creatic.particularteacherprototype.database.SubjectDao;
import com.creatic.particularteacherprototype.databinding.ActivityMainBinding;
import com.creatic.particularteacherprototype.models.Offer;
import com.creatic.particularteacherprototype.models.Subject;
import com.creatic.particularteacherprototype.util.L;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SubjectAdapter.OnItemClick, OfferAdapter.OnItemClick {

    private static final Integer TIMER_DURATION = 2500;

    ActivityMainBinding binding;
    SubjectAdapter subjectAdapter;
    OfferAdapter offerAdapter;
    SubjectDao subjectDao;
    OfferDao offerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        L.subjectStaticList = new ArrayList<>();
        subjectDao = new SubjectDao(this);
        subjectAdapter = new SubjectAdapter(this, this, L.subjectStaticList);
        binding.mainContent.mainSubjectList.setAdapter(subjectAdapter);
        binding.mainContent.mainSubjectList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        L.offerStaticList = new ArrayList<>();
        offerDao = new OfferDao(this);
        offerAdapter = new OfferAdapter(this, this, L.offerStaticList);
        binding.mainContent.mainOfferList.setAdapter(offerAdapter);
        binding.mainContent.mainOfferList.setLayoutManager(new LinearLayoutManager(this));

        binding.mainHeaderImg.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });
        binding.mainHeaderImg.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        binding.mainHeaderImg.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));

        startSlider();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){

        L.subjectStaticList.clear();
        List<Subject> subjects = subjectDao.selectAll();
        if(subjects.size() == 0){
            subjects.add(new Subject("Matematicas"));
            subjects.add(new Subject("Ciencias Naturales"));
            subjects.add(new Subject("Lenguas"));
            subjects.add(new Subject("Artes"));
            subjects.add(new Subject("Ciencias Sociales"));
        }
        for (Subject subject : subjects) {
            L.subjectStaticList.add(subject);
        }
        subjectAdapter.notifyDataSetChanged();

        L.offerStaticList.clear();
        List<Offer> offers = offerDao.selectAll();
        for (Offer offer : offers) {
            L.offerStaticList.add(offer);
        }
        offerAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSubjectClick(View v) {
        int position = binding.mainContent.mainSubjectList.getChildAdapterPosition(v);
        Toast.makeText(this, ""+L.subjectStaticList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOfferClick(View v) {

    }

    private void startSlider(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(MainActivity.this)
                                .load(Uri.parse(getRandomImage()))
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        binding.mainHeaderImg.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                                    }

                                    @Override
                                    public void onBitmapFailed(Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                    }
                                });
                    }
                });
            }
        }, 0, TIMER_DURATION);
    }

    private String getRandomImage(){
        String[] urls = {"http://www.artsfon.com/large/201504/68395.jpg",
                "https://s-media-cache-ak0.pinimg.com/736x/cf/1d/3f/cf1d3fe48d2707aa2c7e8d20d795e6ef.jpg",
                "http://www.deafacademy2013.com/wp-content/uploads/2017/05/635729543467663355-385689442_tumblr_static_wallpaper__book_by_analaurasam-d6cak0w.imgopt1000x70.jpg"};

        int length = urls.length;
        int position = (int) (Math.random() * length);

        return urls[position];
    }
}
