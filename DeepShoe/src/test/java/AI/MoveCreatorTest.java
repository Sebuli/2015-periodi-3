/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebbe
 */
public class MoveCreatorTest {
    
    private Pelilauta pelilauta;
    private MoveCreator creator;
    private Evaluator eval;
    
    @Before
    public void setUp() {
        creator = new MoveCreator();
        eval = new Evaluator();
        pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
    }

    /**
     * Test of allMovesOrdered method, of class MoveCreator.
     */
    @Test
    public void testAllMovesOrdered() {
        String musta = "musta";
        String valkoinen = "valkoinen";
        ArrayList<String> resultMusta = creator.allMovesOrdered(pelilauta.getRuudukko(), pelilauta, musta);
        assertNotNull(resultMusta);
        ArrayList<String> resultValkoinen = creator.allMovesOrdered(pelilauta.getRuudukko(), pelilauta, valkoinen);
        assertNotNull(resultValkoinen);
    }

    /**
     * Test of allMoves method, of class MoveCreator.
     */
    @Test
    public void testAllMoves() {
        String musta = "musta";
        String valkoinen = "valkoinen";
        MoveCreator instance = new MoveCreator();
        ArrayList<String> resultMusta = instance.allMoves(pelilauta.getRuudukko(), pelilauta, musta);
        assertNotNull(resultMusta);
        ArrayList<String> resultValkoinen = instance.allMoves(pelilauta.getRuudukko(), pelilauta, valkoinen);
        assertNotNull(resultValkoinen);
    }

    @Test
    public void evaluoiSiirtaaOikeinTyhjaan() {
        String musta = "musta";
        String valkoinen = "valkoinen";
        Double eka = creator.evaluoi(pelilauta.getRuudukko(), pelilauta, musta, 0, 0, 0, 4, 3);
        pelilauta.siirra(0, 0, 4, 3);
        Double toka = -eval.eval(pelilauta.getRuudukko());
        assertEquals(eka, toka);
    }
    
    @Test
    public void evaluoiSiirtaaOikeinEiTyhjaan() {
        String musta = "musta";
        String valkoinen = "valkoinen";
        Double eka = creator.evaluoi(pelilauta.getRuudukko(), pelilauta, musta, 0, 0, 0, 6, 1);
        pelilauta.siirra(0, 0, 6, 1);
        Double toka = -eval.eval(pelilauta.getRuudukko());
        assertEquals(eka, toka);
    }

}
