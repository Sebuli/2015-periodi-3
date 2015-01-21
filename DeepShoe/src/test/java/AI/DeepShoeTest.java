/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import java.util.ArrayList;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sebbe
 */
public class DeepShoeTest {
    
    public Pelilauta pelilauta;
    public DeepShoe deepShoe;
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
        deepShoe = new DeepShoe();
        
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
        
        
        assertNotNull(deepShoe.bestMove("valkoinen", pelilauta.getRuudukko()));
        assertNotNull(deepShoe.bestMove("musta", pelilauta.getRuudukko()));
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
        assertNotNull(deepShoe.bestMove("musta", pelilauta.getRuudukko()));
    }
    
}
