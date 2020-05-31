package com.example.api;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PrincipalFlowerActivity extends ListActivity {

    public static  final String PHOTO_BASE_URL = "http://services.hanselandpetal.com/photos/";

    ProgressBar progressBar;
    List<MyTask> tarefas;

    List<Flower> flowerList;

    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.principal_flower);

        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);
        tarefas = new ArrayList<>();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(isOnLine()){
            requestData("http://services.hanselandpetal.com/secure/flowers.json");
        }else{
            Toast.makeText(this, "Rede indisponivel", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void requestData(String uri){
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected  void updateDisplay(){
        FlowerAdapter flowerAdapter = new FlowerAdapter(this, R.layout.item_flower, flowerList);
        setListAdapter(flowerAdapter);
    }

    protected boolean isOnLine(){
        /*ConnectivityManager: através dessa classe, podemos consultar o estado de conectividade da rede*/
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        /*NetworkInfo: detalhes sobre a rede de dados padrão ativa no momento*/
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null && netInfo.isConnectedOrConnecting()){
                return true;
            }else{
                return false;
            }
    }

    private class MyTask extends AsyncTask<String, String, List<Flower>>{

        @Override
        protected void onPreExecute(){
            if(tarefas.size() == 0){
                progressBar.setVisibility(View.VISIBLE);
            }
            tarefas.add(this);
        }

        @Override
        protected List<Flower> doInBackground(String... params) {
            String content = HTTPManager.getData(params[0], "feeduser", "feedpassword");
            flowerList = FlowerJsonParser.parserFeed(content);

            for (Flower flower : flowerList) {
                String imageURL = PHOTO_BASE_URL + flower.getFotoString();
                try {
                    InputStream inputStream = (InputStream) new URL(imageURL).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    flower.setFotoBitmap(bitmap);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return flowerList;
        }

        @Override
        protected  void onPostExecute(List<Flower> result){
            tarefas.remove(this);
            if(tarefas.size() == 0){
                progressBar.setVisibility(View.INVISIBLE);
            }
            if(result == null){
                Toast.makeText(PrincipalFlowerActivity.this, "Web Service Indisponível", Toast.LENGTH_LONG).show();
                return;
            }
            updateDisplay();
        }
    }
}
