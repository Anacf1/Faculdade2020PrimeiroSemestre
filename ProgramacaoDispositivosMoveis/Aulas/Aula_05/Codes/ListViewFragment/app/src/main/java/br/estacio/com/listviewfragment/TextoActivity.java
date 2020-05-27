package br.estacio.com.listviewfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TextoActivity extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_texto, container, false);

        /*Seria o findById*/
        textView = view.findViewById(R.id.texto);
        return view;
    }

    public void change(String texto){
        textView.setText(texto);
    }
}
