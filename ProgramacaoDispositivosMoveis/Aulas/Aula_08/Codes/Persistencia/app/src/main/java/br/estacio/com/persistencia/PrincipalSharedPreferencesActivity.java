package br.estacio.com.persistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*Shared Preferences
        Pequenas quantidades de dados
        Permite salvar e recuperar pares de 'chave/valor' de tipos primitivos de dados
        (booleans, floats, ints, longs, e strings) em um arquivo denominado Arquivo de
        Preferências, pois associa um “nome” a uma determinada informação para que
        depois se possa recuperá-la através desse nome.*/

public class PrincipalSharedPreferencesActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    EditText editTextNome;
    EditText editTextEmail;
    String nome;
    String email;
    TextView textViewNomeRecuperado;
    TextView textViewEmailRecuperado;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_sharedpreferences);

        sharedpreferences = getSharedPreferences("Agenda", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        editTextNome = findViewById(R.id.nome);
        editTextEmail = findViewById(R.id.email);

        textViewNomeRecuperado = findViewById(R.id.nomeRecuperado);
        textViewEmailRecuperado = findViewById(R.id.emailRecuperado);

    }

    public void salvar(View view){
        nome = editTextNome.getText().toString();
        email = editTextEmail.getText().toString();

        editor.putString(nome, email);
        editor.commit();
        limpar(view);

    }

    public void obter(View view){
        editTextEmail.setText("");
        nome = editTextNome.getText().toString();

        String emailRecuperado = (String) sharedpreferences.getString(nome, "NAO_ENCONTRADO");

        if(!emailRecuperado.equalsIgnoreCase("NAO_ENCONTRADO")){
            textViewNomeRecuperado.setText("Nome: " + nome);
            textViewEmailRecuperado.setText("Email: " + emailRecuperado);
        }else{
            textViewNomeRecuperado.setText("Erro: " + emailRecuperado);
            textViewEmailRecuperado.setText("");
        }
    }

    public void limpar(View view){
        editTextNome.setText("");
        editTextEmail.setText("");
        textViewNomeRecuperado.setText("");
        textViewEmailRecuperado.setText("");
    }

    public void irParaInternalStorage(View view){
        Intent intent = new Intent(this, PrincipalInternalStorageActivity.class);
        startActivity(intent);
    }
}
