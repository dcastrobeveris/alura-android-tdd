package br.com.alura.leilao.model;

import org.junit.Assert;
import org.junit.Test;

public class LeilaoTest {

    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario DIEGO = new Usuario("Diego");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        // Executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // Testar resultado esperado
        Assert.assertEquals("Console", descricaoDevolvida);
    }

    //[nome do método] [estado de teste] [resultado esperado]
    //[deve] [resultado esperado] [estado de teste]

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        Assert.assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 9000.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        Assert.assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        Assert.assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        Assert.assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 9000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        Assert.assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }

}