package com.example.provider;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal);
    }

    public void incluir(View view){
        final EditText editTextNomeCidade = findViewById(R.id.nomeCidade);
        final String nomeCidade = editTextNomeCidade.getText().toString();
        Button buttonIncluir = findViewById(R.id.incluir);
        buttonIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*9. Quanto efetuarmos um click no botão incluir, o método incluir()
                será chamado. Nele, criaremos o objeto do tipo ContentValues, visando encapsular o
                par chave-valor, onde a chave é nome (campo tabela) e o valor é a cidade digitada.*/
                ContentValues values = new ContentValues();
                values.put(MeuProvedor.CAMPO_NOME, (nomeCidade));

                /*10. Para acessar o Content Provider, usamos a classe ContentResolver, que pode ser
                acessada através do método getContentResolver()*/

                /*10.1 CONTENT_URI -> [content://estacio.com.provider/conteudo]
                    Contém:
                        10.1.1. authority = estacio.com.provider
                        10.1.2. path = /conteudo
                        10.1.3. scheme = content
                        10.1.4  uriString = content://estacio.com.provider/conteudo
                        10.1.5. values = "nome=Rio de Janeiro"*/
                Uri uri = getContentResolver().insert(MeuProvedor.CONTENT_URI, values);

                MeuProvedor meuProvedor = new MeuProvedor();
                meuProvedor.insert(uri, values);
            }

        });
        editTextNomeCidade.setText("");
    }
}
