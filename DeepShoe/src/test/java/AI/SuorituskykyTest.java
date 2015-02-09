/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sebbe
 */
public class SuorituskykyTest {
    
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
    
    
    @Test
    public void nopeusSyvyydellaYksiAlussa() {
        pelilauta.uusiPeli();
        long startTime = mx.getCurrentThreadCpuTime();

        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);

        long finishTime = mx.getCurrentThreadCpuTime();

        System.out.println("Yhden siirron haku syvyydellä 1 kun peli on vasta alkanut kesti: " + (finishTime - startTime) / 4000000 + " ms");
    }

    @Test
    public void nopeusSyvyydellaYksiEiAlussa() {
        pelilauta.uusiPeli();
        pelilauta.siirra(7, 3, 4, 6);
        pelilauta.siirra(7, 0, 4, 1);
        pelilauta.siirra(7, 1, 2, 1);
        pelilauta.siirra(6, 5, 4, 4);
        long startTime = mx.getCurrentThreadCpuTime();

        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 1);

        long finishTime = mx.getCurrentThreadCpuTime();

        System.out.println("Yhden siirron haku syvyydellä 1 kun pelissä on tehty jo siirtoja kesti: " + (finishTime - startTime) / 4000000 + " ms");
    }

    @Test
    public void nopeusSyvyydellaKaksiAlussa() {
        pelilauta.uusiPeli();
        long startTime = mx.getCurrentThreadCpuTime();
        
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);

        long finishTime = mx.getCurrentThreadCpuTime();

        System.out.println("Yhden siirron haku syvyydellä 2 kun peli on vasta alkanut kesti: " + (finishTime - startTime) / 4000000 + " ms");
    }

    @Test
    public void nopeusSyvyydellaKaksiEiAlussa() {
        pelilauta.uusiPeli();
        pelilauta.siirra(7, 3, 4, 6);
        pelilauta.siirra(7, 0, 4, 1);
        pelilauta.siirra(7, 1, 2, 1);
        pelilauta.siirra(6, 5, 4, 4);
        long startTime = mx.getCurrentThreadCpuTime();

        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 2);

        long finishTime = mx.getCurrentThreadCpuTime();

        System.out.println("Yhden siirron haku syvyydellä 2 kun pelissä on tehty jo siirtoja kesti: " + (finishTime - startTime) / 4000000 + " ms");
    }
    
    @Test
    public void nopeusSyvyydellaKolmeAlussa() {
        pelilauta.uusiPeli();
        long startTime = mx.getCurrentThreadCpuTime();
        
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);

        long finishTime = mx.getCurrentThreadCpuTime();

        System.out.println("Yhden siirron haku syvyydellä 3 kun peli on vasta alkanut kesti: " + (finishTime - startTime) / 4000000 + " ms");
    }

    @Test
    public void nopeusSyvyydellaKolmeEiAlussa() {
        pelilauta.uusiPeli();
        pelilauta.siirra(7, 3, 4, 6);
        pelilauta.siirra(7, 0, 4, 1);
        pelilauta.siirra(7, 1, 2, 1);
        pelilauta.siirra(6, 5, 4, 4);
        long startTime = mx.getCurrentThreadCpuTime();

        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);
        deepShoe.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, 3);

        long finishTime = mx.getCurrentThreadCpuTime();

        System.out.println("Yhden siirron haku syvyydellä 3 kun pelissä on tehty jo siirtoja kesti: " + (finishTime - startTime) / 4000000 + " ms");
    }
    
}
