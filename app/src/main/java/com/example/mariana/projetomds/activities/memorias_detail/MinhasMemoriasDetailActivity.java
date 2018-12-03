package com.example.mariana.projetomds.activities.memorias_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mariana.projetomds.R;
import com.example.mariana.projetomds.persist.model.Memoria;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinhasMemoriasDetailActivity extends AppCompatActivity implements MinhasMemoriasDetailView{

    @BindView(R.id.local)
    TextView local;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.imagem)
    ImageView imagem;
    @BindView(R.id.descricao)
    TextView descricao;


    int memoriaId;
    MinhasMemoriasDetailPresenter minhasMemoriasDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_memorias_detail);

        ButterKnife.bind(this);
        minhasMemoriasDetailPresenter = new MinhasMemoriasDetailPresenter(this);

        Intent intent = getIntent();
        memoriaId = intent.getIntExtra("memoria_id", -1);
        minhasMemoriasDetailPresenter.getMemoriasDetails(memoriaId, this);

    }

    @Override
    public void showDetails(Memoria memoria) {
        local.setText(memoria.getLocal());
        data.setText(memoria.getData());
        descricao.setText(memoria.getDescricao());
        setTitle(memoria.getNome());

        File imgFile = new  File(memoria.getImagem());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imagem.setImageBitmap(myBitmap);
        }

    }

    @Override
    public void showError() {
        Toast.makeText(this,"Erro ao pegar infos do banco",Toast.LENGTH_LONG).show();
    }
}
