package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Pomocnicze.Plemiona;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Czlowiek extends Ssak {
    public Plemiona plemie;

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
