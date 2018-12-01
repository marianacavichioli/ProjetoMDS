package com.example.mariana.projetomds.fragments.criar_memoria;

import android.content.Intent;

public interface CriarMemoriaView {

    interface View {
        void carregaImagem(String caminhoArquivo);
        void abreActivity(Intent intent, Integer codigo);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void tiraFoto();
        void abrirGaleria();
        void cadastrar(String titulo, String descricao, String imagePath, String local, double latitude, double longitude);
    }
}

