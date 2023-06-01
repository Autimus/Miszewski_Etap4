package Zwierzeta.Podklasa;

import Pomocnicze.Jedzenie;
import Pomocnicze.TypMlodych;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Mlode extends Zwierze {
    TypMlodych typ;
    public String kolejnyEtap;
    int dorastanie;

    public Mlode(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, TypMlodych typ, String kolejnyEtap, int dorastanie) {
        super(wstepne, szybkosc, sila, rozmiar, coJe);
        this.typ = typ;
        this.kolejnyEtap = kolejnyEtap;
        this.dorastanie = dorastanie;
    }
    @Override
    public void smierc() {
        if(rozmiar!=0) {
            super.smierc();
            iloscZwierzat.put(typ.name(), iloscZwierzat.get(typ.name()) - 1);
            iloscZwierzat.put("Mlode", iloscZwierzat.get("Mlode") - 1);
        }
    }
}
