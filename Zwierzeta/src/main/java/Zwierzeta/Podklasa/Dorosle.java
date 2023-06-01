package Zwierzeta.Podklasa;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Pomocnicze.Koordy;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Dorosle extends Zwierze {
    int dlugoscZycia;
    public Gatunek gatunek;

    public Dorosle(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe);
        this.dlugoscZycia = dlugoscZycia;
        this.gatunek = gatunek;
    }
    @Override
    public void smierc() {
        if(rozmiar!=0) {
            super.smierc();
            iloscZwierzat.put(gatunek.name(), iloscZwierzat.get(gatunek.name()) - 1);
            if (!gatunek.equals(Gatunek.Czlowiek)) {
                iloscZwierzat.put("DorosleNieCzlowiek", iloscZwierzat.get("DorosleNieCzlowiek") - 1);
                System.out.println(getClass().getSimpleName());
                iloscZwierzat.put(getClass().getSimpleName(), iloscZwierzat.get(getClass().getSimpleName()) - 1);
            }
        }
    }
}
