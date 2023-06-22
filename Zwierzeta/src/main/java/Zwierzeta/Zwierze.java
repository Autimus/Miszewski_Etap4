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
     * wiek wyrażany w int
     */
    protected int wiek;
    /**
     * ile pól jest w stanei przejśc co turę
     */
    protected int szybkosc;
    /**
     * wykorzystywana w walce z innymi zwierzeciami
     */
    protected int sila;
    /**
     * wplywa na ilość zjadanego pożywienia
     */
    protected float rozmiar;
    /**
     * miejsce na mapie typu Koordy
     */
    protected Koordy miejsce;
    /**
     * Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     */
    protected Jedzenie coJe;
    /**
     * grafika zwierzęcia przechowywana jako String. Wszystkie grafiki znajdują się w \Utility\src\main\resources
     */
    protected String grafika;
    /**
     * ilość jedzenia jakie posiada aktualnie zwierze
     */
    protected float jedzenie;

    /**
     * Wstępny konstruktor zwierzęcia, bez doprecyzowania gatunku i konkretnej klasy.
     *
     * @param id      id
     * @param wiek    wiek wyrażany w int
     * @param miejsce miejsce na mapie typu Koordy
     * @param grafika grafika zwierzęcia przechowywana jako String. Wszystkie grafiki znajdują się w \Utility\src\main\resources
     */
    public Zwierze(int id, int wiek, Koordy miejsce, String grafika) { //Wstepny konstruktor dla kazdego zwierzecia
        this.id = id;
        this.wiek = wiek;
        this.miejsce=miejsce;
        this.grafika=grafika;
    }

    /**
     * Konstruktor używany przez dziedziczące klasy.
     *
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
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
     * Jedz. Jeżeli zwierze nie ma wystarczająco jedzenia umiera.
     *
     * @param ile ile ulamka swojego rozmiaru ma zjeść
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
     * Szybkość, siła oraz rozmiar jest ustalany na 0. Zwierze jest pomijane w wykonywaniu pętli. Może zostać zjedzone przez inne zwierzęcia. Zmienia się wyświetlana grafika na Smierc.png .
     */
    public void smierc(){
        grafika="Smierc.png";
        szybkosc=0;
        sila=0;
        rozmiar=0;
    }

    /**
     * Zwierze się starzeje, zwraca true jeżeli zwierze żyje.
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
     * Zwraca true jeżeli żyje, zwraca false, jeżeli została wykonana metoda smierc() .
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
