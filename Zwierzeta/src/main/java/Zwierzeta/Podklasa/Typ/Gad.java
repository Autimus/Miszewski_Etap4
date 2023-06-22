package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

/**
 * The type Gad.
 */
public class Gad extends Dorosle {
    private int najedzony;

    /**
     * Instantiates a new Gad.
     *
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param dlugoscZycia ile tur od utworzenia do śmierci ze starości
     * @param gatunek      Enum Gatunek
     */
    public Gad(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
        this.najedzony=0;
    }

    /**
     * Jeżeli gad zje wiecej jedzenia niż posiada rozmiar staje sie najedzony na kilka tur. Najedzenie zmniejsza siłę oraz szybkość gada.
     * @param jedzenie the jedzenie
     */
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
