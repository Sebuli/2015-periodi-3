package Nappulat;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import java.util.ArrayList;

/**
 *
 * Luokassa on metodeja joiden avulla saadaan tietoja nappulasta
 *
 * @author Sebbe
 */
public class Nappula {

    private int siirtojenMaara;

    public enum Tyyppi {

        VKUNINGAS, VKUNINGATAR, VLAHETTI, VRATSU, VSOTILAS, VTORNI,
        MKUNINGAS, MKUNINGATAR, MLAHETTI, MRATSU, MSOTILAS, MTORNI

    };

    /**
     * Nappulan vari, joko musta tai valkoinen
     */
    private String vari;
    /**
     * Lista nappulan mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrot;

    /**
     * Nappulan tyyppi
     */
    Tyyppi tyyppi;

    public Nappula(String vari) {
        this.vari = vari;
        siirtojenMaara = 0;
    }

    public String getVari() {
        return vari;
    }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    /**
     * Metodi kertoo onko nykyisen nappulan vari sama kuin parametrissÃ¤ saadun
     * nappulan vari
     *
     * @param nappula Nappula jonka varia halutaan verrata
     * @return totuusarvon joka kertoo ovatko nappulat samaa varia
     */
    public boolean onkoSamaVari(Nappula nappula) {
        return vari.equals(nappula.getVari());
    }

    /**
     * Metodi palauttaa listan mahdollisia siirtoja nappulalle. Mahdolliset
     * siirrot ovat String muodossa niin ettÃ¤ ensimmÃ¤inen kirjain on siirron x
     * arvo ja toinen on y arvo
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * @return lista mahdollisista siirroista nappulalle
     */
    public ArrayList<String> mahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        Pelilauta kopioLauta = new Pelilauta();
        kopioLauta.setRuudukko(ruudukko);
        String vari = ruudukko[x][y].getNappula().getVari();
        Nappula.Tyyppi tyyppi = ruudukko[x][y].getNappula().getTyyppi();
        Nappula siirettavaNappula = ruudukko[x][y].getNappula();

        ArrayList<String> kaikkiMahdollisetSiirrot = new ArrayList<>();

        kaikkiMahdollisetSiirrot.addAll(siirettavaNappula.kaikkiMahdollisetSiirrot(x, y, ruudukko));

        for (String siirto : kaikkiMahdollisetSiirrot) {
            int uusix = Integer.parseInt("" + siirto.charAt(0));
            int uusiy = Integer.parseInt("" + siirto.charAt(1));
            if (ruudukko[uusix][uusiy].getNappula() == null && (ruudukko[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS
                    || ruudukko[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) && uusiy != y) {

                Nappula nappula = ruudukko[x][uusiy].getNappula();
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (!kopioLauta.onkoShakki(vari)) {
                    siirrot.add(siirto);
                }
                kopioLauta.siirra(uusix, uusiy, x, y);
                ruudukko[x][uusiy].asetaNappula(nappula);

            } else if (ruudukko[uusix][uusiy].getNappula() == null) {
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (!kopioLauta.onkoShakki(vari)) {
                    siirrot.add(siirto);
                }
                kopioLauta.siirra(uusix, uusiy, x, y);

            } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
                Nappula nappula = ruudukko[uusix][uusiy].getNappula();
                kopioLauta.siirra(x, y, uusix, uusiy);
                if (!kopioLauta.onkoShakki(vari)) {
                    siirrot.add(siirto);
                }
                kopioLauta.siirra(uusix, uusiy, x, y);
                ruudukko[uusix][uusiy].asetaNappula(nappula);
            }
        }

        return siirrot;
    }

    public ArrayList<String> kaikkiMahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        return null;
    }

    public int getSiirtojenMaara() {
        return siirtojenMaara;
    }

    public void kasvataSiirtojenMaaraa() {
        siirtojenMaara += 1;
    }

    /**
     * Metodi kertoo onko nappulan tyyppi sama kuin parametrin tyyppi
     *
     * @param tyyppi Tyyppi johon nappulan tyyppia verrataan
     * @return True jos tyypit ovat samoja ja false muuten
     */
    public boolean onkoTyyppi(Tyyppi tyyppi) {
        if (this.tyyppi.equals(tyyppi)) {
            return true;
        }
        return false;
    }
}
