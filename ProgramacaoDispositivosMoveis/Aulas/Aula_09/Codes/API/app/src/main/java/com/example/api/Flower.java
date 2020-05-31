package com.example.api;

import android.graphics.Bitmap;

public class Flower {

    private int florId;
    private String nome;
    private String categoria;
    private String instruções;
    private double preço;
    private String fotoString;
    private Bitmap fotoBitmap;

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFlorId(int florId) {
        this.florId = florId;
    }

    public void setFotoBitmap(Bitmap fotoBitmap) {
        this.fotoBitmap = fotoBitmap;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public void setInstruções(String instruções) {
        this.instruções = instruções;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public Bitmap getFotoBitmap() {
        return fotoBitmap;
    }

    public double getPreço() {
        return preço;
    }

    public int getFlorId() {
        return florId;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFotoString() {
        return fotoString;
    }

    public String getInstruções() {
        return instruções;
    }

    public String getNome() {
        return nome;
    }

}
