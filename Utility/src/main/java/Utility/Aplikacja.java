package Utility;

import Pomocnicze.Koordy;
import Zwierzeta.ListaZwierzat;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

class Aplikacja extends JFrame {
    private Menu menu;
    private Koordy rozmiarOkna,kafelki;
    public ListaZwierzat listaZwierzat;
    private boolean menuZamkniete=false;
    private Map<String,Integer> dane;
    Panel panel;

    public static void main(String[] args) {
        java.util.Timer timer = new Timer();
        Aplikacja app = new Aplikacja(1280,720,20,20);
        timer.schedule(new Petla(app),0,1000);
    }
    private Aplikacja(int rozmiarOknaX,int rozmiarOknaY, int kafelkiX, int kafelkiY) {
        menu=new Menu();
        rozmiarOkna=new Koordy(rozmiarOknaX,rozmiarOknaY);
        kafelki=new Koordy(kafelkiX,kafelkiY);
    }
    private void start(){
        kafelki.x=dane.remove("Szerokosc");
        kafelki.y=dane.remove("Wysokosc");

        panel=new Panel(rozmiarOkna,kafelki);
        listaZwierzat=new ListaZwierzat(kafelki,panel.polaWody);
        Random random=new Random();
        for (Object wpis: dane.entrySet().toArray()) {
            String[] pomoc =wpis.toString().split("=");
            for(int i=0;i<Integer.valueOf(pomoc[1]);i++) {
                listaZwierzat.stworzZwierze(new Koordy().losoweMiejsce(kafelki.x, kafelki.y), pomoc[0]);
            }
        }
        System.out.println(listaZwierzat.iloscZwierzat.toString());

        add(panel);
        pack();
        setLayout(new BorderLayout());
        setSize(rozmiarOkna.x,rozmiarOkna.y);
        setTitle("Plemiona Amazonki");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void stop(){}
    public void rysuj() {
        if (!menuZamkniete && menu.isVisible() == false) {
            menuZamkniete = true;
            this.dane = menu.dane;
            menu.dispose();
            start();
        }else if (menuZamkniete) {
            listaZwierzat.wykonajPetle();
            panel.sendListaZwierzat(listaZwierzat.getLista());
            panel.repaint();
        }
    }
}
