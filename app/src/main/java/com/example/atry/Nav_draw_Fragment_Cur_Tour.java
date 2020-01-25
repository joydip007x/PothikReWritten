package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

@SuppressWarnings("deprecation")
public class Nav_draw_Fragment_Cur_Tour extends Fragment implements DCHAUAdapta.OnNoteListener {


    private DatabaseReference ref;
    static ArrayList<HashMap> listCT, searchlist;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private DCHAUAdapta DCHAUAdaptax;
    private MultiSpinner simpleSpinner;
    private ArrayList<String> selectedItem;
    private int cnt = 0;
    private LinkedHashMap<String, Boolean> myList = new LinkedHashMap<>();

    @Override
    public void onStart() {
        super.onStart();

/*
        DCHAUAdaptax =new DCHAUAdapta(list);
        recyclerView.setAdapter(DCHAUAdaptax);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        */
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.nav_a_fragment_up_tour, container, false);


        ref = FirebaseDatabase.getInstance().getReference().child("post");
        recyclerView = view.findViewById(R.id.recycle_UPA);
        searchView = view.findViewById(R.id.searchview_UPA);

        List<String> temp = FilterItemLlist.getList();
        for (String O : temp) {
            myList.put(O, false);
        }

        /*-------*/



        /*myList.put("Food",false);
        myList.put("Food2",false);*/

         /*spinList=new ArrayList<>();
         spinList.add("Food");
         spinList.add("TALESA");
         spinList.add("LL");
         spinList.add("COOL DUDE Z ");*/

        /*-------------*/


        simpleSpinner = view.findViewById(R.id.multiselectspin_UPA);

        /// simpleSpinner.setVisibility(View.INVISIBLE);


        simpleSpinner.setItems(myList, selected -> {


            selectedItem = new ArrayList<>();
            cnt++;
            String info = "SEARCH FILTER : ";
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    // Log.i("XXXX", i + " : "+FilterItemLlist.getItemName(i));
                    selectedItem.add(FilterItemLlist.getItemName2(i));
                    info = info.concat(FilterItemLlist.getItemName(i) + " | ");
                }

            }

            if (selectedItem.size() != 0)
                Toasty.info(Objects.requireNonNull(getContext()), info, Toasty.LENGTH_LONG, true).show();
            else if (cnt > 1)
                Toasty.info(Objects.requireNonNull(getContext()), info.concat("NONE"), Toasty.LENGTH_SHORT, true).show();

