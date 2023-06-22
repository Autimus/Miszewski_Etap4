package Utility;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * The type Podsumowanie.
 */
public class Podsumowanie extends JFrame {
    /**
     * Instantiates a new Podsumowanie. Tworzy nowe okno wyswietlajace uzbierane statystyki ko&#x144;cowe. Zamkni&#x119;cie okna oznacza zako&#x144;czenie programu.
     *
     * @param dane statystyki ko&#x144;cowe zbierane przez Aplikacj&#x119;
     */
    public Podsumowanie(Map<String,Float> dane){
        JPanel centrum = new JPanel();
        setContentPane(centrum);
        centrum.setLayout(new BoxLayout(centrum,BoxLayout.Y_AXIS));
        setSize(800,600);
        setTitle("Plemiona Amazonki");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel tytul = new JLabel("Podsumowanie: ");
        tytul.setFont(new Font("Arial", Font.BOLD, 50));
        tytul.setAlignmentX(Component.CENTER_ALIGNMENT);
        centrum.add(tytul);
        for (Object wpis: dane.entrySet().toArray()) {
            String[] pomoc =wpis.toString().split("=");
            JLabel etykieta = new JLabel(pomoc[0]+": "+ String.format("%.1f",Float.parseFloat(pomoc[1])).replaceFirst(",0",""));
            etykieta.setFont(new Font("Verdana", Font.PLAIN, 25));
            etykieta.setAlignmentX(Component.CENTER_ALIGNMENT);
            centrum.add(etykieta);
            System.out.println(pomoc[0]+": "+pomoc[1]);
        }
        setVisible(true);
    }

}