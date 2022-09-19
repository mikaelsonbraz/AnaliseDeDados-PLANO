package com.mikaelsonroque.services;

import com.mikaelsonroque.models.Empresa;
import com.mikaelsonroque.models.Lancamento;

import java.util.ArrayList;
import java.util.List;

public class VerificarInadiplencia {


    public String verificarInadiplencia(Empresa empresa){
        double totalLancamentos = empresa.getLancamentos().size();
        double  totalInadiplencia = 0;

        if (totalLancamentos >= 1){
            for (Lancamento lancamento : empresa.getLancamentos()){
                if (!lancamento.isQuitado()){
                    totalInadiplencia++;
                }
            }
        } else {
            return "0,00";
        }

        return String.format("%.2f", totalInadiplencia * 100 / totalLancamentos);
    }


    public List<String> verificarListaDeEmpresas(List<Empresa> empresas){
        List<String> lista = new ArrayList<>();

        for (Empresa empresa : empresas){
            lista.add("A empresa " + empresa.getName() + " possui " + verificarInadiplencia(empresa) + "% de inadiplência");
        }

        return lista;
    }
}
