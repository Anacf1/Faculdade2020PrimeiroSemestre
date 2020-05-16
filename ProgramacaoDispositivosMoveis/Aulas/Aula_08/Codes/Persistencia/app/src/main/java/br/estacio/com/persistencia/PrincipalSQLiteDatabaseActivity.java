package br.estacio.com.persistencia;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class PrincipalSQLiteDatabaseActivity extends AppCompatActivity {

    ManagerSQLiteDatabase manager;
    ListView listView;
    SimpleCursorAdapter cursorAdapter;

    String identificador;
    String nome;
    String endereco;
    String telefone;

    final String [] contatoOrigem = new String [] {DbHelperSQLiteDatabase.COLUNA_ID,
            DbHelperSQLiteDatabase.COLUNA_NOME,
            DbHelperSQLiteDatabase.COLUNA_ENDERECO,
            DbHelperSQLiteDatabase.COLUNA_TELEFONE};

    final int [] contatoDestino = new int [] {R.id.idSQLiteObter, R.id.nomeSQLiteObter, R.id.enderecoSQLiteObter, R.id.telefoneSQLiteObter};

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_sqllitedatabase);

        manager = new ManagerSQLiteDatabase(this);
        try {
            manager.open();
            Cursor cursor = manager.fetch();
        }catch (SQLException e){
            e.printStackTrace();
        }

        Cursor cursor = manager.fetch();

        listView = findViewById(R.id.lista);
        listView.setEmptyView(findViewById(R.id.listaVazia));

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.principal_visualizar_sqllitedatabase, cursor,
                contatoOrigem, contatoDestino, 0);
        cursorAdapter.notifyDataSetChanged();

        listView.setAdapter(cursorAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               TextView textViewId = (TextView) findViewById(R.id.idSQLiteObter);
               TextView textViewNome = (TextView) findViewById(R.id.nomeSQLiteObter);
               TextView textViewEndereco = (TextView) findViewById(R.id.enderecoSQLiteObter);
               TextView textViewTelefone = (TextView) findViewById(R.id.telefoneSQLiteObter);

               identificador = textViewId.getText().toString();
               nome = textViewNome.getText().toString();
               endereco= textViewEndereco.getText().toString();
               telefone = textViewTelefone.getText().toString();

               Intent intent = new Intent(getApplicationContext(), PrincipalAtualizarSQLiteDatabaseActivity.class);
                intent.putExtra("ID", identificador);
                intent.putExtra("NOME", nome);
                intent.putExtra("ENDRECO", endereco);
                intent.putExtra("TELEFONE", telefone);
                startActivity(intent);
           }
       });
    }

    /*@Override
    protected void onListItemClick(ListView list, View view, int posicaoSelecionada, long id){
        super.onListItemClick(list, view, posicaoSelecionada, id);

        String servico = (String) list.getItemAtPosition(posicaoSelecionada);

        Intent intent = new Intent(this, PrincipalAtualizarSQLiteDatabaseActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("NOME", list.get)

    }*/


}
