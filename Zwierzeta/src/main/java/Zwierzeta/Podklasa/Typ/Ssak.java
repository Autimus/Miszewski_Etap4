package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

public class Ssak extends Dorosle {
    public int ciaza=-1;

    public Ssak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    public boolean urodz(){
        return --ciaza==0;
    }
    public void setCiaza(){
        ciaza = (ciaza==-1)? 2:ciaza;
    }
}
