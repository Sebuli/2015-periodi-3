/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

import ChessMaster.Ruutu;
import java.util.ArrayList;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VTORNI tai MTORNI
 *
 * @author Sebbe
 */
public class Torni extends Nappula {

    public Torni(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VTORNI);
        } else {
            setTyyppi(Tyyppi.MTORNI);
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Torni
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista tornille
     */
    @Override
    public ArrayList<String> kaikkiMahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        for (int i = x + 1; i <= 7; i++) {
            if (ruudukko[i][y].getNappula() == null) {
                siirrot.add("" + (i) + (y));
                continue;
            }
            if (!onkoSamaVari(ruudukko[i][y].getNappula())) {
                siirrot.add("" + (i) + (y));

            }
            break;

        }

        for (int i = x - 1; i >= 0; i--) {
            if (ruudukko[i][y].getNappula() == null) {
                siirrot.add("" + (i) + (y));
                continue;
            }
            if (!onkoSamaVari(ruudukko[i][y].getNappula())) {
                siirrot.add("" + (i) + (y));

            }
            break;

        }

        for (int i = y + 1; i <= 7; i++) {
            if (ruudukko[x][i].getNappula() == null) {
                siirrot.add("" + (x) + (i));
                continue;
            }
            if (!onkoSamaVari(ruudukko[x][i].getNappula())) {
                siirrot.add("" + (x) + (i));

            }
            break;

        }

        for (int i = y - 1; i >= 0; i--) {
            if (ruudukko[x][i].getNappula() == null) {
                siirrot.add("" + (x) + (i));
                continue;
            }
            if (!onkoSamaVari(ruudukko[x][i].getNappula())) {
                siirrot.add("" + (x) + (i));

            }
            break;

        }
        return siirrot;
    }

}
