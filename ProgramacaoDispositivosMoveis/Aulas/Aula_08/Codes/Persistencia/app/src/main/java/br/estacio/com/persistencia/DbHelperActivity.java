package br.estacio.com.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.net.IDN;

public class DbHelperActivity extends SQLiteOpenHelper {

    public static String NOME_BANCO = "contato.db";
    public static int VERSAO_BANCO = 1;
    public static String NOME_TABELA = "contatoTB";

    public static String COLUNA_ID = "id";
    public static String COLUNA_NOME = "nome";
    public static String COLUNA_ENDERECO = "endereco";
    public static String COLUNA_TELEFONE = "telefone";

    public static String CREATE_TABLE = "create table " + NOME_TABELA + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                                                COLUNA_NOME + " TEXT NOT NULL, " +
                                                                                COLUNA_ENDERECO + " TEXT " +
                                                                                COLUNA_TELEFONE + " TEXT);";

    public DbHelperActivity(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
