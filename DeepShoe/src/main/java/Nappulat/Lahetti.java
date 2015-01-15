/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

import ChessMaster.Ruutu;
import java.util.ArrayList;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VLAHETTI tai MLAHETTI
 *
 * @author Sebbe
 */
public class Lahetti extends Nappula {

    public Lahetti(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VLAHETTI);
        } else {
            setTyyppi(Tyyppi.MLAHETTI);
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Lahetti
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista lahetille
     */
    @Override
    public ArrayList<String> kaikkiMahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                siirrot.add("" + (x + i) + (y + i));
                continue;
            }
            if (x + i <= 7 && y + i <= 7 && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                siirrot.add("" + (x + i) + (y + i));

            }
            break;
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                siirrot.add("" + (x - i) + (y - i));
                continue;
            }
            if (x - i >= 0 && y - i >= 0 && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                siirrot.add("" + (x - i) + (y - i));

            }
            break;
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                siirrot.add("" + (x - i) + (y + i));
                continue;
            }
            if (x - i >= 0 && y + i <= 7 && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                siirrot.add("" + (x - i) + (y + i));

            }
            break;

        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                siirrot.add("" + (x + i) + (y - i));
                continue;
            }
            if (x + i <= 7 && y - i >= 0 && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                siirrot.add("" + (x + i) + (y - i));

            }
            break;

        }
        return siirrot;
    }

}
