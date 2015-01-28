/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import Nappulat.Nappula;

/**
 * Evaluator laskee pelilaudan arvon
 *
 * @author Sebbe
 */
public class Evaluator {

    /**
     * Evaluointiarvo joka palautetaan
     */
    public double ret;
    
    private int nappuloidenMaara;
    
    private Pelilauta lauta;

    public double[][] sotilasTaulukko = new double[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 10, 10},
        {5, 5, 10, 27, 27, 10, 5, 5},
        {0, 0, 0, 25, 25, 0, 0, 0},
        {5, -5, -10, 0, 0, -10, -5, 5},
        {5, 10, 10, -25, -25, 10, 10, 5},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public double[][] ratsuTaulukko = new double[][]{
        {-50, -40, -30, -30, -30, -30, -40, -50},
        {-40, -20, 0, 0, 0, 0, -20, -40},
        {-30, 0, 10, 15, 15, 10, 0, -30},
        {-30, 5, 15, 20, 20, 15, 5, -30},
        {-30, 0, 15, 20, 20, 15, 0, -30},
        {-30, 5, 10, 15, 15, 10, 5, -30},
        {-40, -20, 0, 5, 5, 0, -20, -40},
        {-50, -40, -20, -30, -30, -20, -40, -50}
    };

    public double[][] lahettiTaulukko = new double[][]{
        {-20, -10, -10, -10, -10, -10, -10, -20},
        {-10, 0, 0, 0, 0, 0, 0, -10},
        {-10, 0, 5, 10, 10, 5, 0, -10},
        {-10, 5, 5, 10, 10, 5, 5, -10},
        {-10, 0, 10, 10, 10, 10, 0, -10},
        {-10, 10, 10, 10, 10, 10, 10, -10},
        {-10, 5, 0, 0, 0, 0, 5, -10},
        {-20, -10, -40, -10, -10, -40, -10, -20}
    };

