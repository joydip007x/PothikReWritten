package com.example.atry;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RedirectedAgencyP extends AppCompatActivity {


    private TextView t1,t2,t3,t4;
    private ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirected_agency_p);

        t1=findViewById(R.id.RETVaAddress);
        t2=findViewById(R.id.RETVaDescription);
        t3=findViewById(R.id.RETVaEmail);
        t4=findViewById(R.id.RETVaNumber);
        i1=findViewById(R.id.REIVadp);

        String uid=TravelersViewOfTourDetails.M.get("agencyUID").toString();

        DatabaseReference dm=FirebaseDatabase.getInstance().getReference().child("user/agency").child(uid);


        System.out.println(" YOYOYO "+dm);

        dm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                HashMap MX= (HashMap) dataSnapshot.getValue();

                t1.setText(MX.get("address").toString());
                t2.setText(MX.get("description").toString());
                t3.setText(MX.get("email").toString());
                t4.setText(MX.get("number").toString());

                Glide.with(getApplicationContext())
                        .load(MX.get("DP"))
                        .into(i1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
