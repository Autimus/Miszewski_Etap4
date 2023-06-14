package Zwierzeta.Podklasa;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Zwierze;

import static Utility.Aplikacja.koncoweStatystyki;
import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Dorosle extends Zwierze {
    private int dlugoscZycia;
    private Gatunek gatunek;

    public Dorosle(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe);
        this.dlugoscZycia = dlugoscZycia;
        this.gatunek = gatunek;
    }
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
