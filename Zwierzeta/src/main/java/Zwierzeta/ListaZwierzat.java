package Zwierzeta;

import Pola.Pole;
import Pomocnicze.*;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Podklasa.Mlode;
import Zwierzeta.Podklasa.Typ.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static Pomocnicze.Jedzenie.*;

/**
 * The type Lista zwierzat.
 */
public class ListaZwierzat implements Iterable<Zwierze>{
    /**
     * The Lista.
     */
    public List<Zwierze> lista =new ArrayList<>();
    private final List<Zwierze> tymczasowa =new ArrayList<>();
    private final List<Koordy> polaWody;
    private final List<Pole> listaPol;
    private static int liczbaPorzatkowa=0;
    private final Koordy kafelki;
    /**
     * Mapa zawieraj&#x105;ca nazwy gatunk&oacute;w oraz ich liczebno&#x15B;&#x107;.
     */
    public static Map<String, Integer> iloscZwierzat = new HashMap<>();
    private final List<Integer> doUsuniecia=new ArrayList<>();
    private final float ileJedzaCoTure;
    /**
     * The constant koncoweStatystyki.
     */
    public static Map<String,Float> koncoweStatystyki=new HashMap<>();

    /**
     * Instantiates a new Lista zwierzat.
     *
     * @param kafelki  rozmiar mapy typu Koordy, okre&#x15B;laj&#x105;cy z ilu kafelek sk&#x142;ada si&#x119; mapa w wymiarze X oraz Y
     * @param listaPol lista wszystkich p&oacute;l z kt&oacute;rych sk&#x142;ada si&#x119; mapa
     * @param polaWody wsp&oacute;&#x142;rz&#x119;dne wszystkich p&oacute;l wody na mapie
     * @param jedzenie ile zwierz&#x119;ta musz&#x105; je&#x15B;&#x107; co tur&#x119; (wyra&#x17C;one w u&#x142;amku ich wielko&#x15B;ci)
     */
    public ListaZwierzat(Koordy kafelki,List<Pole> listaPol,List<Koordy> polaWody,float jedzenie) {
        this.listaPol=listaPol;
        this.polaWody = polaWody;
        this.kafelki=kafelki;
        this.ileJedzaCoTure=jedzenie;
        for (Object zwierze: Stream.concat(Stream.concat(Arrays.stream(Gatunek.values()),Arrays.stream(TypMlodych.values())),Arrays.stream(Plemiona.values())).toArray()) {
            iloscZwierzat.put(zwierze.toString(),0);
        }
    }

