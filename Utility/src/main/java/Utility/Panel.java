package Utility;

import Pola.Pole;
import Pomocnicze.*;
import Zwierzeta.ListaZwierzat;
import Zwierzeta.Zwierze;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;

import static Pomocnicze.TypPola.Woda;
import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Panel extends JPanel {
    Koordy rozmiarOkna, rozmiarSymulacji ,kafelki, rozmiarKafelek;
    //    TypPola[][] mapa;
    public static List<Pole> listaPol=new ArrayList<>();
    public List<Koordy> polaWody=new ArrayList<>();
    List<Zwierze> listaZwierzat;
    public Panel(Koordy rozmiarOkna, Koordy kafelki) {
        setPreferredSize(new Dimension(rozmiarOkna.x, rozmiarOkna.y));
        this.rozmiarOkna = rozmiarOkna;
        this.rozmiarSymulacji=new Koordy((int)(this.rozmiarOkna.x*0.8),this.rozmiarOkna.y-41);
        this.rozmiarSymulacji=new Koordy(rozmiarSymulacji.x- rozmiarSymulacji.x% kafelki.x, rozmiarSymulacji.y- rozmiarSymulacji.y% kafelki.y);
        this.kafelki = kafelki;
        this.rozmiarKafelek=new Koordy(rozmiarSymulacji.x/kafelki.x, rozmiarSymulacji.y/kafelki.y);
//        this.mapa=new TypPola[kafelki.y][kafelki.x];
        this.generujMape();
    }
    public void rysujKafelki(Graphics g){
        for(Pole kafelek: listaPol){
            g.setColor(kafelek.kolor);
            g.fillRect(kafelek.miejsce.x* rozmiarKafelek.x, kafelek.miejsce.y* rozmiarKafelek.y,rozmiarKafelek.x, rozmiarKafelek.y);
        }
        g.setColor(new Color(0,0,0));
        for(int i = 0;i<=kafelki.x;i++){
            g.drawLine(i*rozmiarKafelek.x,0,i* rozmiarKafelek.x,rozmiarSymulacji.y);
        }
        for(int i = 0;i<=kafelki.y;i++){
            g.drawLine(0,i*rozmiarKafelek.y,rozmiarSymulacji.x,i*rozmiarKafelek.y);
        }
    }
    private void rysujStatystyki(Graphics g){
        Object[] kolejnoscWypisywania= Stream.concat(
                Stream.concat(
                        Arrays.stream(new Gatunek[]{Gatunek.Czlowiek}),
                        Arrays.stream(Plemiona.values())
                ),
                Stream.concat(
                        Stream.concat(
                                Arrays.stream(new Gatunek[]{Gatunek.DorosleNieCzlowiek}),
                                Arrays.stream(Gatunek.values()).filter(val->!val.equals(Gatunek.Czlowiek)&&!val.equals(Gatunek.DorosleNieCzlowiek))
                        ),
                        Stream.concat(
                                Arrays.stream(new TypMlodych[]{TypMlodych.Mlode}),
                                Arrays.stream(TypMlodych.values()).filter(val->!val.equals(TypMlodych.Mlode))
                        )
                )
        ).collect(Collectors.toList()).toArray();
        int line =1;
        for (Object zwierze: kolejnoscWypisywania) {
            if(line==1||line==10||line==26)
                g.setFont(new Font("Arial",Font.BOLD,15));
            else if(line==11||line==14||line==18||line==22)
                g.setFont(new Font("Arial Black",Font.ITALIC,12));
            else
                g.setFont(new Font("Arial",Font.PLAIN,12));
            g.drawString(zwierze+": "+iloscZwierzat.get(zwierze.toString()), (int)(rozmiarOkna.x*0.8),line*15);
            line++;
        }
    }
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
                            }catch(Exception e){}
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
        listaPol.sort((pole1,pole2)-> pole1.id- pole2.id);
    }
    private void stworzPole(Koordy gdzie, TypPola typ){
        Color kolor=new Color(255,255,255);
        float ileGeneruje=0.5F;
        int czasGeneracji=1;
        switch (typ){
            case Woda:
                kolor=new Color(0,100,200);
                break;
            case Trawa:
                kolor=new Color(150,255,50);
                break;
            case Drzewa:
                kolor=new Color(0,100,50);
                ileGeneruje+=5.5;
                czasGeneracji+=2;
                break;
            case Krzaki:
                kolor=new Color(0,255,0);
                ileGeneruje+=1.5;
                czasGeneracji+=1;
                break;
            case Ziemia:
                kolor=new Color(235,190,103);
                czasGeneracji+=2;
                break;
        }
        listaPol.add(new Pole(gdzie.x+ gdzie.y*kafelki.y,typ,gdzie,ileGeneruje,czasGeneracji,kolor));
    }
    private void rysujZwierzeta(Graphics g) {
        if(!listaZwierzat.equals(null))
            for (Zwierze zwierze : listaZwierzat) {
                g.drawImage((new ImageIcon("Utility/src/main/resources/" + zwierze.grafika)).getImage(),
                        rozmiarKafelek.x * zwierze.getMiejsce().x, rozmiarKafelek.y * zwierze.getMiejsce().y, rozmiarKafelek.x, rozmiarKafelek.y, this);
            }
    }
    public void sendListaZwierzat(List<Zwierze> listaZwierzat){
        this.listaZwierzat=listaZwierzat;
    }

    @Override
    protected void paintComponent(Graphics grap) {
        super.paintComponent(grap);
        Graphics2D g = (Graphics2D) grap;
        rysujKafelki(g);
        rysujStatystyki(g);
        rysujZwierzeta(g);
    }
}
