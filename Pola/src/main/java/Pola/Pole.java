package Pola;

import Pomocnicze.Koordy;
import Pomocnicze.TypPola;

import java.awt.*;

public class Pole {
    public int id;
    TypPola typ;
    public Koordy miejsce;
    public float jedzenie;
    float ileGeneruje;
    int czasGeneracji;
    public Color kolor;

    public Pole(int id, TypPola typ, Koordy miejsce, float ileGeneruje, int czasGeneracji, Color kolor) {
        this.id = id;
        this.typ = typ;
        this.miejsce = miejsce;
        this.jedzenie = ileGeneruje*2;
        this.ileGeneruje = ileGeneruje;
        this.czasGeneracji = czasGeneracji;
        this.kolor = kolor;
    }

    public Koordy getMiejsce() {
        return miejsce;
    }

    public void generujJedzenie(){

    }
}
