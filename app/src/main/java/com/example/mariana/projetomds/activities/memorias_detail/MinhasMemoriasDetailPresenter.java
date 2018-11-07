package com.example.mariana.projetomds.activities.memorias_detail;

import android.content.Context;
import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;

public class MinhasMemoriasDetailPresenter {

    private MinhasMemoriasDetailView minhasMemoriasDetailView;

    MinhasMemoriasDetailPresenter(MinhasMemoriasDetailActivity minhasMemoriasDetailView){
        this.minhasMemoriasDetailView = minhasMemoriasDetailView;
    }

    public void getMemoriasDetails(int memoriaId, Context context) {
        MemoriaDAO memoriaDAO = new MemoriaDAO(context);
        Memoria memoria = memoriaDAO.getMemoriaId(memoriaId);

        if(memoria != null){
            minhasMemoriasDetailView.showDetails(memoria);
        }else{
            minhasMemoriasDetailView.showError();
        }
    }
}
