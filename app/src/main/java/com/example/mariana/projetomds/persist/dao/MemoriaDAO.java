package com.example.mariana.projetomds.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.mariana.projetomds.persist.model.Memoria;
import com.example.mariana.projetomds.persist.util.Database;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MemoriaDAO {

    private final String TABLE = "memoria";

    private SQLiteDatabase db;
    private Database database;

    public MemoriaDAO(Context context) {
        database = new Database(context);
    }

    public boolean insert(Memoria memoria) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", memoria.getNome());
        values.put("local", memoria.getLocal());
        values.put("latitude", memoria.getLatitude());
        values.put("longitude", memoria.getLongitude());
        values.put("data", memoria.getData());
        values.put("descricao", memoria.getDescricao());
        values.put("imagem", memoria.getImagem());


        long result = db.insert(TABLE, null, values);
        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Memoria> getMemorias() {

        ArrayList<Memoria> list = new ArrayList<Memoria>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "descricao", "local", "latitude", "longitude", "data", "imagem"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "nome");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Memoria memoria = new Memoria();
                memoria.setId(cursor.getInt(cursor.getColumnIndex("id")));
                memoria.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                memoria.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                memoria.setLocal(cursor.getString(cursor.getColumnIndex("local")));
                memoria.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
                memoria.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
                memoria.setData(cursor.getString(cursor.getColumnIndex("data")));
                memoria.setImagem(cursor.getString(cursor.getColumnIndex("imagem")));
                list.add(memoria);
            }
        }

        db.close();

        return list;
    }

    public Memoria getMemoriaId(int id) {

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "descricao", "local", "latitude", "longitude", "data", "imagem"};

        Cursor cursor = db.query(TABLE, campos, "id=?", new String[] { Integer.toString(id) }, null, null, "nome");

        if(cursor==null) {
            db.close();
            return null;
        }

        cursor.moveToFirst();
        Memoria memoria = new Memoria();
        memoria.setId(cursor.getInt(cursor.getColumnIndex("id")));
        memoria.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        memoria.setLocal(cursor.getString(cursor.getColumnIndex("local")));
        memoria.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        memoria.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
        memoria.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
        memoria.setData(cursor.getString(cursor.getColumnIndex("data")));
        memoria.setImagem(cursor.getString(cursor.getColumnIndex("imagem")));

        db.close();

        return memoria;
    }

    public Memoria update(Memoria memoria) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", memoria.getNome());
        values.put("local", memoria.getLocal());
        values.put("latitude", memoria.getLatitude());
        values.put("longitude", memoria.getLongitude());
        values.put("data", memoria.getData());
        values.put("descricao", memoria.getDescricao());
        values.put("imagem", memoria.getImagem());


        long result = db.update(TABLE, values, "id=?", new String[] { Integer.toString(memoria.getId()) });
        db.close();

        if(result == -1)
            return null;

        return memoria;
    }

}
