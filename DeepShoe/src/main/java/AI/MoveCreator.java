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
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * MoveCreator sisältää metodit joilla saa, ja laittaa järjestykseen kaikki
 * siirrot.
 *
 * @author Sebbe
 */
public class MoveCreator {

    private Evaluator evaluator;

    public MoveCreator() {
        evaluator = new Evaluator();
    }

    /**
     * allMoves hakee pelilaudasta kaikki tietyn varin siirrot jarjestettyna
     *
     * @param ruudukko Pelilauta josta siirrot otetaan
     * @param vari Vari jonka siirrot halutaan
     * @return Lista kaikista siirrosta
     */
    public ArrayList<String> allMovesOrdered(Ruutu[][] ruudukko, Pelilauta pelilauta, String vari) {

        TreeMap<String, Double> moves = new TreeMap<>();

        for (int i = 0; i < 8; i++) {
            for (int t = 0; t < 8; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getVari().equals(vari)) {

                    try {
                        for (String currentMove : ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko)) {

                            int x = Integer.parseInt("" + currentMove.charAt(2));
                            int y = Integer.parseInt("" + currentMove.charAt(3));
                            int uusix = Integer.parseInt("" + currentMove.charAt(0));
                            int uusiy = Integer.parseInt("" + currentMove.charAt(1));

                            Double arvo = evaluoi(pelilauta.getRuudukko(), pelilauta, vari, uusiy, x, y, uusix, uusiy);

                            moves.put(currentMove, arvo);
                        }
                    } catch (NullPointerException e) {

                    }
                }

            }
        }

        return new ArrayList<>(moves.keySet());
    }

    /**
     * allMoves hakee pelilaudasta kaikki tietyn varin siirrot
     *
     * @param ruudukko Pelilauta josta siirrot otetaan
     * @param vari Vari jonka siirrot halutaan
     * @return Lista kaikista siirrosta
     */
    public ArrayList<String> allMoves(Ruutu[][] ruudukko, Pelilauta pelilauta, String vari) {
        ArrayList<String> allMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int t = 0; t < 8; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getVari().equals(vari)) {
                    allMoves.addAll(ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko));
                }
            }
        }
        return allMoves;
    }

    /**
     * Metodi siirtaa nappulan ja evaluoi pelilaudan arvon ja siirtaa lopuksi
     * nappulan takaisin
     *
     * @param ruudukko Ruudukko jossa kaikki nappulat ovat
     * @param ruudukko Pelilauta jossa tehdään siirto
     * @param vari Siirettavan nappulan vari
     * @param syvyys Syvyys jolla halutaan suorittaa alphabetaa
     * @param x Nappulan x sijainti
     * @param y Nappulan y sijainti
     * @param uusix x sijainti jonne nappula siirretaan
     * @param uusiy y sijainti jonne nappula siirretaan
     * @return Parhaan siirron arvo
     */
    public Double evaluoi(Ruutu[][] ruudukko, Pelilauta pelilauta, String vari, int syvyys, int x, int y, int uusix, int uusiy) {

        Double score = 0.0;

        if (ruudukko[uusix][uusiy].getNappula() == null) {
            pelilauta.siirra(x, y, uusix, uusiy);
            if (vari.equals("musta")) {
                score = -evaluator.eval(pelilauta.getRuudukko());
            } else {
                score = evaluator.eval(pelilauta.getRuudukko());
            }
            pelilauta.siirra(uusix, uusiy, x, y);

        } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
            Nappula nappula = ruudukko[uusix][uusiy].getNappula();
            pelilauta.siirra(x, y, uusix, uusiy);
            if (vari.equals("musta")) {
                score = -evaluator.eval(pelilauta.getRuudukko());
            } else {
                score = evaluator.eval(pelilauta.getRuudukko());
            }
            pelilauta.siirra(uusix, uusiy, x, y);
            ruudukko[uusix][uusiy].asetaNappula(nappula);
        }

        return score;
    }
}
