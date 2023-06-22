package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Pomocnicze.Plemiona;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;

/**
 * The type Czlowiek.
 */
public class Czlowiek extends Ssak {
    /**
     * Plemie do ktorego przynale&#x17C;y czlowiek.
     */
    public Plemiona plemie;

    /**
     * Instantiates a new Czlowiek.
     *
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param dlugoscZycia ile tur od utworzenia do &#x15B;mierci ze staro&#x15B;ci
     * @param gatunek      Enum Gatunek
     * @param plemie       Enum Plemiona
     */
    public Czlowiek(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek, Plemiona plemie) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
        this.plemie = plemie;
    }
    @Override
    public void smierc() {
        if(rozmiar!=0) {
            super.smierc();
            iloscZwierzat.put(plemie.name(), iloscZwierzat.get(plemie.name()) - 1);
        }
    }
}
