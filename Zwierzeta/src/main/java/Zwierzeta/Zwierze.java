package Zwierzeta;

import Pomocnicze.Jedzenie;
import Pomocnicze.Koordy;

import static Zwierzeta.ListaZwierzat.koncoweStatystyki;

/**
 * The type Zwierze.
 */
public class Zwierze {
    /**
     * Id
     */
    protected int id;
    /**
     * wiek wyra&#x17C;any w int
     */
    protected int wiek;
    /**
     * ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     */
    protected int szybkosc;
    /**
     * wykorzystywana w walce z innymi zwierzeciami
     */
    protected int sila;
    /**
     * wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     */
    protected float rozmiar;
    /**
     * miejsce na mapie typu Koordy
     */
    protected Koordy miejsce;
    /**
     * Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     */
    protected Jedzenie coJe;
    /**
     * grafika zwierz&#x119;cia przechowywana jako String. Wszystkie grafiki znajduj&#x105; si&#x119; w \Utility\src\main\resources
     */
    protected String grafika;
    /**
     * ilo&#x15B;&#x107; jedzenia jakie posiada aktualnie zwierze
     */
    protected float jedzenie;

    /**
     * Wst&#x119;pny konstruktor zwierz&#x119;cia, bez doprecyzowania gatunku i konkretnej klasy.
     *
     * @param id      id
     * @param wiek    wiek wyra&#x17C;any w int
     * @param miejsce miejsce na mapie typu Koordy
     * @param grafika grafika zwierz&#x119;cia przechowywana jako String. Wszystkie grafiki znajduj&#x105; si&#x119; w \Utility\src\main\resources
     */
    public Zwierze(int id, int wiek, Koordy miejsce, String grafika) { //Wstepny konstruktor dla kazdego zwierzecia
        this.id = id;
        this.wiek = wiek;
        this.miejsce=miejsce;
        this.grafika=grafika;
    }

    /**
     * Konstruktor u&#x17C;ywany przez dziedzicz&#x105;ce klasy.
     *
     * @param wstepne  wst&#x119;pne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafik&#x119;.
     * @param szybkosc szybkosc - ile p&oacute;l jest w stanei przej&#x15B;c co tur&#x119;
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilo&#x15B;&#x107; zjadanego po&#x17C;ywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze mo&#x17C;e je&#x15B;&#x107; mi&#x119;so albo ro&#x15B;liny
     */
    public Zwierze(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe) { //Pełny konstruktor, na podstawie wstepnego zwierzecia
        this.id = wstepne.id;
        this.wiek = wstepne.wiek;
        this.miejsce = wstepne.miejsce;
        this.grafika=wstepne.grafika;
        this.szybkosc = szybkosc;
        this.sila = sila;
        this.rozmiar = rozmiar;
        this.coJe = coJe;
        this.jedzenie=rozmiar*2;
    }

    /**
     * Gets miejsce.
     *
     * @return the miejsce
     */
    public Koordy getMiejsce() {
        return miejsce;
    }

    /**
     * Ruch.
     *
     * @param gdzie the gdzie
     */
    public void ruch(Koordy gdzie){
        this.miejsce=gdzie;
    }

    /**
     * Zdobadz jedzenie.
     *
     * @param jedzenie the jedzenie
     */
    public void zdobadzJedzenie(float jedzenie){
        this.jedzenie+=jedzenie;
    }

    /**
     * Jedz. Je&#x17C;eli zwierze nie ma wystarczaj&#x105;co jedzenia umiera.
     *
     * @param ile ile ulamka swojego rozmiaru ma zje&#x15B;&#x107;
     */
    public void jedz(float ile){
        if(czyZyje()) {
            this.jedzenie-=this.rozmiar*ile;
            if (this.jedzenie < 0) {
                this.smierc();
                koncoweStatystyki.put("Smierci z glodu",koncoweStatystyki.get("Smierci z glodu")+1);
            }
        }
    }

    /**
     * Szybko&#x15B;&#x107;, si&#x142;a oraz rozmiar jest ustalany na 0. Zwierze jest pomijane w wykonywaniu p&#x119;tli. Mo&#x17C;e zosta&#x107; zjedzone przez inne zwierz&#x119;cia. Zmienia si&#x119; wy&#x15B;wietlana grafika na Smierc.png .
     */
    public void smierc(){
        grafika="Smierc.png";
        szybkosc=0;
        sila=0;
        rozmiar=0;
    }

    /**
     * Zwierze si&#x119; starzeje, zwraca true je&#x17C;eli zwierze &#x17C;yje.
     *
     * @return the boolean
     */
    public boolean starzej(){
        if(czyZyje()) {
            this.wiek += 1;
            return true;
        }else return false;
    }

    /**
     * Zwraca true je&#x17C;eli &#x17C;yje, zwraca false, je&#x17C;eli zosta&#x142;a wykonana metoda smierc() .
     *
     * @return the boolean
     */
    public boolean czyZyje(){
        return (this.rozmiar!=0);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets sila.
     *
     * @return the sila
     */
    public int getSila() {
        return sila;
    }

    /**
     * Gets szybkosc.
     *
     * @return the szybkosc
     */
    public int getSzybkosc() {
        return szybkosc;
    }

    /**
     * Gets rozmiar.
     *
     * @return the rozmiar
     */
    public float getRozmiar() {
        return rozmiar;
    }

    /**
     * Gets jedzenie.
     *
     * @return the jedzenie
     */
    public float getJedzenie() {
        return jedzenie;
    }

    /**
     * Gets grafika.
     *
     * @return the grafika
     */
    public String getGrafika() {
        return grafika;
    }

    /**
     * Gets co je.
     *
     * @return the co je
     */
    public Jedzenie getCoJe() {
        return coJe;
    }

    /**
     * Gets wiek.
     *
     * @return the wiek
     */
    public int getWiek() {
        return wiek;
    }

    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);
        return ((Zwierze)obj).id==id;
    }
}
