package com.example.mariana.projetomds.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mariana.projetomds.R;

import butterknife.ButterKnife;

public class CriarMemoriaActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_criar, container, false);

        ButterKnife.bind(this, view);

        return view;
    }
}
