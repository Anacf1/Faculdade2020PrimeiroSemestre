package com.example.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FlowerJsonParser {

    public static List<Flower> parserFeed(String content){
        try{
            JSONArray jsonArray = new JSONArray(content); /*Criamos um objeto do tipo JSONArray, passando a string recebida*/
            List<Flower> flowerList = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Flower flower = new Flower();

                flower.setFlorId(jsonObject.getInt("productId"));
                flower.setNome(jsonObject.getString("name"));
                flower.setCategoria(jsonObject.getString("category"));
                flower.setInstruções(jsonObject.getString("instructions"));
                flower.setFotoString(jsonObject.getString("photo"));
                flower.setPreço(jsonObject.getDouble("price"));
                flowerList.add(flower);
            }

            return flowerList;

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
