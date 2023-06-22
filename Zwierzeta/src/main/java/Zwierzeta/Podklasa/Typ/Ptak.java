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
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param dlugoscZycia ile tur od utworzenia do śmierci ze starości
     * @param gatunek      Enum Gatunek
     */
    public Ptak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    /**
     * Ptak za pomocą lotu ma szansę na ucieczkę z walki, jednakże będąc w powietrzu jest w stanie zbierać jedynie 1/3 znalezionego jedzenia.
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
