package br.com.alura.leilao.model;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class LeilaoTest {

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // Criar cenário de teste
        Leilao console = new Leilao("Console");

        // Executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // Testar resultado esperado
        Assert.assertEquals("Console", descricaoDevolvida);
    }

    //[nome do método] [Estado de teste] [resultado esperado]

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolvidoMaiorLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Diego"), 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Sofia"), 100.0));
        computador.propoe(new Lance(new Usuario("Ana Laura"), 200.0));

        double maiorLanceDevolvidoDoComputador = computador.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Sofia"), 10000.0));
        carro.propoe(new Lance(new Usuario("Ana Laura"), 9000.0));

        double maiorLanceDevolvidoDoCarro = carro.getMaiorLance();

        Assert.assertEquals(10000.0, maiorLanceDevolvidoDoCarro, 0.0001);
    }

}