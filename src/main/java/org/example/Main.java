package org.example;

import Utility.Aplikacja;

/**
 * The type Main.
 */
public class Main {
    /**
     * Tworzy obiekt klasy Aplikacja i ustawia w nim p&#x119;tl&#x119; o okresie 1s
     *
     * @param args the args
     */
    public static void main(String[] args){
        Aplikacja app = new Aplikacja(800,600,20,20);
        app.ustawPetle(1000);
    }
}
