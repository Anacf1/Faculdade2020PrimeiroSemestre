package br.estacio.com.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class PrincipalAtualizarSQLiteDatabaseActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextEndereco;
    EditText editTextTelefone;

    String id;
    String nome;
    String endereco;
    String telefone;

    ManagerSQLiteDatabase manager;

    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_atualizar_sqllitedatabase);

        editTextNome = findViewById(R.id.nomeSQLiteUpdate);
        editTextEndereco = findViewById(R.id.enderecoSQLiteUpdate);
        editTextTelefone = findViewById(R.id.telefoneSQLiteUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        nome = intent.getStringExtra("NOME");
        endereco = intent.getStringExtra("ENDERECO");
        telefone = intent.getStringExtra("TELEFONE");

        manager = new ManagerSQLiteDatabase(this);
        try {
            manager.open();
        }catch (SQLException e){
            e.printStackTrace();
        }

        editTextNome.setText(nome);
        editTextEndereco.setText(endereco);
        editTextTelefone.setText(telefone);

    }

    public void atualizarSQLite(View view){
        nome = editTextNome.getText().toString();
        endereco = editTextEndereco.getText().toString();
        telefone = editTextTelefone.getText().toString();

        manager.atualizar(id, nome, endereco, telefone);
        voltarTelaPrincipal(view);
    }

    public void excluirSQLite(View view){
        manager.excluir(id);
        voltarTelaPrincipal(view);
    }

    private void voltarTelaPrincipal(View view){
        Intent intent = new Intent(this, PrincipalSQLiteDatabaseActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
