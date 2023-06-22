package Zwierzeta.Podklasa;

import Pomocnicze.Jedzenie;
import Pomocnicze.TypMlodych;
import Zwierzeta.Zwierze;

import static Zwierzeta.ListaZwierzat.iloscZwierzat;

/**
 * The type Mlode.
 */
public class Mlode extends Zwierze {
    /**
     * The Typ.
     */
    protected TypMlodych typ;
    /**
     * The Kolejny etap.
     */
    protected String kolejnyEtap;
    /**
     * The Dorastanie.
     */
    protected int dorastanie;

    /**
     * Instantiates a new Mlode.
     *
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param typ         Enum TypMlodych
     * @param kolejnyEtap Gatunek zwierzęcia gdy dorośnie
     * @param dorastanie  ile tur zajmuje dorastanie
     */
    public Mlode(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, TypMlodych typ, String kolejnyEtap, int dorastanie) {
        super(wstepne, szybkosc, sila, rozmiar, coJe);
        this.typ = typ;
        this.kolejnyEtap = kolejnyEtap;
        this.dorastanie = dorastanie;
    }

    /**
     * Gets typ.
     *
     * @return the typ
     */
    public TypMlodych getTyp() {
        return typ;
    }

    /**
     * Gets kolejny etap.
     *
     * @return the kolejny etap
     */
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
