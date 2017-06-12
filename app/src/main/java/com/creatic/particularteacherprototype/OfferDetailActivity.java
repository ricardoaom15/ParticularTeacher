package com.creatic.particularteacherprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.creatic.particularteacherprototype.databinding.ActivityOfferDetailBinding;
import com.creatic.particularteacherprototype.models.Offer;
import com.creatic.particularteacherprototype.util.L;

public class OfferDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityOfferDetailBinding binding;
    Offer offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfferDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        int pos = bundle.getInt("pos");
        offer = L.offerStaticList.get(pos);
        binding.setOffer(offer);

        binding.offerDetTeacherProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.offer_det_teacher_profile:
                Intent intent = new Intent(OfferDetailActivity.this, UserProfileActivity.class);
                intent.putExtra("id", offer.getUserId());
                startActivity(intent);
                break;
        }
    }
}



