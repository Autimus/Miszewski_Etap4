package Zwierzeta.Podklasa;

import Pomocnicze.Jedzenie;
import Pomocnicze.TypMlodych;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;

public class Mlode extends Zwierze {
    protected TypMlodych typ;
    protected String kolejnyEtap;
    protected int dorastanie;

    public Mlode(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, TypMlodych typ, String kolejnyEtap, int dorastanie) {
        super(wstepne, szybkosc, sila, rozmiar, coJe);
        this.typ = typ;
        this.kolejnyEtap = kolejnyEtap;
        this.dorastanie = dorastanie;
    }

    public TypMlodych getTyp() {
        return typ;
    }

    public String getKolejnyEtap() {
        return kolejnyEtap;
    }

    @Override
    public void smierc() {
        if(this.czyZyje()) {
            super.smierc();
            iloscZwierzat.put(typ.name(), iloscZwierzat.get(typ.name()) - 1);
            iloscZwierzat.put("Mlode", iloscZwierzat.get("Mlode") - 1);
        }
    }

    @Override
    public boolean starzej() {
        if(!super.starzej())
            return false;
        if(this.wiek>=this.dorastanie)
            return true;
        return false;
    }
}
