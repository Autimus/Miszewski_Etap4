package Utility;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Menu extends JFrame{
    private JPanel panel;
    private JFormattedTextField Drzewica;
    private JFormattedTextField Tukan;
    private JFormattedTextField Bielczyk;
    private JFormattedTextField Mistigodryas;
    private JFormattedTextField Bazyliszek;
    private JFormattedTextField Anakonda;
    private JFormattedTextField Zbrojnik;
    private JFormattedTextField Kirysek;
    private JFormattedTextField Akara;
    private JFormattedTextField Kapibara;
    private JFormattedTextField Puma;
    private JFormattedTextField Takano;
    private JFormattedTextField Yawanawa;
    private JFormattedTextField Kaxinawa;
    private JFormattedTextField Nukini;
    private JFormattedTextField Puyanawa;
    private JFormattedTextField Katakina;
    private JFormattedTextField Kuntanawa;
    private JFormattedTextField Shadawa;
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
    private JFormattedTextField Wysokosc;
    private JFormattedTextField Szerokosc;
    private JButton a800X600PxButton;
    private JButton a1280X720PxButton;
    private JButton a1920X1080PxButton;
    private JSlider Jedzenie;
    private JTextField Wszystkie;
    private JTextField Plemiennicy;
    private int[] rozdzielczosc={1280,720};

    public Map<String,Integer> dane=new HashMap<>();

    public Menu(){
        JScrollPane panelPane = new JScrollPane(panel);
        setContentPane(panelPane);
        setSize(1280,720);
        setTitle("Plemiona Amazonki");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

//        NumberFormat format1 = NumberFormat.getIntegerInstance();
//        NumberFormatter format2 = new NumberFormatter(format1);
//        format2.setValueClass(Integer.class);
//        format2.setAllowsInvalid(false);
//        format2.setMinimum(0);
//        DefaultFormatterFactory format3=new DefaultFormatterFactory(format2);
//        Drzewica.setFormatterFactory(format3);

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
        a800X600PxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rozdzielczosc[0]=800;
                rozdzielczosc[1]=600;
            }
        });
        a1280X720PxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rozdzielczosc[0]=1280;
                rozdzielczosc[1]=720;
            }
        });
        a1920X1080PxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rozdzielczosc[0]=1920;
                rozdzielczosc[1]=1080;
            }
        });

        Hashtable<Integer, JLabel> podpisy = new Hashtable<>();
        for(int x=1;x<=10;x++)
            podpisy.put(x,new JLabel(String.format("%.1f",(0.1f*x))));
        Jedzenie.setLabelTable(podpisy);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Integer.parseInt(Wysokosc.getText())<=0 || Integer.parseInt(Szerokosc.getText())<=0 )
                        throw new ArithmeticException("Dzielenie przez zero");
                    dane.put("Wysokosc", Integer.valueOf(Wysokosc.getText()));
                    dane.put("Szerokosc", Integer.valueOf(Szerokosc.getText()));
                    dane.put("Drzewica", Integer.valueOf(Drzewica.getText()));
                    dane.put("Tukano", Integer.valueOf(Takano.getText()));
                    dane.put("Yawanawa", Integer.valueOf(Yawanawa.getText()));
                    dane.put("Kaxinawa", Integer.valueOf(Kaxinawa.getText()));
                    dane.put("Nukini", Integer.valueOf(Nukini.getText()));
                    dane.put("Puyanawa", Integer.valueOf(Puyanawa.getText()));
                    dane.put("Katukina", Integer.valueOf(Katakina.getText()));
                    dane.put("Kuntanawa", Integer.valueOf(Kuntanawa.getText()));
                    dane.put("Shawadawa", Integer.valueOf(Shadawa.getText()));
                    dane.put("Tukan", Integer.valueOf(Tukan.getText()));
                    dane.put("Bielczyk", Integer.valueOf(Bielczyk.getText()));
                    dane.put("Mastigodryas", Integer.valueOf(Mistigodryas.getText()));
                    dane.put("Bazyliszek", Integer.valueOf(Bazyliszek.getText()));
                    dane.put("Anakonda", Integer.valueOf(Anakonda.getText()));
                    dane.put("Zbrojnik", Integer.valueOf(Zbrojnik.getText()));
                    dane.put("Kirysek", Integer.valueOf(Kirysek.getText()));
                    dane.put("Akara", Integer.valueOf(Akara.getText()));
                    dane.put("Kapibara", Integer.valueOf(Kapibara.getText()));
                    dane.put("Puma", Integer.valueOf(Puma.getText()));
                    dane.put("RozdzielczoscX", rozdzielczosc[0]);
                    dane.put("RozdzielczoscY", rozdzielczosc[1]);
                    dane.put("Glod",Jedzenie.getValue());
//                System.out.println(dane.toString());
                    setVisible(false);
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(panel,"Wprowadzono błędne dane wejściowe. Ilość zwierząt musi być liczbą całkowitą.",
                            "Błędna ilość zwierząt",JOptionPane.INFORMATION_MESSAGE
                    );
                }catch(ArithmeticException e2){
                    JOptionPane.showMessageDialog(panel, "Wprowadzono błędne dane wejściowe. Szerokość oraz wysokosć mapy muszą być większe od ZERA.",
                            "Podano niemożliwe wymiary mapy",JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        Wszystkie.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Kirysek.setValue(Wszystkie.getText());
                Akara.setValue(Wszystkie.getText());
                Anakonda.setValue(Wszystkie.getText());
                Bazyliszek.setValue(Wszystkie.getText());
                Drzewica.setValue(Wszystkie.getText());
                Kapibara.setValue(Wszystkie.getText());
                Mistigodryas.setValue(Wszystkie.getText());
                Puma.setValue(Wszystkie.getText());
                Tukan.setValue(Wszystkie.getText());
                Zbrojnik.setValue(Wszystkie.getText());
                Bielczyk.setValue(Wszystkie.getText());
            }
        });
        Plemiennicy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Kuntanawa.setValue(Plemiennicy.getText());
                Katakina.setValue(Plemiennicy.getText());
                Kaxinawa.setValue(Plemiennicy.getText());
                Takano.setValue(Plemiennicy.getText());
                Nukini.setValue(Plemiennicy.getText());
                Puyanawa.setValue(Plemiennicy.getText());
                Yawanawa.setValue(Plemiennicy.getText());
                Shadawa.setValue(Plemiennicy.getText());
            }
        });
    }


}
