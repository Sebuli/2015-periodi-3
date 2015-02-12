/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import Nappulat.Nappula;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * DeepShoe hoitaa alpha-beta karsinnan.
 *
 * @author Sebbe
 */
public class DeepShoe {

    private Double beta;
    private Double alpha;
    private Double bestScore;
    private Evaluator evaluator;
    private String bestMove;
    private MoveCreator helper;

    public DeepShoe() {
        beta = Double.MAX_VALUE;
        alpha = Double.MIN_VALUE;
        helper = new MoveCreator();
        evaluator = new Evaluator();
        bestMove = "";
    }

    /**
     * bestMove palauttaa parhaan mahdollisen siirron. Se kutsuu alphabetamax, tai min, metodia kaikille siirroille ja
     * katsoo mika niista on paras.
     *
     * @param vari Vari jonka paras siirron me haluamme saada
     * @param pelilauta Pelilauta josta saamme siirron
     * @return Paras siirto
     */
    public String bestMove(String vari, Ruutu[][] ruudukko, Pelilauta pelilauta, int haluttuSyvyys) {

        Double max = -1. / 0.;
        Double min = 1. / 0.;
        int syvyys = haluttuSyvyys;
        Double score = 0.0;

        ArrayList<String> allMoves = helper.allMovesOrdered(ruudukko, pelilauta, vari);

        pelilauta.setRuudukko(ruudukko);

        if (allMoves.isEmpty()) {
            return "";
        }

        if (pelilauta.onkoToistanutSiirron(vari, ruudukko, pelilauta) && !pelilauta.onkoShakkiMatti(vari) && !pelilauta.onkoShakki(vari) && allMoves.size() > 1) {
            Random rand = new Random();
            int randomLuku = rand.nextInt(allMoves.size() - 1);
            return allMoves.get(randomLuku).substring(2, 4) + allMoves.get(randomLuku).substring(0, 2);
        }

        for (String move : allMoves) {

            int x = Integer.parseInt("" + move.charAt(2));
            int y = Integer.parseInt("" + move.charAt(3));
            int uusix = Integer.parseInt("" + move.charAt(0));
            int uusiy = Integer.parseInt("" + move.charAt(1));

            if (move.equals("") || ruudukko[x][y].getNappula() == null) {
                continue;
            }

            score = siirra(ruudukko, pelilauta, vari, syvyys, x, y, uusix, uusiy);

            if (max < score && vari.equals("valkoinen")) {
                bestMove = move.substring(2, 4) + move.substring(0, 2);
                max = score;
            } else if (min > score && vari.equals("musta")) {
                bestMove = move.substring(2, 4) + move.substring(0, 2);
                min = score;
            }
        }

        return bestMove;
    }

    /**
     * Metodi siirtaa nappulan ja kutsuu sen jalkeen alphbetaMax metodia ja
     * palauttaa parhaan siirron arvon ja siirtaa lopuksi nappulan takaisin
     *
     * @param ruudukko Ruudukko jossa kaikki nappulat ovat
     * @param pelilauta Pelilauta jossa tehdÃ¤Ã¤n siirto
     * @param vari Siirettavan nappulan vari
     * @param syvyys Syvyys jolla halutaan suorittaa alphabetaa
     * @param x Nappulan x sijainti
     * @param y Nappulan y sijainti
     * @param uusix x sijainti jonne nappula siirretaan
     * @param uusiy y sijainti jonne nappula siirretaan
     * @return Parhaan siirron arvo
     */
    private Double siirra(Ruutu[][] ruudukko, Pelilauta pelilauta, String vari, int syvyys, int x, int y, int uusix, int uusiy) {

        Double score = 0.0;


        if (ruudukko[uusix][uusiy].getNappula() == null) {
            pelilauta.siirra(x, y, uusix, uusiy);
            if (vari.equals("musta")) {
                score = alphaBetaMin(ruudukko, "musta", syvyys, beta, alpha);
            } else {
                score = alphaBetaMax(ruudukko, "valkoinen", syvyys, beta, alpha);
            }
            pelilauta.siirra(uusix, uusiy, x, y);

        } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
            Nappula nappula = ruudukko[uusix][uusiy].getNappula();
            pelilauta.siirra(x, y, uusix, uusiy);
            if (vari.equals("musta")) {
                score = alphaBetaMin(ruudukko, "musta", syvyys, beta, alpha);
            } else {
                score = alphaBetaMax(ruudukko, "valkoinen", syvyys, beta, alpha);
            }
            pelilauta.siirra(uusix, uusiy, x, y);
            ruudukko[uusix][uusiy].asetaNappula(nappula);
        }

