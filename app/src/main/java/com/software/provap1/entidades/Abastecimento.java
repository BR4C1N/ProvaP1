package com.software.provap1.entidades;

public class Abastecimento {
    private Float quilometragemAtual;
    private Float quantidadeAbastecida;
    private String data;
    private Float valor;

    public Float getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(Float quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public Float getQuantidadeAbastecida() {
        return quantidadeAbastecida;
    }

    public void setQuantidadeAbastecida(Float quantidadeAbastecida) {
        this.quantidadeAbastecida = quantidadeAbastecida;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
