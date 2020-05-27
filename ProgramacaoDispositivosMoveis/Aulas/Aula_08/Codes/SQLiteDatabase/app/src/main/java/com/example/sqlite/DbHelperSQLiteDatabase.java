package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*1. Para criar um banco de dados SQLite, precisamos implementar uma subclasse de SQLiteOpenHelper, que
        tem por responsabilidade criar o banco de dados, assim como gerenciar a versão do mesmo.*/
public class DbHelperSQLiteDatabase extends SQLiteOpenHelper {

    public static String NOME_BANCO = "contato.db";
    public static int VERSAO_BANCO = 2;
    public static String NOME_TABELA = "contatoTB";

    /*Obs_01: Como estou trabalhando com 'SimpleCursorAdapter' (em PrincipalActivity.java),
        O framework requer que:
        * 1. A tabela tenha um coluna '_id', que seja INTEGER PRIMARY KEY AUTOINCREMENT
        * 2. Quando for criar o Cursor: Cursor cursor = database.query(. . . ); ,
        *   tenha também com coluna com o nome '_id', na lista de colunas*/
    public static String COLUNA_ID_CREATE_TABLE = "_id";

    public static String COLUNA_ID = "id";
    public static String COLUNA_NOME = "nome";
    public static String COLUNA_ENDERECO = "endereco";
    public static String COLUNA_TELEFONE = "telefone";

    /*Obs_02. Este código so é executado uma única vez! Caso queira modificar alguma coisa no código:
    * 1. Incrementar (+1) a versao do banco - atributo: VERSAO_BANCO
    * 2. Deletar a tabela e recriá-la*/
    public static String CREATE_TABLE = "create table " + NOME_TABELA + " (" + COLUNA_ID_CREATE_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUNA_NOME + " TEXT NOT NULL, " +
            COLUNA_ENDERECO + " TEXT, " +
            COLUNA_TELEFONE + " TEXT)";

    public DbHelperSQLiteDatabase(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /*2. O método onUpdate() faz uso do execSQL quando a versão do banco é alterada
        A partir de agora, toda vez que a versão do banco é alterada, o método onUpdate é executado*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(db);
    }
}
