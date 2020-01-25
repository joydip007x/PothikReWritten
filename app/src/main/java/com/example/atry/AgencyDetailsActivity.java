package com.example.atry;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.atry.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgencyDetailsActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    private ImageView agencyDP;
    private HashMap M;

    private TextView descriptionET;
    private String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_details);


        Bundle extra=getIntent().getExtras();
        int pos1=extra.getInt("Map");


        M=Nav_draw_Fragment_Cur_Tour.listCT.get(pos1);


        ImageSlider imageSlider=findViewById(R.id.imageslider);
        List<SlideModel> slideModels=new ArrayList<>();



       /* slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(0),""));
        slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(1),""));
        slideModels.add(new SlideModel(CustomSwipeAdapter.image_res2.get(2),""));*/


        for(String i:CustomSwipeAdapter.image_res2){

              slideModels.add(new SlideModel(i,""));
        }

        imageSlider.setImageList(slideModels,true);

       // description=M.get("Tourdetails").toString();
        //descriptionET.setText(description);


        //System.out.println(" LALALALA jfldksfjsdlfsdglgfdhgjkfhdghdfjkhgjldhgjklfhgjdfhsgklhdfslg" + pos1 );
//        System.out.println(M.get("TourName").toString()+"          Ishmam Lawra");
//        System.out.println(" LALALALA" + pos1 );
//        System.out.println(" LALALALA jfldksfjsdlfsdglgfdhgjkfhdghdfjkhgjldhgjklfhgjdfhsgklhdfslg" + pos1 );

        sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager=findViewById(R.id.container_);
        setupViewPager(viewPager);

        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TravelersView_AgencyAboutFragment(),"About");
        adapter.addFragment(new TravelersView_AgencyToursFragment(),"Current Tours");
        viewPager.setAdapter(adapter);
    }
}