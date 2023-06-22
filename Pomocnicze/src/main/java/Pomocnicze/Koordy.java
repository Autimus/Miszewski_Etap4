package Pomocnicze;

import java.util.Objects;
import java.util.Random;

/**
 * The type Koordy.
 */
public class Koordy {
    /**
     * warto&#x15B;&#x107; int w wymiarze X
     */
    public int x, /**
     * warto&#x15B;&#x107; int w wymiarze Y
     */
    y;

    /**
     * Systematyzuje wymiary w dw&oacute;ch wymiarach X oraz Y, u&#x142;awiaj&#x105;c do nich dost&#x119;p, poprzez uzycie publicznych atrybut&oacute;w.
     *
     * @param x warto&#x15B;&#x107; int w wymiarze X
     * @param y warto&#x15B;&#x107; int w wymiarze Y
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
     * Zwraca odleg&#x142;o&#x15B;&#x107; obecnego obiektu Koordy od zadanego w parametrach. Liczona jest poruszaj&#x105;c sie jedynie prawo, lewo, gora, d&oacute;&#x142;
     *
     * @param koordy Koordy oodalone od obecnych
     * @return odleg&#x142;o&#x15B;&#x107;
     */
    public int odleglosc(Koordy koordy){
        return Math.abs(x- koordy.x)+Math.abs(y- koordy.y);
    }

    /**
     * Je&#x17C;eli x==-1 oraz y==-1 Koordy s&#x105; null
     *
     * @return the boolean
     */
    public boolean isNull(){
        return x==-1&&y==-1;
    }

    /**
     * Tworzy nowe koordy z losowymi zmiennymi sta&#x142;ymi, poni&#x17C;ej zadanych parametr&oacute;w
     *
     * @param underX poni&#x17C;ej jakiego X ma by&#x107; zwracana warto&#x15B;&#x107; X
     * @param underY poni&#x17C;ej jakiego Y ma by&#x107; zwracana warto&#x15B;&#x107; Y
     * @return the koordy
     */
    public Koordy losoweMiejsce(int underX,int underY){
        Random random = new Random();
        return new Koordy(random.nextInt(underX), random.nextInt(underY));
    }

    /**
     * Por&oacute;wnuje swoje x oraz y z innym obiektem Koordy.
     * @param o Obiekt to sprawdzenia. Warto&#x15B;c true jest mo&#x17C;liwa do zwr&oacute;cenia jedynie kiedy Obiekt jest typu Koordy
     * @return true je&#x17C;eli x oraz y s&#x105; sobie r&oacute;wne, w przeciwnym wypadku false
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
