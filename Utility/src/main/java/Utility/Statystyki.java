package Utility;

import Pola.Pole;
import Pomocnicze.Gatunek;
import Pomocnicze.Koordy;
import Pomocnicze.Plemiona;
import Pomocnicze.TypMlodych;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Stream;

import static Utility.Aplikacja.koncoweStatystyki;
import static Utility.Aplikacja.tura;
import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Statystyki extends JPanel{
    private Koordy rozmiarStatystyk;
    public Statystyki(Koordy rozmiarOkna) {
        rozmiarStatystyk= new Koordy((int)(rozmiarOkna.x*0.2),(int)(rozmiarOkna.y*0.75));
//        setPreferredSize(new Dimension(rozmiarStatystyk.x+20, rozmiarStatystyk.y));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, rozmiarStatystyk.y+20));
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
                                Arrays.stream(Gatunek.values()).filter(val -> !val.equals(Gatunek.Czlowiek) && !val.equals(Gatunek.DorosleNieCzlowiek))
                        ),
                        Stream.concat(
                                Arrays.stream(new TypMlodych[]{TypMlodych.Mlode}),
                                Arrays.stream(TypMlodych.values()).filter(val -> !val.equals(TypMlodych.Mlode))
                        )
                )
        ).toArray();
        int line =1;
        int duzeLitery=(int)(rozmiarStatystyk.y/33.75);
        int maleLitery=(int)(duzeLitery*0.75);
        for (Object zwierze: kolejnoscWypisywania) {
            int tabulacja=0;
            if(line==1||line==10||line==26)
                g.setFont(new Font("Arial",Font.BOLD,duzeLitery));
            else if(line==11||line==14||line==18||line==22)
                g.setFont(new Font("Arial Black",Font.ITALIC,maleLitery));
            else {
                float populacja=iloscZwierzat.get(zwierze.toString());
                if(populacja>koncoweStatystyki.get("Najliczniejszy gatunek"))
                    koncoweStatystyki.put("Najliczniejszy gatunek",populacja);
                g.setFont(new Font("Arial", Font.PLAIN, maleLitery));
                tabulacja=1;
            }
            g.drawImage((new ImageIcon("Utility/src/main/resources/"+zwierze.toString()+".png")).getImage(),0,(line-1)*duzeLitery,duzeLitery,duzeLitery,this);
            g.drawString(zwierze+": "+iloscZwierzat.get(zwierze.toString()), tabulacja*duzeLitery,line*duzeLitery);
            line++;
        }
        g.setFont(new Font("Arial",Font.BOLD,duzeLitery));
        g.drawString("Tura nr. "+tura,0,++line*duzeLitery);
    }

    @Override
    protected void paintComponent(Graphics grap) {
        super.paintComponent(grap);
        Graphics2D g = (Graphics2D) grap;
        rysujStatystyki(g);
    }
}
