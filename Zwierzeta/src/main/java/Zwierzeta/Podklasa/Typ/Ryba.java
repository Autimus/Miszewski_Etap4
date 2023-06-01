package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

public class Ryba extends Dorosle {
    boolean zanurzona=false;
    public Ryba(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    public void nurkuj(){

    }
}
