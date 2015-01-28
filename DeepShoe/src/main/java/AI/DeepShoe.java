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
    

    /**
     *
     */
    public DeepShoe() {
        beta = Double.MAX_VALUE;
        alpha = Double.MIN_VALUE;

        evaluator = new Evaluator();
        bestMove = "";
    }

    /**
     * bestMove palauttaa parhaan mahdollisen siirron
     *
     * @param vari Vari jonka paras siirron me haluamme saada
     * @param pelilauta Pelilauta josta saamme siirron
     * @return Paras siirto
     */
    public String bestMove(String vari, Ruutu[][] ruudukko) {

        Double max = -1. / 0.;
        Double min = 1. / 0.;
        int syvyys = 3;
        Double score = 0.0;

        ArrayList<String> allMoves = allMoves(ruudukko, vari);

        Pelilauta pelilauta = new Pelilauta();
        pelilauta.setRuudukko(ruudukko);

        if (allMoves.isEmpty()) {
            return "";
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
     * Metodi siirtaa nappulan ja kutsuu sen jälkeen alphbetaMax metodia ja palauttaa parhaan siirron arvon
     * ja siirtaa lopuksi nappulan takaisin
     * @param ruudukko Ruudukko jossa kaikki nappulat ovat
     * @param pelilauta Pelilauta jossa tehdään siirto
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
                score = alphaBetaMax(ruudukko, "valkoinen", syvyys, beta, alpha);
            } else {
                score = alphaBetaMax(ruudukko, "musta", syvyys, beta, alpha);
            }
            pelilauta.siirra(uusix, uusiy, x, y);

        } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
            Nappula nappula = ruudukko[uusix][uusiy].getNappula();
            pelilauta.siirra(x, y, uusix, uusiy);
            if (vari.equals("musta")) {
                score = alphaBetaMax(ruudukko, "valkoinen", syvyys, beta, alpha);
            } else {
                score = alphaBetaMax(ruudukko, "musta", syvyys, beta, alpha);
            }
            pelilauta.siirra(uusix, uusiy, x, y);
            ruudukko[uusix][uusiy].asetaNappula(nappula);
        }
        
        return score;
    }

    /**
     *
     * @param pelilauta Pelilauta josta halutaan lÃ¶ytÃ¤Ã¤ paras siirto
     * @param vari Vari jonka siirto halutaan
     * @param syvyys Tamanhetkinen syvyys
     * @param beta
     * @param alpha
     * @return Pelilaudan arvo
     */
    public Double alphaBetaMin(Ruutu[][] pelilauta, String vari, int syvyys, Double beta, Double alpha) {

        if (syvyys == 0) {
            return -evaluator.eval(pelilauta);
        }
        ArrayList<String> allMoves = allMoves(pelilauta, vari);

        Pelilauta kopioLauta = new Pelilauta();
        kopioLauta.setRuudukko(pelilauta);

        bestScore = Double.MAX_VALUE;
        String toinenVari = "";
        if (vari.equals("musta")) {
            toinenVari = "valkoinen";
        } else {
            toinenVari = "musta";
        }

        if (kopioLauta.onkoShakkiMatti(vari)) {
            return -50000.0;
        } else if (kopioLauta.onkoShakkiMatti(toinenVari)) {
            return 50000.0;
        }

        for (String move : allMoves) {

            int x = Integer.parseInt("" + move.charAt(2));
            int y = Integer.parseInt("" + move.charAt(3));
            int uusix = Integer.parseInt("" + move.charAt(0));
            int uusiy = Integer.parseInt("" + move.charAt(1));
            
            if (move.equals("") || pelilauta[x][y].getNappula() == null) {
                continue;
            }

            if (pelilauta[uusix][uusiy].getNappula() == null) {
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (vari.equals("musta")) {
                    bestScore = Math.min(bestScore, alphaBetaMax(pelilauta, "valkoinen", syvyys - 1, beta, alpha));
                } else {
                    bestScore = Math.min(bestScore, alphaBetaMax(pelilauta, "musta", syvyys - 1, beta, alpha));
                }
                kopioLauta.siirra(uusix, uusiy, x, y);

            } else if (!pelilauta[uusix][uusiy].getNappula().onkoSamaVari(pelilauta[x][y].getNappula())) {
                Nappula nappula = pelilauta[uusix][uusiy].getNappula();
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (vari.equals("musta")) {
                    bestScore = Math.min(bestScore, alphaBetaMax(pelilauta, "valkoinen", syvyys - 1, beta, alpha));
                } else {
                    bestScore = Math.min(bestScore, alphaBetaMax(pelilauta, "musta", syvyys - 1, beta, alpha));
                }
                kopioLauta.siirra(uusix, uusiy, x, y);
                pelilauta[uusix][uusiy].asetaNappula(nappula);
            }
            if (bestScore <= alpha) {
                break;
            }
            if (bestScore < beta) {

                beta = bestScore;
            }

        }

        return bestScore;

    }
    
  

    /**
     *
     * @param pelilauta Pelilauta josta halutaan lÃ¶ytÃ¤Ã¤ paras siirto
     * @param vari Vari jonka siirto halutaan
     * @param syvyys Tamanhetkinen syvyys
     * @param beta
     * @param alpha
     * @return Pelilaudan arvo
     * @return
     */
    public Double alphaBetaMax(Ruutu[][] pelilauta, String vari, int syvyys, Double beta, Double alpha) {

        if (syvyys == 0) {

            return evaluator.eval(pelilauta);
        }

        ArrayList<String> allMoves = allMoves(pelilauta, vari);

        Pelilauta kopioLauta = new Pelilauta();
        kopioLauta.setRuudukko(pelilauta);

        bestScore = Double.MIN_VALUE;
        String toinenVari = "";
        if (vari.equals("musta")) {
            toinenVari = "valkoinen";
        } else {
            toinenVari = "musta";
        }

        if (kopioLauta.onkoShakkiMatti(vari)) {
            return -50000.0;
        } else if (kopioLauta.onkoShakkiMatti(toinenVari)) {
            return 50000.0;
        }

        for (String move : allMoves) {

            int x = Integer.parseInt("" + move.charAt(2));
            int y = Integer.parseInt("" + move.charAt(3));
            int uusix = Integer.parseInt("" + move.charAt(0));
            int uusiy = Integer.parseInt("" + move.charAt(1));

            if (move.equals("") || pelilauta[x][y].getNappula() == null) {
                continue;
            }

            if (pelilauta[uusix][uusiy].getNappula() == null) {
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (vari.equals("musta")) {
                    bestScore = Math.max(bestScore, alphaBetaMin(pelilauta, "valkoinen", syvyys - 1, beta, alpha));
                } else {
                    bestScore = Math.max(bestScore, alphaBetaMin(pelilauta, "musta", syvyys - 1, beta, alpha));
                }
                kopioLauta.siirra(uusix, uusiy, x, y);

            } else if (!pelilauta[uusix][uusiy].getNappula().onkoSamaVari(pelilauta[x][y].getNappula())) {
                Nappula nappula = pelilauta[uusix][uusiy].getNappula();
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (vari.equals("musta")) {
                    bestScore = Math.max(bestScore, alphaBetaMin(pelilauta, "valkoinen", syvyys - 1, beta, alpha));
                } else {
                    bestScore = Math.max(bestScore, alphaBetaMin(pelilauta, "musta", syvyys - 1, beta, alpha));
                }
                kopioLauta.siirra(uusix, uusiy, x, y);
                pelilauta[uusix][uusiy].asetaNappula(nappula);
            }

            if (bestScore >= beta) {
                break;
            }
            if (bestScore > alpha) {

                alpha = bestScore;
            }

        }
        return bestScore;
    }

    /**
     * allMoves hakee pelilaudasta kaikki tietyn varin siirrot
     *
     * @param pelilauta Pelilauta josta siirrot otetaan
     * @param vari Vari jonka siirrot halutaan
     * @return Lista kaikista siirrosta
     */
    public ArrayList<String> allMoves(Ruutu[][] pelilauta, String vari) {
        ArrayList<String> allMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int t = 0; t < 8; t++) {
                if (pelilauta[i][t].getNappula() != null && pelilauta[i][t].getNappula().getVari().equals(vari)) {
                    allMoves.addAll(pelilauta[i][t].getNappula().mahdollisetSiirrot(i, t, pelilauta));
                }
            }
        }
        return allMoves;
    }

}
