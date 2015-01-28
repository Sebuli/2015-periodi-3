/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

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
        eval.evalNappuloidenArvot(pelilauta.getRuudukko());
    }

    

    

    /**
     * Test of evalSotilasSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalSotilasSijainti() {
        eval.evalSotilasSijainti(pelilauta.getRuudukko());

    }

    

    /**
     * Test of evalTorniSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalTorniSijainti() {
        eval.evalTorniSijainti(pelilauta.getRuudukko());
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

    
    
   

}
