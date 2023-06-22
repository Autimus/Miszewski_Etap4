package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

/**
 * The type Ryba.
 */
public class Ryba extends Dorosle {
    private boolean zanurzona=false;

    /**
     * Instantiates a new Ryba.
     *
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param dlugoscZycia ile tur od utworzenia do śmierci ze starości
     * @param gatunek      Enum Gatunek
     */
    public Ryba(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    /**
     * Tyba potrafi w przypadku głodu zanurzyć się i znaleźć jakiekolwiek jedzenie (pomija metodę "jedz()" dla jednego obiegu petli)
     * @param ile ile ulamka swojego rozmiaru ma zjeść
     */
    @Override
    public void jedz(float ile) {
        if(jedzenie<rozmiar*ile && !zanurzona)
            zanurzona=true;
        else{
            zanurzona=false;
            super.jedz(ile);
        }
    }
}
