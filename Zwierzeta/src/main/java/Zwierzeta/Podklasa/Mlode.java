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
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     * @param typ         Enum TypMlodych
     * @param kolejnyEtap Gatunek zwierz&#x119;cia gdy doro&#x15B;nie
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
