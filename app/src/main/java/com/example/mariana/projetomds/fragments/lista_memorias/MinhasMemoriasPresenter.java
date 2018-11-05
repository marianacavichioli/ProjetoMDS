package com.example.mariana.projetomds.fragments.lista_memorias;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

public class MinhasMemoriasPresenter {

    private MinhasMemoriasView minhasMemoriasView;
    private Context context;

    MinhasMemoriasPresenter(MinhasMemoriasView minhasMemoriasView){
        this.minhasMemoriasView = minhasMemoriasView;
    }

    //pega informações do banco
    public void updateList(List<MinhasMemoriasEntity> memoriasList) {
        if(memoriasList!=null){
            minhasMemoriasView.updateList(memoriasList);
        }
        else{
            Toast.makeText(context,"Erro ao carregar lista",Toast.LENGTH_LONG);
        }

    }
}