    /**
     * Tworzy nowe zwierze w wskazanym miejscu o okre&#x15B;lonym gatunku. Gatunek wp&#x142;ywa na statystyki zwierz&#x119;cia i jego klas&#x119;. Utworzenie zwierz&#x119;cia jest komunikowane w terminalu.
     *
     * @param gdzie   wsp&oacute;&#x142;rz&#x119;dne na mapie
     * @param gatunek gatunek nowo tworzsonego zwierz&#x119;cia. Przy podaniu 2 string&oacute;w, drugi oznacza kolejny etap rozwoju (potrzebne przy tworzeniu m&#x142;odych zwierz&#x105;t)
     */
    public void stworzZwierze(Koordy gdzie, String... gatunek){
        String jakie=gatunek[0];
        Zwierze zwierze=new Zwierze(liczbaPorzatkowa++,0, gdzie,jakie+".png");
        try {
            String kolejnyEtap=gatunek[1];
            int dorastanie=2, sila=0, szybkosc=0;
            float rozmiar=0.2F;
            switch (TypMlodych.valueOf(jakie)){
                case JajkoGad:
                case JajkoPtak:
                    break;
                case MlodyGad:
                    sila+=1;
                    szybkosc+=1;
                    break;
                case MlodyRyba:
                    dorastanie+=2;
                    szybkosc+=3;
                    break;
                case MlodyPtak:
                    szybkosc+=1;
                    break;
                case MlodySsak:
                    szybkosc+=2;
                    sila+=1;
                    break;
            }
            zwierze=new Mlode(zwierze,szybkosc,sila,rozmiar,Wszystko,TypMlodych.valueOf(jakie),kolejnyEtap,dorastanie);
            iloscZwierzat.put("Mlode",iloscZwierzat.get("Mlode")+1);
            System.out.println("Tworze "+TypMlodych.valueOf(jakie)+"; KolejnyEtap: "+kolejnyEtap);
        }catch (Exception e1){
            try{
                int szybkosc=1,sila=10,dlugoscZycia=20;
                float rozmiar=1;
                Jedzenie jedzenie=null;
                switch(Gatunek.valueOf(jakie)){
                    case Puma:
                        szybkosc+=2;
                        sila+=1;
                        jedzenie=Mieso;
                    case Kapibara:
                        if (jedzenie==null) {
                            jedzenie = Rosliny;
                            sila-=6;
                            rozmiar-=1/2f;
                        }
                        zwierze=new Ssak(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Ssak",iloscZwierzat.get("Ssak")+1);
                        break;
                    case Akara:
                        jedzenie = Wszystko;
                        sila-=9;
                        rozmiar-=3/4f;
                        szybkosc+=1;
                    case Zbrojnik:
                        if (jedzenie==null) {
                            jedzenie = Wszystko;
                            sila-=7;
                        }
                    case Kirysek:
                        if (jedzenie==null) {
                            jedzenie = Rosliny;
                            sila-=10;
                            rozmiar-=3/4f;
                            szybkosc+=2;
                        }
                        if(!polaWody.contains(gdzie))
                            zwierze = new Zwierze(zwierze.getId(), zwierze.wiek, polaWody.get(new Random().nextInt(polaWody.size())), zwierze.getGrafika());
                        zwierze=new Ryba(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Ryba",iloscZwierzat.get("Ryba")+1);
                        break;
                    case Anakonda:
                        jedzenie=Mieso;
                        sila+=10;
                        rozmiar+=2;
                    case Bazyliszek:
                        if (jedzenie==null) {
                            jedzenie=Mieso;
                            sila-=5;
                            szybkosc+=1;
                            rozmiar-=1/2f;
                        }
                    case Mastigodryas:
                        if (jedzenie==null) {
                            jedzenie=Mieso;
                            sila+=2;
                            szybkosc+=1;
                            rozmiar-=1/2f;
                        }
                        zwierze=new Gad(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Gad",iloscZwierzat.get("Gad")+1);
                        break;
                    case Tukan:
                        jedzenie=Rosliny;
                        szybkosc+=1;
                        sila-=5;
                        rozmiar-=1/4f;
                    case Bielczyk:
                        if (jedzenie==null) {
                            jedzenie=Rosliny;
                            szybkosc+=2;
                            sila-=10;
                            rozmiar-=3/4f;
                        }
                    case Drzewica:
                        if (jedzenie==null) {
                            jedzenie=Rosliny;
                            szybkosc+=1;
                            sila-=6;
                            rozmiar-=1/2f;
                        }
                        zwierze=new Ptak(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Ptak",iloscZwierzat.get("Ptak")+1);
                        break;
                }
                iloscZwierzat.put("DorosleNieCzlowiek",iloscZwierzat.get("DorosleNieCzlowiek")+1);
                System.out.println("Tworze "+Gatunek.valueOf(jakie));
            }catch (Exception e2){
                try{
                    int szybkosc=1, sila=10;
                    float rozmiar=1.5f;
                    Jedzenie jedzenie=Wszystko;
                    switch(Plemiona.valueOf(jakie)){
                        case Tukano:
                            break;
                        case Yawanawa:
                            szybkosc+=1;
                            sila-=2;
                            break;
                        case Kaxinawa:
                            sila-=2;
                            rozmiar-=0.3f;
                            break;
                        case Nukini:
                            sila+=2;
                            break;
                        case Katukina:
                            szybkosc+=2;
                            rozmiar-=0.2;
                            jedzenie=Mieso;
                            break;
                        case Puyanawa:
                            sila+=5;
                            rozmiar+=0.2;
                            jedzenie=Mieso;
                            break;
                        case Kuntanawa:
                            sila-=4;
                            rozmiar-=0.6;
                            jedzenie=Rosliny;
                            break;
                        case Shawadawa:
                            rozmiar-=0.6;
                            jedzenie=Rosliny;
                    }
                    zwierze = new Czlowiek(zwierze,szybkosc,sila,rozmiar, jedzenie,50, Gatunek.Czlowiek,Plemiona.valueOf(jakie));
                    iloscZwierzat.put("Czlowiek",iloscZwierzat.get("Czlowiek")+1);
                    System.out.println("Tworze "+Plemiona.valueOf(jakie));
                }catch (Exception exc){
                    System.out.println("Blad w tworzeniu zwierzecia: '"+jakie+"' nie istnieje jako typ, ani gatunek.");
                    return;
                }
            }
        }
        iloscZwierzat.put(jakie,Integer.parseInt(iloscZwierzat.get(jakie).toString())+1);
        float suma=0;
        List<String> powtorzenia = List.of(new String[]{"Ssak", "Gad", "Ptak", "Ryba", "DorosleNieCzlowiek", "Mlode", "Czlowiek"});
        for(String ilosc: iloscZwierzat.keySet()){
            if (!powtorzenia.contains(ilosc))
                suma+=iloscZwierzat.get(ilosc);
            }

        if(suma > koncoweStatystyki.get("Maksymalna populacja"))
            koncoweStatystyki.put("Maksymalna populacja",suma);
        tymczasowa.add(zwierze);
    }

    /**
     * Wyszukiwuje wsp&oacute;&#x142;rz&#x119;dne na mapie dla wybranego zwierze oraz warunek. Wyszukiwane jest miejsce w zasi&#x119;gu szybko&#x15B;ci zwierz&#x119;cia, spe&#x142;niaj&#x105;ce zadany warunek.
     * @param zwierze Zwierze
     * @param warunek Predicate
     * @return Pierwsze pole spe&#x142;niaj&#x105;ce zadany warunek, znajduj&#x105;ce si&#x119; w zasi&#x119;gu szybko&#x15B;ci zwierz&#x119;cia. Je&#x17C;eli takie pole nie istnieje zwraca new Koordy(), czyli Koordy o danych: x=-1, y=-1.
     * @param <T> parametr mog&#x105;cy przyjmowa&#x107; typ Pole albo Zwierze, w zale&#x17C;nos&#x107;i od potrzeb wyszukiwania
     */
    private <T> Koordy wyszukiwanie(Zwierze zwierze,Predicate<T> warunek){
        try{
            Predicate<Zwierze> konkret = (Predicate<Zwierze>) warunek;
            Stream<Zwierze> ciag=lista.stream().filter(zwierze1 -> (zwierze1.getMiejsce().odleglosc(zwierze.getMiejsce())<=zwierze.getSzybkosc()) && (zwierze1.getId() != zwierze.getId()));
            Object[] list =ciag.filter(konkret).toArray();
            return (list.length==0)?new Koordy():((Zwierze)list[0]).getMiejsce();
        }catch(Exception e1){
            Predicate<Pole> konkret = (Predicate<Pole>) warunek;
            Stream<Pole> ciag=listaPol.stream().filter(pole1 -> (pole1.getMiejsce().odleglosc(pole1.getMiejsce())<=zwierze.getSzybkosc()));
            Object[] list = ciag.filter(konkret).toArray();
            return (list.length==0)?new Koordy():((Pole)list[0]).getMiejsce();
        }
    }

    /**
     * Na podstawie ilo&#x15B;ci jedzenia zwierz&#x119;cia oraz okolicznych zwierz&#x105;t, podejmuje decyzje jakiego pola chce szuka&#x107;, a w ko&#x144;cu na jakie pole si&#x119; przesunie.
     * @param zwierze Zwierze
     * @return wsp&oacute;&#x142;rz&#x119;dne na kt&oacute;re ma si&#x119; przesun&#x105;&#x107; zwierze. Mo&#x17C;e si&#x119; zdarzy&#x107; &#x17C;e jest takie samo jak obecne miejsce przebywania zwierz&#x119;cia.
     */

    private Koordy decyzjaGdzieIdzie(Zwierze zwierze){
        Koordy miejsce=new Koordy();
        if (zwierze.getSzybkosc()==0) return zwierze.getMiejsce();
        if(zwierze.getJedzenie()<=zwierze.getRozmiar()) { //Krytycznie malo jedzenia
            if(zwierze.getCoJe()!=Rosliny){
                miejsce=wyszukiwanie(zwierze,(Zwierze zwierze1)->((zwierze1).getSila()<zwierze.getSila()));
            }
            if(zwierze.getCoJe()!=Mieso && miejsce.isNull()){
                miejsce=wyszukiwanie(zwierze,(Pole pole1)->(pole1.getJedzenie()>=zwierze.getRozmiar()));
            }
        } else if (zwierze.getJedzenie()>=2*ileJedzaCoTure*zwierze.getRozmiar()&&zwierze.getClass()!=Mlode.class) { //Ma duzo jedzenia
            if (zwierze.getClass() == Czlowiek.class) {
                miejsce = wyszukiwanie(zwierze, mlode1 -> (mlode1.getClass() == Mlode.class) && (Objects.equals(((Mlode) mlode1).getKolejnyEtap(), ((Czlowiek) zwierze).plemie.name())));
                if(miejsce.isNull())
                    miejsce = wyszukiwanie(zwierze, czlowiek1 -> (czlowiek1.getClass() == Czlowiek.class) && (((Czlowiek) czlowiek1).plemie == ((Czlowiek) zwierze).plemie));
            }else {
                    miejsce = wyszukiwanie(zwierze, mlode1 -> (mlode1.getClass() == Mlode.class) && (Objects.equals(((Mlode) mlode1).getKolejnyEtap(), ((Dorosle) zwierze).getGatunek().name())));
                if (miejsce.isNull())
                    miejsce = wyszukiwanie(zwierze, dorosle1 -> (dorosle1.getClass() != Mlode.class) && (((Dorosle) dorosle1).getGatunek() == ((Dorosle) zwierze).getGatunek()));
            }
        } else if (zwierze.getCoJe()==Rosliny) // Jest roslinozerca
            miejsce=wyszukiwanie(zwierze,(Pole pole1)->(lista.stream().map(Zwierze::getMiejsce).anyMatch(miejsce1->!miejsce1.equals(pole1.getMiejsce()))));
        Random random=new Random();
        if(miejsce.isNull()) {
            Object[] wZasiegu = listaPol.stream().map(Pole::getMiejsce).filter(pole1Miejsce -> (pole1Miejsce.odleglosc(zwierze.getMiejsce()) <= zwierze.getSzybkosc())).toArray();
            if(wZasiegu.length>0)
                miejsce=(Koordy) wZasiegu[random.nextInt(wZasiegu.length)];
            else
                miejsce=zwierze.getMiejsce();
        }if((zwierze.getClass()== Ryba.class || (zwierze.getClass()== Mlode.class && ((Mlode) zwierze).getTyp()==TypMlodych.MlodyRyba)) && !polaWody.contains(miejsce))
            miejsce=zwierze.getMiejsce();
        return miejsce;
    }

    /**
     * Ka&#x17C;de zwierze wykonuje ruch, po czym akcj&#x119; na polu na kt&oacute;rym si&#x119; znajduj&#x119; (w zale&#x17C;no&#x15B;ci jakie inne zwierz&#x119;ta si&#x119; na nim znajduj&#x105;). nast&#x119;pnie zwierze jest postarzane i musi zje&#x15B;&#x107; okre&#x15B;lon&#x105; ilo&#x15B;&#x107; jedzenia, aby prze&#x17C;y&#x107;.
     */
    public synchronized void wykonajPetle(){
        lista.addAll(tymczasowa);
        tymczasowa.clear();
        usun();
        for (Zwierze zwierze:lista) {
            if (!zwierze.czyZyje())
                continue;
            //Ruch
            zwierze.ruch(decyzjaGdzieIdzie(zwierze));

            //Akcja
            Object[] obecneZwierzeta = lista.stream().filter(zwierze1 -> zwierze1.getMiejsce() == zwierze.getMiejsce()).toArray();
            Map<String, List<Zwierze>> liczebnosc = new HashMap<>();
            if (obecneZwierzeta.length == 1) { //Jezeli zwierze jest samo na polu
                if (zwierze.getCoJe() != Mieso) {
                    Pole pole = listaPol.get(zwierze.getMiejsce().x + zwierze.getMiejsce().y * kafelki.y);
                    float ile=pole.utracJedzenie();
                    koncoweStatystyki.put("Zjedzone rosliny",koncoweStatystyki.get("Zjedzone rosliny")+ile);
                    zwierze.zdobadzJedzenie(ile);
                }
            }
            else{
                for (Object zwierze1 : obecneZwierzeta) {
                    String klucz = "null";
                    if (zwierze1.getClass() == Czlowiek.class)
                        klucz = ((Czlowiek) zwierze1).plemie.name();
                    else if (zwierze1 instanceof Dorosle)
                        klucz = ((Dorosle) zwierze1).getGatunek().name();
                    if (zwierze1.getClass() == Mlode.class)
                        klucz = ((Mlode) zwierze1).getKolejnyEtap();
                    if (!liczebnosc.containsKey(klucz)) {
                        if (liczebnosc.size() == 2)
                            continue;
                        liczebnosc.put(klucz, new ArrayList<>());
                    }
                    liczebnosc.get(klucz).add((Zwierze) zwierze1);
                }
                if(liczebnosc.size()>=2) {
                    walka(liczebnosc);
                }else if(zwierze.getClass()!= Mlode.class && zwierze.getJedzenie()>=2*zwierze.getRozmiar()){ //Na polu jest jednostka tego samego gatunku
                    for(Object zwierze1: obecneZwierzeta){
                        Zwierze zwierz=(Zwierze)zwierze1;
                        if (!zwierz.czyZyje())
                            continue;
                        if(zwierz.getJedzenie()>=2*ileJedzaCoTure*zwierz.getRozmiar()){
                            if(zwierz.getClass()== Mlode.class){ //Na polu jest mlode
                                nakarm((Dorosle) zwierze,(Mlode) zwierz);
                            }else{
                                if(zwierze instanceof Ssak)
                                    ((Ssak) zwierze).setCiaza();
                                else
                                    reprodukcja((Dorosle) zwierze,(Dorosle)zwierz);
                            }
                        }
                    }
                }
            }
            zwierze.jedz(ileJedzaCoTure);
            if(zwierze.starzej()) //Jeżeli true - jest to młode gotowe dorosnąć
                dorosnij((Mlode) zwierze);
            if(zwierze instanceof Ssak && ((Ssak) zwierze).urodz())
                reprodukcja((Dorosle) zwierze, (Dorosle) zwierze);
        }
    }

    /**
     * Doros&#x142;e zwierze przekazuje jedzenie m&#x142;odemu zwierz&#x119;ciu z tego samego gatunku
     * @param dorosle
     * @param mlode
     */
    private void nakarm(Dorosle dorosle,Mlode mlode){
        mlode.zdobadzJedzenie(dorosle.getRozmiar()*ileJedzaCoTure);
        dorosle.jedz(ileJedzaCoTure);
    }

    /**
     * Dwa gatunki zwierz&#x105;t walcz&#x105; ze sob&#x105;. Ta grupa kt&oacute;ra ma w sumie wi&#x119;ksz&#x105; si&#x142;&#x119; wygrywa. Przegrani umieraj&#x105;, je&#x17C;eli zwyci&#x119;&#x17C;cy nie s&#x105; ro&#x15B;lino&#x17C;ercami, odrazu zjadaj&#x105; przegranych. W przypadku remis&oacute;w nikt nie wygrywa.
     * @param walczacy lista zwierz&#x105;t walcz&#x105;cych ze sob&#x105; na danym polu
     */
    private void walka(Map<String,List<Zwierze>> walczacy) {
        String[] id=walczacy.keySet().toArray(new String[0]);
        Integer[] sila={walczacy.get(id[0]).stream().mapToInt(Zwierze::getSila).sum(),walczacy.get(id[1]).stream().mapToInt(Zwierze::getSila).sum()};
        List<Zwierze> zwyciezcy=walczacy.get(id[0]);
        List<Zwierze> przegrani=walczacy.get(id[1]);
        if (!sila[0].equals(sila[1])) {
            if(sila[0]<sila[1]){
                zwyciezcy=walczacy.get(id[1]);
                przegrani=walczacy.get(id[0]);
            }

            if(przegrani.get(0).getClass()== Ptak.class && przegrani.stream().mapToInt(Zwierze::getSzybkosc).sum()>zwyciezcy.stream().mapToInt(Zwierze::getSzybkosc).sum()) {
                for (int i = 0; i < przegrani.size(); i++) {
                    if (przegrani.get(i).getClass()== Ptak.class){
                        Random random = new Random();
                        Ptak ptak = (Ptak) przegrani.get(i);
                        if (ptak.czyZyje() && random.nextInt(2)==1) { //Ptaki maja 50% szans na uczieczkę z walki
                            ptak.lot();
                        }
                    }
                }
                return;
            }

            float jedzenie=(float)(przegrani.stream().mapToDouble(Zwierze::getJedzenie).sum()+przegrani.stream().mapToDouble(Zwierze::getRozmiar).sum());

            for (int i=1;i<przegrani.size();i++) {
                if(przegrani.get(i).czyZyje()) {
                    przegrani.get(i).smierc();
                    doUsuniecia.add(przegrani.get(i).getId());
                    koncoweStatystyki.put("Smierci w walce",koncoweStatystyki.get("Smierci w walce")+1);
                }
            }
            if(zwyciezcy.get(0).getCoJe()!=Rosliny){
                for (Zwierze zwyciezca:zwyciezcy) {
                    float ile=jedzenie/zwyciezcy.size();
                    koncoweStatystyki.put("Zjedzone mieso",koncoweStatystyki.get("Zjedzone mieso")+ile);
                    zwyciezca.zdobadzJedzenie(ile);
                }
                przegrani.get(0).smierc();
                doUsuniecia.add(przegrani.get(0).getId());
            }else if(przegrani.get(0).getRozmiar()!=0) {
                przegrani.get(0).jedzenie = jedzenie;
                przegrani.get(0).smierc();
            }
            koncoweStatystyki.put("Smierci w walce",koncoweStatystyki.get("Smierci w walce")+1);
        }
    }

    /**
     * Dwa doros&#x142;e zwierz&#x119;cia, tego samego gatunku, tworz&#x105; nowe m&#x142;od&#x119; zwierz&#x119;.
     * @param zwierze1
     * @param zwierze2
     */
    private void reprodukcja(Dorosle zwierze1,Dorosle zwierze2){
        zwierze1.jedz(ileJedzaCoTure);
        zwierze2.jedz(ileJedzaCoTure);
        if(zwierze1.getClass()==Czlowiek.class)
            stworzZwierze(zwierze1.getMiejsce(),"MlodySsak",((Czlowiek) zwierze1).plemie.name());
        else {
            String nazwa=(zwierze1.getClass()== Gad.class || zwierze1.getClass()== Ptak.class)?"Jajko":"Mlody";
            stworzZwierze(zwierze1.getMiejsce(), nazwa + zwierze1.getClass().getSimpleName(), zwierze1.getGatunek().name());
        }
    }

    /**
     * Usuwa zwierz&#x119;cia z listy zwierz&#x105;t.
     */
    private void usun(){
        for (Integer id:doUsuniecia) {
            lista.removeIf(zwierze -> zwierze.getId()==id);
        }
        doUsuniecia.clear();
    }

    /**
     * Zwierze dorasta, czyli zostaje usuni&#x119;te, a na jego miejsce powstaje zwierze o kolejnym etapie rozwoju.
     * @param zwierze
     */
    private void dorosnij(Mlode zwierze){
        String typ = zwierze.getTyp().toString();
        if(typ.contains("Jajko"))
            this.stworzZwierze(zwierze.getMiejsce(),"Mlody"+typ.replace("Jajko",""),zwierze.getKolejnyEtap());
        else
            this.stworzZwierze(zwierze.getMiejsce(), zwierze.getKolejnyEtap());
        zwierze.smierc();
        doUsuniecia.add(zwierze.getId());
    }

    @Override
    public Iterator<Zwierze> iterator() {
        return new IteratorListyZwierzat();
    }

    /**
     * Pozwala na iteracj&#x119; ListaZwierzat po jej lista.
     */
    class IteratorListyZwierzat implements Iterator<Zwierze>{
        private int id=0;
        @Override
        public boolean hasNext() {
            return id<lista.size();
        }

        @Override
        public Zwierze next() {
            return lista.get(id++);
        }
    }
}