    public int[][] kuningasTaulukko = new int[][]{
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-20, -30, -30, -40, -40, -30, -30, -20},
        {-10, -20, -20, -20, -20, -20, -20, -10},
        {20, 20, 0, 0, 0, 0, 20, 20},
        {20, 30, 10, 0, 0, 10, 30, 20}
    };

    public double[][] kuningasTaulukkoEndgame = new double[][]{
        {-50, -40, -30, -20, -20, -30, -40, -50},
        {-30, -20, -10, 0, 0, -10, -20, -30},
        {-30, -10, 20, 30, 30, 20, -10, -30},
        {-30, -10, 30, 40, 40, 30, -10, -30},
        {-30, -10, 30, 40, 40, 30, -10, -30},
        {-30, -10, 20, 30, 30, 20, -10, -30},
        {-30, -30, 0, 0, 0, 0, -30, -30},
        {-50, -30, -30, -30, -30, -30, -30, -50}
    };

    public double[][] kuningatarTaulukko = new double[][]{
        {-20, -10, -10, -5, -5, -10, -10, -20},
        {-10, 0, 0, 0, 0, 0, 0, -10},
        {-10, 0, 5, 5, 5, 5, 0, -10},
        {-5, 0, 5, 5, 5, 5, 0, -5},
        {0, 0, 5, 5, 5, 5, 0, -5},
        {-10, 5, 5, 5, 5, 5, 0, -10},
        {-10, 0, 5, 0, 0, 0, 0, -10},
        {-20, -10, -10, -5, -5, -10, -10, -20}
    };

    public double[][] torniTaulukko = new double[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {5, 10, 10, 10, 10, 10, 10, 5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {0, 0, 0, 5, 5, 0, 0, 0}
    };

    /**
     * Evaluointifunktio joka kutsuu kaikkia muita funktioita
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return Ruudukon arvo
     */
    public double eval(Ruutu[][] p) {
        nappuloidenMaara = 0;
        ret = 0;
        lauta = new Pelilauta();
        lauta.setRuudukko(p);
        if (lauta.onkoShakkiMatti("valkoinen")) {
            return -50000.0;
        } else if (lauta.onkoShakkiMatti("musta")) {
            return 50000.0;
        }
        
        if (lauta.onkoShakki("valkoinen")) {
            ret += -75;
        } else if (lauta.onkoShakki("musta")) {
            ret += 75;
        }
        evalKuningasSijainti(p);
        evalKuningatarSijainti(p);
        evalLahettiSijainti(p);
        evalNappuloidenArvot(p);
        evalRatsuSijainti(p);
        evalSotilasSijainti(p);
        evalTorniSijainti(p);

        return ret;
    }

    /**
     * Laskee kaikkien nappuloiden arvon, riippumatta niitten sijainnista
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalNappuloidenArvot(Ruutu[][] p) {

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS) {
                    ret += 32767;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR) {
                    ret += 975;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI) {
                    ret += 500;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI) {
                    ret += 325;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU) {
                    ret += 320;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                    ret += 100;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    ret -= 32767;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR) {
                    ret -= 975;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI) {
                    ret -= 500;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI) {
                    ret -= 325;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU) {
                    ret -= 320;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
                    ret -= 100;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && (((x + 1 < p.length && y - 1 > p[x].length) && p[x + 1][y - 1].getNappula() != null
                        && p[x + 1][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS))
                        || ((x + 1 < p.length && y + 1 < p[x].length) && p[x + 1][y + 1].getNappula() != null && p[x + 1][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS)) {
                    ret += 30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && (((x - 1 > 0 && y - 1 > p[x].length) && p[x - 1][y - 1].getNappula() != null
                        && p[x - 1][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS))
                        || ((x - 1 > 0 && y + 1 < p[x].length) && p[x - 1][y + 1].getNappula() != null && p[x - 1][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS)) {
                    ret += -30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && ((x + 1 < 6 && p[x + 1][y].getNappula() != null
                        && p[x + 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS))
                        || ((x - 1 >= 0 && p[x - 1][y].getNappula() != null && p[x - 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS))) {
                    ret -= 7;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && ((x + 1 < 6 && p[x + 1][y].getNappula() != null
                        && p[x + 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS))
                        || ((x - 1 >= 0 && p[x - 1][y].getNappula() != null && p[x - 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS))) {
                    ret += 7;
                }
                
                nappuloidenMaara++;
            }

        }
        
        if ( nappuloidenMaara < 10){
            lauta.setEndGame(true);
        }

    }

    /**
     * Laskee sotilaitten sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalSotilasSijainti(Ruutu[][] p) {
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && ((x - 1 > 0 && p[x - 1][y].getNappula() != null && p[x - 1][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS)
                        || (x - 2 > 0 && p[x - 2][y].getNappula() != null && p[x - 2][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS)
                        || (x - 3 > 0 && p[x - 3][y].getNappula() != null && p[x - 3][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS)
                        || (x - 4 > 0 && p[x - 4][y].getNappula() != null && p[x - 4][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS))) {
                    ret += (6 - x) * 3;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && ((x + 1 < 6 && p[x + 1][y].getNappula() != null && p[x + 1][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)
                        || (x + 2 < 6 && p[x + 2][y].getNappula() != null && p[x + 2][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)
                        || (x + 3 < 6 && p[x + 3][y].getNappula() != null && p[x + 3][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)
                        || x + 4 < 6 && p[x + 4][y].getNappula() != null && p[x + 4][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)) {
                    ret -= x * 3;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                    ret += sotilasTaulukko[x][y];
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
                    ret += -sotilasTaulukko[7 - x][y];
                }
            }
        }
    }

    /**
     * Laskee ratsujen sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalRatsuSijainti(Ruutu[][] p) {

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU) {
                    ret += ratsuTaulukko[x][y];
                    if(lauta.onkoEndgame()){
                        ret += -10;
                    }
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU) {
                    ret += -ratsuTaulukko[7 - x][y];
                    if(lauta.onkoEndgame()){
                        ret += 10;
                    }
                }

            }
        }

    }

    /**
     * Laskee tornien sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalTorniSijainti(Ruutu[][] p) {
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI) {
                    ret += ratsuTaulukko[x][y];
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI) {
                    ret += -ratsuTaulukko[7 - x][y];
                }
            }
        }
    }

    /**
     * Laskee lahettien sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalLahettiSijainti(Ruutu[][] p) {
        int mustat = 0;
        int valkoiset = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI) {
                    ret += lahettiTaulukko[x][y];
                    valkoiset++;
                    if (lauta.onkoEndgame()){
                        ret += 10;
                    }
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI) {
                    ret += -lahettiTaulukko[7 - x][y];
                    mustat++;
                    if (lauta.onkoEndgame()){
                        ret += -10;
                    }
                }

            }
        }
        if ( valkoiset >= 2){
            ret += 10;
        } else if (mustat >= 2){
            ret -= 10;
        }
        
        
    }

    /**
     * Laskee kuningattarien sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalKuningatarSijainti(Ruutu[][] p) {
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR) {
                    ret += kuningatarTaulukko[x][y];
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR) {
                    ret += -kuningatarTaulukko[7 - x][y];
                }
            }
        }
    }

    /**
     * Laskee kuninkaitten sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalKuningasSijainti(Ruutu[][] p) {
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
                Pelilauta kopiolauta = new Pelilauta();
                kopiolauta.setRuudukko(p);
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS) {
                    if (!kopiolauta.onkoEndgame()) {
                        ret += kuningasTaulukko[x][y];
                    } else {
                        ret += kuningasTaulukkoEndgame[x][y];
                    }

                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    if (!kopiolauta.onkoEndgame()) {
                        ret += -kuningasTaulukko[7 - x][y];
                    } else {
                        ret += -kuningasTaulukkoEndgame[7 - x][y];
                    }
                }

            }
        }
    }

}
