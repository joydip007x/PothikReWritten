package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TravelersViewOfTourDetails extends AppCompatActivity {

    static HashMap M;
    private TextView agencyNameTV;
    private TextView tourDescriptionTV,textxx,call1;

    private String  agencyName;
    static String  tourDescription,pUID;
    private FloatingActionButton f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelers_view_of_tour_details);

        Bundle extra = getIntent().getExtras();
        int pos1 = extra.getInt("Map");

        agencyNameTV=findViewById(R.id.tour_agency_name_fb);
        tourDescriptionTV=findViewById(R.id.tour_agency_tour_details_fb);
        f=findViewById(R.id.floatingActionButton4);
        call1=findViewById(R.id.calltheagency1);

        textxx=findViewById(R.id.textXXXX);

        if (Nav_draw_Fragment_Up_Tour.fromCurrent) {
            M = Nav_draw_Fragment_Cur_Tour.listCT.get(pos1);

            if(MainActivity.traveleruser)
            pUID=Nav_draw_Fragment_Profile_Traveler.postUIDCT.get(pos1);

        } else {
            M = Nav_draw_Fragment_Up_Tour.listUP.get(pos1);

            if(MainActivity.traveleruser)
            pUID=Nav_draw_Fragment_Profile_Traveler.postUIDUT.get(pos1);
        }

        agencyNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  startActivity(new Intent(TravelersViewOfTourDetails.this,RedirectedAgencyP.class));
            }
        });


      ////  System.out.println(" KALA KALA"+pUID);



        agencyName = M.get("Agency").toString();
        //System.out.println(description+" BAAL");
        agencyNameTV.setText(agencyName);

        tourDescription=M.get("Tourdetails").toString();
        tourDescriptionTV.setText(tourDescription);

        call1.setText(M.get("Phone").toString());
        ImageSlider imageSlider = findViewById(R.id.imageslider);
        List<SlideModel> slideModels = new ArrayList<>();


        if(Nav_draw_Fragment_Up_Tour.fromUPcoming && MainActivity.traveleruser){

            f.setVisibility(View.GONE);
            textxx.setVisibility(View.GONE);

        }

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startActivity(new Intent(TravelersViewOfTourDetails.this,GiveDetails.class));
            }
        });
       /* slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(0),""));
        slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(1),""));
        slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(2),""));*/


        for (String i : CustomSwipeAdapter.image_res2) {

            slideModels.add(new SlideModel(i, ""));
        }

        imageSlider.setImageList(slideModels, true);
    }
}
