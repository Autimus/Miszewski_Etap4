package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

/**
 * The type Ptak.
 */
public class Ptak extends Dorosle {
    private boolean wPowietrzu=false;

    /**
     * Instantiates a new Ptak.
     *
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param dlugoscZycia ile tur od utworzenia do &#x15B;mierci ze staro&#x15B;ci
     * @param gatunek      Enum Gatunek
     */
    public Ptak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    /**
     * Ptak za pomoc&#x105; lotu ma szans&#x119; na ucieczk&#x119; z walki, jednak&#x17C;e b&#x119;d&#x105;c w powietrzu jest w stanie zbiera&#x107; jedynie 1/3 znalezionego jedzenia.
     */
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
