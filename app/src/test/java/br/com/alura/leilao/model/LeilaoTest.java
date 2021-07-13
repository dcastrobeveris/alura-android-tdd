package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario DIEGO = new Usuario("Diego");

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescricao() {
        // Executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // Testar resultado esperado
        // assertEquals("Console", descricaoDevolvida);
        assertThat(descricaoDevolvida, is(equalTo("Console")));
    }

    //[nome do método] [estado de teste] [resultado esperado]
    //[deve] [resultado esperado] [estado de teste]

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));

        // Delta = a diferença entre os valores com ponto flutuante e se ele for maior,
        // significa que os valores são equivalentes.

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        // assertEquals(200.0, maiorLanceDevolvido, DELTA);
        assertThat(maiorLanceDevolvido, closeTo(200.0, DELTA));
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(DIEGO, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
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
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 300.0));
        CONSOLE.propoe(new Lance(DIEGO, 400.0));

        // Test Driven Development
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        // assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertThat(tresMaioresLancesDevolvidos, hasSize(equalTo(3)));

        // assertEquals(400.0,
        //      tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        // assertThat(tresMaioresLancesDevolvidos, hasItem(new Lance(DIEGO, 400.0)));

        // assertEquals(300.0,
        //      tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);

        // assertEquals(200.0,
        //      tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

        // assertThat(tresMaioresLancesDevolvidos, contains(
        //      new Lance(DIEGO, 400.0),
        //      new Lance(new Usuario("Ana"), 300.0),
        //      new Lance(DIEGO, 200.0)));

        assertThat(tresMaioresLancesDevolvidos,
                both(Matchers.<Lance>hasSize(3))
                        .and(contains(
                        new Lance(DIEGO, 400.0),
                        new Lance(new Usuario("Ana"), 300.0),
                        new Lance(DIEGO, 200.0))));
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebemosLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(DIEGO, 200.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances(){
        CONSOLE.propoe(new Lance(DIEGO, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        CONSOLE.propoe(new Lance(DIEGO, 300.0));
        final Usuario ANA = new Usuario("Ana");
        CONSOLE.propoe(new Lance(ANA, 400.0));
        CONSOLE.propoe(new Lance(DIEGO, 500.0));
        CONSOLE.propoe(new Lance(ANA, 600.0));

        final List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(DIEGO, 700.0));

        final List<Lance> tresMaioresLancesDevolvidosParaCincoLances =
                CONSOLE.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test (expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance(){
        CONSOLE.propoe(new Lance(DIEGO, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 400.0));
    }

    @Test (expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance(){
        CONSOLE.propoe(new Lance(DIEGO, 500.0));
        CONSOLE.propoe(new Lance(DIEGO, 600.0));
    }

    @Test (expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances(){
        CONSOLE.propoe(new Lance(DIEGO, 100.0));
        final Usuario ANA = new Usuario("Ana");
        CONSOLE.propoe(new Lance(ANA, 200.0));
        CONSOLE.propoe(new Lance(DIEGO, 300.0));
        CONSOLE.propoe(new Lance(ANA, 400.0));
        CONSOLE.propoe(new Lance(DIEGO, 500.0));
        CONSOLE.propoe(new Lance(ANA, 600.0));
        CONSOLE.propoe(new Lance(DIEGO, 700.0));
        CONSOLE.propoe(new Lance(ANA, 800.0));
        CONSOLE.propoe(new Lance(DIEGO, 900.0));
        CONSOLE.propoe(new Lance(ANA, 1000.0));
        CONSOLE.propoe(new Lance(DIEGO, 1100.0));
    }

}