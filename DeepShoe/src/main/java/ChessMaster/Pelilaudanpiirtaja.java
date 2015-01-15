/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessMaster;

import Nappulat.Nappula;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Luokka huolehtii pelilaudan piirtamisesta.
 *
 * @author Sebbe
 */
public class Pelilaudanpiirtaja {

    public static final int KUNINGATAR = 0, KUNINGAS = 1,
            TORNI = 2, RATSU = 3, LAHETTI = 4, SOTILAS = 5;
    public static final int[] STARTING_ROW = {
        TORNI, RATSU, LAHETTI, KUNINGAS, KUNINGATAR, LAHETTI, RATSU, TORNI
    };
    public static final int MUSTA = 0, VALKOINEN = 1;
    private Image[][] chessPieceImages = new Image[2][6];

    /**
     * Varittaa pelilaudan ruudut mustavalkoisiksi
     */
    public void varitaPelilauta(JButton[][] ruudukko) {
        for (int k = 0; k <= 7; k++) {
            for (int j = 0; j <= 7; j++) {
                if ((j % 2 == 1 && k % 2 == 1) || (j % 2 == 0 && k % 2 == 0)) {
                    ruudukko[k][j].setBackground(Color.WHITE);
                } else {
                    ruudukko[k][j].setBackground(Color.BLACK);
                }

            }
        }
    }

    /**
     * Piirtaa pelinappulat niihin sijanteihin missa ne ovat pelilaudassa.
     */
    public final void piirraNappulat(JButton[][] ruudukko, Pelilauta pelilauta) {

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t].setIcon(null);
            }
        }
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {

                Nappula nappula = pelilauta.getRuudukko()[i][t].getNappula();

                if (nappula != null) {

                    if (nappula.onkoTyyppi(Nappula.Tyyppi.VKUNINGAS)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][KUNINGATAR]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.MKUNINGAS)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][KUNINGATAR]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.VKUNINGATAR)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][KUNINGAS]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.MKUNINGATAR)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][KUNINGAS]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.VLAHETTI)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][LAHETTI]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.MLAHETTI)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][LAHETTI]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.VRATSU)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][RATSU]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.MRATSU)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][RATSU]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.VSOTILAS)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][SOTILAS]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.MSOTILAS)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][SOTILAS]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.VTORNI)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][TORNI]));
                    }
                    if (nappula.onkoTyyppi(Nappula.Tyyppi.MTORNI)) {
                        ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][TORNI]));
                    }
                }
            }
        }
    }

    /**
     * Metodi lataa kuvat nappuloista ruudukkoon
     */
    public final void luoNappulaKuvat() {
        try {

            BufferedImage bi = ImageIO.read(new File(System.getProperty("user.dir") + "/Shakkikuva.png"));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    chessPieceImages[i][j] = bi.getSubimage(
                            j * 64, i * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Kun ruutu on valitti niin metodi piirtaa sen nappulan joka on ruudusa
     * mahdolliset siirtoruudut keltaisiksi.
     */
    public void varitaMahdollisetSiirrot(JButton[][] ruudukko, Pelilauta pelilauta, int ekaX, int ekaY) {
        ArrayList<String> mahdolliset = pelilauta.getRuudukko()[ekaX][ekaY].getNappula().mahdollisetSiirrot(ekaX, ekaY, pelilauta.getRuudukko());

        for (int a = 0; a <= 7; a++) {
            for (int b = 0; b <= 7; b++) {

                if (mahdolliset.contains("" + a + b)) {
                    ruudukko[a][b].setBackground(Color.yellow);
                }
            }
        }
    }

}
