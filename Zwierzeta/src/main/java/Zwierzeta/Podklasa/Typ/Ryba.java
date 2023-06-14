package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

public class Ryba extends Dorosle {
    private boolean zanurzona=false;
    public Ryba(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }
    @Override
    public void jedz(float ile) {
        if(jedzenie<rozmiar*ile && !zanurzona) //Pozwala w przypadku głodu zanurzyć się i znaleźć jakiekolwiek jedzenie (pomija metodę "jedz()" dla jednego obiegu petli)
            zanurzona=true;
        else{
            zanurzona=false;
            super.jedz(ile);
        }
    }
}