            if (searchView.getQuery() != null)
                search(searchView.getQuery().toString());
            else {

                searchView.requestFocus();
            }
        });


        /*----------*/


        if (listCT.size() == 0) {

            view = inflater.inflate(R.layout.empty_page, container, false);
            return view;
        }

        DCHAUAdaptax = new DCHAUAdapta(listCT, this);

        recyclerView.setAdapter(DCHAUAdaptax);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                cnt++;
                return false;
            }

        });

        searchView.setOnCloseListener(() -> {


            recyclerView.setAdapter(DCHAUAdaptax);
            return false;
        });


        /// recyclerView.setOnClickListener(v -> startActivity(new Intent(getContext(),EmptyPage.class)));
        return view;
    }

    private void search(String s) {


        searchlist = new ArrayList<>();
        //  System.out.println("LLLL6 "+ 1);
        if (s.length() != 0) {
            ///    System.out.println("LLLL2"+selectedItem.size());
            if (selectedItem.size() == 0) {

                for (HashMap O : listCT) {

                   /* if( FilterItemLlist.KMP(s, Objects.requireNonNull(O.get("TourName")).toString())>= s.length()*.60 )
                         searchlist.add(O);*/


                    List<String> L = FilterItemLlist.L3;
                    for (String S : L) {
                        if (FilterItemLlist.KMP(s, Objects.requireNonNull(O.get(S)).toString()) >= s.length() * .50) {
                            searchlist.add(O);
                            break;
                        }
                    }

                }
            } else {


                for (HashMap O : listCT) {

                    for (String S : selectedItem) {

                        if (FilterItemLlist.KMP(s, O.get(S).toString()) >= s.length() * .70) {
                            searchlist.add(O);
                            break;
                        }
                    }
                }


            }
        } else searchlist = listCT;


        // System.out.println("LLLL6 "+ 3);

        /// System.out.println("LLLLEND" + searchlist.toString());

        //System.out.println("0000"+A.toString());
        DCHAUAdapta dx = new DCHAUAdapta(searchlist, this);
        recyclerView.setAdapter(dx);


    }


    @Override
    public void OnNoteClick(int position) {

        HashMap M = listCT.get(position);
        CustomSwipeAdapter.image_res2 = new ArrayList<String>();
        CustomSwipeAdapter.image_res2.add(M.get("cover").toString());
        if (M.get("image1") != null) {
            CustomSwipeAdapter.image_res2.add(M.get("image1").toString());
        }
        if (M.get("image2") != null) {
            CustomSwipeAdapter.image_res2.add(M.get("image2").toString());
        }

        Nav_draw_Fragment_Up_Tour.fromUPcoming = false;
        Nav_draw_Fragment_Up_Tour.fromCurrent = true;

        if (MainActivity.agencyuser) {
            Intent intent = new Intent(getContext(), AgencyViewOfTourDetails.class);
            intent.putExtra("Map", position);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), TravelersViewOfTourDetails.class);
            intent.putExtra("Map", position);
            startActivity(intent);
        }


    }
}
/*

  ref.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           /*     list= new ArrayList<>();

                for(DataSnapshot d: dataSnapshot.getChildren()){

                    HashMap M= (HashMap) d.getValue();
                    list.add(M);
                    ///  System.out.println("AddedX"+M.toString());
                }
                /// System.out.println("ACCA"+1);
                DCHAUAdapta DX=new DCHAUAdapta(list);
              ///  recyclerView.setAdapter(DX);*/


/// recyclerView = findViewById(R.id.recycler_view);


               /* recyclerView.setItemViewCacheSize(5);
                recyclerView.setAdapter(DX);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter( DX );*/

///System.out.println("ACCA"+2);
/*

        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });

*/

   /* @Override
    public void onStart() {
        super.onStart();


        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("post");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                ArrayList<HashMap> list= new ArrayList<>();

                for(DataSnapshot d: dataSnapshot.getChildren()){

                    HashMap M= (HashMap) d.getValue();
                    list.add(M);
                    ///  System.out.println("AddedX"+M.toString());
                }

                Nav_draw_Fragment_Up_Tour.list=list;

                /// System.out.println("ACCA"+ list.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/


     /* searchView.setOnFocusChangeListener((v, hasFocus) -> {

            if( !searchView.isFocused() && !simpleSpinner.isFocused() ){
                simpleSpinner.setVisibility(View.INVISIBLE);
            }
            else {
                simpleSpinner.setVisibility(View.VISIBLE);

            }
        });*/
       /* simpleSpinner.setOnFocusChangeListener((v, hasFocus) -> {

            if( !searchView.isFocused() && !simpleSpinner.isFocused() ){
                simpleSpinner.setVisibility(View.INVISIBLE);
            }
            else {
                simpleSpinner.setVisibility(View.VISIBLE);

            }
        });*/


//  System.out.println("LLLL5"+O.values().toString());
                   /* if (O.containsKey(s) || O.containsValue(s)) {

                        searchlist.add(O);
                    }*/

//  Iterator I=O.entrySet().iterator();

//  System.out.println("LLLL6 "+ 2);

                   /* while (I.hasNext()){

                        Entry L= (Entry) I.next();
                        String SK= (String) L.getValue();

                        ///System.out.println("LLLL4"+SK);


                        int P=FilterItemLlist.KMP(s,SK);

                        if(P<=SK.length()/3){
                            searchlist.add(O);
                            break;
                        }

                    }*/
                  /*  for(Map.Entry<String ,String> Key : O.entrySet()  ) {
                        String SK = (String) Key;

                        System.out.println("LLLL4"+SK);
                        if(  ){

                            searchlist.add(O);
                            break;
                        }
                       /// System.out.println("LLLL" + SK);
                    }*/