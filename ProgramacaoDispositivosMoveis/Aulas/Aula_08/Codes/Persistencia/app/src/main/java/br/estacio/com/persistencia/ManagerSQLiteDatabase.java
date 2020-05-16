package br.estacio.com.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class ManagerSQLiteDatabase {
    DbHelperSQLiteDatabase dbHelper;
    Context context;
    SQLiteDatabase database;

    public ManagerSQLiteDatabase(Context context){
        this.context = context;
    }

    public ManagerSQLiteDatabase open() throws SQLException {
        dbHelper = new DbHelperSQLiteDatabase(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
     dbHelper.close();
    }

    public void incluir(String nome, String endereco, String telefone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUNA_NOME, nome);
        contentValues.put(dbHelper.COLUNA_ENDERECO, endereco);
        contentValues.put(dbHelper.COLUNA_TELEFONE, telefone);
        database.insert(dbHelper.NOME_TABELA, null, contentValues);
    }

    public Cursor fetch(){
        String [] colunas = new String [] {dbHelper.COLUNA_ID, dbHelper.COLUNA_NOME, dbHelper.COLUNA_ENDERECO, dbHelper.COLUNA_TELEFONE};
        Cursor cursor = database.query(dbHelper.NOME_TABELA, colunas, null, null , null, null , null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int atualizar(String id, String nome, String endereco, String telefone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUNA_NOME, nome);
        contentValues.put(dbHelper.COLUNA_ENDERECO, endereco);
        contentValues.put(dbHelper.COLUNA_TELEFONE, telefone);
        int i = database.update(dbHelper.NOME_TABELA, contentValues, dbHelper.COLUNA_ID + " = " + id, null);
        return i;
    }

    public void excluir(String id){
        database.delete(dbHelper.NOME_TABELA, dbHelper.COLUNA_ID + " = " + id, null);
    }
}
