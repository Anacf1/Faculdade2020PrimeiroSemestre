package br.estacio.com.listviewbotoes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ItensListaActivity extends ArrayAdapter<String> {

    private Activity context;
    private String[] web;
    private Integer[] imageId;

    public ItensListaActivity(Activity context, String[] web, Integer[] imageId) {
        super(context, R.layout.itenslista, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.itenslista, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagem);

        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }

}
