package com.example.api;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower> {

    Context context;
    List<Flower> listaFlores;

    public FlowerAdapter(@NonNull Context context, int resource, List<Flower> objects) {
        super(context, resource, objects);
        this.context = context;
        listaFlores = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_flower, parent, false);

        Flower flower = listaFlores.get(position);

        TextView textView = (TextView) view.findViewById(R.id.item_texto);
        textView.setText(flower.getNome());

        ImageView imageView = (ImageView) view.findViewById(R.id.item_imagem);
        imageView.setImageBitmap(flower.getFotoBitmap());

        return view;
    }
}
