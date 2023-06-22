package Utility;

import Pola.Pole;
import Pomocnicze.*;
import Zwierzeta.Zwierze;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import static Pomocnicze.TypPola.Woda;
import static Utility.Aplikacja.listaZwierzat;

/**
 * The type Symulacja.
 */
public class Symulacja extends JPanel {
    private Koordy rozmiarSymulacji;
    private final Koordy kafelki;
    private final Koordy rozmiarKafelek;
    /**
     * The Lista pol.
     */
    public List<Pole> listaPol=new ArrayList<>();
    /**
     * The Pola wody.
     */
    public List<Koordy> polaWody=new ArrayList<>();
    private boolean zwloki;

    /**
     * Instantiates a new Symulacja.
     *
     * @param rozmiarOkna rozmiar okna typu Koordy, zawiera wymiary okna Aplikacji wyra&#x17C;one w pixelach
     * @param kafelki     rozmiar mapy typu Koordy, okre&#x15B;laj&#x105;cy z ilu kafelek sk&#x142;ada si&#x119; mapa w wymiarze X oraz Y
     */
    public Symulacja(Koordy rozmiarOkna, Koordy kafelki) {
        this.rozmiarSymulacji=new Koordy((int)(rozmiarOkna.x*0.8),rozmiarOkna.y-41);
        this.rozmiarSymulacji=new Koordy(rozmiarSymulacji.x- rozmiarSymulacji.x% kafelki.x, rozmiarSymulacji.y- rozmiarSymulacji.y% kafelki.y);
        setPreferredSize(new Dimension(rozmiarSymulacji.x+2, rozmiarSymulacji.y+2));
        this.kafelki = kafelki;
        this.rozmiarKafelek=new Koordy(rozmiarSymulacji.x/kafelki.x, rozmiarSymulacji.y/kafelki.y);
        this.generujMape();
    }

    /**
     * Rysuje wszystkie pola mapy oraz linie oddzielajace.
     *
     * @param g the Graphics g
     */
    public void rysujKafelki(Graphics g){
        for(Pole kafelek: listaPol){
            g.setColor(kafelek.getKolor());
            g.fillRect(kafelek.getMiejsce().x* rozmiarKafelek.x, kafelek.getMiejsce().y* rozmiarKafelek.y,rozmiarKafelek.x, rozmiarKafelek.y);
        }
        g.setColor(new Color(0,0,0));
        for(int i = 0;i<=kafelki.x;i++){
            g.drawLine(i*rozmiarKafelek.x,0,i* rozmiarKafelek.x,rozmiarSymulacji.y);
        }
        for(int i = 0;i<=kafelki.y;i++){
            g.drawLine(0,i*rozmiarKafelek.y,rozmiarSymulacji.x,i*rozmiarKafelek.y);
        }
    }

    /**
     * Generuj&#x119; now&#x105; losow&#x105; map&#x119; na podstawie wymiar&oacute;w okna oraz mapy. Lokalizacja oraz typ pol s&#x105; losowe, lecz pola wody mog&#x105; wyst&#x119;powa&#x107; jedynie obok innego pola wody.
     */
    private void generujMape(){
        java.util.List<Koordy> pustePola=new ArrayList<>();
        for (int x=0;x< kafelki.x;x++){
            for (int y=0;y< kafelki.y;y++){
                pustePola.add(new Koordy(x,y));
            }
        }
        Random random= new Random();
        List<Koordy> pustePolaObokWody=new ArrayList<>();
        boolean pierwszePole=true;
        boolean mozliwaWoda=true;
        while(pustePola.size()!=0){
            int losowyIndex=random.nextInt(pustePola.size());
            if(pierwszePole){
                stworzPole(pustePola.get(losowyIndex),Woda);
                polaWody.add(pustePola.remove(losowyIndex));
                pierwszePole=false;
            }else {
                TypPola losowyTyp = TypPola.values()[random.nextInt(TypPola.values().length)];
                if (losowyTyp == Woda) {
                    if(!mozliwaWoda)
                        continue;
                    pustePolaObokWody.clear();
                    for (Koordy poleWody: polaWody) {
                        int[][] sasiedzi={{-1,0},{1,0},{0,-1},{0,1}};
                        for(int[] sasiad: sasiedzi){
                            try{
                                pustePolaObokWody.add(pustePola.get(pustePola.indexOf(new Koordy(poleWody.x+sasiad[0],poleWody.y+sasiad[1]))));
                            }catch(Exception ignored){}
                        }
                    }
                    if(pustePolaObokWody.size()==0)
                        mozliwaWoda=false;
                    else{
                        losowyIndex=pustePola.indexOf(pustePolaObokWody.get(random.nextInt(pustePolaObokWody.size())));
                        stworzPole(pustePola.get(losowyIndex),Woda);
                        polaWody.add(pustePola.remove(losowyIndex));
                    }
                }else{
                    stworzPole(pustePola.remove(losowyIndex),losowyTyp);
                }
            }
        }
        listaPol.sort((pole1,pole2)-> pole1.getId()- pole2.getId());
    }

