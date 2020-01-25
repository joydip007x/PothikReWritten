package com.example.atry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CustomSwipeAdapter extends PagerAdapter {



     public static ArrayList<String > image_res2;
     private Context cxt;
     private LayoutInflater layoutInflater;

     public CustomSwipeAdapter(Context cxt){

         this.cxt=cxt;
     }


    @Override
    public int getCount() {

         return  image_res2.size();
        // return  image_resource.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (  view==object);
    }

       @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

         layoutInflater= (LayoutInflater) cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview= layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView  imageView=itemview.findViewById(R.id.ti_image1);
      // imageView.setImageResource(image_resource[position]);
        Glide.with(container.getContext())
                .load(image_res2.get(position))
                .into(imageView);

         container.addView(itemview);
         return itemview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

           container.removeView((LinearLayout)object);
       // super.destroyItem(container, position, object);
    }
}
