package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Pomocnicze.Koordy;
import Zwierzeta.Zwierze;

public class Ptak extends Dorosle {
    boolean wPowietrzu=false;
    public Ptak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    public void lot(Koordy gdzie){

    }
}
