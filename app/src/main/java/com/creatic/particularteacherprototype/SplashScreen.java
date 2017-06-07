package com.creatic.particularteacherprototype;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    public static final int segundos=5;
    public  static final int milisegundos = segundos*1000;
    private ProgressBar pbProgreso;
    public static final int delay=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pbProgreso =(ProgressBar)findViewById(R.id.pbProgreso);
        pbProgreso.setMax(maximo_progreso());
        empezaranimacion();
    }
    public void  empezaranimacion()
    {
        new CountDownTimer(milisegundos,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                pbProgreso.setProgress(establecer_progreso(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(SplashScreen.this,UserLoginActivity.class);
                startActivity(intent);

            }
        }.start();

    }

    public  int establecer_progreso(long miliseconds){
        return (int)( (milisegundos-miliseconds)/1000);

    }

    public  int maximo_progreso (){
        return segundos-delay;
    }
}
