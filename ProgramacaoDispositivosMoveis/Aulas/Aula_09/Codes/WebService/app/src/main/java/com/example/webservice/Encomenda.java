package com.example.webservice;

public class Encomenda {

    private String codigoServico;
    private String prazoEntrega;
    private String entregaDomiciliar;
    private String entregaSabado;

    public Encomenda(){}

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public void setPrazoEntrega(String prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public void setEntregaDomiciliar(String entregaDomiciliar) {
        this.entregaDomiciliar = entregaDomiciliar;
    }

    public void setEntregaSabado(String entregaSabado) {
        this.entregaSabado = entregaSabado;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public String getPrazoEntrega() {
        return prazoEntrega;
    }

    public String getEntregaDomiciliar() {
        return entregaDomiciliar;
    }

    public String getEntregaSabado() {
        return entregaSabado;
    }
}
