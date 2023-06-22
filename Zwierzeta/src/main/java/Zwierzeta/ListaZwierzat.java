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
     * Mapa zawierająca nazwy gatunków oraz ich liczebność.
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
     * @param kafelki  rozmiar mapy typu Koordy, określający z ilu kafelek składa się mapa w wymiarze X oraz Y
     * @param listaPol lista wszystkich pól z których składa się mapa
     * @param polaWody współrzędne wszystkich pól wody na mapie
     * @param jedzenie ile zwierzęta muszą jeść co turę (wyrażone w ułamku ich wielkości)
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
     * Tworzy nowe zwierze w wskazanym miejscu o określonym gatunku. Gatunek wpływa na statystyki zwierzęcia i jego klasę. Utworzenie zwierzęcia jest komunikowane w terminalu.
     *
     * @param gdzie   współrzędne na mapie
     * @param gatunek gatunek nowo tworzsonego zwierzęcia. Przy podaniu 2 stringów, drugi oznacza kolejny etap rozwoju (potrzebne przy tworzeniu młodych zwierząt)
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
     * Wyszukiwuje współrzędne na mapie dla wybranego zwierze oraz warunek. Wyszukiwane jest miejsce w zasięgu szybkości zwierzęcia, spełniające zadany warunek.
     * @param zwierze Zwierze
     * @param warunek Predicate
     * @return Pierwsze pole spełniające zadany warunek, znajdujące się w zasięgu szybkości zwierzęcia. Jeżeli takie pole nie istnieje zwraca new Koordy(), czyli Koordy o danych: x=-1, y=-1.
     * @param <T> parametr mogący przyjmować typ Pole albo Zwierze, w zależnosći od potrzeb wyszukiwania
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
     * Na podstawie ilości jedzenia zwierzęcia oraz okolicznych zwierząt, podejmuje decyzje jakiego pola chce szukać, a w końcu na jakie pole się przesunie.
     * @param zwierze Zwierze
     * @return współrzędne na które ma się przesunąć zwierze. Może się zdarzyć że jest takie samo jak obecne miejsce przebywania zwierzęcia.
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
     * Każde zwierze wykonuje ruch, po czym akcję na polu na którym się znajduję (w zależności jakie inne zwierzęta się na nim znajdują). następnie zwierze jest postarzane i musi zjeść określoną ilość jedzenia, aby przeżyć.
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
     * Dorosłe zwierze przekazuje jedzenie młodemu zwierzęciu z tego samego gatunku
     * @param dorosle
     * @param mlode
     */
    private void nakarm(Dorosle dorosle,Mlode mlode){
        mlode.zdobadzJedzenie(dorosle.getRozmiar()*ileJedzaCoTure);
        dorosle.jedz(ileJedzaCoTure);
    }

    /**
     * Dwa gatunki zwierząt walczą ze sobą. Ta grupa która ma w sumie większą siłę wygrywa. Przegrani umierają, jeżeli zwyciężcy nie są roślinożercami, odrazu zjadają przegranych. W przypadku remisów nikt nie wygrywa.
     * @param walczacy lista zwierząt walczących ze sobą na danym polu
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
     * Dwa dorosłe zwierzęcia, tego samego gatunku, tworzą nowe młodę zwierzę.
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
     * Usuwa zwierzęcia z listy zwierząt.
     */
    private void usun(){
        for (Integer id:doUsuniecia) {
            lista.removeIf(zwierze -> zwierze.getId()==id);
        }
        doUsuniecia.clear();
    }

    /**
     * Zwierze dorasta, czyli zostaje usunięte, a na jego miejsce powstaje zwierze o kolejnym etapie rozwoju.
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
     * Pozwala na iterację ListaZwierzat po jej lista.
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
