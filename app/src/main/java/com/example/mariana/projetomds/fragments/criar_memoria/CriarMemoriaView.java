package com.example.mariana.projetomds.fragments.criar_memoria;

import android.content.Intent;
import android.view.Menu;

public interface CriarMemoriaView {

    interface View {
        void carregaImagem(String caminhoArquivo);
        void abreActivity(Intent intent, Integer codigo);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void tiraFoto();
        void abrirGaleria();
        void cadastrar(String titulo, String descricao, String imagePath, String local);
    }
}

