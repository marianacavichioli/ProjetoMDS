package com.example.mariana.projetomds.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mariana.projetomds.persist.model.Memoria;
import com.example.mariana.projetomds.persist.util.Database;

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
        values.put("imagem", memoria.getImagem());

        //Log.d("TO LOC", "teoricamente salvou");

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

        String[] campos = {"id", "nome", "local", "latitude", "longitude", "data", "imagem"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "nome");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Memoria memoria = new Memoria();
                memoria.setId(cursor.getInt(0));
                memoria.setNome(cursor.getString(1));
                memoria.setLocal(cursor.getString(2));
                memoria.setLatitude(cursor.getDouble(3));
                memoria.setLongitude(cursor.getDouble(4));
                memoria.setData(cursor.getString(5));
                memoria.setImagem(cursor.getString(6));
                list.add(memoria);
            }
        }

        db.close();

        return list;
    }

    public Memoria getMemoriaId(int id) {

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "local", "latitude", "longitude", "data", "imagem"};

        Cursor cursor = db.query(TABLE, campos, "id=?", new String[] { Integer.toString(id) }, null, null, "nome");

        if(cursor==null) {
            db.close();
            return null;
        }

        cursor.moveToFirst();
        Memoria memoria = new Memoria();
        memoria.setId(cursor.getInt(0));
        memoria.setNome(cursor.getString(1));
        memoria.setLocal(cursor.getString(2));
        memoria.setLatitude(cursor.getDouble(3));
        memoria.setLongitude(cursor.getDouble(4));
        memoria.setData(cursor.getString(5));
        memoria.setImagem(cursor.getString(6));

        db.close();

        return memoria;
    }

    public void popularMemorias() {
        Memoria memoria = new Memoria();

        memoria.setNome("Ricardo Pereira");
        memoria.setLocal("São Carlos");
        memoria.setLatitude(-121.97972238);
        memoria.setLongitude(-47.88054228);
        memoria.setData("23/02/2010");
        memoria.setImagem("");
        this.insert(memoria);

        memoria.setNome("Mariana Cavichioli");
        memoria.setLocal("Matão");
        memoria.setLatitude(-121.97972238);
        memoria.setLongitude(-47.88054228);
        memoria.setData("23/02/2010");
        memoria.setImagem("");
        this.insert(memoria);
    }
}
