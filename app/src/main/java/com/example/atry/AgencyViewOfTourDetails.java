package com.example.atry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgencyViewOfTourDetails extends AppCompatActivity {

    private HashMap M;
    private TextView descriptionTV;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_view_of_tour_details);

        Bundle extra = getIntent().getExtras();
        int pos1 = extra.getInt("Map");

        descriptionTV = findViewById(R.id.tour_details);


        if (Nav_draw_Fragment_Up_Tour.fromCurrent) {
            M = Nav_draw_Fragment_Cur_Tour.listCT.get(pos1);
        } else {
            M = Nav_draw_Fragment_Up_Tour.listUP.get(pos1);
        }


        //System.out.println(" KALA KALA"+M);

        description = M.get("Tourdetails").toString();
        //System.out.println(description+" BAAL");
        descriptionTV.setText(description);


        ImageSlider imageSlider = findViewById(R.id.imageslider);
        List<SlideModel> slideModels = new ArrayList<>();


       /* slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(0),""));
        slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(1),""));
        slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(2),""));*/


        for (String i : CustomSwipeAdapter.image_res2) {

            slideModels.add(new SlideModel(i, ""));
        }

        imageSlider.setImageList(slideModels, true);
    }


}
