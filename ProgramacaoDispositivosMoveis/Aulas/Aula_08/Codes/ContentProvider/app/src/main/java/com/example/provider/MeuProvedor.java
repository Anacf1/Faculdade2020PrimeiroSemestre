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

    /*1. Essa string define a autoridade para o nosso Content Provider. Não podemos esquecer de que
        deve ser registrado no arquivo AndroidManifest.xml. [Dominio]*/
    static final String PROVEDOR = "estacio.com.provider";

    /*2. Aqui eu defino a minha URL (completa) [content://estacio.com.provider/conteudo]*/
    static final String URL = "content://" + PROVEDOR + "/conteudo";

    /*3. Definindo a CONTENT_URI personalizada, através do método estático parse() da classe URI.
        [content://estacio.com.provider/conteudo]*/
    static  final Uri CONTENT_URI = Uri.parse(URL);

    static final int uri_code = 1;

    static UriMatcher uriMather;  /*4. O Content Provider utiliza um UriMatcher para determinar a operação a ser efetuada em seus métodos query, insert, update e delete.*/

    private static HashMap values;
    static {


        uriMather = new UriMatcher(UriMatcher.NO_MATCH); /*5. A linha uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) indica se certa URL é do tipo lista ou do tipo item.*/
                                                        /*6. O parâmetro UriMatcher.NO_MATCH informa ao aplicativo qual valor padrão retornará para uma correspondência.*/

        uriMather.addURI(PROVEDOR, "conteudo", uri_code); /*7. A linha uriMatcher.addURI (PROVEDOR, "conteudo", uriCode) define qualquer URL que use a autoridade
                                                                    estacio.com.provider e tem um caminho denominado “conteúdo” retornando o valor uriCode,
                                                                    que, em nosso exemplo, foi definido como 1.*/

        uriMather.addURI(PROVEDOR, "conteudo/*", uri_code); /*8. De forma similar à anterior, a linha uriMatcher.addURI(PROVEDOR, 'conteudo/*', uriCode) define qualquer
                                                                        URL que use a autoridade estacio.com.provider e tem um caminho parecido com “conteudo/*”, onde * é um
                                                                        número e retornando o valor uriCode, que, em nosso exemplo, foi definido como 1.*/

    }

    private SQLiteDatabase db;
    /*static final String id = "id";*/
    static final String CAMPO_NOME = "nome";

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
            sortOrder = CAMPO_NOME;
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
        /*11. Retorna o id de nossa linha no banco.*/
        Long rowId = db.insert(DataBaseHelper.NOME_TABELA, "", values);
        if(rowId > 0 ){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId); /*12. Através da linha Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID), construímos
                                                                          um URI para um conteúdo específico, pois adiciona um id específico.*/

            getContext().getContentResolver().notifyChange(_uri, null); /*13. É importante chamar o método notifyChange(). Isso fará com que as aplicações que
                                                                                        estejam utilizando esse conjunto de dados sejam notificadas, permitindo a elas
                                                                                        atualizar também os dados mostrados ao usuário.*/
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
