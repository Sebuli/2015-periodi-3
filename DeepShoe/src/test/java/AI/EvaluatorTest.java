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
     * Test of evalSotilasSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalSotilasSijainti() {
        pelilauta.uusiPeli();
        pelilauta.siirra(6, 1, 5, 1);
        pelilauta.siirra(1, 1, 2, 1);
        pelilauta.siirra(6, 6, 5, 5);
        pelilauta.siirra(1, 6, 2, 5);
        pelilauta.siirra(0, 2, 2, 2);
        int arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        
        int odotettu = 1;
        
        assertEquals(odotettu, arvo);

    }

    /**
     * Test of evalTorniSijainti method, of class Evaluator.
     */
    @Test
    public void testEvalTorniSijainti() {
        int arvo = eval.evalTorniSijainti(pelilauta.getRuudukko());
        
        int odotettu = 0;
        
        assertEquals(odotettu, arvo);
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
        int arvo = eval.evalKuningatarSijainti(pelilauta.getRuudukko());
        
        int odotettu = 0;
        
        assertEquals(odotettu, arvo);
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
        pelilauta.siirra(0, 4, 7, 0);
        
        int arvo = eval.evalKuningasSijainti(pelilauta.getRuudukko());
        int odotettu = 30;
        assertEquals(odotettu, arvo);
        
        pelilauta.setEndGame(true);
        pelilauta.siirra(7, 4, 3, 7);
        arvo = eval.evalKuningasSijainti(pelilauta.getRuudukko());
        odotettu = 0;
        assertEquals(odotettu, arvo);
    }

    @Test
    public void testEvalRatsuSijainti() {
        pelilauta.uusiPeli();
        int arvo = eval.evalRatsuSijainti(pelilauta.getRuudukko());
        int odotettu = 0;
        assertEquals(odotettu, arvo);
        
        pelilauta.setEndGame(true);
        pelilauta.siirra(7, 1, 4, 3);
        arvo = eval.evalRatsuSijainti(pelilauta.getRuudukko());
        odotettu = 60;
        assertEquals(odotettu, arvo);
    }
    
    @Test
    public void testEvalLahettiSijainti() {
        pelilauta.uusiPeli();
        int arvo = eval.evalLahettiSijainti(pelilauta.getRuudukko());
        int odotettu = 0;
        assertEquals(odotettu, arvo);
        
        pelilauta.setEndGame(true);
        pelilauta.siirra(0, 2, 4, 5);
        arvo = eval.evalLahettiSijainti(pelilauta.getRuudukko());
        odotettu = -45;
        assertEquals(odotettu, arvo);
    }
    
    @Test
    public void evalPalauttaaOikein(){
        Double value = 0.0;
        pelilauta.siirra(7, 4, 4, 5);
        pelilauta.siirra(7, 3, 4, 0);
        pelilauta.siirra(7, 1, 4, 7);
        pelilauta.siirra(6, 6, 4, 6);
        pelilauta.siirra(7, 7, 3, 7);
        value += eval.evalKuningatarSijainti(pelilauta.getRuudukko());
        value += eval.evalKuningasSijainti(pelilauta.getRuudukko());
        value += eval.evalLahettiSijainti(pelilauta.getRuudukko());
        value += eval.evalRatsuSijainti(pelilauta.getRuudukko());
        value += eval.evalSotilasSijainti(pelilauta.getRuudukko());
        value += eval.evalTorniSijainti(pelilauta.getRuudukko());
        
        Double toinen = eval.eval(pelilauta.getRuudukko());
        
        assertEquals(value, toinen);
    }
    
    @Test
    public void palauttaaOikeinKunShakkiMattiValkoinen(){
        pelilauta.siirra(0, 3, 6, 3);
        pelilauta.siirra(0, 0, 7, 3);
        Double value = eval.eval(pelilauta.getRuudukko());
        Double expected = -50000.0;
        assertEquals(expected, value);
    }
    
    @Test
    public void palauttaaOikeinKunShakkiMattiMusta(){
        pelilauta.siirra(7, 3, 1, 3);
        pelilauta.siirra(7, 0, 0, 3);
        Double value = eval.eval(pelilauta.getRuudukko());
        Double expected = 50000.0;
        assertEquals(expected, value);
    }
    
    @Test
    public void passedSoldierValkoinen(){
        pelilauta.poistaNappula(1, 2);
        int arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        int odotettu = 110;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(6, 2, 5, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = 120;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(5, 2, 4, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = 100;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(4, 2, 3, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = 110;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(3, 2, 2, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = 120;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(0, 2, 1, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = 132;
        assertEquals(odotettu, arvo);
    }
    @Test
    public void passedSoldierMusta(){
        pelilauta.poistaNappula(6, 2);
        int arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        int odotettu = -110;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(1, 2, 2, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = -120;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(2, 2, 3, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = -100;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(3, 2, 4, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = -110;
        assertEquals(odotettu, arvo);
        pelilauta.siirra(4, 2, 5, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = -120;
        assertEquals(odotettu, arvo);
        
        pelilauta.siirra(7, 2, 6, 2);
        arvo = eval.evalSotilasSijainti(pelilauta.getRuudukko());
        odotettu = -120;
        assertEquals(odotettu, arvo);
    }

}
