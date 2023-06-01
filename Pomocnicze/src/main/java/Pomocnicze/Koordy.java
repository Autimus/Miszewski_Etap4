package Pomocnicze;

import java.util.Objects;
import java.util.Random;

public class Koordy {
    public int x,y;

    public Koordy(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Koordy() {
        this.x = -1;
        this.y = -1;
    }
    public int odleglosc(Koordy koordy){
        return Math.abs(x- koordy.x)+Math.abs(y- koordy.y);
    }
    public boolean isNull(){
        return x==-1&&y==-1;
    }
    public Koordy losoweMiejsce(int underX,int underY){
        Random random = new Random();
        return new Koordy(random.nextInt(underX), random.nextInt(underY));
    }

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
