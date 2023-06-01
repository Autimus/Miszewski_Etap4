package Zwierzeta;

import Pomocnicze.Jedzenie;
import Pomocnicze.Koordy;

public class Zwierze {
    public int id;
    int wiek;
    int szybkosc;
    int sila;
    protected float rozmiar;
    public Koordy miejsce;
    Jedzenie coJe;
    public String grafika;
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
    public void jedz(){

    }
    public void smierc(){
        grafika="Smierc.png";
        szybkosc=0;
        sila=0;
        rozmiar=0;
    }
    public void starzej(){

    }

    public int getSila() {
        return sila;
    }

    public float getRozmiar() {
        return rozmiar;
    }

    public float getJedzenie() {
        return jedzenie;
    }

    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);
        return ((Zwierze)obj).id==id;
    }
}
