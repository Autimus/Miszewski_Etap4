package Pola;

import Pomocnicze.Koordy;
import Pomocnicze.TypPola;

import java.awt.*;

public class Pole {
    private int id;
    private TypPola typ;
    private Koordy miejsce;
    private float jedzenie;
    private float ileGeneruje;
    private int czasGeneracji;
    private Color kolor;

    public Pole(int id, TypPola typ, Koordy miejsce, float ileGeneruje, int czasGeneracji, Color kolor) {
        this.id = id;
        this.typ = typ;
        this.miejsce = miejsce;
        this.jedzenie = ileGeneruje * 2;
        this.ileGeneruje = ileGeneruje;
        this.czasGeneracji = czasGeneracji;
        this.kolor = kolor;
    }

    public Koordy getMiejsce() {
        return miejsce;
    }

    public int getId() {
        return id;
    }

    public void generujJedzenie() {
        this.jedzenie+=ileGeneruje;
    }

    public float utracJedzenie() {
        float jedzenie = this.jedzenie;
        this.jedzenie = 0;
        return jedzenie;
    }

    public int getCzasGeneracji() {
        return czasGeneracji;
    }

    public Color getKolor() {
        return kolor;
    }

    public float getJedzenie() {
        return jedzenie;
    }
}
