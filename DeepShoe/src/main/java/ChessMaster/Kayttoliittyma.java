package ChessMaster;

import AI.DeepShoe;
import Nappulat.Kuningatar;
import Nappulat.Nappula;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

/**
 * Luokka huolehtii ohjelman ulkonaosta
 *
 * @author Sebbe
 */
public class Kayttoliittyma {

    private JButton[][] ruudukko = new JButton[8][8];
    private Pelilauta pelilauta;
    private JPanel shakkiLauta;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private final JLabel kenenVuoro = new JLabel("");
    private final JLabel viesti = new JLabel("");
    private static final String COLS = "ABCDEFGH";
    private Pelilaudanpiirtaja piirtaja;
    private DeepShoe tekoaly;
    private String[] kirjaimet = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private JLabel AIpreviousMove = new JLabel("");

    private boolean onkoMustaRandomAIpaalla;
    private boolean onkoValkoinenRandomAIpaalla;
    private boolean onkoNaytaMahdollisetSiirrot;
    private boolean valkoisenVuoro;
    private boolean peliAlkanut;
    private boolean onkoStalemate;
    private SwingWorker worker;

    private int valkoinenSyvyys;
    private int mustaSyvyys;

    private int ekaX;
    private int ekaY;
    private int tokaX;
    private int tokaY;

    public Kayttoliittyma() {
        initComponents();
    }

