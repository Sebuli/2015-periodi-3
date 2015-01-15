/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

import ChessMaster.Ruutu;
import java.util.ArrayList;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VRATSU tai MRATSU
 *
 * @author Sebbe
 */
public class Ratsu extends Nappula {

    public Ratsu(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VRATSU);
        } else {
            setTyyppi(Tyyppi.MRATSU);
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Ratsu
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista ratsulle
     */
    @Override
    public ArrayList<String> kaikkiMahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        if (x - 1 >= 0 && y - 2 >= 0 && (ruudukko[x - 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 2].getNappula()))) {
            siirrot.add("" + (x - 1) + (y - 2));
        }
        if (x - 2 >= 0 && y - 1 >= 0 && (ruudukko[x - 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y - 1].getNappula()))) {
            siirrot.add("" + (x - 2) + (y - 1));
        }
        if (x - 1 >= 0 && y + 2 <= 7 && (ruudukko[x - 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 2].getNappula()))) {
            siirrot.add("" + (x - 1) + (y + 2));
        }
        if (x - 2 >= 0 && y + 1 <= 7 && (ruudukko[x - 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y + 1].getNappula()))) {
            siirrot.add("" + (x - 2) + (y + 1));
        }
        if (x + 1 <= 7 && y - 2 >= 0 && (ruudukko[x + 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 2].getNappula()))) {
            siirrot.add("" + (x + 1) + (y - 2));
        }
        if (x + 2 <= 7 && y - 1 >= 0 && (ruudukko[x + 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y - 1].getNappula()))) {
            siirrot.add("" + (x + 2) + (y - 1));
        }
        if (x + 1 <= 7 && y + 2 <= 7 && (ruudukko[x + 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 2].getNappula()))) {
            siirrot.add("" + (x + 1) + (y + 2));
        }
        if (x + 2 <= 7 && y + 1 <= 7 && (ruudukko[x + 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y + 1].getNappula()))) {
            siirrot.add("" + (x + 2) + (y + 1));
        }
        return siirrot;
    }

}
