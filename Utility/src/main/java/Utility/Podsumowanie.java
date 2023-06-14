package Utility;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Podsumowanie extends JFrame {
    public Podsumowanie(Map<String,Float> dane){
        JPanel centrum = new JPanel();
        setContentPane(centrum);
//        centrum.setBounds(0,0,800,600);
//        centrum.setMaximumSize(new Dimension(800,600));
//        centrum.setMinimumSize(new Dimension(800,600));
//        centrum.setSize(new Dimension(800,600));
//        centrum.setPreferredSize(new Dimension(800,600));
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