    /**
     * Metodi luo kayttoliittymalle kaikki tarvittavas komponentit ja alustaa
     * muuttujia.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        piirtaja = new Pelilaudanpiirtaja();
        this.pelilauta = new Pelilauta();
        peliAlkanut = false;
        piirtaja.luoNappulaKuvat();
        onkoNaytaMahdollisetSiirrot = false;
        onkoMustaRandomAIpaalla = false;
        onkoValkoinenRandomAIpaalla = false;
        onkoStalemate = false;
        tekoaly = new DeepShoe();
        valkoinenSyvyys = 3;
        mustaSyvyys = 2;

        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New Game") {

            @Override
            public void actionPerformed(ActionEvent e) {
                aloitaPeli();
            }

        };

        tools.add(newGameAction);
        tools.addSeparator();

        Action Giveup = new AbstractAction("Give Up") {

            @Override
            public void actionPerformed(ActionEvent e) {
                annaPeriksi();

            }

        };
        tools.add(Giveup);
        tools.addSeparator();

        JCheckBox naytaMahdollisetSiirrot = new JCheckBox(new Action() {

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
                onkoNaytaMahdollisetSiirrot = b;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (onkoNaytaMahdollisetSiirrot) {
                    onkoNaytaMahdollisetSiirrot = false;
                    piirtaja.varitaPelilauta(ruudukko);
                    if (ekaX != -1) {
                        ruudukko[ekaX][ekaY].setBackground(Color.orange);
                    }
                } else {
                    onkoNaytaMahdollisetSiirrot = true;
                    if (ekaX != -1) {
                        piirtaja.varitaMahdollisetSiirrot(ruudukko, pelilauta, ekaX, ekaY);
                    }
                }
            }
        });

        JCheckBox mustaRandomAI = new JCheckBox(new Action() {

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
                onkoMustaRandomAIpaalla = b;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (onkoMustaRandomAIpaalla) {
                    onkoMustaRandomAIpaalla = false;

                } else {
                    onkoMustaRandomAIpaalla = true;
                    if (!valkoisenVuoro) {
                        String siirto = tekoaly.bestMove("musta", pelilauta.getRuudukko(), pelilauta, mustaSyvyys);
                        ekaX = Integer.parseInt("" + siirto.charAt(0));
                        ekaY = Integer.parseInt("" + siirto.charAt(1));
                        tokaX = Integer.parseInt("" + siirto.charAt(2));
                        tokaY = Integer.parseInt("" + siirto.charAt(3));
                        siirra();
                    }

                }
            }
        });

        JCheckBox valkoinenRandomAI = new JCheckBox(new Action() {

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
                onkoValkoinenRandomAIpaalla = b;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (onkoValkoinenRandomAIpaalla) {
                    onkoValkoinenRandomAIpaalla = false;

                } else {
                    onkoValkoinenRandomAIpaalla = true;

                    if (valkoisenVuoro) {
                        String siirto = tekoaly.bestMove("valkoinen", pelilauta.getRuudukko(), pelilauta, valkoinenSyvyys);
                        ekaX = Integer.parseInt("" + siirto.charAt(0));
                        ekaY = Integer.parseInt("" + siirto.charAt(1));
                        tokaX = Integer.parseInt("" + siirto.charAt(2));
                        tokaY = Integer.parseInt("" + siirto.charAt(3));
                        siirra();
                    }
                }
            }
        });

        naytaMahdollisetSiirrot.setText(
                "Enable help");

        mustaRandomAI.setText("Black AI");
        valkoinenRandomAI.setText("White AI");

        tools.add(naytaMahdollisetSiirrot);
        tools.add(mustaRandomAI);
        tools.add(valkoinenRandomAI);
        tools.addSeparator();

        tools.add(kenenVuoro);

        tools.addSeparator();

        tools.add(viesti);

        tools.add(AIpreviousMove);

        shakkiLauta = new JPanel(new GridLayout(0, 9)) {

            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
                } else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = (w > h ? h : w);
                return new Dimension(s, s);
            }
        };

        Color vari = new Color(204, 204, 255);

        shakkiLauta.setBackground(vari);
        JPanel boardConstrain = new JPanel(new GridBagLayout());

        boardConstrain.setBackground(vari);

        boardConstrain.add(shakkiLauta);

        gui.add(boardConstrain);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0;
                i < ruudukko.length;
                i++) {
            for (int j = 0; j < ruudukko[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                b.addMouseListener(new Mouse());
                ruudukko[j][i] = b;
            }

        }

        shakkiLauta.add(
                new JLabel(""));
        for (int i = 0;
                i < 8; i++) {
            shakkiLauta.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }

        for (int i = 0;
                i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                        shakkiLauta.add(new JLabel("" + (9 - (i + 1)), SwingConstants.CENTER));
                    default:
                        shakkiLauta.add(ruudukko[i][j]);

                }
            }
        }
    }

    /**
     * Tyhjentaa ruudukon nappuloista
     */
    private final void annaPeriksi() {
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t].setIcon(null);
            }
        }
        peliAlkanut = false;
        piirtaja.varitaPelilauta(ruudukko);
        pelilauta.uusiPeli();
        viesti.setText("Coward...");
        kenenVuoro.setText("");
    }

    /**
     * Varittaa pelilaudan ja laittaa nappulat oikeisiin paikkoihin. Ruutuja voi
     * metodin suorituksen jalkeen painaa.
     */
    private final void aloitaPeli() {
        viesti.setText("");
        peliAlkanut = true;
        pelilauta.uusiPeli();
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t].setEnabled(true);
            }
        }
        kenenVuoro.setText("White to move");
        ekaX = -1;
        ekaY = -1;
        tokaX = -1;
        tokaY = -1;

        valkoisenVuoro = true;

        piirtaja.varitaPelilauta(ruudukko);
        viesti.setText("");
        piirtaja.piirraNappulat(ruudukko, pelilauta);
    }

    /**
     * Lopettaa pelin ja nollaa pelilaudan.
     */
    private void lopetaPeli() {
        if (onkoStalemate) {
            viesti.setText("Draw!");
        } else if (!valkoisenVuoro) {
            viesti.setText("Checkmate, Black wins!");
        } else {
            viesti.setText("Checkmate, White wins!");
        }
        peliAlkanut = false;
        pelilauta.uusiPeli();

    }

    /**
     * Siirtaa nappulan ekaX,ekaY sijainnista tokaX,tokaY sijaintiin.
     */
    private void siirra() {

        if (pelilauta.getNappula(ekaX, ekaY) == null) {
            return;
        }
        if (valkoisenVuoro) {
            pelilauta.asetaViimeSiirto(ekaX, ekaY, tokaX, tokaY, "valkoinen");
        } else {
            pelilauta.asetaViimeSiirto(ekaX, ekaY, tokaX, tokaY, "musta");
        }

        pelilauta.getNappula(ekaX, ekaY).kasvataSiirtojenMaaraa();
        pelilauta.siirra(ekaX, ekaY, tokaX, tokaY);
        pelilauta.otaEnPassantMahdollisuusPois();

        if (tokaX == 0 && pelilauta.getNappula(tokaX, tokaY).getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
            pelilauta.asetaNappula(tokaX, tokaY, new Kuningatar("valkoinen"));
        }

        if (tokaX == 7 && pelilauta.getNappula(tokaX, tokaY).getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
            pelilauta.asetaNappula(tokaX, tokaY, new Kuningatar("musta"));
        }
        piirtaja.varitaPelilauta(ruudukko);

        ekaX = -1;
        ekaY = -1;
        tokaX = -1;
        tokaY = -1;

        viesti.setText("");
        piirtaja.piirraNappulat(ruudukko, pelilauta);

        if (pelilauta.onkoShakkiMatti("valkoinen") || pelilauta.onkoShakkiMatti("musta")) {
            lopetaPeli();
            return;
        }

        if (pelilauta.onkoShakki("musta")) {
            viesti.setText("Chess against Black!");
        } else if (pelilauta.onkoShakki("valkoinen")) {
            viesti.setText("Chess against White!");
        }

        if (valkoisenVuoro) {
            valkoisenVuoro = false;
            kenenVuoro.setText("Black to move");
        } else {
            valkoisenVuoro = true;
            kenenVuoro.setText("White to move");
        }

        if ((!valkoisenVuoro && onkoMustaRandomAIpaalla) || (valkoisenVuoro && onkoValkoinenRandomAIpaalla)) {

            String vari1 = "";
            if (valkoisenVuoro) {
                vari1 = "valkoinen";
            } else {
                vari1 = "musta";
            }

            final String vari = vari1;

            execute(vari);

            if ((pelilauta.onkoShakkiMatti("valkoinen") || pelilauta.onkoShakkiMatti("musta"))) {
                lopetaPeli();
                return;
            }
        }
    }

    private synchronized void execute(final String vari) {
        worker = new SwingWorker() {

            long time;

            @Override
            protected String doInBackground() throws Exception {
                if (pelilauta.onkoShakkiMatti(vari)) {
                    lopetaPeli();
                }
                time = System.currentTimeMillis();
                peliAlkanut = false;
                String siirto = "";
                if (vari.equals("valkoinen")) {
                    siirto = tekoaly.bestMove(vari, pelilauta.getRuudukko(), pelilauta, valkoinenSyvyys);
                } else {
                    siirto = tekoaly.bestMove(vari, pelilauta.getRuudukko(), pelilauta, mustaSyvyys);
                }

                return siirto;
            }

            @Override
            protected void done() {
                peliAlkanut = true;
                String siirto = "";

                try {
                    siirto = (String) get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!siirto.equals("")) {

                    ekaX = Integer.parseInt("" + siirto.charAt(0));
                    ekaY = Integer.parseInt("" + siirto.charAt(1));
                    tokaX = Integer.parseInt("" + siirto.charAt(2));
                    tokaY = Integer.parseInt("" + siirto.charAt(3));
                    System.out.println(vari + " siirsi " + kirjaimet[ekaY] + (8 - ekaX) + " to " + kirjaimet[tokaY] + (8 - tokaX) + " " + (System.currentTimeMillis() - time) / 1000.0 + " sekuntissa");
                    if (valkoisenVuoro) {
                        AIpreviousMove.setText(" The White AI moved " + kirjaimet[ekaY] + (8 - ekaX) + " to " + kirjaimet[tokaY] + (8 - tokaX));
                    } else {
                        AIpreviousMove.setText(" The Black AI moved " + kirjaimet[ekaY] + (8 - ekaX) + " to " + kirjaimet[tokaY] + (8 - tokaX));
                    }
                    siirra();

                } else if (!pelilauta.onkoShakkiMatti(vari)) {
                    worker.execute();
                } else if (pelilauta.onkoShakkiMatti(vari)) {
                    lopetaPeli();
                }

            }

        };

        worker.execute();
    }

    public final JComponent getGui() {
        return gui;

    }

    public class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (peliAlkanut) {

                for (int i = 0; i <= 7; i++) {
                    for (int t = 0; t <= 7; t++) {
                        if (e.getComponent() == ruudukko[i][t]) {
                            if (pelilauta.getNappula(i, t) != null
                                    && ((pelilauta.getNappula(i, t).getVari().equals("musta") && !valkoisenVuoro)
                                    || (pelilauta.getNappula(i, t).getVari().equals("valkoinen") && valkoisenVuoro))
                                    && ekaX == -1) {

                                ekaX = i;
                                ekaY = t;

                                e.getComponent().setBackground(Color.ORANGE);

                                if (onkoNaytaMahdollisetSiirrot) {
                                    piirtaja.varitaMahdollisetSiirrot(ruudukko, pelilauta, ekaX, ekaY);
                                }
                                break;
                            } else if (ekaX != -1) {

                                tokaX = i;
                                tokaY = t;

                                ArrayList<String> mahdollisetSiirrot = pelilauta.getNappula(ekaX, ekaY).mahdollisetSiirrot(ekaX, ekaY, pelilauta.getRuudukko());

                                for (String mahdollinen : mahdollisetSiirrot) {
                                    mahdollinen = mahdollinen.substring(0, 2);
                                    if (pelilauta.getNappula(i, t) != null && (pelilauta.getNappula(i, t).onkoTyyppi(Nappula.Tyyppi.MKUNINGAS) || pelilauta.getNappula(i, t).onkoTyyppi(Nappula.Tyyppi.VKUNINGAS))) {
                                        lopetaPeli();
                                    }
                                    if (mahdollinen.contains("" + tokaX + tokaY)) {
                                        siirra();
                                        break;
                                    }
                                }
                                piirtaja.varitaPelilauta(ruudukko);
                                if (ekaX != tokaX || ekaY != tokaY) {
                                    viesti.setText("Illegal move");
                                }
                                ekaX = -1;
                                ekaY = -1;
                                tokaX = -1;
                                tokaY = -1;

                                break;

                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

}
