package ChessMaster;

import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sebbe
 */
public class Main {

    public static void main(String args[]) {

        Runnable r = new Runnable() {

            @Override
            public void run() {
                Kayttoliittyma lauta = new Kayttoliittyma();

                JFrame f = new JFrame("Chess Master 2014");
                f.add(lauta.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);

    }

}
