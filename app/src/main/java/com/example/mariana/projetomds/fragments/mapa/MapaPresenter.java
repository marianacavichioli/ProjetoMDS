package com.example.mariana.projetomds.fragments.mapa;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaPresenter {

    MapaView mapaView;

    public MapaPresenter(MapaView mapaView) {
        this.mapaView = mapaView;
    }


    public MarkerOptions createMarket(double lat, double lng, String title, String snippet){
        return new MarkerOptions().position(new LatLng(lat, lng)).title(title) .snippet(snippet);
    }


    public void updateMarkers(GoogleMap mGoogleMap) {

        //TODO: na verdade tem que verificar todas as memorias do banco de dados

        // Por enquanto está com alguns markers padrão
        mGoogleMap.addMarker(createMarket(40.689247, -74.044502,"State of Liberty","I hope to go there some day"));
        mGoogleMap.addMarker(createMarket(-21.97972238, -47.88054228,"Melhor departamento","O melhor departamento da UFSCar"));
        mGoogleMap.addMarker(createMarket(-21.98002085, -47.87833214, "Observatório", "Lindo observar tudo"));

    }
}


