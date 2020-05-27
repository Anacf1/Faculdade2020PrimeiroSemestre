package br.estacio.com.persistencia;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalContentProviderActivity extends AppCompatActivity {

    EditText editTextCidade;
    Button buttonAdicionarCidade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_contentprovider);

        editTextCidade = findViewById(R.id.nomeCidade);
        /*buttonAdicionarCidade = findViewById(R.id.adicionarCidade);
        buttonAdicionarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MeuProvedor.nome, (editTextCidade.getText().toString()));
                Uri uri = getContentResolver().insert(MeuProvedor.CONTENT_URI, values);

                MeuProvedor meuProvedor = new MeuProvedor();
                meuProvedor.insert(uri, values);

            }
        });*/
    }

    public void adicionarCidade(View view){
        /*
        Quanto efetuarmos um click no botão Adicionar, o método adicionarCidade() será chamado.
        Nele, criaremos o objeto do tipo ContentValues, visando encapsular o par chave-valor, onde a
        chave é nome e o valor é a cidade digitada. *//*
        ContentValues values = new ContentValues();
        values.put(MeuProvedor.nome, (editTextCidade.getText().toString()));

        *//*Para acessar o Content Provider, usamos a classe ContentResolver, que pode ser acessada
        através do método getContentResolver(), conforme demonstrado abaixo: *//*
        Uri uri = getContentResolver().insert(MeuProvedor.CONTENT_URI, values);

        MeuProvedor meuProvedor = new MeuProvedor();
        meuProvedor.insert(uri, values);*/

        buttonAdicionarCidade = findViewById(R.id.adicionarCidade);
        buttonAdicionarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MeuProvedor.nome, (editTextCidade.getText().toString()));
                Uri uri = getContentResolver().insert(MeuProvedor.CONTENT_URI, values);

                MeuProvedor meuProvedor = new MeuProvedor();
                meuProvedor.insert(uri, values);
            }
        });


    }
}
