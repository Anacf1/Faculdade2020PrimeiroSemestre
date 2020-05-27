package com.example.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MeuProvedor extends ContentProvider {

    private SQLiteDatabase db;
    static final String id = "id";
    static final String nome = "nome";
    static final String PROVEDOR = "estacio.com.provider";

    static final String URL = "content://" + PROVEDOR + "/conteudo";

    /* Definindo a CONTENT_URI personalizada, através do método estático parse() da classe URI. */
    static  final Uri CONTENT_URI = Uri.parse(URL);
    static final int uri_code = 1;
    static UriMatcher uriMather;
    private static HashMap values;
    static {
        uriMather = new UriMatcher(UriMatcher.NO_MATCH);
        uriMather.addURI(PROVEDOR, "conteudo", uri_code);
        uriMather.addURI(PROVEDOR, "conteudo/*", uri_code);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if(db != null){
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DataBaseHelper.NOME_TABELA);
        switch (uriMather.match(uri)){
            case uri_code:
                queryBuilder.setProjectionMap(values);
                break;
            default:
                throw  new IllegalArgumentException("URI desconhecida: " + uri);
        }
        if(sortOrder == null || sortOrder == ""){
            sortOrder = nome;
            ;
        }
        Cursor c = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMather.match(uri)){
            case uri_code:
                return "vnd.android.cursor.dir/cte";
            default:
                throw new IllegalArgumentException("URI não suportada: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        /*Retorna o id de nossa linha no banco.*/
        Long rowId = db.insert(DataBaseHelper.NOME_TABELA, "", values);
        if(rowId > 0 ){
            /*Através da linha Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID), construímos
            um URI para um conteúdo específico, pois adiciona um id específico.*/
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            /*É importante chamar o método notifyChange(). Isso fará com que as aplicações que estejam
            utilizando esse conjunto de dados sejam notificadas, permitindo a elas atualizar também
            os dados mostrados ao usuário.*/
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }else {
            throw new SQLException("Falha da inclusãodacidade na uri: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