        return score;
    }

    /**
     * Min yrittaa loytaa pienimman arvon. Kun syvyys on 0 evaluoidaan pelilauta ja palautetaan se arvo.
     * Muuten nappula siiirretaan ja kutsutaan alphabetamax metodia pienemmalla syvyydella.
     * @param pelilauta Pelilauta josta halutaan loytaa paras siirto
     * @param vari Vari jonka siirto halutaan
     * @param syvyys Tamanhetkinen syvyys
     * @param beta
     * @param alpha
     * @return Pelilaudan arvo
     */
    public Double alphaBetaMin(Ruutu[][] ruudukko, String vari, int syvyys, Double beta, Double alpha) {

        if (syvyys == 0) {
            return evaluator.eval(ruudukko);
        }

        Pelilauta pelilauta = new Pelilauta();
        pelilauta.setRuudukko(ruudukko);
        String toinenVari = "valkoinen";
        
        if (pelilauta.onkoShakkiMatti(vari)) {
            return -50000.0;
        } else if (pelilauta.onkoShakkiMatti(toinenVari)) {
            return 50000.0;
        }

        ArrayList<String> allMoves = helper.allMoves(ruudukko, pelilauta, vari);

        bestScore = Double.POSITIVE_INFINITY;

        for (String move : allMoves) {

            int x = Integer.parseInt("" + move.charAt(2));
            int y = Integer.parseInt("" + move.charAt(3));
            int uusix = Integer.parseInt("" + move.charAt(0));
            int uusiy = Integer.parseInt("" + move.charAt(1));

            
            if (move.equals("") || ruudukko[x][y].getNappula() == null) {
                continue;
            }
           
            if (ruudukko[uusix][uusiy].getNappula() == null) {
                pelilauta.siirra(x, y, uusix, uusiy);
                bestScore = Math.min(bestScore, alphaBetaMax(ruudukko, "valkoinen", syvyys - 1, beta, alpha));
                pelilauta.siirra(uusix, uusiy, x, y);

            } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
                Nappula nappula = ruudukko[uusix][uusiy].getNappula();
                pelilauta.siirra(x, y, uusix, uusiy);
                bestScore = Math.min(bestScore, alphaBetaMax(ruudukko, "valkoinen", syvyys - 1, beta, alpha));
                pelilauta.siirra(uusix, uusiy, x, y);
                ruudukko[uusix][uusiy].asetaNappula(nappula);
            }
            if (beta <= alpha) {
                break;
            }
            if (bestScore < beta) {
                beta = bestScore;
            }

        }

        return bestScore;

    }

    /**
     * Max yrittaa loytaa suurimman arvon. Kun syvyys on 0 evaluoidaan pelilauta ja palautetaan se arvo.
     * Muuten nappula siiirretaan ja kutsutaan alphabetamin metodia pienemmalla syvyydella.
     * @param pelilauta Pelilauta josta halutaan loytaa paras siirto
     * @param vari Vari jonka siirto halutaan
     * @param syvyys Tamanhetkinen syvyys
     * @param beta
     * @param alpha
     * @return Pelilaudan arvo
     */
    public Double alphaBetaMax(Ruutu[][] ruudukko, String vari, int syvyys, Double beta, Double alpha) {

        if (syvyys == 0) {
            return evaluator.eval(ruudukko);
        }

        Pelilauta pelilauta = new Pelilauta();
        pelilauta.setRuudukko(ruudukko);

        String toinenVari = "musta";
        

        if (pelilauta.onkoShakkiMatti(vari)) {
            return -50000.0;
        } else if (pelilauta.onkoShakkiMatti(toinenVari)) {
            return 50000.0;
        }
        ArrayList<String> allMoves = helper.allMoves(ruudukko, pelilauta, vari);

        bestScore = Double.NEGATIVE_INFINITY;

        for (String move : allMoves) {

            int x = Integer.parseInt("" + move.charAt(2));
            int y = Integer.parseInt("" + move.charAt(3));
            int uusix = Integer.parseInt("" + move.charAt(0));
            int uusiy = Integer.parseInt("" + move.charAt(1));

            if (move.equals("") || ruudukko[x][y].getNappula() == null) {
                continue;
            }
            if (ruudukko[uusix][uusiy].getNappula() == null) {
                pelilauta.siirra(x, y, uusix, uusiy);

                bestScore = Math.max(bestScore, alphaBetaMin(ruudukko, "musta", syvyys - 1, beta, alpha));

                pelilauta.siirra(uusix, uusiy, x, y);

            } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
                Nappula nappula = ruudukko[uusix][uusiy].getNappula();
                pelilauta.siirra(x, y, uusix, uusiy);

                bestScore = Math.max(bestScore, alphaBetaMin(ruudukko, "musta", syvyys - 1, beta, alpha));

                pelilauta.siirra(uusix, uusiy, x, y);
                ruudukko[uusix][uusiy].asetaNappula(nappula);
            }

            if (alpha >= beta) {
                break;
            }
            if (bestScore > alpha) {

                alpha = bestScore;
            }

        }
        return bestScore;
    }
}
