package com.example.mariana.projetomds.fragments.criar_memoria;

import android.content.Intent;
import android.graphics.Bitmap;

public interface CriarMemoriaView {

    interface View {
        void carregaImagem(Bitmap caminhoArquivo);

        void carregaImagemGaleria(Bitmap caminhoArquivo, String path);

        void abreActivity(Intent intent, Integer codigo);
    }

    interface Presenter {
        void verificaResultado(int requestCode, int resultCode, Intent data);
        void tiraFoto();
        void abrirGaleria();
        void cadastrar(String titulo, String descricao, String imagePath, String local, double latitude, double longitude);
    }
}

