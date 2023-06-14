package Zwierzeta;

import Pomocnicze.Jedzenie;
import Pomocnicze.Koordy;

import static Utility.Aplikacja.koncoweStatystyki;

public class Zwierze {
    protected int id;
    protected int wiek;
    protected int szybkosc;
    protected int sila;
    protected float rozmiar;
    protected Koordy miejsce;
    protected Jedzenie coJe;
    protected String grafika;
    protected float jedzenie;

    public Zwierze(int id, int wiek, Koordy miejsce, String grafika) { //Wstepny konstruktor dla kazdego zwierzecia
        this.id = id;
        this.wiek = wiek;
        this.miejsce=miejsce;
        this.grafika=grafika;
    }

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

    public Koordy getMiejsce() {
        return miejsce;
    }

    public void ruch(Koordy gdzie){
        this.miejsce=gdzie;
    }
    public void zdobadzJedzenie(float jedzenie){
        this.jedzenie+=jedzenie;
    }
    public void jedz(float ile){
        if(czyZyje()) {
            this.jedzenie-=this.rozmiar*ile;
            if (this.jedzenie < 0) {
                this.smierc();
                koncoweStatystyki.put("Smierci z glodu",koncoweStatystyki.get("Smierci z glodu")+1);
            }
        }
    }
    public void smierc(){
        grafika="Smierc.png";
        szybkosc=0;
        sila=0;
        rozmiar=0;
    }
    public boolean starzej(){
        if(czyZyje()) {
            this.wiek += 1;
            return true;
        }else return false;
    }

    public boolean czyZyje(){
        return (this.rozmiar!=0);
    }
    public int getId() {
        return id;
    }

    public int getSila() {
        return sila;
    }

    public int getSzybkosc() {
        return szybkosc;
    }

    public float getRozmiar() {
        return rozmiar;
    }

    public float getJedzenie() {
        return jedzenie;
    }

    public String getGrafika() {
        return grafika;
    }

    public Jedzenie getCoJe() {
        return coJe;
    }

    public int getWiek() {
        return wiek;
    }

    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);
        return ((Zwierze)obj).id==id;
    }
}
