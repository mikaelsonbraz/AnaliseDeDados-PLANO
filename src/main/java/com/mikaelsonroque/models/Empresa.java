package com.mikaelsonroque.models;


import java.util.HashSet;
import java.util.Set;

public class Empresa {

    private String name;
    private Set<Lancamento> lancamentos = new HashSet<>();

    public Empresa(String name, Set<Lancamento> lancamentos) {
        this.name = name;
        this.lancamentos = lancamentos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(Set<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
}
