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
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param dlugoscZycia ile tur od utworzenia do &#x15B;mierci ze staro&#x15B;ci
     * @param gatunek      Enum Gatunek
     */
    public Ryba(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    /**
     * Tyba potrafi w przypadku g&#x142;odu zanurzy&#x107; si&#x119; i znale&#x17A;&#x107; jakiekolwiek jedzenie (pomija metod&#x119; "jedz()" dla jednego obiegu petli)
     * @param ile ile ulamka swojego rozmiaru ma zje&#x15B;&#x107;
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
