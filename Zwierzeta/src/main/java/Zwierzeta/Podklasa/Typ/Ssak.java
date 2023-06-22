package Zwierzeta.Podklasa.Typ;

import Pomocnicze.Gatunek;
import Pomocnicze.Jedzenie;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Zwierze;

/**
 * The type Ssak.
 */
public class Ssak extends Dorosle {
    /**
     * Po reprodukcji ssaki nie tworzą nowego zwierzęcia od razu, tylko dopiero po upływie czasu wyrażonego jako liczba tur w tej zmiennej.
     */
    public int ciaza=-1;

    /**
     * Instantiates a new Ssak.
     *
     * @param wstepne  wstępne zwierze, zawierajace jedynie id, wiek, miejsce oraz grafikę.
     * @param szybkosc szybkosc - ile pól jest w stanei przejśc co turę
     * @param sila     sila - wykorzystywana w walce z innymi zwierzeciami
     * @param rozmiar  rozmiar - wplywa na ilość zjadanego pożywienia
     * @param coJe     co je - Enum Jedzenie. Ustala czy zwierze może jeść mięso albo rośliny
     * @param dlugoscZycia ile tur od utworzenia do śmierci ze starości
     * @param gatunek      Enum Gatunek
     */
    public Ssak(Zwierze wstepne, int szybkosc, int sila, float rozmiar, Jedzenie coJe, int dlugoscZycia, Gatunek gatunek) {
        super(wstepne, szybkosc, sila, rozmiar, coJe, dlugoscZycia, gatunek);
    }

    /**
     * Jeżeli ciąża dobiegła końca, tworzy nowego młodego ssaka.
     *
     * @return the boolean
     */
    public boolean urodz(){
        return --ciaza==0;
    }

    /**
     * Set ciaza.
     */
    public void setCiaza(){
        ciaza = (ciaza==-1)? 2:ciaza;
    }
}
