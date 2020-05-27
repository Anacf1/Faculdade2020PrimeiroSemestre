package br.estacio.com.persistencia;

import android.content.Context;
import android.content.ContentValues;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MeuProvedor extends ContentProvider {

    static final String id = "id";
    static final String nome = "nome";

    /* Essa string define a autoridade para o nosso Content Provider.
    Não podemos esquecer de que deve ser registrado no arquivo AndroidManifest.xml. */
    static final String PROVEDOR = "estacio.com.contentprovider";

    static final String URL = "content://" + PROVEDOR + "/conteudo";

    /* Definindo a CONTENT_URI personalizada, através do método estático parse() da classe URI. */
    static  final Uri CONTENT_URI = Uri.parse(URL);

    static final int uri_code = 1;
    /* O Content Provider utiliza um UriMatcher para determinar a operação a ser efetuada em seus
    métodos query, insert, update e delete. */
    static UriMatcher uriMather;
    private static HashMap values;
    static {
        /* A linha uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) indica se certa URL é do tipo
        lista ou do tipo item.
        O parâmetro UriMatcher.NO_MATCH informa ao aplicativo qual valor padrão retornará para uma
        correspondência.*/
        uriMather = new UriMatcher(UriMatcher.NO_MATCH);
        /* A linha uriMatcher.addURI (PROVEDOR, "conteudo", uriCode) define qualquer URL que use a
        autoridade profoswaldo.com.MeuProvedorConteudo e tem um caminho denominado “conteúdo”
        retornando o valor uriCode, que, em nosso exemplo, foi definido como 1. */
        uriMather.addURI(PROVEDOR, "conteudo", uri_code);
        /* De forma similar à anterior, a linha uriMatcher.addURI(PROVEDOR, "conteudo/*", uriCode)
        define qualquer URL que use a autoridade profoswaldo.com.MeuProvedorConteudo e tem um
        caminho parecido com 'conteudo/*', onde * é um número e retornando o valor uriCode, que,
        em nosso exemplo, foi definido como 1. */
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
        queryBuilder.setTables(NOME_TABELA);
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
        Long rowId = db.insert(NOME_TABELA, "", values);
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

    private SQLiteDatabase db;
    static  final String BANCO_DADOS = "cidade_db";
    static  final String NOME_TABELA = "cidade_tb";
    static  final int VERSAO_BANCO_DADOS = 1;
    static final String SCRIPT_CREATE_TABLE = "create table " + NOME_TABELA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, cidade TEXT NOT NULL)";

    /* Início da classe estática - DataBaseHelper */
    private static class DataBaseHelper extends SQLiteOpenHelper{
        DataBaseHelper (Context context) {
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
    /* Final classe estática - DataBaseHelper */

}
