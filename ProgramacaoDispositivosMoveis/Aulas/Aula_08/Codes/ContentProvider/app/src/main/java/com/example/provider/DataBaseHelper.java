package com.example.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    static  final String BANCO_DADOS = "cidade_db";
    static  final String NOME_TABELA = "cidade_tb";
    static  final int VERSAO_BANCO_DADOS = 2;
    static final String SCRIPT_CREATE_TABLE = "create table " + NOME_TABELA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL)";

    public DataBaseHelper(@Nullable Context context) {
        super(context, BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(db);
    }
}
