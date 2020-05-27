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
                ContentValues values = new ContentValues();
                values.put(MeuProvedor.nome, (nomeCidade));
                Uri uri = getContentResolver().insert(MeuProvedor.CONTENT_URI, values);

                MeuProvedor meuProvedor = new MeuProvedor();
                meuProvedor.insert(uri, values);

                editTextNomeCidade.setText("");
            }
        });

    }
}
