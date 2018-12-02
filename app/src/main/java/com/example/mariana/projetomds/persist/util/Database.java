package com.example.mariana.projetomds.persist.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

    private Context context;
    private static final int VERSAO = 1;
    private static final String NOME_BANCO = "memorykeeper.db";


    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.getSQLCreateTableMemorias());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String getSQLCreateTableMemorias() {
        String sql = "CREATE TABLE memoria( " +
                "id integer primary key autoincrement, " +
                "nome text, " +
                "descricao text, " +
                "local text, " +
                "latitude real, " +
                "longitude real, " +
                "data text, " +
                "imagem text )";
        return sql;
    }
}
