package br.estacio.com.listviewfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class ListaActivity extends ListFragment {

    String [] servicos = new String [] {"Cabeleireiro", "Depilacao", "Manicure"};

    String [] descricao = new String [] {"Profissional que cuida do cabelo",
                                         "Profissional que cuida dos pêlos",
                                         "Profissional que cuida das unhas"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_lista, container, false);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, servicos);
        setListAdapter(arrayAdapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView list, View view, int posicaoSelecionada, long id){
        super.onListItemClick(list, view, posicaoSelecionada, id);

        /*Vincula o fragmento que está no layout principal 'textoFragmento', com a classe java 'TextoActivity',
        * uma vez em que 'ambos' são fragmentos*/



        TextoActivity textoFragment = (TextoActivity) getFragmentManager().findFragmentById(R.id.textoFragmento);

        /*Aqui eu determino o que vai aparecer no fragmento de texto*/
        textoFragment.change(servicos[posicaoSelecionada] + " : " + descricao[posicaoSelecionada] );

        /*ALterando a cor da lista*/
        getListView().setSelector(android.R.color.holo_orange_light);

    }
}
