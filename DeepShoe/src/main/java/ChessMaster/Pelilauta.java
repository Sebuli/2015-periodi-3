/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessMaster;

import Nappulat.Torni;
import Nappulat.Lahetti;
import Nappulat.Kuningatar;
import Nappulat.Sotilas;
import Nappulat.Nappula;
import Nappulat.Ratsu;
import Nappulat.Kuningas;
import java.util.ArrayList;

/**
 * Luokalla on metodeja pelilaudan muunteluun.
 *
 * @author Sebbe
 */
public class Pelilauta {

    /**
     * Kaksiuloitteinen array ruutuja
     */
    private Ruutu[][] ruudukko;

    /**
     * Metodi kutsuu kahta metodia ja ne tekevÃ¤t uuden pelilaudan jossa on 64
     * nappulaa.
     */
    public void uusiPeli() {
        luoRuudukko();
        lisaaNappulatLaudalle();

    }

    /**
     * Metodi alustaa ruudukon ja lisÃ¤Ã¤ jokaiseen paikkaan uuden Ruudun
     */
    public void luoRuudukko() {
        ruudukko = new Ruutu[8][8];
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t] = new Ruutu();
            }
        }
    }

    /**
     * Metodi luo jokaisen tarvittavan Nappulan shakkipelin alkuun ja asettaa ne
     * oikeisiin ruutuihin.
     */
    public void lisaaNappulatLaudalle() {
        for (int i = 0; i <= 7; i++) {
            if (i == 0 || i == 7) {
                Torni mtorni = new Torni("musta");
                Torni vtorni = new Torni("valkoinen");
                ruudukko[0][i].asetaNappula(mtorni);
                ruudukko[7][i].asetaNappula(vtorni);
            }
            if (i == 1 || i == 6) {
                Ratsu mratsu = new Ratsu("musta");
                Ratsu vratsu = new Ratsu("valkoinen");
                ruudukko[0][i].asetaNappula(mratsu);
                ruudukko[7][i].asetaNappula(vratsu);
            }
            if (i == 2 || i == 5) {
                Lahetti mlahetti = new Lahetti("musta");
                Lahetti vlahetti = new Lahetti("valkoinen");
                ruudukko[0][i].asetaNappula(mlahetti);
                ruudukko[7][i].asetaNappula(vlahetti);
            }
            if (i == 4) {
                Kuningas mkuningas = new Kuningas("musta");
                Kuningas vkuningas = new Kuningas("valkoinen");
                ruudukko[0][i].asetaNappula(mkuningas);
                ruudukko[7][i].asetaNappula(vkuningas);
            }
            if (i == 3) {
                Kuningatar mkuningatar = new Kuningatar("musta");
                Kuningatar vkuningatar = new Kuningatar("valkoinen");
                ruudukko[0][i].asetaNappula(mkuningatar);
                ruudukko[7][i].asetaNappula(vkuningatar);
            }

            Sotilas msotilas = new Sotilas("musta");
            Sotilas vsotilas = new Sotilas("valkoinen");
            ruudukko[1][i].asetaNappula(msotilas);
            ruudukko[6][i].asetaNappula(vsotilas);

        }
    }

    /**
     * Metodi siirtaa nappulan joka sijaitsee vanhaX ja vanhaY koordinaattien
     * paattamassa paikassa uusiX:n ja uusiY:n maarittamaan paikkaan.
     *
     * @param vanhaX Nappulan jota halutaan siirtaa x sijainti ruudukossa
     * @param vanhaY Nappulan jota halutaan siirtaa y sijainti ruudukossa
     * @param uusiX x sijainti minne halutaan siirtaa nappula
     * @param uusiY y sijainti minne halutaan siirtaa nappula
     */
    public void siirra(int vanhaX, int vanhaY, int uusiX, int uusiY) {

        Nappula nappula = getNappula(vanhaX, vanhaY);

        if (getNappula(uusiX, uusiY) == null) {
            if (vanhaX == 4 && nappula.getTyyppi() == Nappula.Tyyppi.MSOTILAS && uusiY != vanhaY
                    && getNappula(uusiX - 1, uusiY) != null && getNappula(uusiX - 1, uusiY).getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                poistaNappula(uusiX - 1, uusiY);
            } else if (vanhaX == 3 && nappula.getTyyppi() == Nappula.Tyyppi.VSOTILAS && uusiY != vanhaY
                    && getNappula(uusiX + 1, uusiY) != null && getNappula(uusiX + 1, uusiY).getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
                poistaNappula(uusiX + 1, uusiY);
            }
            poistaNappula(vanhaX, vanhaY);
            asetaNappula(uusiX, uusiY, nappula);

        } else if (!nappula.onkoSamaVari(getNappula(uusiX, uusiY))) {
            poistaNappula(vanhaX, vanhaY);
            poistaNappula(uusiX, uusiY);
            asetaNappula(uusiX, uusiY, nappula);
        }
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }

    public void setRuudukko(Ruutu[][] ruudukko) {
        this.ruudukko = ruudukko;
    }

    public Nappula getNappula(int x, int y) {
        return ruudukko[x][y].getNappula();
    }

    public void poistaNappula(int x, int y) {
        ruudukko[x][y].poistaNappula();
    }

    public void asetaNappula(int x, int y, Nappula nappula) {
        ruudukko[x][y].asetaNappula(nappula);
    }

    /**
     * Metodia kutsutaan kun halutaan tietää onko vari parametrin maarittama
     * puoli shakki tilanteessa
     *
     * @param vari Vari joka tarkistetaan onko shakki tilanteessa
     * @return Palauttaa totta jos vari parametrin puoli on shakki tilanteessa
     */
    public boolean onkoShakki(String vari) {
        int x = 0;
        int y = 0;

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (getNappula(i, t) != null && ((vari.equals("musta") && getNappula(i, t).getTyyppi() == Nappula.Tyyppi.MKUNINGAS)
                        || vari.equals("valkoinen") && getNappula(i, t).getTyyppi() == Nappula.Tyyppi.VKUNINGAS)) {
                    x = i;
                    y = t;
                    break;
                }
            }
        }

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (getNappula(i, t) != null && !getNappula(i, t).getVari().equals(vari)) {
                    Pelilauta kopioLauta = new Pelilauta();
                    kopioLauta.setRuudukko(ruudukko);
                    Nappula.Tyyppi tyyppi = getNappula(i, t).getTyyppi();
                    ArrayList<String> kaikkiMahdollisetSiirrot = new ArrayList<>();

                    kaikkiMahdollisetSiirrot.addAll(getNappula(i, t).kaikkiMahdollisetSiirrot(i, t, ruudukko));

                    if (kaikkiMahdollisetSiirrot.contains("" + x + y)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * Metodi tarkistaa onko vari parametrin maarittama puoli shakkimatti
     * tilanteessa.
     *
     * @param vari Vari joka tarkistetaan onko shakki tilanteessa
     * @return Palauttaa totta jos vari parametrin puoli on shakkimatti
     * tilanteessa
     */
    public boolean onkoShakkiMatti(String vari) {

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (getNappula(i, t) != null && getNappula(i, t).getVari().equals(vari)
                        && !getNappula(i, t).mahdollisetSiirrot(i, t, ruudukko).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Metodi lisaa kaikkien sotilaiden jotka ovat joko neljannella tai
     * viidennella rivilla siirtojenmaaraa. Nain varmistetaan etta En Passant
     * toimii oikein.
     */
    public void otaEnPassantMahdollisuusPois() {
        for (int i = 0; i <= 7; i++) {
            if (getNappula(3, i) != null && getNappula(3, i).getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
                getNappula(3, i).kasvataSiirtojenMaaraa();
            }
            if (getNappula(4, i) != null && getNappula(4, i).getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                getNappula(4, i).kasvataSiirtojenMaaraa();
            }
        }
    }

    /**
     * Metodi palauttaa kaikkien nappuloiden siirtojen maarat yhteen laskettuna
     *
     * @return summa nappuloiden siirtoejen maarasta
     */
    public int siirtojenMaara() {
        int summa = 0;
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (getNappula(i, t) != null) {
                    summa += getNappula(i, t).getSiirtojenMaara();
                }
            }
        }
        return summa;
    }

}
