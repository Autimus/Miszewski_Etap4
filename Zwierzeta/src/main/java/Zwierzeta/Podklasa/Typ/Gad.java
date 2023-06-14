package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

public class Gad extends Dorosle {
    private int najedzony;

    public Gad(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
        this.najedzony=0;
    }

    @Override
    public void zdobadzJedzenie(float jedzenie) {
        super.zdobadzJedzenie(jedzenie);
        if(jedzenie > rozmiar) {
            najedzony = 2;
            sila=-1;
            szybkosc-=1;
        }
    }

    @Override
    public void jedz(float ile) {
        super.jedz(ile);
        if (--najedzony==0) {
            sila += 1;
            szybkosc +=1;
        }
    }
}
