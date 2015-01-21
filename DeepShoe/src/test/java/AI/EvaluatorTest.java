/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebbe
 */
public class EvaluatorTest {

    private Pelilauta pelilauta;
    private Evaluator eval;

    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
        eval = new Evaluator();
    }

    /**
     * Test of eval method, of class Evaluator.
     */
    @Test
    public void testEval() {
        Double arvo = eval.eval(pelilauta.getRuudukko());
        assertNotNull(arvo);
    }

    /**
     * Test of evalNappuloidenArvot method, of class Evaluator.
     */
    @Test
    public void testEvalNappuloidenArvot() {
        pelilauta.siirra(6, 1, 5, 1);
        pelilauta.siirra(1, 1, 2, 1);
        eval.evalNappuloidenArvot(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

    /**
     * Test of evalSotilasSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalSotilasSijaintiValkoinen() {
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(6, 0, 1, 0);
        pelilauta.siirra(6, 1, 1, 1);
        pelilauta.siirra(6, 2, 1, 2);
        pelilauta.siirra(6, 3, 1, 3);
        pelilauta.siirra(6, 4, 1, 4);
        pelilauta.siirra(6, 5, 1, 5);
        pelilauta.siirra(6, 6, 0, 0);
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
        pelilauta.siirra(6, 0, 2, 0);
        pelilauta.siirra(6, 1, 2, 1);
        pelilauta.siirra(6, 2, 2, 2);
        pelilauta.siirra(6, 3, 2, 3);
        pelilauta.siirra(6, 4, 2, 4);
        pelilauta.siirra(6, 5, 2, 5);
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
        pelilauta.siirra(6, 0, 3, 0);
        pelilauta.siirra(6, 1, 3, 1);
        pelilauta.siirra(6, 2, 3, 2);
        pelilauta.siirra(6, 3, 3, 3);
        pelilauta.siirra(6, 4, 3, 4);
        pelilauta.siirra(6, 5, 3, 5);
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
        pelilauta.siirra(6, 0, 4, 0);
        pelilauta.siirra(6, 1, 4, 1);
        pelilauta.siirra(6, 2, 4, 2);
        pelilauta.siirra(6, 3, 4, 3);
        pelilauta.siirra(6, 4, 4, 4);
        pelilauta.siirra(6, 5, 4, 5);
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();

    }

    /**
     * Test of evalSotilasSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalSotilasSijaintiMusta() {
        pelilauta.siirra(6, 1, 5, 1);
        pelilauta.siirra(6, 2, 4, 2);
        pelilauta.siirra(1, 1, 2, 1);
        pelilauta.siirra(1, 2, 3, 1);
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
        pelilauta.siirra(1, 0, 4, 0);
        pelilauta.siirra(1, 2, 4, 2);
        pelilauta.siirra(1, 3, 4, 3);
        pelilauta.siirra(1, 4, 4, 4);
        pelilauta.siirra(1, 5, 4, 5);
        eval.evalSotilasSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();

    }

    /**
     * Test of evalRatsuSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalRatsuSijaintiValkoinen() {
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(7, 6, 0, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(0, 0, 0, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(0, 5, 1, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 0, 1, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 1, 1, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 2, 1, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 3, 1, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 4, 1, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 5, 2, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 0, 2, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 1, 2, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 2, 2, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 3, 2, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 4, 2, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 5, 3, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 0, 3, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 1, 3, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 2, 3, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 3, 3, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 4, 3, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 5, 4, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 0, 4, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 1, 4, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 2, 4, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 3, 4, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 4, 4, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();

    }

    /**
     * Test of evalRatsuSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalRatsuSijaintiMusta() {
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(0, 6, 1, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 0, 1, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 1, 1, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 2, 1, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 3, 1, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 4, 1, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 5, 2, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 0, 2, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 1, 2, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 2, 2, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 3, 2, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 4, 2, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 5, 3, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 0, 3, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 1, 3, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 2, 3, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 3, 3, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 4, 3, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 5, 4, 0);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 0, 4, 1);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 1, 4, 2);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 2, 4, 3);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 3, 4, 4);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 4, 4, 5);
        eval.evalRatsuSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

    /**
     * Test of evalTorniSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalTorniSijainti() {
        pelilauta.siirra(0, 7, 4, 0);
        eval.evalTorniSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

    /**
     * Test of evalLahettiSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalLahettiSijaintiValkoinen() {
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(7, 5, 0, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(0, 5, 1, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 0, 1, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 1, 1, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 2, 1, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 3, 1, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 4, 1, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());

        pelilauta.siirra(1, 5, 2, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 0, 2, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 1, 2, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 2, 2, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 3, 2, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 4, 2, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 5, 3, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 0, 3, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 1, 3, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 2, 3, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 3, 3, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 4, 3, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 5, 4, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 0, 4, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 1, 4, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 2, 4, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 3, 4, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 4, 4, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 5, 5, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(5, 0, 5, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(5, 1, 5, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(5, 2, 5, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(5, 3, 5, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(5, 4, 5, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

    /**
     * Test of evalLahettiSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalLahettiSijaintiMusta() {

        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(0, 5, 1, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 0, 1, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(1, 5, 2, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 0, 2, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 1, 2, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 2, 2, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 3, 2, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 4, 2, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(2, 5, 3, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 0, 3, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 1, 3, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 2, 3, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 3, 3, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 4, 3, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 5, 4, 0);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 0, 4, 1);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 1, 4, 2);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 2, 4, 3);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 3, 4, 4);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(4, 4, 4, 5);
        eval.evalLahettiSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

    /**
     * Test of evalKuningatarSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalKuningatarSijainti() {
        eval.evalKuningatarSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(7, 3, 2, 3);
        pelilauta.siirra(0, 3, 3, 3);
        eval.evalKuningatarSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(3, 3, 4, 0);
        eval.evalKuningatarSijainti(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

    /**
     * Test of evalKuningasSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalKuningasSijainti() {
        eval.evalKuningasSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(7, 4, 0, 0);
        pelilauta.siirra(0, 4, 5, 0);
        eval.evalKuningasSijainti(pelilauta.getRuudukko());
        pelilauta.siirra(0, 0, 3, 0);
        pelilauta.siirra(5, 0, 4, 0);
        eval.evalKuningasSijainti(pelilauta.getRuudukko());

        pelilauta.uusiPeli();
    }

    /**
     * Test of evalOnkoShakki method, of class Evaluator.
     */
    @Test
    public void testEvalOnkoShakki() {
        pelilauta.siirra(6, 2, 1, 2);
        pelilauta.siirra(1, 4, 6, 4);
        eval.evalOnkoShakki(pelilauta.getRuudukko());
        pelilauta.uusiPeli();
    }

}
