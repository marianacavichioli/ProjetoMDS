package com.example.mariana.projetomds.fragments.mapa;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.fragments.criar_memoria.CriarMemoriaPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import butterknife.ButterKnife;

public class MapaActivity extends Fragment implements OnMapReadyCallback, MapaView {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    MapaPresenter mapaPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_mapa, container, false);

        ButterKnife.bind(this, mView);

        mapaPresenter = new MapaPresenter(this);

        return mView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mapaPresenter.updateMarkers(mGoogleMap);

        //TODO: aqui ta fixo nesse exemplo, depois ver se vai colocar na ultima memoria, seja o que for (talvez levar pra dentro da funcao updateMarkers
        CameraPosition DC = CameraPosition.builder().target(new LatLng(-21.97972238, -47.88054228)).zoom(16).bearing(0).tilt(45).build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(DC));
    }

}
