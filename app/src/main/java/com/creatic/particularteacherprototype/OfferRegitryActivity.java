package com.creatic.particularteacherprototype;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.creatic.particularteacherprototype.adapters.SubjectSpinnerAdapter;
import com.creatic.particularteacherprototype.database.OfferDao;
import com.creatic.particularteacherprototype.database.SubjectDao;
import com.creatic.particularteacherprototype.models.Offer;
import com.creatic.particularteacherprototype.models.Subject;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfferRegitryActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SubjectSpinnerAdapter adapter;
    SubjectDao subjectDao;
    List<Subject> subjectList;
    OfferDao offerDao;

    @BindView(R.id.offer_reg_subject)
    Spinner subjectSpinner;

    @BindView(R.id.offer_reg_title)
    EditText title;

    @BindView(R.id.offer_reg_price)
    EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_regitry);
        ButterKnife.bind(this);

        offerDao = new OfferDao(this);
        subjectDao = new SubjectDao(this);
        subjectList = new ArrayList<>();
        adapter = new SubjectSpinnerAdapter(this, subjectList);
        subjectSpinner.setAdapter(adapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.offer_reg_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        subjectList.clear();
        subjectList = subjectDao.selectAll();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(2.452479, -76.598173);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }

    @OnClick(R.id.offer_reg_accept)
    public void addOffer(){
        Offer offer = new Offer();
        offer.setTitle(title.getText().toString());
        offer.setPrice(Long.parseLong(price.getText().toString()));
        offer.setSubjectId(subjectList.get(subjectSpinner.getSelectedItemPosition()).getId());
        SharedPreferences preferences = getSharedPreferences("preferencias",MODE_PRIVATE);
        offer.setUserId(preferences.getLong("userId", 1));
        if(offerDao.insert(offer)){
            Toast.makeText(this, "Oferta agregada correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "No se pudo agregar la oferta", Toast.LENGTH_SHORT).show();
        }
    }
}
