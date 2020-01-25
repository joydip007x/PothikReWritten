package com.example.atry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Objects.requireNonNull;

public class DCHAUAdapta extends RecyclerView.Adapter {


    ArrayList<HashMap> list;
   OnNoteListener onNoteListener;

    public DCHAUAdapta(){


    }
    public DCHAUAdapta(ArrayList<HashMap> list,OnNoteListener onNoteListener){

        this.list=list;
        this.onNoteListener=onNoteListener;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.card_holder_a_up,
                        parent,
                        false);

        return new MyViewHolder(v,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder)holder).bindView(position);
    }

  /*  @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(list.get(position).get("TourName").toString());
        holder.desc.setText(list.get(position).get("Tourloc").toString());

       /// System.out.println("ACCA"+list.get(position).get("TourName").toString());
    }
*/
    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tname, tamount,tloc,tdate,ttime,tregg;
        ImageView img1;

        Context contextxy;
        OnNoteListener onNoteListener;

        MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tname=  itemView.findViewById(R.id.adaptarTourname);
            tamount=  itemView.findViewById(R.id.adaptarTourAmount);
            tloc=  itemView.findViewById(R.id.adaptarTourLoc);
            tdate=  itemView.findViewById(R.id.adaptarTourDate);
            ttime=  itemView.findViewById(R.id.adaptarTOurlike);
            tregg=  itemView.findViewById(R.id.adaptarTourRegged);
            img1= itemView.findViewById(R.id.adaptarIMG1);
            contextxy=itemView.getContext();
            this.onNoteListener=onNoteListener;


            itemView.setOnClickListener(this);
        }
        public void bindView(int position){


            //System.out.println("WOW"+1);

            HashMap M=list.get(position);

            if(!validate(M)) return;

           tname.setText(requireNonNull(M.get("TourName")).toString());
           tloc.setText("Location : ".concat(requireNonNull(M.get("Tourloc")).toString() ) );
           tamount.setText("Cost : ".concat(requireNonNull(M.get("Tourtk")).toString() ).concat(" BDT ") );
           tdate.setText(requireNonNull(M.get("Tourtime")).toString());
           ttime.setText("Likes : ".concat(requireNonNull(M.get("Like")).toString() ) );
           tregg.setText("Registered : ".concat(requireNonNull(M.get("Registered")).toString() ) );

             Glide.with(contextxy)
                    .load(requireNonNull(M.get("cover")).toString())
                    .into(img1);
            //img1.setImageURI(Uri.parse(M.get("cover").toString()));
        }

        @Override
        public void onClick(View v) {

            onNoteListener.OnNoteClick(getAdapterPosition());
        }
    }
    public  interface OnNoteListener{


        void OnNoteClick(int position);

    }
    private boolean validate(HashMap m) {

        int cnt=0;
        if(m.get("Agency")!=null) cnt++;
        if(m.get("cover")!=null) cnt++;
        if(m.get("image1")!=null ||   m.get("image2")!=null) cnt++;
        if(m.get("TourName")!=null) cnt++;
        if(m.get("Tourloc")!=null) cnt++;


        return cnt == 5 || m.size()==14 ;
    }

}
