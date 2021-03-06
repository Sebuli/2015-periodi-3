/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa DeepShoe luokkaa
 * @author Sebbe
 */
public class DeepShoeTest {

    public Pelilauta pelilauta;
    public DeepShoe deepShoe;
    public Evaluator eval;
    private ThreadMXBean mx;

    @Before
    public void setUp() {
        mx = ManagementFactory.getThreadMXBean();
        pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
        deepShoe = new DeepShoe();
        eval = new Evaluator();

        pelilauta.siirra(1, 1, 4, 1);
        pelilauta.siirra(6, 2, 4, 2);
        pelilauta.getNappula(4, 2).asetaSiirtojenMaaraa(2);
        pelilauta.siirra(6, 3, 2, 3);
        pelilauta.siirra(6, 7, 3, 7);
        pelilauta.siirra(1, 6, 3, 6);
        pelilauta.getNappula(3, 7).asetaSiirtojenMaaraa(2);

    }

    /**
     * Test of bestMove method, of class DeepShoe.
     */
    @Test
    public void testBestMove() {
        assertNotNull(deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 0));
        assertNotNull(deepShoe.bestMove("musta", pelilauta.getRuudukko(), pelilauta, 0));
    }

    /**
     * Test of alphaBetaMin method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMinMusta() {
        assertNotNull(deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 0, Double.NaN, Double.NaN));
    }

    /**
     * Test of alphaBetaMin method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMinValkoinen() {
        assertNotNull(deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "valkoinen", 0, Double.NaN, Double.NaN));
    }

    /**
     * Test of alphaBetaMax method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMaxMusta() {
        assertNotNull(deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "musta", 0, Double.NaN, Double.NaN));
    }

    /**
     * Test of alphaBetaMax method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMaxValkoinen() {
        assertNotNull(deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 0, Double.NaN, Double.NaN));
    }

    @Test
    public void testAlphaBetaMinMustaOne() {
        assertNotNull(deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 1, Double.MAX_VALUE, Double.MIN_VALUE));
    }

    /**
     * Test of alphaBetaMin method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMinValkoinenOne() {
        assertNotNull(deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "valkoinen", 1, Double.MAX_VALUE, Double.MIN_VALUE));
    }

    /**
     * Test of alphaBetaMax method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMaxMustaOne() {
        assertNotNull(deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "musta", 1, Double.MAX_VALUE, Double.MIN_VALUE));
    }

    /**
     * Test of alphaBetaMax method, of class DeepShoe.
     */
    @Test
    public void testAlphaBetaMaxValkoinenOne() {
        assertNotNull(deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, Double.MAX_VALUE, Double.MIN_VALUE));
    }

    /**
     * Test of allMoves method, of class DeepShoe.
     */
    @Test
    public void testAllMoves() {
        assertNotNull(deepShoe.bestMove("musta", pelilauta.getRuudukko(), pelilauta, 0));
    }

    @Test
    public void testAlphaBetaKunShakkiMatti() {
        pelilauta.uusiPeli();
        pelilauta.siirra(6, 5, 1, 5);
        pelilauta.siirra(7, 3, 0, 3);
        pelilauta.siirra(0, 7, 7, 3);
        pelilauta.siirra(0, 0, 6, 3);
        assertNotNull(deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, Double.MAX_VALUE, Double.MIN_VALUE));
        assertNotNull(deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "valkoinen", 1, Double.MAX_VALUE, Double.MIN_VALUE));
        assertNotNull(deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "musta", 1, Double.MAX_VALUE, Double.MIN_VALUE));
        assertNotNull(deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 1, Double.MAX_VALUE, Double.MIN_VALUE));
    }

    @Test
    public void kutsuukoRandomSiirtoaKunSamaaSiirtoaOnToistettu() {
        pelilauta.uusiPeli();
        pelilauta.siirra(0, 0, 2, 0);
        pelilauta.asetaViimeSiirto(0, 0, 2, 0, "musta");
        pelilauta.siirra(2, 0, 0, 0);
        pelilauta.asetaViimeSiirto(2, 0, 0, 0, "musta");
        pelilauta.siirra(0, 0, 2, 0);
        pelilauta.asetaViimeSiirto(0, 0, 2, 0, "musta");
        pelilauta.siirra(2, 0, 0, 0);
        pelilauta.asetaViimeSiirto(2, 0, 0, 0, "musta");
        Pelilauta uusilauta = new Pelilauta();
        uusilauta.uusiPeli();
        assertFalse(deepShoe.bestMove("musta", uusilauta.getRuudukko(), uusilauta, 1).equals(deepShoe.bestMove("musta", pelilauta.getRuudukko(), pelilauta, 1)));
    }

    @Test
    public void alphaBetaMinPalauttaaOikeinKunShakkiMatti() {
        pelilauta.siirra(0, 3, 6, 3);
        pelilauta.siirra(0, 0, 7, 3);
        Double value = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "valkoinen", 0, Double.NaN, Double.NaN);
        Double expected = -50000.0;
        assertEquals(expected, value);

        pelilauta.uusiPeli();

        pelilauta.siirra(7, 3, 1, 3);
        pelilauta.siirra(7, 0, 0, 3);
        value = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "valkoinen", 0, Double.NaN, Double.NaN);
        expected = 50000.0;
        assertEquals(expected, value);
    }

    @Test
    public void alphaBetaMaxPalauttaaOikeinKunShakkiMatti() {
        pelilauta.siirra(0, 3, 6, 3);
        pelilauta.siirra(0, 0, 7, 3);
        Double value = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, Double.NaN, Double.NaN);
        Double expected = -50000.0;
        assertEquals(expected, value);

        pelilauta.uusiPeli();

        pelilauta.siirra(7, 3, 1, 3);
        pelilauta.siirra(7, 0, 0, 3);
        value = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, Double.NaN, Double.NaN);
        expected = 50000.0;
        assertEquals(expected, value);
    }

    @Test
    public void siirtaaOikein() {
        pelilauta.uusiPeli();
        pelilauta.siirra(0, 3, 5, 3);
        pelilauta.siirra(7, 3, 2, 3);
        Double expected = -16.0;

        Double value = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 1, 0.0, 0.0);
        assertEquals(expected, value);
        value = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, 0.0, 0.0);
        expected = -6.0;
        assertEquals(expected, value);

        value = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 1, 0.0, 0.0);
        expected = -16.0;
        assertEquals(expected, value);

        value = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, 0.0, 0.0);
        expected = -6.0;
        assertEquals(expected, value);
    }
    
    @Test
    public void palauttaakoParemmanTaiSamanKunIsompiSyvyysValkoinen(){
        Double whiteValueThree = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 3, Double.MAX_VALUE, Double.MIN_VALUE);
        Double whiteValueTwo = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 2, Double.MAX_VALUE, Double.MIN_VALUE);
        Double whiteValueOne = deepShoe.alphaBetaMax(pelilauta.getRuudukko(), "valkoinen", 1, Double.MAX_VALUE, Double.MIN_VALUE);
        System.out.println(whiteValueThree);
        System.out.println(whiteValueTwo);
        System.out.println(whiteValueOne);
        assertTrue(whiteValueThree >= whiteValueTwo && whiteValueTwo >= whiteValueOne);
    }
    
    @Test
    public void palauttaakoParemmanTaiSamanKunIsompiSyvyysMusta(){
        Double blackValueThree = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 3, Double.MAX_VALUE, Double.MIN_VALUE);
        Double blackValueTwo = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 2, Double.MAX_VALUE, Double.MIN_VALUE);
        Double blackValueOne = deepShoe.alphaBetaMin(pelilauta.getRuudukko(), "musta", 1, Double.MAX_VALUE, Double.MIN_VALUE);
        System.out.println(blackValueThree);
        System.out.println(blackValueTwo);
        System.out.println(blackValueOne);
        assertTrue(blackValueThree >= blackValueTwo && blackValueTwo >= blackValueOne);
    }

    

}
