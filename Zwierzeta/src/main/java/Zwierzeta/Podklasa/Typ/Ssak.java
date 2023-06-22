package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

/**
 * The type Ssak.
 */
public class Ssak extends Dorosle {
    /**
     * Po reprodukcji ssaki nie tworz&#x105; nowego zwierz&#x119;cia od razu, tylko dopiero po up&#x142;ywie czasu wyra&#x17C;onego jako liczba tur w tej zmiennej.
     */
    public int ciaza=-1;

    /**
     * Instantiates a new Ssak.
     *
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param dlugoscZycia ile tur od utworzenia do &#x15B;mierci ze staro&#x15B;ci
     * @param gatunek      Enum Gatunek
     */
    public Ssak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    /**
     * Je&#x17C;eli ci&#x105;&#x17C;a dobieg&#x142;a ko&#x144;ca, tworzy nowego m&#x142;odego ssaka.
     *
     * @return the boolean
     */
    public boolean urodz(){
        return --ciaza==0;
    }

    /**
     * Set ciaza.
     */
    public void setCiaza(){
        ciaza = (ciaza==-1)? 2:ciaza;
    }
}
