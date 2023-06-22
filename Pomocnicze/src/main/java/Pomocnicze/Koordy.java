package Pomocnicze;

import java.util.Objects;
import java.util.Random;

/**
 * The type Koordy.
 */
public class Koordy {
    /**
     * wartość int w wymiarze X
     */
    public int x, /**
     * wartość int w wymiarze Y
     */
    y;

    /**
     * Systematyzuje wymiary w dwóch wymiarach X oraz Y, uławiając do nich dostęp, poprzez uzycie publicznych atrybutów.
     *
     * @param x wartość int w wymiarze X
     * @param y wartość int w wymiarze Y
     */
    public Koordy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Tworzy nowy obiekt z wymiarami x=-1, y=-1.
     */
    public Koordy() {
        this.x = -1;
        this.y = -1;
    }

    /**
     * Zwraca odległość obecnego obiektu Koordy od zadanego w parametrach. Liczona jest poruszając sie jedynie prawo, lewo, gora, dół
     *
     * @param koordy Koordy oodalone od obecnych
     * @return odległość
     */
    public int odleglosc(Koordy koordy){
        return Math.abs(x- koordy.x)+Math.abs(y- koordy.y);
    }

    /**
     * Jeżeli x==-1 oraz y==-1 Koordy są null
     *
     * @return the boolean
     */
    public boolean isNull(){
        return x==-1&&y==-1;
    }

    /**
     * Tworzy nowe koordy z losowymi zmiennymi stałymi, poniżej zadanych parametrów
     *
     * @param underX poniżej jakiego X ma być zwracana wartość X
     * @param underY poniżej jakiego Y ma być zwracana wartość Y
     * @return the koordy
     */
    public Koordy losoweMiejsce(int underX,int underY){
        Random random = new Random();
        return new Koordy(random.nextInt(underX), random.nextInt(underY));
    }

    /**
     * Porównuje swoje x oraz y z innym obiektem Koordy.
     * @param o Obiekt to sprawdzenia. Wartośc true jest możliwa do zwrócenia jedynie kiedy Obiekt jest typu Koordy
     * @return true jeżeli x oraz y są sobie równe, w przeciwnym wypadku false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Koordy koordy = (Koordy) o;
        return x == koordy.x && y == koordy.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Koordy{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
