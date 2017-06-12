package com.creatic.particularteacherprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creatic.particularteacherprototype.database.UserDao;
import com.creatic.particularteacherprototype.databinding.ActivityUserProfileBinding;
import com.creatic.particularteacherprototype.models.User;

import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    ActivityUserProfileBinding binding;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        userDao = new UserDao(this);
        List<User> userList = userDao.selectAll();
        for (User user : userList) {
            if (user.getId() == id){
                binding.setUser(user);
            }
        }
    }
}
