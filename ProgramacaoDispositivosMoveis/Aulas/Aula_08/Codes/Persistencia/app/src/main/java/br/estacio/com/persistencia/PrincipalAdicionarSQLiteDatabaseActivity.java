package br.estacio.com.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class PrincipalAdicionarSQLiteDatabaseActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextEndereco;
    EditText editTextTelefone;

    String nome;
    String endereco;
    String telefone;

    ManagerSQLiteDatabase manager;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_adicionar_sqllitedatabase);

        editTextNome = findViewById(R.id.nomeSQLiteCreate);
        editTextEndereco = findViewById(R.id.enderecoSQLiteCreate);
        editTextTelefone = findViewById(R.id.telefoneSQLiteCreate);

        manager = new ManagerSQLiteDatabase(this);
        try {
            manager.open();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public  void incluirSQLite(View view){
        nome = editTextNome.getText().toString();
        endereco = editTextEndereco.getText().toString();
        telefone = editTextTelefone.getText().toString();

        manager.incluir(nome, endereco, telefone);

        Intent intent = new Intent(this, PrincipalSQLiteDatabaseActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public  void limparSQLite(View view){
        editTextNome.setText("");
        editTextEndereco.setText("");
        editTextTelefone.setText("");
    }


}
