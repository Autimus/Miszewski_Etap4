package Zwierzeta.Podklasa;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;
import static Zwierzeta.ListaZwierzat.koncoweStatystyki;

/**
 * The type Dorosle.
 */
public class Dorosle extends Zwierze {
    private final int dlugoscZycia;
    private final Gatunek gatunek;

    /**
     * Instantiates a new Dorosle.
     *
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param dlugoscZycia ile tur od utworzenia do &#x15B;mierci ze staro&#x15B;ci
     * @param gatunek      Enum Gatunek
     */
    public Dorosle(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe);
        this.dlugoscZycia = dlugoscZycia;
        this.gatunek = gatunek;
    }

    /**
     * Gets gatunek.
     *
     * @return the gatunek
     */
    public Gatunek getGatunek() {
        return gatunek;
    }
    @Override
    public void smierc() {
        if(this.rozmiar!=0) {
            super.smierc();
            iloscZwierzat.put(gatunek.name(), iloscZwierzat.get(gatunek.name()) - 1);
            if (!gatunek.equals(Gatunek.Czlowiek)) {
                iloscZwierzat.put("DorosleNieCzlowiek", iloscZwierzat.get("DorosleNieCzlowiek") - 1);
//                System.out.println(getClass().getSimpleName());
                iloscZwierzat.put(getClass().getSimpleName(), iloscZwierzat.get(getClass().getSimpleName()) - 1);
            }
        }
    }

    /**
     * Postarza zwierze. Je&#x17C;eli jego wiek jest wi&#x119;kszy od dlugo&#x15B;ci &#x17C;ycia, zwierze umiera ze staro&#x15B;ci.
     * @return boolean
     */
    @Override
    public boolean starzej() {
        if(!super.starzej())
            return false;
        if(this.wiek>=dlugoscZycia) {
            this.smierc();
            koncoweStatystyki.put("Smierci ze starosci",koncoweStatystyki.get("Smierci ze starosci")+1);
        }
        return false;
    }
}
