package com.example.atry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class GiveDetails extends AppCompatActivity {


    private EditText t1,t2,t3;
    Button br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_details);


        t1=findViewById(R.id.ETcomNamexxx);
        t2=findViewById(R.id.ETownerNamexxx);
        t3=findViewById(R.id.ETownerNamexxx2);

        br=findViewById(R.id.buttonxxx);

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s1=t1.getText().toString().toLowerCase();
                String s2=t2.getText().toString().toLowerCase();
                String s3=t3.getText().toString().toLowerCase();

                //String s4=s1+"_"+s2+"_"+s3;

                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Register").child(TravelersViewOfTourDetails.pUID);
                /// HashMap M2=new HashMap();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        HashMap M3=(HashMap)dataSnapshot.getValue();
                        if(M3==null) M3=new HashMap();
                        else{

                            String s66=FirebaseAuth.getInstance().getUid().toString();

                            if(M3.get(s66)!=null){

                                Toasty.warning(GiveDetails.this.getApplicationContext(), "You have already registered for this tour",
                                        Toast.LENGTH_LONG,true).show();

                               return;
                            }
                        }
                        M3.put(FirebaseAuth.getInstance().getUid(),s2);
                        ref.updateChildren(M3);

                        Toasty.success(GiveDetails.this.getApplicationContext(), "Registration successful",
                                Toast.LENGTH_LONG,true).show();

                        DatabaseReference dd=FirebaseDatabase.getInstance().getReference().child("Notify").child(TravelersViewOfTourDetails.M.get("agencyUID").toString());

                        HashMap XX=new HashMap();
                        XX.put(FirebaseAuth.getInstance().getUid().toString(),"1");
                        dd.updateChildren(XX);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}
