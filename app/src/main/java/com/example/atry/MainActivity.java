package com.example.atry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements DExitApp.DExitAppLis, DRelogin.DReloginLis {

    private Button BsignIn,BsignUp,BsignInTreveler,BsignInAgency;
    public  static  String USERUID;

    public static boolean isAgencyuser() {
        return agencyuser;
    }

    public static void setAgencyuser(boolean agencyuser) {
        MainActivity.agencyuser = agencyuser;
    }

    public static boolean isTraveleruser() {
        return traveleruser;
    }

    public static void setTraveleruser(boolean traveleruser) {
        MainActivity.traveleruser = traveleruser;
    }
    public static boolean agencyuser = false, traveleruser = false;
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance(),
            mAuth2 = FirebaseAuth.getInstance();
    public static FirebaseUser user;
    public static String userID;

    public static String getUserID() {
        return userID;
    }

    public static String getUserID2() {
        return getUserID();
    }

    public  static  String getCrrentUserEmail(){

        return  FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }



    public static void setUserID() {

        setUser();
        if (user == null) return;
        MainActivity.userID = mAuth.getCurrentUser().getUid();
    }

    public static void setUserID2() {
        setUserID();
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static void setmAuth(FirebaseAuth mAuth) {
        MainActivity.mAuth = mAuth;
    }

    public static FirebaseUser getUser() {
        setUser();
        return user;
    }

    public static FirebaseUser getUser2() {
        return getUser();
    }

    public static void setUser() {
        MainActivity.user = mAuth.getCurrentUser();
    }

    public static void setUser2() {
        setUser();
    }

    public  void ok(){

        MainActivity.this.finish();
    }


    public  Context okk(){

         return  getApplicationContext();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUserID();




        if (getIntent().getBooleanExtra("EXIT", false)) {

            finish();
        }

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

               DRelogin d=new DRelogin();
               d.show(getSupportFragmentManager(),"OO");

        }


        BsignIn = findViewById(R.id.BsignIn);
        BsignUp = findViewById(R.id.BsignUp);
        BsignInAgency = findViewById(R.id.BsignInAgency);
        BsignInTreveler = findViewById(R.id.BsignInTreveler);

     /*   getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);*/


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BsignUp.setOnClickListener(view -> {
            Intent a = new Intent(getApplicationContext(), SignUpAs.class);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(a);
            finish();
        });
        BsignInAgency.setOnClickListener(view -> {
            Intent b = new Intent(getApplicationContext(), SignInAsAgency.class);
            b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(b);
            finish();

        });
        BsignInTreveler.setOnClickListener(view -> {
            Intent c = new Intent(getApplicationContext(), SignInAsTraveler.class);
            c.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(c);
            finish();
        });


    }

    @Override
    public void onBackPressed() {
      ///  super.onBackPressed();

        DExitApp D=new DExitApp();
        D.show(getSupportFragmentManager(),"O");

    }

    @Override
    public void applyTexts(String Email) {

        if(Email=="Y"){

            finish();
           /* System.exit(0);
            finishActivity(0);*/
        }
    }

    public  static  void startAftSignUp(Context context){

        Intent a = new Intent( context,MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        context.startActivity(a);


    }

    @Override
    public void applyTexts2(String Email) {

        if(Email!="Y") {

            FirebaseUser U=FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference d= FirebaseDatabase.getInstance().getReference("user/agency").child(U.getUid());

            d.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists()){

                        MainActivity.setAgencyuser(true);MainActivity.setTraveleruser(false);
                        MainActivity.user = MainActivity.getUser();
                        Toasty.success(MainActivity.this.getApplicationContext(), "Logged in ",
                                Toast.LENGTH_SHORT,true).show();

                        MainActivity.USERUID=FirebaseAuth.getInstance().getUid();
                        Intent a = new Intent(MainActivity.this.getApplicationContext(), Nav_Draw_Agency.class);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(a);
                        finish();
                        finishActivity(0);


                    }
                    else{

                        MainActivity.setAgencyuser(false);MainActivity.setTraveleruser(true);
                        Toasty.success(MainActivity.this.getApplicationContext(), "Logged in ",
                                Toast.LENGTH_LONG,true).show();

                        MainActivity.USERUID=FirebaseAuth.getInstance().getUid();
                        Intent a = new Intent(MainActivity.this.getApplicationContext(), Nav_Draw_Traveler.class);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(a);
                        finish();
                        finishActivity(0);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
      else   {
          FirebaseAuth.getInstance().signOut();

          Toasty.info(getApplicationContext(),"Logged out from Previous Session",Toasty.LENGTH_LONG,true).show();
        }

    }
}

/*BsignUp.setOnClickListener(this);
        BsignInAgency.setOnClickListener(this);
        BsignInTreveler.setOnClickListener(this);*/
/*
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.BsignUp:
                Intent i = new Intent(getApplicationContext(), SignUpAs.class);
                startActivity(i);
                break;

            case R.id.BsignInAgency:
                Intent b = new Intent(getApplicationContext(), SignInAsAgency.class);
                startActivity(b);
                break;
            case R.id.BsignInTreveler:
                Intent c = new Intent(getApplicationContext(), SignInAsTraveler.class);
                startActivity(c);
                break;
        }


    }*/
