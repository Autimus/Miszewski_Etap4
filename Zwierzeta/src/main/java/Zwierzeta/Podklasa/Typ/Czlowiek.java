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
     * Plemie do ktorego przynależy czlowiek.
     */
    public Plemiona plemie;

    /**
     * Instantiates a new Czlowiek.
     *
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param dlugoscZycia ile tur od utworzenia do śmierci ze starości
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
