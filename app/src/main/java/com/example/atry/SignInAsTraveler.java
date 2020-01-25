package com.example.atry;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import es.dmoral.toasty.Toasty;

public class SignInAsTraveler extends AppCompatActivity implements ForgotPassDialog.ForgotPassDListerner {

    private EditText sinEmailET, sinPassET;
    private Button Bsin, forgot;
    private TextView pothik, siat;
    private  String pass,email;
    private ProgressBar progressBar;
    private int laaaa=-1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinas_traveler);
        sinEmailET = findViewById(R.id.ETsInEmail);
        sinPassET = findViewById(R.id.ETsInPass);
        Bsin = findViewById(R.id.BsIn);
        pothik = findViewById(R.id.tv);
        siat = findViewById(R.id.tv2);

        forgot=findViewById(R.id.buttonForgotPassT);

        MainActivity.mAuth = FirebaseAuth.getInstance();

/*

        if(MainActivity.getUser2()!=null &&  MainActivity.getUser2().isEmailVerified()){

            Intent a = new Intent(SignInAsTraveler.this.getApplicationContext(), EmptyPage.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(a);
          //  finish();
            finishAndRemoveTask();

        }
*/

        progressBar=findViewById(R.id.progressBar4);

        progressBar.setVisibility(View.INVISIBLE);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    openForgotPassDialog();
            }
        });
        Bsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email = sinEmailET.getText().toString().trim();
               pass = sinPassET.getText().toString().trim();
                if (email.isEmpty()) {
                    sinEmailET.setError("Enter an email address");
                    sinEmailET.requestFocus();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    sinEmailET.setError("Enter a valid email address");
                    sinEmailET.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    sinPassET.setError("Enter a password");
                    sinPassET.requestFocus();
                    return;
                }
                if (pass.length() < 6) {
                    sinPassET.setError("Password Length should be minimum 6");
                    sinPassET.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                MainActivity.mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignInAsTraveler.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.INVISIBLE);

                                if (task.isSuccessful()) {

                                    if(!MainActivity.getUser().isEmailVerified()){

                                        Toasty.info(SignInAsTraveler.this.getApplicationContext(), "Verify Email first !",
                                                Toast.LENGTH_SHORT,true).show();
                                        return;
                                    }
                                   /// setContentView(R.layout.empty_page);
                                    DataBaseOpOfTraveler.delForFirstLogIn("temp_traveler","email",email);

                                    DatabaseReference d=FirebaseDatabase.getInstance().getReference("user/traveler").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                    d.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if(dataSnapshot.exists()){
                                                MainActivity.setAgencyuser(false);MainActivity.setTraveleruser(true);
                                                Toasty.success(SignInAsTraveler.this.getApplicationContext(), "Logged in ",
                                                        Toast.LENGTH_LONG,true).show();

                                                MainActivity.USERUID=FirebaseAuth.getInstance().getUid().toString();
                                                Intent a = new Intent(SignInAsTraveler.this.getApplicationContext(), Nav_Draw_Traveler.class);
                                                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                MainActivity.traveleruser=true;
                                                MainActivity.agencyuser=false;
                                                startActivity(a);
                                                finish();
                                            }
                                            else{

                                                progressBar.setVisibility(View.INVISIBLE);

                                               /// setContentView(R.layout.activity_signinas_traveler);
                                                Toasty.warning(SignInAsTraveler.this.getApplicationContext(), "You have signed up a Agency .Try Sign in as Agency instead",
                                                        Toast.LENGTH_SHORT,true).show();
                                                FirebaseAuth.getInstance().signOut();
                                                ///progressBar.setVisibility(View.INVISIBLE);

                                                ///SignInAsTraveler.super.onBackPressed();

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                } else {

                                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }
    private void openForgotPassDialog() {

        ForgotPassDialog forgotPassDialog=new ForgotPassDialog();
        forgotPassDialog.show(getSupportFragmentManager(),"Forgot password?");

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent a = new Intent(SignInAsTraveler.this.getApplicationContext(), MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        a.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(a);
        finish();
    }

    @Override
    public void applyTexts(final String Email) {

        TravelerProfile ag=new TravelerProfile(Email);
        ag.isIDexits(SignInAsTraveler.this);

    }
    public  static  void toastShow(final String msg, String Email, int success, final Context context){

        if(success==0){
            Toast.makeText(context,
                    msg, Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(context,
                            msg, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context,
                            task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

    /*@Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.BsIn :
                agencyLogin();
                break;
        }
    }

    private void agencyLogin() {
        String email = sinEmailET.getText().toString().trim();
        String pass = sinPassET.getText().toString().trim();
        if(email.isEmpty())
        {
            sinEmailET.setError("Enter an email address");
            sinEmailET.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            sinEmailET.setError("Enter a valid email address");
            sinEmailET.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            sinPassET.setError("Enter a password");
            sinPassET.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            sinPassET.setError("Password Length should be minimum 6");
            sinPassET.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            *//*Intent a = new Intent(getApplicationContext(),SignInAsAgency.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(a);*//*
                        } else {

                            Toast.makeText(getApplicationContext(),"Log in unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}*/