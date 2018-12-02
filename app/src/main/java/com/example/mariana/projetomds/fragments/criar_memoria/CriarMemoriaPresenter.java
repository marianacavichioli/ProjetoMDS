package com.example.mariana.projetomds.fragments.criar_memoria;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.example.mariana.projetomds.activities.main.MainActivity;
import com.example.mariana.projetomds.persist.dao.MemoriaDAO;
import com.example.mariana.projetomds.persist.model.Memoria;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CriarMemoriaPresenter implements CriarMemoriaView.Presenter{

    CriarMemoriaView.View criarMemoriaView;

    private Context context;

    private static final int CODIGO_CAMERA = 1;
    private static final int CODIGO_GALERIA = 2;

    CriarMemoriaPresenter(Context context, CriarMemoriaView.View criarMemoriaView){
        this.context = context;
        this.criarMemoriaView = criarMemoriaView;
    }

    @Override
    public void verificaResultado(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CODIGO_CAMERA:
                if(Activity.RESULT_OK == resultCode){
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    Uri tempUri = getImageUri(context, imageBitmap);

                    String finalFile = (getRealPathFromURI(tempUri));

                    Log.d("data", "to aqui caminho" + finalFile);

                    criarMemoriaView.carregaImagemGaleria(imageBitmap, finalFile);
                }
                break;

            case CODIGO_GALERIA:
                if(Activity.RESULT_OK == resultCode){
                    Uri returnUri = data.getData();

                    String path = getRealPathFromURI(returnUri);

                    Bitmap caminhoDaImagem = null;
                    try {
                        caminhoDaImagem = MediaStore.Images.Media.getBitmap(context.getContentResolver(), returnUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    criarMemoriaView.carregaImagemGaleria(caminhoDaImagem, path);
                }

                break;
        }
    }

    @Override
    public void tiraFoto() {
        Intent tiraFoto = new Intent();
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        criarMemoriaView.abreActivity(intentCamera, CODIGO_CAMERA);
    }

    @Override
    public void abrirGaleria() {
        Intent abreGaleria = new Intent();

        if (Build.VERSION.SDK_INT <= 19) {
            abreGaleria.setType("image/*");
            abreGaleria.setAction(Intent.ACTION_GET_CONTENT);
            abreGaleria.addCategory(Intent.CATEGORY_OPENABLE);
        } else {
            abreGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        criarMemoriaView.abreActivity(Intent.createChooser(abreGaleria, "Select Picture"), CODIGO_GALERIA);
    }

    @Override
    public void cadastrar(String titulo, String descricao, String imagePath, String local, double latitude, double longitude) {

        Date currentTime = Calendar.getInstance().getTime();

        String curTime = DateFormat.getDateInstance().format(currentTime);
        Log.d("TIME", curTime);

        Memoria memoria = new Memoria();
        memoria.setNome(titulo);
        memoria.setDescricao(descricao);
        memoria.setLocal(local);
        memoria.setLatitude(latitude);
        memoria.setLongitude(longitude);
        memoria.setImagem(imagePath);
        memoria.setData(curTime);

        MemoriaDAO memoriaDAO = new MemoriaDAO(context);

        memoriaDAO.insert(memoria);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



}
