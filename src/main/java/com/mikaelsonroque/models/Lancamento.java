package com.mikaelsonroque.models;

import java.util.Date;

public class Lancamento {

    private Date lancamento;
    private boolean quitado;

    public Lancamento(Date lancamento, boolean quitado) {
        this.lancamento = lancamento;
        this.quitado = quitado;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public boolean isQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }
}
