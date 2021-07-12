package br.com.alura.leilao.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario DIEGO = new Usuario("Diego");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        // Executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // Testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    //[nome do método] [estado de teste] [resultado esperado]
    //[deve] [resultado esperado] [estado de teste]

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 9000.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(10000.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 10000.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 9000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(9000.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 300.0));
        CONSOLE.propoe(new Lance(DIEGO, 400.0));

        // Test Driven Development
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0,
                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0,
                tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0,
                tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

}