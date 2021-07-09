package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public double getMaiorLance(){
        return maiorLance;
    }

    public String getDescricao() {
        return descricao;
    }

}
