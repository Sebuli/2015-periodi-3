/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

import ChessMaster.Ruutu;
import java.util.ArrayList;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VSOTILAS tai MSOTILAS
 *
 * @author Sebbe
 */
public class Sotilas extends Nappula {

    public Sotilas(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VSOTILAS);
        } else {
            setTyyppi(Tyyppi.MSOTILAS);
        }

    }

    /**
     * Metodia kutsutaan kun nappula on Sotilas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista sotilaalle
     */
    @Override
    public ArrayList<String> kaikkiMahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        if (ruudukko[x][y].getNappula().getVari().equals("musta")) {
            return mahdollisetSiirrotMSotilas(x, y, ruudukko);
        } else {
            return mahdollisetSiirrotVSotilas(x, y, ruudukko);
        }
    }

    /**
     * Metodia kutsutaan kun nappula on valkoinen Sotilas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista valkoiselle sotilaalle
     */
    public ArrayList<String> mahdollisetSiirrotVSotilas(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();

        if (x == 6 && (ruudukko[x - 2][y].getNappula() == null && ruudukko[x - 1][y].getNappula() == null)) {
            siirrot.add("" + (x - 2) + (y));
        }
        if (x - 1 >= 0 && ruudukko[x - 1][y].getNappula() == null) {
            siirrot.add("" + (x - 1) + (y));
        }

        if (x - 1 >= 0 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula())) {
            siirrot.add("" + (x - 1) + (y - 1));
        }

        if (x - 1 >= 0 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula())) {
            siirrot.add("" + (x - 1) + (y + 1));
        }
        if (x == 3 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() == null && ruudukko[x][y + 1].getNappula() != null
                && ruudukko[x][y + 1].getNappula().getTyyppi() == Tyyppi.MSOTILAS && ruudukko[x][y + 1].getNappula().getSiirtojenMaara() == 2) {
            siirrot.add("" + (x - 1) + (y + 1));
        }
        if (x == 3 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() == null && ruudukko[x][y - 1].getNappula() != null
                && ruudukko[x][y - 1].getNappula().getTyyppi() == Tyyppi.MSOTILAS && ruudukko[x][y - 1].getNappula().getSiirtojenMaara() == 2) {
            siirrot.add("" + (x - 1) + (y - 1));
        }

        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on musta Sotilas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     *
     * @return Lista mahdollisista siirroista mustalle sotilaalle
     */
    public ArrayList<String> mahdollisetSiirrotMSotilas(int x, int y, Ruutu[][] ruudukko) {

        ArrayList<String> siirrot = new ArrayList<>();
        if (x == 1 && (ruudukko[x + 2][y].getNappula() == null && ruudukko[x + 1][y].getNappula() == null)) {
            siirrot.add("" + (x + 2) + (y));
        }
        if (x + 1 <= 7 && ruudukko[x + 1][y].getNappula() == null) {
            siirrot.add("" + (x + 1) + (y));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && ruudukko[x + 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula())) {
            siirrot.add("" + (x + 1) + (y + 1));
        }

        if (x + 1 <= 7 && y - 1 >= 0 && ruudukko[x + 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula())) {
            siirrot.add("" + (x + 1) + (y - 1));
        }
        if (x == 4 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() == null && ruudukko[x][y + 1].getNappula() != null
                && ruudukko[x][y + 1].getNappula().getTyyppi() == Tyyppi.VSOTILAS && ruudukko[x][y + 1].getNappula().getSiirtojenMaara() == 2) {
            siirrot.add("" + (x + 1) + (y + 1));
        }
        if (x == 4 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() == null && ruudukko[x][y - 1].getNappula() != null
                && ruudukko[x][y - 1].getNappula().getTyyppi() == Tyyppi.VSOTILAS && ruudukko[x][y - 1].getNappula().getSiirtojenMaara() == 2) {
            siirrot.add("" + (x + 1) + (y - 1));
        }
        return siirrot;
    }

}
