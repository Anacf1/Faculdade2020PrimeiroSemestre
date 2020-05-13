package br.estacio.com.persistencia;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrincipalInternalStorageActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextEmail;
    String nome;
    String email;
    TextView textViewNomeRecuperado;
    TextView textViewEmailRecuperado;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_internalstorage);

        editTextNome = findViewById(R.id.nome);
        editTextEmail = findViewById(R.id.email);

        textViewNomeRecuperado = findViewById(R.id.nomeRecuperado);
        textViewEmailRecuperado = findViewById(R.id.emailRecuperado);

    }

    public void salvar(View view){
        nome = editTextNome.getText().toString();
        email = editTextEmail.getText().toString();

        File arquivo = new File("contatos.txt");

        try {

            FileWriter fileWriter = new FileWriter(arquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(nome);
            bufferedWriter.write(email);
        }catch (IOException io){
            io.printStackTrace();
        }

        String linha= null;

        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while(bufferedReader.ready()){
                linha += bufferedReader.readLine();
            }

        }catch (FileNotFoundException file){
            file.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textViewNomeRecuperado.setText(linha);
        //limpar(view);

    }

    public void obter(View view){
        /*editTextEmail.setText("");
        nome = editTextNome.getText().toString();



        if(!emailRecuperado.equalsIgnoreCase("NAO_ENCONTRADO")){
            textViewNomeRecuperado.setText("Nome: " + nome);
            textViewEmailRecuperado.setText("Email: " + emailRecuperado);
        }else{
            textViewNomeRecuperado.setText("Erro: " + emailRecuperado);
            textViewEmailRecuperado.setText("");
        }*/
    }

    public void limpar(View view){
        editTextNome.setText("");
        editTextEmail.setText("");
        textViewNomeRecuperado.setText("");
        textViewEmailRecuperado.setText("");
    }
}
