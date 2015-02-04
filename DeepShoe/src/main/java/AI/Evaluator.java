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
 
    public Evaluator() {
        ret = 0.0;
        lauta = new Pelilauta();
        lauta.uusiPeli();
    }
 
    /**
     * Evaluointifunktio joka kutsuu kaikkia muita funktioita
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return Ruudukon arvo
     */
    public double eval(Ruutu[][] p) {
        ret = 0.0;
        lauta.setRuudukko(p);
 
        if (lauta.onkoShakkiMatti("valkoinen")) {
            return -50000.0;
        } else if (lauta.onkoShakkiMatti("musta")) {
            return 50000.0;
        }
 
        if (lauta.onkoShakki("valkoinen")) {
            ret += -75.0;
        } else if (lauta.onkoShakki("musta")) {
            ret += 75.0;
        }
        ret += evalKuningasSijainti(p);
        ret += evalKuningatarSijainti(p);
        ret += evalLahettiSijainti(p);
        ret += evalRatsuSijainti(p);
        ret += evalSotilasSijainti(p);
        ret += evalTorniSijainti(p);
 
        return ret;
    }
 
   
 
    /**
     * Laskee sotilaitten sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return 
     */
    public int evalSotilasSijainti(Ruutu[][] p) {
        int value = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                    if (((x - 1 > 0 && p[x - 1][y].getNappula() != null && p[x - 1][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS)
                            || (x - 2 > 0 && p[x - 2][y].getNappula() != null && p[x - 2][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS)
                            || (x - 3 > 0 && p[x - 3][y].getNappula() != null && p[x - 3][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS)
                            || (x - 4 > 0 && p[x - 4][y].getNappula() != null && p[x - 4][y].getNappula().getTyyppi() != Nappula.Tyyppi.MSOTILAS))) {
                        value += (6 - x) * 3.0;
                    }
                    if ((((x + 1 < p.length && y - 1 > p[x].length) && p[x + 1][y - 1].getNappula() != null
                            && p[x + 1][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS))
                            || ((x + 1 < p.length && y + 1 < p[x].length) && p[x + 1][y + 1].getNappula() != null && p[x + 1][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS)) {
                        value += 30.0;
                    }
                    if (((x + 1 < 6 && p[x + 1][y].getNappula() != null
                            && p[x + 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS))
                            || ((x - 1 >= 0 && p[x - 1][y].getNappula() != null && p[x - 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS))) {
                        value -= 7.0;
                    }
                    value += 100.0;
                    value += sotilasTaulukko[x][y];
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
                    if (((x + 1 < 6 && p[x + 1][y].getNappula() != null && p[x + 1][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)
                            || (x + 2 < 6 && p[x + 2][y].getNappula() != null && p[x + 2][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)
                            || (x + 3 < 6 && p[x + 3][y].getNappula() != null && p[x + 3][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)
                            || x + 4 < 6 && p[x + 4][y].getNappula() != null && p[x + 4][y].getNappula().getTyyppi() != Nappula.Tyyppi.VSOTILAS)) {
                        value -= x * 3.0;
                    }
                    if ((((x - 1 > 0 && y - 1 > p[x].length) && p[x - 1][y - 1].getNappula() != null
                            && p[x - 1][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS))
                            || ((x - 1 > 0 && y + 1 < p[x].length) && p[x - 1][y + 1].getNappula() != null && p[x - 1][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS)) {
                        value += -30.0;
                    }
                    if (((x + 1 < 6 && p[x + 1][y].getNappula() != null
                            && p[x + 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS))
                            || ((x - 1 >= 0 && p[x - 1][y].getNappula() != null && p[x - 1][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS))) {
                        value += 7.0;
                    }
                    value += -100.0;
                    value += -sotilasTaulukko[7 - x][y];
                }
            }
 
        }
        return value;
    }
 
    /**
     * Laskee ratsujen sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return 
     */
    public int evalRatsuSijainti(Ruutu[][] p) {
        int value = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU) {
                    value += 320.0;
                    value += ratsuTaulukko[x][y];
                    if (lauta.onkoEndgame()) {
                        value += -10.0;
                    }
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU) {
                    value += -320.0;
                    value += -ratsuTaulukko[7 - x][y];
                    if (lauta.onkoEndgame()) {
                        value += 10.0;
                    }
                }
 
            }
        }
        return value;
    }
 
    /**
     * Laskee tornien sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return 
     */
    public int evalTorniSijainti(Ruutu[][] p) {
        int value = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI) {
                    value += 500.0;
                    value += torniTaulukko[x][y];
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI) {
                    value += -500.0;
                    value += -torniTaulukko[7 - x][y];
                }
            }
        }
        return value;
    }
 
    /**
     * Laskee lahettien sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return 
     */
    public int evalLahettiSijainti(Ruutu[][] p) {
        int mustat = 0;
        int valkoiset = 0;
        int value = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI) {
                    value += 325.0;
                    value += lahettiTaulukko[x][y];
                    valkoiset++;
                    if (lauta.onkoEndgame()) {
                        value += 10.0;
                    }
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI) {
                    value += -325.0;
                    value += -lahettiTaulukko[7 - x][y];
                    mustat++;
                    if (lauta.onkoEndgame()) {
                        value += -10.0;
                    }
                }
 
            }
        }
        if (valkoiset >= 2) {
            value += 10.0;
        }if (mustat >= 2) {
            value -= 10.0;
        }
 
        return value;
 
    }
 
    /**
     * Laskee kuningattarien sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return 
     */
    public int evalKuningatarSijainti(Ruutu[][] p) {
        int value = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR) {
                    value += 975.0;
                    value += kuningatarTaulukko[x][y];
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR) {
                    value += -975.0;
                    value += -kuningatarTaulukko[7 - x][y];
                }
            }
        }
        return value;
    }
 
    /**
     * Laskee kuninkaitten sijaintien arvon
     *
     * @param p Ruudukko jonka arvo lasketaan
     * @return 
     */
    public int evalKuningasSijainti(Ruutu[][] p) {
        int value = 0;
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
                Pelilauta kopiolauta = new Pelilauta();
                kopiolauta.setRuudukko(p);
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS) {
                    value += 32767.0;
                    if (!kopiolauta.onkoEndgame()) {
                        value += kuningasTaulukko[x][y];
                    } else {
                        value += kuningasTaulukkoEndgame[x][y];
                    }
 
                }
 
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    value += -32767.0;
                    if (!kopiolauta.onkoEndgame()) {
                        value += -kuningasTaulukko[7 - x][y];
                    } else {
                        value += -kuningasTaulukkoEndgame[7 - x][y];
                    }
                }
 
            }
        }
        return value;
    }
 
}