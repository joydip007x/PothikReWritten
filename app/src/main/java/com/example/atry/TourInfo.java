package com.example.atry;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TourInfo extends AppCompatActivity {


    ViewPager viewPager;
    CustomSwipeAdapter customSwipeAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_info);

        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/


        ////  int p=getIntent().getExtras().getInt("Map");
        ///  System.out.println("LAAA "+p);
        ////  HashMap M= Nav_draw_Fragment_Up_Tour.listUP.get(p);
        //System.out.println("LAAA "+M.values().toString());

        viewPager=findViewById(R.id.ti_viewpager);
        customSwipeAdapter= new CustomSwipeAdapter(getApplicationContext());
        viewPager.setAdapter(customSwipeAdapter);
    }
}
