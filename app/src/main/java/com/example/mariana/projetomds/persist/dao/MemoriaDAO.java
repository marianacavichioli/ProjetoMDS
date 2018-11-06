package com.example.mariana.projetomds.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        values.put("data", memoria.getData());
//        values.put("imagem", memoria.getImagem());

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

        String[] campos = {"id", "nome", "local", "data"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "nome");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Memoria memoria = new Memoria();
                memoria.setId(cursor.getInt(0));
                memoria.setNome(cursor.getString(1));
                memoria.setLocal(cursor.getString(2));
                memoria.setData(cursor.getString(3));
//                memoria.setImagem(cursor.getString(4));
                list.add(memoria);
            }
        }

        db.close();

        return list;
    }

    public void popularMemorias() {
        Memoria memoria = new Memoria();

        memoria.setNome("Ricardo Pereira");
        memoria.setLocal("Ricardo");
        memoria.setData("23/02/2010");
        this.insert(memoria);
    }
}
