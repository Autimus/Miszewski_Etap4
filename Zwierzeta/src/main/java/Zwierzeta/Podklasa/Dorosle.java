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
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param dlugoscZycia ile tur od utworzenia do śmierci ze starości
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
     * Postarza zwierze. Jeżeli jego wiek jest większy od dlugości życia, zwierze umiera ze starości.
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
