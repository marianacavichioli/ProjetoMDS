package com.example.mariana.projetomds.activities.main;

import android.content.Context;

import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;

import java.util.ArrayList;

public class MainPresenter {

    MainView mainView;

    MainPresenter(MainView mainView){
        this.mainView = mainView;
    }


//    @Override
//    public void cadastrar(String titulo, String local, String data, Context context){
//
//        Memoria memoria = new Memoria();
//        memoria.setNome(titulo);
//        memoria.setLocal(local);
//        memoria.setData(data);
//
//
//        MemoriaDAO memoriaDAO = new MemoriaDAO(context);
//        memoriaDAO.inserir(memoria);
//
//
//        mainView.abrirMainActivity();
//    }
}
