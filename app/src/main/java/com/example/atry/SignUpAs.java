package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpAs extends AppCompatActivity implements DExitApp.DExitAppLis {

    private Button BsignIn,BsignUp,BsignUpTreveler,BsignUpAgency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupas);

        BsignIn = findViewById(R.id.BsignIn);
        BsignUp = findViewById(R.id.BsignUp);
        BsignUpAgency = findViewById(R.id.BsignUpAgency);
        BsignUpTreveler = findViewById(R.id.BsignUpTreveler);

        BsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(a);
                finish();
            }
        });
        BsignUpAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), SignUpA.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(a);

            }
        });
        BsignUpTreveler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), SignUpT.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(a);

            }
        });
    }

    @Override
    public void onBackPressed() {
       /// super.onBackPressed();

        DExitApp D=new DExitApp();
        D.show(getSupportFragmentManager(),"O");

    }

    @Override
    public void applyTexts(String Email) {

        if(Email=="Y"){


            System.exit(0);
            finishActivity(0);

        }
    }
}
