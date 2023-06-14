package Utility;

import Pomocnicze.Koordy;
import Zwierzeta.ListaZwierzat;
import Zwierzeta.Zwierze;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.Timer;

public class Aplikacja extends JFrame {
    private Menu menu;
    private Podsumowanie podsumowanie;
    private Koordy rozmiarOkna,kafelki;
    public ListaZwierzat listaZwierzat;
    private boolean menuZamkniete=false;
    private boolean pauza=false;
    private boolean czyZwloki = true;
    private Integer odswiezanie=1000;
    private Map<String,Integer> dane;
    static int tura=0;
    public static Map<String, Float> koncoweStatystyki =new HashMap<>();
    private Timer petla;
    private Symulacja symulacja;
    private Statystyki statystyki;
    private JPanel panelBoczny;

    private JSlider predkoscOdswierzania;
    public Aplikacja(int rozmiarOknaX,int rozmiarOknaY, int kafelkiX, int kafelkiY) {
        menu=new Menu();
        rozmiarOkna=new Koordy(rozmiarOknaX,rozmiarOknaY);
        kafelki=new Koordy(kafelkiX,kafelkiY);
    }
    private void start(){
        kafelki.x=dane.remove("Szerokosc");
        kafelki.y=dane.remove("Wysokosc");
        rozmiarOkna.x=dane.remove("RozdzielczoscX");
        rozmiarOkna.y=dane.remove("RozdzielczoscY");
        float jedzenie = dane.remove("Glod")/10f;

        for(String stat: new String[]{"Smierci z glodu", "Smierci w walce", "Smierci ze starosci", "Zjedzone rosliny", "Zjedzone mieso", "Maksymalna populacja", "Najliczniejszy gatunek"})
            koncoweStatystyki.put(stat,0F);

        symulacja=new Symulacja(rozmiarOkna,kafelki);
        listaZwierzat=new ListaZwierzat(kafelki,symulacja.polaWody,jedzenie);
        for (Object wpis: dane.entrySet().toArray()) {
            String[] pomoc =wpis.toString().split("=");
            for(int i=0;i<Integer.valueOf(pomoc[1]);i++) {
                listaZwierzat.stworzZwierze(new Koordy().losoweMiejsce(kafelki.x, kafelki.y), pomoc[0]);
            }
        }
        statystyki=new Statystyki(rozmiarOkna);

        predkoscOdswierzania=new JSlider(0,6,3);
        predkoscOdswierzania.setMaximumSize(new Dimension((int)(rozmiarOkna.x*0.3),40));
        predkoscOdswierzania.setMajorTickSpacing(1);
        Hashtable<Integer, JLabel> podpisy = new Hashtable<>();
        podpisy.put(0, new JLabel("0.1s"));
        podpisy.put(1, new JLabel("0.2s"));
        podpisy.put(2, new JLabel("0.5s"));
        podpisy.put(3, new JLabel("1s"));
        podpisy.put(4, new JLabel("2s"));
        podpisy.put(5, new JLabel("5s"));
        podpisy.put(6, new JLabel("10s"));
        predkoscOdswierzania.setLabelTable(podpisy);
        predkoscOdswierzania.setPaintLabels(true);
        predkoscOdswierzania.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float[] predkosci= {1/10F,1/5F,1/2F,1,2,5,10};
                System.out.println((int)(predkosci[predkoscOdswierzania.getValue()]*1000));
                petla.cancel();
                ustawPetle((int)(predkosci[predkoscOdswierzania.getValue()]*1000));
            }
        });

        JLabel opisPredkosci =new JLabel("Czas miedzy turami:");
        opisPredkosci.setMaximumSize(new Dimension(500,10));
        opisPredkosci.setMinimumSize(new Dimension(500,10));
//        opisPredkosci.setPreferredSize(new Dimension(500,10));
//        opisPredkosci.setSize(new Dimension(500,10));
        opisPredkosci.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton stop=new JButton("STOP");
//        stop.setMinimumSize(new Dimension(100,100));
//        stop.setSize(new Dimension(100,100));
        stop.setMargin(new Insets(0,0,0,0));
        stop.setFont(new Font("Arial",Font.BOLD,15));
        stop.setMaximumSize(new Dimension(100,50));
        stop.setPreferredSize(new Dimension(80,40));
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop.setText((stop.getText()=="STOP")?"START":"STOP");
                stop();
            }
        });

        Image grafika = new ImageIcon("Utility/src/main/resources/smierc.png").getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JButton zwloki=new JButton(new ImageIcon(grafika));
        zwloki.setMaximumSize(new Dimension(50,50));
        zwloki.setPreferredSize(new Dimension(50,50));

        zwloki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                czyZwloki=!czyZwloki;
            }
        });

        panelBoczny = new JPanel();
        panelBoczny.setLayout(new BoxLayout(panelBoczny,BoxLayout.Y_AXIS));
        panelBoczny.revalidate();
//        panelBoczny.setLayout(new BorderLayout());
        panelBoczny.add(statystyki);
        panelBoczny.add(opisPredkosci);
        panelBoczny.add(predkoscOdswierzania);
        JPanel przyciski = new JPanel();
        przyciski.setMaximumSize(new Dimension(Integer.MAX_VALUE,60));
        przyciski.add(stop);
        przyciski.add(zwloki);
        panelBoczny.add(przyciski);

        setLayout(new BorderLayout());
        add(symulacja,BorderLayout.WEST);
        add(panelBoczny);

        pack();
        setSize(rozmiarOkna.x,rozmiarOkna.y);
        setTitle("Plemiona Amazonki");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
//        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                koniec();
            }
        });
    }
    private void stop(){
        pauza=!pauza;
    }
    public void rysuj() {
        if(!pauza){
            if (!menuZamkniete && !menu.isVisible()) {
                menuZamkniete = true;
                this.dane = menu.dane;
                menu.dispose();
                start();
            }else if (menuZamkniete) {
                this.tura+=1;
                listaZwierzat.wykonajPetle();
                symulacja.sendListaZwierzat(listaZwierzat.getLista());
                symulacja.wykonajPetle(this.tura,czyZwloki);
                symulacja.repaint();
                statystyki.repaint();
                int sumaZwierzat=listaZwierzat.iloscZwierzat.values().stream().mapToInt(Integer::intValue).sum();
                if(sumaZwierzat==0 || sumaZwierzat>=10000)
                    koniec();
            }
        }
    }
    void ustawPetle(Integer odswiezanie1) {
        petla=new Timer();
        petla.schedule(new TimerTask() {
            public void run() {
                rysuj();
                if(!odswiezanie1.equals(odswiezanie)) {
                    petla.cancel();
                    odswiezanie = odswiezanie1;
                    ustawPetle(odswiezanie1);
                }
            }
        }, 0, odswiezanie);
    }
    private void koniec(){
        System.out.println("nice");
        stop();
        koncoweStatystyki.put("Tury",Float.parseFloat(Integer.toString(tura)));
        this.podsumowanie=new Podsumowanie(koncoweStatystyki);
        dispose();
    }

}
