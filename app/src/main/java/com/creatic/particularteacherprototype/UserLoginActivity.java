package com.creatic.particularteacherprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.creatic.particularteacherprototype.database.UserDao;
import com.creatic.particularteacherprototype.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class UserLoginActivity extends AppCompatActivity  {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    UserDao userDao;
    long uId;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("preferencias",MODE_PRIVATE);
        boolean logged = preferences.getBoolean("logged", false);
        if(logged){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);

        userDao = new UserDao(this);

    }

    @OnClick(R.id.sign_in_button)
    public void login(){
        if(validate()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("logged", true);
            editor.putLong("userId", uId);
            editor.commit();
            Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.create_account)
    public void createAccount(){
        Intent intent = new Intent(UserLoginActivity.this, UserRegistryActivity.class);
        startActivity(intent);
    }

    private boolean validate(){
        List<User> userList = userDao.selectAll();

        String u = username.getText().toString();
        String p = password.getText().toString();

        for (User user : userList) {
            if(user.getUsername().equals(u) && user.getPassword().equals(p)){
                uId = user.getId();
                return true;
            }
        }

        return false;
    }

}

