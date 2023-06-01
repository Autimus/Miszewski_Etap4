package Utility;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Menu extends JFrame{
    private JPanel panel;
    private JTextField Drzewica;
    private JTextField Tukan;
    private JTextField Bielczyk;
    private JTextField Mistigodryas;
    private JTextField Bazyliszek;
    private JTextField Anakonda;
    private JTextField Zbrojnik;
    private JTextField Kirysek;
    private JTextField Akara;
    private JTextField Kapibara;
    private JTextField Puma;
    private JTextField Takano;
    private JTextField Yawanawa;
    private JTextField Kaxinawa;
    private JTextField Nukini;
    private JTextField Puyanawa;
    private JTextField Katakina;
    private JTextField Kuntanawa;
    private JTextField Shadawa;
    private JButton startButton;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JSlider slider4;
    private JSlider slider5;
    private JSlider slider6;
    private JSlider slider7;
    private JSlider slider8;
    private JSlider slider9;
    private JSlider slider10;
    private JSlider slider11;
    private JSlider slider12;
    private JSlider slider13;
    private JSlider slider14;
    private JSlider slider15;
    private JSlider slider16;
    private JSlider slider20;
    private JSlider slider21;
    private JSlider slider22;
    private JTextField Wysokosc;
    private JTextField Szerokosc;

    public Map<String,Integer> dane=new HashMap<>();

    public Menu(){
        setContentPane(panel);
        setSize(1280,720);
        setTitle("Plemiona Amazonki");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Drzewica.setText(String.valueOf(slider1.getValue()));
            }
        });
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Tukan.setText(String.valueOf(slider2.getValue()));
            }
        });
        slider3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Bielczyk.setText(String.valueOf(slider3.getValue()));
            }
        });
        slider4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Mistigodryas.setText(String.valueOf(slider4.getValue()));
            }
        });
        slider5.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Bazyliszek.setText(String.valueOf(slider5.getValue()));
            }
        });
        slider6.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Takano.setText(String.valueOf(slider6.getValue()));
            }
        });
        slider7.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Yawanawa.setText(String.valueOf(slider7.getValue()));
            }
        });
        slider8.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Kaxinawa.setText(String.valueOf(slider8.getValue()));
            }
        });
        slider9.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Nukini.setText(String.valueOf(slider9.getValue()));
            }
        });
        slider10.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Puyanawa.setText(String.valueOf(slider10.getValue()));
            }
        });
        slider11.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Anakonda.setText(String.valueOf(slider11.getValue()));
            }
        });
        slider12.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Zbrojnik.setText(String.valueOf(slider12.getValue()));
            }
        });
        slider13.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Kirysek.setText(String.valueOf(slider13.getValue()));
            }
        });
        slider14.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Akara.setText(String.valueOf(slider14.getValue()));
            }
        });
        slider15.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Kapibara.setText(String.valueOf(slider15.getValue()));
            }
        });
        slider16.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Puma.setText(String.valueOf(slider16.getValue()));
            }
        });
        slider20.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Katakina.setText(String.valueOf(slider20.getValue()));
            }
        });
        slider21.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Kuntanawa.setText(String.valueOf(slider21.getValue()));
            }
        });
        slider22.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Shadawa.setText(String.valueOf(slider22.getValue()));
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dane.put("Drzewnica",Integer.valueOf(Drzewica.getText()));
                dane.put("Tukano",Integer.valueOf(Takano.getText()));
                dane.put("Yawanawa",Integer.valueOf(Yawanawa.getText()));
                dane.put("Kaxinawa",Integer.valueOf(Kaxinawa.getText()));
                dane.put("Nukini",Integer.valueOf(Nukini.getText()));
                dane.put("Puyanawa",Integer.valueOf(Puyanawa.getText()));
                dane.put("Katukina",Integer.valueOf(Katakina.getText()));
                dane.put("Kuntanawa",Integer.valueOf(Kuntanawa.getText()));
                dane.put("Shawadawa",Integer.valueOf(Shadawa.getText()));
                dane.put("Tukan",Integer.valueOf(Tukan.getText()));
                dane.put("Bielczyk",Integer.valueOf(Bielczyk.getText()));
                dane.put("Mastigodryas",Integer.valueOf(Mistigodryas.getText()));
                dane.put("Bazyliszek",Integer.valueOf(Bazyliszek.getText()));
                dane.put("Anakonda",Integer.valueOf(Anakonda.getText()));
                dane.put("Zbrojnik",Integer.valueOf(Zbrojnik.getText()));
                dane.put("Kirysek",Integer.valueOf(Kirysek.getText()));
                dane.put("Akara",Integer.valueOf(Akara.getText()));
                dane.put("Kapibara",Integer.valueOf(Kapibara.getText()));;
                dane.put("Puma",Integer.valueOf(Puma.getText()));
                dane.put("Wysokosc",Integer.valueOf(Wysokosc.getText()));;
                dane.put("Szerokosc",Integer.valueOf(Szerokosc.getText()));
                System.out.println(dane.toString());
                setVisible(false);
            }
        });
    }


}
