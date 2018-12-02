package com.example.mariana.projetomds.fragments.criar_memoria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mariana.projetomds.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CriarMemoriaActivity extends Fragment implements CriarMemoriaView.View{

    @BindView(R.id.imagem_memoria)
    ImageView imageView;

    @BindView(R.id.titulo)
    EditText titulo;

    @BindView(R.id.descricao)
    EditText descricao;

    String selectedImagePath;

    CriarMemoriaPresenter criarMemoriaPresenter;

    Location localizacaoAtual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_criar, container, false);

        ButterKnife.bind(this, view);

        criarMemoriaPresenter = new CriarMemoriaPresenter(getActivity(), this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        criarMemoriaPresenter.verificaResultado(requestCode, resultCode, data);
    }

    public void setLocalizacao(Location localizacao){
        this.localizacaoAtual = localizacao;
    }

    @OnClick(R.id.btn_galeria)
    public void acessarGaleria() {
        criarMemoriaPresenter.abrirGaleria();
    }

    @OnClick(R.id.btn_camera)
    public void tirarFoto() {
        criarMemoriaPresenter.tiraFoto();
    }

    @OnClick(R.id.botao_salvar)
    public void salvar(){
        criarMemoriaPresenter.cadastrar(titulo.getText().toString(), descricao.getText().toString(), selectedImagePath, "PADRAO", localizacaoAtual.getLatitude(), localizacaoAtual.getLongitude());
    }

    @Override
    public void carregaImagem(Bitmap caminhoArquivo) {
        imageView.setImageBitmap(caminhoArquivo);
    }

    @Override
    public void abreActivity(Intent intent, Integer codigo) {
        startActivityForResult(intent, codigo);
    }
}
