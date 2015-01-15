/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

import ChessMaster.Ruutu;
import java.util.ArrayList;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VKUNINGAS tai MKUNINGAS
 *
 * @author Sebbe
 */
public class Kuningas extends Nappula {

    public Kuningas(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VKUNINGAS);
        } else {
            setTyyppi(Tyyppi.MKUNINGAS);
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Kuningas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista kuninkaalle
     */
    @Override
    public ArrayList<String> kaikkiMahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {

        ArrayList<String> siirrot = new ArrayList<>();

        if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y].getNappula()))) {
            siirrot.add("" + (x + 1) + (y));
        }
        if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y + 1].getNappula()))) {
            siirrot.add("" + (x) + (y + 1));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula()))) {
            siirrot.add("" + (x + 1) + (y + 1));
        }
        if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y].getNappula()))) {
            siirrot.add("" + (x - 1) + (y));
        }
        if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y - 1].getNappula()))) {
            siirrot.add("" + (x) + (y - 1));
        }
        if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula()))) {
            siirrot.add("" + (x - 1) + (y - 1));
        }
        if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula()))) {
            siirrot.add("" + (x + 1) + (y - 1));
        }
        if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula()))) {
            siirrot.add("" + (x - 1) + (y + 1));
        }

        return siirrot;

    }

}
