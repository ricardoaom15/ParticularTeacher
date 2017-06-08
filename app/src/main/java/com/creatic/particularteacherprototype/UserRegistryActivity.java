package com.creatic.particularteacherprototype;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creatic.particularteacherprototype.database.UserDao;
import com.creatic.particularteacherprototype.models.User;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class UserRegistryActivity extends AppCompatActivity  {
    EditText edtname;
    EditText edtusername;
    EditText edtemail;
    EditText edtpassword;
    EditText edtphone;
    Button btnregistry;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registry);
        edtname = (EditText) findViewById(R.id.edtname);
        edtusername = (EditText) findViewById(R.id.edtusername);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtphone = (EditText) findViewById(R.id.edtphone);
        edtpassword = (EditText) findViewById(R.id.edtpassword);


        btnregistry = (Button) findViewById(R.id.btnRegistry);
        btnregistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao userDao = new UserDao(getApplicationContext());

                boolean in = userDao.insert(new User(edtname.getText().toString(), edtusername.getText().toString(),
                        edtemail.getText().toString(), edtphone.getText().toString(), edtpassword.getText().toString()));

                if (in == false) {
                    Toast.makeText(UserRegistryActivity.this, "Inserto Correctamente" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserRegistryActivity.this, "No Inserto" , Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}

