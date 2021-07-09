package br.com.alura.leilao.model;

import junit.framework.TestCase;

import org.junit.Test;

public class LeilaoTest extends TestCase {

    @Test
    public void testGetDescricao() {
        // Criar cenário de teste
        Leilao console = new Leilao("Console");

        // Executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }
}