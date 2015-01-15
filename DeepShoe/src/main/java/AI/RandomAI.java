/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
import Nappulat.Nappula;
import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka joka käytetään jos randomAI on päällä
 *
 * @author Sebbe
 */
public class RandomAI {

    /**
     * Kertoo random siirron mustalle pelaajalle.
     *
     * @param pelilauta Pelilauta josta haluamme saada random siirron.
     * @return siirto joka halutaan että musta pelaaja tekee
     */
    public String haeSiirto(Pelilauta pelilauta, String vari) {
        String siirto = "";

        Random random = new Random();

        while (true) {
            int randomX = random.nextInt(7);
            int randomY = random.nextInt(7);

            Nappula nappula = pelilauta.getNappula(randomX, randomY);

            if (nappula != null && nappula.getVari().equals(vari)) {
                ArrayList<String> mahdollisetSiirrot = nappula.mahdollisetSiirrot(randomX, randomY, pelilauta.getRuudukko());

                if (mahdollisetSiirrot.size() > 0) {
                    int randomSiirto = random.nextInt(mahdollisetSiirrot.size());

                    siirto = siirto + randomX + randomY + mahdollisetSiirrot.get(randomSiirto);
                    return siirto;
                }

            }

        }

    }

}
