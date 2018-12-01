package com.example.mariana.projetomds.fragments.mapa;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapaPresenter {

    MapaView mapaView;
    private Context context;

    public MapaPresenter(MapaView mapaView) {
        this.mapaView = mapaView;
    }


    public MarkerOptions createMarket(double lat, double lng, String title, String snippet){
        return new MarkerOptions().position(new LatLng(lat, lng)).title(title) .snippet(snippet);
    }

    //pega informações do banco e cria os markers
    public void updateMarkers(GoogleMap mGoogleMap, List<Memoria> memoriasList) {
        if(memoriasList!=null){
            for (Memoria memoria:memoriasList){
                mGoogleMap.addMarker(createMarket(memoria.getLatitude(), memoria.getLongitude(),memoria.getNome(),memoria.getDescricao()));
            }
        }
        else{
            Toast.makeText(context,"Erro ao carregar markers",Toast.LENGTH_LONG).show();
        }

    }

    public void markersList(Context context, GoogleMap mGoogleMap){
        MemoriaDAO memoriaDAO = new MemoriaDAO(context);
        List<Memoria> memoriasList = memoriaDAO.getMemorias(); //Banco de dados

        updateMarkers(mGoogleMap, memoriasList);
    }

}