    /**
     * Tworzy nowe Pole, nadaje mu odpowiednie statystyki na podstawie jego typu.
     * @param gdzie lokalizacja tworzonego miejsca typu Koordy
     * @param typ typ tworzonego Pola (wartos&#x107; Enum TypPola)
     */
    private void stworzPole(Koordy gdzie, TypPola typ){
        Color kolor=new Color(255,255,255);
        float ileGeneruje=0.5f;
        int czasGeneracji=1;
        switch (typ){
            case Woda:
                kolor=new Color(0,100,200);
                ileGeneruje+=0.5f;
                czasGeneracji+=1;
                break;
            case Trawa:
                kolor=new Color(150,255,50);
                break;
            case Drzewa:
                kolor=new Color(0,100,50);
                ileGeneruje+=5.5f;
                czasGeneracji+=2;
                break;
            case Krzaki:
                kolor=new Color(0,255,0);
                ileGeneruje+=2.5f;
                czasGeneracji+=1;
                break;
            case Ziemia:
                kolor=new Color(235,190,103);
                czasGeneracji+=3;
                break;
        }
        listaPol.add(new Pole(gdzie.x+ gdzie.y*kafelki.y,typ,gdzie,ileGeneruje,czasGeneracji,kolor));
    }

    /**
     * Rysuje wszystkie zwierz&#x119;ta z sta&#x142;ej zmiennej listaZwierz&#x105;t zaimportowanej z Utility.Aplikacja.
     *
     * @param g the Graphics g
     */
    public synchronized void rysujZwierzeta(Graphics g) {
        try {
            for (Zwierze zwierze : listaZwierzat) {
                if(zwloki || zwierze.czyZyje())
                    g.drawImage((new ImageIcon("Utility/src/main/resources/" + zwierze.getGrafika())).getImage(),
                        rozmiarKafelek.x * zwierze.getMiejsce().x, rozmiarKafelek.y * zwierze.getMiejsce().y, rozmiarKafelek.x, rozmiarKafelek.y, this);
            }
        }catch (Exception ignored){}
    }

    /**
     * Wykonaj petle.
     *
     * @param tura   obecna tura - int
     * @param zwloki wyra&#x17C;a czy wy&#x15B;wietla&#x107; na symulacji zwloki zwierz&#x105;t
     */
    public synchronized void wykonajPetle(int tura,boolean zwloki){
        this.zwloki = zwloki;
        for (Pole pole: this.listaPol) {
            if(tura%pole.getCzasGeneracji()==0) //Jeżeli minął określony czas generacji jedzenia
                pole.generujJedzenie();
        }
    }

    /**
     * Przy ka&#x17C;dym wywo&#x142;aniu paint albo repaint maluje map&#x119; symulacji oraz zwierz&#x119;ta, korzystajac z metod rysujKafelki() oraz rysujZwierzeta()
     * @param grap the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics grap) {
        super.paintComponent(grap);
        Graphics2D g = (Graphics2D) grap;
        rysujKafelki(g);
        rysujZwierzeta(g);
    }
}
