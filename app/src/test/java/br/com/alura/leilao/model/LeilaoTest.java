package br.com.alura.leilao.model;

import junit.framework.TestCase;

import org.junit.Test;

public class LeilaoTest extends TestCase {

    @Test
    public void testgetDescricao() {
        // Criar cenário de teste
        Leilao console = new Leilao("Console");

        // Executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void testgetMaiorLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Diego"), 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

}