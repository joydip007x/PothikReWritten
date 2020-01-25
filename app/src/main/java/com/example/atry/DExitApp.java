package com.example.atry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DExitApp extends AppCompatDialogFragment  {


    private EditText ed;
    private String email;
    private DExitAppLis listerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listerner=(DExitAppLis) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();

        builder.setTitle("")
                .setMessage("Exit App ? ")
                .setIcon(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                .setNegativeButton("NO", (dialog, which) -> {

                    email="N";
                    listerner.applyTexts(email) ;
                }).setPositiveButton("YES", (dialog, which) -> {

           /// getActivity().setContentView(R.layout.empty_page);
            email="Y";
            listerner.applyTexts(email) ;

        });

        return builder.create();
    }

    public interface DExitAppLis{

        void applyTexts(String Email);
    }
}
