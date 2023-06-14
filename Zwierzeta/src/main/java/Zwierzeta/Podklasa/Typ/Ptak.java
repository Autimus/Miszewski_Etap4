package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

public class Ptak extends Dorosle {
    private boolean wPowietrzu=false;
    public Ptak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    public void lot(){
        wPowietrzu=true;
    }

    @Override
    public void zdobadzJedzenie(float jedzenie) {
        if(wPowietrzu){
            wPowietrzu=false;
            super.zdobadzJedzenie(jedzenie/3f);
        }
        else
            super.zdobadzJedzenie(jedzenie);
    }
}
