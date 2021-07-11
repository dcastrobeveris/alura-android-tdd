package br.com.alura.leilao.model;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class LeilaoTest {

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        // Criar cenário de teste
        Leilao console = new Leilao("Console");

        // Executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // Testar resultado esperado
        Assert.assertEquals("Console", descricaoDevolvida);
    }

    //[nome do método] [estado de teste] [resultado esperado]
    //[deve] [resultado esperado] [estado de teste]

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Diego"), 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double maiorLanceDevolvido = console.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Sofia"), 100.0));
        computador.propoe(new Lance(new Usuario("Ana Laura"), 200.0));

        double maiorLanceDevolvido = computador.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Sofia"), 10000.0));
        carro.propoe(new Lance(new Usuario("Ana Laura"), 9000.0));

        double maiorLanceDevolvido = carro.getMaiorLance();

        Assert.assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Diego"), 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double menorLanceDevolvido = console.getMenorLance();

        Assert.assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Sofia"), 100.0));
        computador.propoe(new Lance(new Usuario("Ana Laura"), 200.0));

        double menorLanceDevolvido = computador.getMenorLance();

        Assert.assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Sofia"), 10000.0));
        carro.propoe(new Lance(new Usuario("Ana Laura"), 9000.0));

        double menorLanceDevolvido = carro.getMenorLance();

        Assert.assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }

}