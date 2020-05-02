package br.estacio.com.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        String [] servicos = {"Manicure", "Depilacao", "Cabeleireiro"};

        textView = findViewById(R.id.output);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, servicos);
        setListAdapter(arrayAdapter);

      }

    /**/
    @Override
    protected void onListItemClick(ListView list, View view, int posicaoSelecionada, long id){
        super.onListItemClick(list, view, posicaoSelecionada, id);

        String servico = (String) list.getItemAtPosition(posicaoSelecionada);
        textView.setText("Serviço selecionado: " + servico.toString());

    }
}
