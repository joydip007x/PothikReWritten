package com.example.atry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentContainer;

import es.dmoral.toasty.Toasty;

public class DRelogin extends AppCompatDialogFragment  {


    private EditText ed;
    private String email;
    private DReloginLis listerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listerner=(DReloginLis) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();

        builder.setTitle("")
                .setMessage("You were previously logged in.LogOut?")
                .setIcon(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                .setNegativeButton("NO", (dialog, which) -> {
                    email="N";

                  //  Toasty.warning(getContext(),"     Wait     ",Toasty.LENGTH_LONG,true).show();
                   /// getActivity().setContentView(R.layout.empty_page);
                    listerner.applyTexts2(email) ;
                }).setPositiveButton("YES", (dialog, which) -> {

                    email="Y";
                    listerner.applyTexts2(email) ;
                });

        return builder.create();
    }

    public interface DReloginLis{

        void applyTexts2(String Email);
    }

}
