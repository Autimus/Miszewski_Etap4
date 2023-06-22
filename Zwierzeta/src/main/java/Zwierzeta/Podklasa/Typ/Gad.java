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
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param dlugoscZycia ile tur od utworzenia do &#x15B;mierci ze staro&#x15B;ci
     * @param gatunek      Enum Gatunek
     */
    public Gad(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
        this.najedzony=0;
    }

    /**
     * Je&#x17C;eli gad zje wiecej jedzenia ni&#x17C; posiada rozmiar staje sie najedzony na kilka tur. Najedzenie zmniejsza si&#x142;&#x119; oraz szybko&#x15B;&#x107; gada.
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
