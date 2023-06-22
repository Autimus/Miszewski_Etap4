package Pola;

import Pomocnicze.Koordy;
import Pomocnicze.TypPola;

import java.awt.*;

/**
 * The type Pole.
 */
public class Pole {
    private final int id;
    private final TypPola typ;
    private final Koordy miejsce;
    private float jedzenie;
    private final float ileGeneruje;
    private final int czasGeneracji;
    private final Color kolor;

    /**
     * Instantiates a new Pole.
     *
     * @param id            id pola
     * @param typ           typ pola - wartość Enum z TypPola
     * @param miejsce       miejsce na mapie wyrażone za pomocą Koordy
     * @param ileGeneruje   ile generuje jedzenia co turę
     * @param czasGeneracji ile tur zajmuje generacja jedzenia
     * @param kolor         wyświetlany kolor pola
     */
    public Pole(int id, TypPola typ, Koordy miejsce, float ileGeneruje, int czasGeneracji, Color kolor) {
        this.id = id;
        this.typ = typ;
        this.miejsce = miejsce;
        this.jedzenie = ileGeneruje * 2;
        this.ileGeneruje = ileGeneruje;
        this.czasGeneracji = czasGeneracji;
        this.kolor = kolor;
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
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Zwiększa jedzenie pola o ilość przechowywaną w zmiennej ileGeneruje.
     */
    public void generujJedzenie() {
        this.jedzenie+=ileGeneruje;
    }

    /**
     * Zeruje jedzenie pola i zwraca uzbierane jedzenie jako float
     *
     * @return Uzbierane na polu jedzenie jako float
     */
    public float utracJedzenie() {
        float jedzenie = this.jedzenie;
        this.jedzenie = 0;
        return jedzenie;
    }

    /**
     * Gets czas generacji.
     *
     * @return the czas generacji
     */
    public int getCzasGeneracji() {
        return czasGeneracji;
    }

    /**
     * Gets kolor.
     *
     * @return the kolor
     */
    public Color getKolor() {
        return kolor;
    }

    /**
     * Gets jedzenie.
     *
     * @return the jedzenie
     */
    public float getJedzenie() {
        return jedzenie;
    }
}
