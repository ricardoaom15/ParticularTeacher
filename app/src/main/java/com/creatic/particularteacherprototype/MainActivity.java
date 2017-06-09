package com.creatic.particularteacherprototype;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.creatic.particularteacherprototype.adapters.OfferAdapter;
import com.creatic.particularteacherprototype.adapters.SubjectAdapter;
import com.creatic.particularteacherprototype.database.OfferDao;
import com.creatic.particularteacherprototype.database.SubjectDao;
import com.creatic.particularteacherprototype.databinding.ActivityMainBinding;
import com.creatic.particularteacherprototype.models.Offer;
import com.creatic.particularteacherprototype.models.Subject;
import com.creatic.particularteacherprototype.util.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SubjectAdapter.OnItemClick, OfferAdapter.OnItemClick {

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
}
