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
 * @author Sebbe
 */
public class Evaluator {

    /**
     * Evaluointiarvo joka palautetaan
     */
    public double ret;

    /**
     * Evaluointifunktio joka kutsuu kaikkia muita funktioita
     * @param p Ruudukko jonka arvo lasketaan
     * @return Ruudukon arvo
     */
    public double eval(Ruutu[][] p) {
        ret = 0;
        evalKuningasSijainti(p);
        evalKuningatarSijainti(p);
        evalLahettiSijainti(p);
        evalNappuloidenArvot(p);
        evalRatsuSijainti(p);
        evalSotilasSijainti(p);
        evalTorniSijainti(p);
        evalOnkoShakki(p);
        
        return ret;
    }

    /**
     * Laskee kaikkien nappuloiden arvon, riippumatta niitten sijainnista
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalNappuloidenArvot(Ruutu[][] p) {

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS) {
                    ret += 30000;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR) {
                    ret += 900;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI) {
                    ret += 500;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI) {
                    ret += 330;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU) {
                    ret += 320;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                    ret += 100;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    ret -= 30000;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR) {
                    ret -= 900;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI) {
                    ret -= 500;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI) {
                    ret -= 330;
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
            }

        }

    }
    
    /**
     * Laskee sotilaitten sijaintien arvon
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalSotilasSijainti(Ruutu[][] p ){
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

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 0) {
                    ret += 50;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 1 && y == 0) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 1 && y == 1) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 1 && y == 2) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 1 && y == 3) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 1 && y == 4) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 1 && y == 5) {
                    ret += 10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 2 && y == 0) {
                    ret += 5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 2 && y == 1) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 2 && y == 2) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 2 && y == 3) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 2 && y == 4) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 2 && y == 5) {
                    ret += 5;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 3 && y == 0) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 3 && y == 1) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 3 && y == 2) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 3 && y == 3) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 3 && y == 4) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 3 && y == 5) {
                    ret += -5;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 4 && y == 0) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 4 && y == 1) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 4 && y == 2) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 4 && y == 3) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 4 && y == 4) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS && x == 4 && y == 5) {
                    ret += 10;
                }
                
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 1 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 1 && y == 1) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 1 && y == 2) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 1 && y == 3) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 1 && y == 4) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 1 && y == 5) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 2 && y == 0) {
                    ret += 5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 2 && y == 1) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 2 && y == 2) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 2 && y == 3) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 2 && y == 4) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 2 && y == 5) {
                    ret += 5;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 3 && y == 0) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 3 && y == 1) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 3 && y == 2) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 3 && y == 3) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 3 && y == 4) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 3 && y == 5) {
                    ret += -5;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 4 && y == 0) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 4 && y == 1) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 4 && y == 2) {
                    ret += -30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 4 && y == 3) {
                    ret += -30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 4 && y == 4) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 4 && y == 5) {
                    ret += -20;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS && x == 5) {
                    ret -= 50;
                }
            }
    }
}
    
    /**
     * Laskee ratsujen sijaintien arvon
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalRatsuSijainti(Ruutu[][] p){
        
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
                
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 0 && y == 0) {
                    ret += -40;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 0 && y == 5) {
                    ret += -40;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 1 && y == 0) {
                    ret += -30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 1 && y == 1) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 1 && y == 2) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 1 && y == 3) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 1 && y == 4) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 1 && y == 5) {
                    ret += -30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 2 && y == 0) {
                    ret += -30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 2 && y == 1) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 2 && y == 2) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 2 && y == 3) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 2 && y == 4) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 2 && y == 5) {
                    ret += -30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 3 && y == 0) {
                    ret += -30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 3 && y == 1) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 3 && y == 2) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 3 && y == 3) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 3 && y == 4) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 3 && y == 5) {
                    ret += -30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 4 && y == 0) {
                    ret += -30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 4 && y == 1) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 4 && y == 2) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 4 && y == 3) {
                    ret += 15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 4 && y == 4) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 4 && y == 5) {
                    ret += -30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU && x == 5) {
                    ret += -40;
                }
                
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 0) {
                    ret += 40;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 1 && y == 0) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 1 && y == 1) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 1 && y == 2) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 1 && y == 3) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 1 && y == 4) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 1 && y == 5) {
                    ret += 30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 2 && y == 0) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 2 && y == 1) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 2 && y == 2) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 2 && y == 3) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 2 && y == 4) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 2 && y == 5) {
                    ret += 30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 3 && y == 0) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 3 && y == 1) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 3 && y == 2) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 3 && y == 3) {
                    ret += -20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 3 && y == 4) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 3 && y == 5) {
                    ret += 30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 4 && y == 0) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 4 && y == 1) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 4 && y == 2) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 4 && y == 3) {
                    ret += -15;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 4 && y == 4) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 4 && y == 5) {
                    ret += 30;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU && x == 0 && (y == 0 || y == 5)) {
                    ret += 40;
                }
            }
        }
        
    }
    
    /**
     * Laskee tornien sijaintien arvon
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalTorniSijainti(Ruutu[][] p){
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
        
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI && x == 1) {
                    ret += 10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI && x == 4) {
                    ret -= 10;
                }
            }
        }
    }
    
    /**
     * Laskee lahettien sijaintien arvon
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalLahettiSijainti(Ruutu[][] p){
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
        

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 0) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 1 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 1 && y == 1) {
                    ret += 5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 1 && y == 2) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 1 && y == 3) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 1 && y == 4) {
                    ret += 5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 1 && y == 5) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 2 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 2 && y == 1) {
                    ret += 5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 2 && y == 2) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 2 && y == 3) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 2 && y == 4) {
                    ret += 5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 2 && y == 5) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 3 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 3 && y == 1) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 3 && y == 2) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 3 && y == 3) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 3 && y == 4) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 3 && y == 5) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 4 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 4 && y == 1) {
                    ret += 0;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 4 && y == 2) {
                    ret += 0;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 4 && y == 3) {
                    ret += 0;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 4 && y == 4) {
                    ret += 0;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 4 && y == 5) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 5 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 5 && y == 1) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 5 && y == 2) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 5 && y == 3) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 5 && y == 4) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI && x == 5 && y == 5) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 1 && y == 0) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 1 && y == 5) {
                    ret += -10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 2 && y == 0) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 2 && y == 1) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 2 && y == 2) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 2 && y == 3) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 2 && y == 4) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 2 && y == 5) {
                    ret += 10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 3 && y == 0) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 3 && y == 1) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 3 && y == 2) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 3 && y == 3) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 3 && y == 4) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 3 && y == 5) {
                    ret += 10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 4 && y == 0) {
                    ret += 10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 4 && y == 1) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 4 && y == 2) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 4 && y == 3) {
                    ret += -10;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 4 && y == 4) {
                    ret += -5;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 4 && y == 5) {
                    ret += 10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 0) {
                    ret += 10;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI && x == 5) {
                    ret += 10;
                }
            }
        }
    }
    
    /**
     * Laskee kuningattarien sijaintien arvon
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalKuningatarSijainti(Ruutu[][] p){
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
                
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR && ((x > 1 && x < 4) && (y < 4 && y > 1))) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR && (y == 0 || y == 5)) {
                    ret -= 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR && (x == 5 || x == 0)) {
                    ret -= 20;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR && ((x > 1 && x < 4) && (y < 4 && y > 1))) {
                    ret -= 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR && (y == 0 || y == 5)) {
                    ret += 20;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR && (x == 5 || x == 0)) {
                    ret += 20;
                }
            }
        }
    }
    
    /**
     * Laskee kuninkaitten sijaintien arvon
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalKuningasSijainti(Ruutu[][] p){
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                if (p[x][y].getNappula() == null) {
                    continue;
                }
        
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && x == 0) {
                    ret -= 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && (y == 0 || y == 5) && x != 0) {
                    ret -= 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && ((y == 0 || y == 5) && x == 0)) {
                    ret -= 20;
                }

                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && x == 5) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && (y == 0 || y == 5) && x != 0) {
                    ret += 30;
                }
                if (p[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && ((y == 0 || y == 5) && x == 5)) {
                    ret += 20;
                }

            }
        }
    }
    
    /**
     * Laskee onko shakki
     * @param p Ruudukko jonka arvo lasketaan
     */
    public void evalOnkoShakki(Ruutu[][] p){
        Pelilauta lauta = new Pelilauta();
        lauta.setRuudukko(p);
        if ( lauta.onkoShakki("valkoinen")){
            ret -= 50000;
        } else if(lauta.onkoShakki("musta")){
            ret += +50000;
        }
    }
}
