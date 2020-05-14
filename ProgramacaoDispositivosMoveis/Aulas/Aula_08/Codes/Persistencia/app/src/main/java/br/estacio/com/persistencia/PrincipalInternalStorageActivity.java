package br.estacio.com.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PrincipalInternalStorageActivity extends AppCompatActivity {

    EditText editTextTarefa;
    String tarefa;
    TextView textViewTarefaRecuperada;


    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_internalstorage);

        editTextTarefa = findViewById(R.id.tarefa);
        textViewTarefaRecuperada = findViewById(R.id.tarefaRecuperada);

    }

    public void salvar(View view){
        tarefa = editTextTarefa.getText().toString();

           try {
            FileOutputStream fileOut = openFileOutput("contato.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            outputWriter.write(tarefa);
            outputWriter.close();

            Toast.makeText(getBaseContext(), "Gravação efetuada com sucesso!!", Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException f){
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*File arquivo = new File("contatos.txt");

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
        //limpar(view);*/

    }

    public void obter(View view){
        try {
            FileInputStream fileInput = openFileInput("contato.txt");
            InputStreamReader inputReader = new InputStreamReader(fileInput);

            char [] inputBuffers = new char[100];
            String s = "";
            int charRead;

            while((charRead = inputReader.read(inputBuffers))>0){
                    String readString = String.copyValueOf(inputBuffers,0,charRead);
                    s += readString;
            }
            inputReader.close();
            textViewTarefaRecuperada.setText(s);
        }catch (FileNotFoundException f){

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        editTextTarefa.setText("");
        textViewTarefaRecuperada.setText("");
    }

    public void irParaExternalStorage(View view){
        Intent intent = new Intent(this.getApplicationContext(), PrincipalExternalStorageActivity.class);
        startActivity(intent);
    }
}
