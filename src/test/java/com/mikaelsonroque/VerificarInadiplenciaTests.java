package com.mikaelsonroque;

import com.mikaelsonroque.models.Empresa;
import com.mikaelsonroque.models.Lancamento;
import com.mikaelsonroque.services.VerificarInadiplencia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VerificarInadiplenciaTests {


    @Autowired
    private VerificarInadiplencia verificarInadiplencia =  new VerificarInadiplencia();

    private Empresa empresaA = new Empresa("Empresa A", new HashSet<>());
    private Empresa empresaB = new Empresa("Empresa B", new HashSet<>());
    private Empresa empresaC = new Empresa("Empresa C", new HashSet<>());
    private Lancamento lancamentoAdiplente1 = new Lancamento(new Date(), true);
    private Lancamento lancamentoAdiplente2 = new Lancamento(new Date(), true);
    private Lancamento lancamentoAdiplente3 = new Lancamento(new Date(), true);
    private Lancamento lancamentoAdiplente4 = new Lancamento(new Date(), true);
    private Lancamento lancamentoInadiplente1 = new Lancamento(new Date(), false);
    private Lancamento lancamentoInadiplente2 = new Lancamento(new Date(), false);
    private Lancamento lancamentoInadiplente3 = new Lancamento(new Date(), false);
    private Lancamento lancamentoInadiplente4 = new Lancamento(new Date(), false);


    @Test
    @DisplayName("Deve verificar 0.0% de inadiplência da Empresa A, 75.0% da Empresa B e 100% da empresa C")
    public void verificarInadiplenciaTest(){
        //Cenário
        empresaA.getLancamentos().addAll(Set.of(lancamentoAdiplente1, lancamentoAdiplente2, lancamentoAdiplente3, lancamentoAdiplente4));
        empresaB.getLancamentos().addAll(Set.of(lancamentoAdiplente1, lancamentoInadiplente2, lancamentoInadiplente3));
        empresaC.getLancamentos().addAll(Set.of(lancamentoInadiplente1, lancamentoInadiplente2, lancamentoInadiplente3, lancamentoInadiplente4));

        //Execução
        String inadiplenciaA = verificarInadiplencia.verificarInadiplencia(empresaA);
        String inadiplenciaB = verificarInadiplencia.verificarInadiplencia(empresaB);
        String inadiplenciaC = verificarInadiplencia.verificarInadiplencia(empresaC);

        //Verificação
        Assertions.assertThat(inadiplenciaA).isEqualTo("0,00");
        Assertions.assertThat(inadiplenciaB).isEqualTo("66,67");
        Assertions.assertThat(inadiplenciaC).isEqualTo("100,00");
    }

    @Test
    @DisplayName("Deve retornar a lista das empresas respectivamente com suas porcentagens de inadiplência")
    public void verificarListaDeEmpresasTest(){
        //Cenário
        empresaA.getLancamentos().addAll(Set.of(lancamentoAdiplente1, lancamentoInadiplente2, lancamentoInadiplente3));
        empresaC.getLancamentos().addAll(Set.of(lancamentoAdiplente1, lancamentoInadiplente1));
        List<Empresa> listaEmpresas = List.of(empresaA, empresaB, empresaC);

        //Execução
        List<String> listaInadiplências = verificarInadiplencia.verificarListaDeEmpresas(listaEmpresas);

        //Verificação
        Assertions.assertThat(listaInadiplências).hasSize(3);
        Assertions.assertThat(listaInadiplências).contains("A empresa Empresa A possui 66,67% de inadiplência");
        Assertions.assertThat(listaInadiplências).contains("A empresa Empresa C possui 50,00% de inadiplência");
        Assertions.assertThat(listaInadiplências).contains("A empresa Empresa B possui 0,00% de inadiplência");
    }
}
