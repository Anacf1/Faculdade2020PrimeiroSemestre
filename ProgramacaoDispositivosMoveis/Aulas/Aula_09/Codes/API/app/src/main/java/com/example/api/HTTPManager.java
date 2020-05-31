package com.example.api;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPManager {

    public static String getData(String uri) {
         /*BufferedReader: Já a classe BufferedReader pertence ao pacote java.io. É usada para ler streams
                do tipo texto.*/
        BufferedReader bufferedReader = null;
        try {
            /*Estamos obtendo uma instância do tipo HttpURLConnection, a partir do método
                    .openConnection() da classe URL*/
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            /*StringBuilder: A classe StringBuilder é uma classe do pacote java.lang. Optamos por usá-la no
                lugar da classe String para concatenação.*/
            StringBuilder stringBuilder = new StringBuilder();

            /*A classe BufferedReader, é utilizada para estabelecer um contrato de leitura com a
            conexão HTTP, ou seja, efetuará a leitura de dados vindo do InputStream.*/
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
               try {
                   bufferedReader.close();

               } catch (IOException e) {
                   e.printStackTrace();
                   return null;
               }
            }
        }
    }

    public static String getData(String uri, String username, String password){
        byte loginBytes[] = (username + ":" + password).getBytes();
        StringBuilder loginBuilder = new StringBuilder().append("Basic ").append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        HttpURLConnection httpURLConnection = null;

        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.addRequestProperty("Authorization", loginBuilder.toString());

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                int status = httpURLConnection.getResponseCode();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
