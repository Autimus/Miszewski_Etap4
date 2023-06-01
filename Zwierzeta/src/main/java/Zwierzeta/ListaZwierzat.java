package Zwierzeta;

import Pola.Pole;
import Pomocnicze.*;
import Zwierzeta.Podklasa.Dorosle;
import Zwierzeta.Podklasa.Mlode;
import Zwierzeta.Podklasa.Typ.*;

import java.nio.file.Watchable;
import java.sql.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Pomocnicze.Jedzenie.*;
import static Utility.Panel.listaPol;

public class ListaZwierzat {
    public List<Zwierze> lista =new ArrayList<>();
    public List<Zwierze> tymczasowa =new ArrayList<>();
    List<Koordy> polaWody;
    static int liczbaPorzatkowa=0;
    Koordy kafelki;
   public static Map<String, Integer> iloscZwierzat = new HashMap<>();
   List<Integer> doUsuniecia=new ArrayList<>();

    public ListaZwierzat(Koordy kafelki,List<Koordy> polaWody) {
        this.polaWody = polaWody;
        this.kafelki=kafelki;
        for (Object zwierze: Stream.concat(Stream.concat(Arrays.stream(Gatunek.values()),Arrays.stream(TypMlodych.values())),Arrays.stream(Plemiona.values())).toArray()) {
            iloscZwierzat.put(zwierze.toString(),0);
        }
        System.out.println(iloscZwierzat.toString());
    }

    public int stworzZwierze(Koordy gdzie, String... gatunek){
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
                    szybkosc+=3;
                    break;
                case MlodyPtak:
                    dorastanie+=2;
                    szybkosc+=1;
                    break;
                case MlodySsak:
                    dorastanie+=3;
                    szybkosc+=2;
                    sila+=1;
                    break;
            }
            zwierze=new Mlode(zwierze,szybkosc,sila,rozmiar,Wszystko,TypMlodych.valueOf(jakie),kolejnyEtap,dorastanie);
            iloscZwierzat.put("Mlode",iloscZwierzat.get("Mlode")+1);
            System.out.println("Tworze "+TypMlodych.valueOf(jakie));
        }catch (Exception e1){
            try{
                int szybkosc=1,sila=1,dlugoscZycia=1;
                float rozmiar=1;
                Jedzenie jedzenie=null;
                switch(Gatunek.valueOf(jakie)){
                    case Puma:
                        jedzenie=Mieso;
                    case Kapibara:
                        jedzenie=jedzenie==null?Rosliny:Mieso;
                        zwierze=new Ssak(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Ssak",iloscZwierzat.get("Ssak")+1);
                        break;
                    case Akara:
                    case Zbrojnik:
                        jedzenie=Wszystko;
                    case Kirysek:
                        jedzenie=jedzenie==null?Rosliny:Wszystko;
                        if(polaWody.indexOf(gdzie)==-1)
                            zwierze = new Zwierze(zwierze.id, zwierze.wiek, polaWody.get(new Random().nextInt(polaWody.size())), zwierze.grafika);
                        zwierze=new Ryba(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Ryba",iloscZwierzat.get("Ryba")+1);
                        break;
                    case Anakonda:
                    case Bazyliszek:
                    case Mastigodryas:
                        jedzenie=Mieso;
                        zwierze=new Gad(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Gad",iloscZwierzat.get("Gad")+1);
                        break;
                    case Tukan:
                    case Bielczyk:
                    case Drzewnica:
                        jedzenie=Rosliny;
                        zwierze=new Ptak(zwierze,szybkosc,sila,rozmiar,jedzenie,dlugoscZycia,Gatunek.valueOf(jakie));
                        iloscZwierzat.put("Ptak",iloscZwierzat.get("Ptak")+1);
                        break;
                }
                iloscZwierzat.put("DorosleNieCzlowiek",iloscZwierzat.get("DorosleNieCzlowiek")+1);
                System.out.println("Tworze "+Gatunek.valueOf(jakie));
            }catch (Exception e2){
                try{
                    int szybkosc=1, sila=10;
                    float rozmiar=2;
                    Jedzenie jedzenie=Wszystko;
                    switch(Plemiona.valueOf(jakie)){
                        case Tukano:
                            break;
                        case Yawanawa:
//                            szybkosc+=2;
                            sila-=2;
                            break;
                        case Kaxinawa:
//                            sila-=2;
                            rozmiar-=0.3;
                            break;
                        case Nukini:
//                            szybkosc-=2;
                            sila+=2;
                            break;
                        case Katukina:
//                            szybkosc+=5;
                            rozmiar-=0.2;
                            jedzenie=Mieso;
                            break;
                        case Puyanawa:
//                            sila+=5;
                            rozmiar+=0.2;
                            jedzenie=Mieso;
                            break;
                        case Kuntanawa:
//                            sila-=4;
                            rozmiar-=0.6;
                            jedzenie=Rosliny;
                            break;
                        case Shawadawa:
//                            szybkosc-=4;
                            rozmiar-=0.6;
                            jedzenie=Rosliny;
                    }
                    zwierze = new Czlowiek(zwierze,szybkosc,sila,rozmiar, jedzenie,50, Gatunek.Czlowiek,Plemiona.valueOf(jakie));
                    iloscZwierzat.put("Czlowiek",iloscZwierzat.get("Czlowiek")+1);
                    System.out.println("Tworze "+Plemiona.valueOf(jakie));
                }catch (Exception exc){
                    System.out.println("Blad w tworzeniu zwierzecia: '"+jakie+"' nie istnieje jako typ, ani gatunek.");
                    return 0;
                }
            }
        }
        iloscZwierzat.put(jakie,Integer.valueOf(iloscZwierzat.get(jakie).toString())+1);
        tymczasowa.add(zwierze);
        return 1;
    }
    <T> Koordy wyszukiwanie(Zwierze zwierze,Predicate<T> warunek){
        try{
            Predicate<Zwierze> konkret = (Predicate<Zwierze>) warunek;
            Stream ciag=lista.stream().filter(zwierze1 -> (zwierze1.getMiejsce().odleglosc(zwierze.getMiejsce())<=zwierze.szybkosc) && (zwierze1.id != zwierze.id));
            Object[] list =ciag.filter(konkret).toArray();
            return (list.length==0)?new Koordy():((Zwierze)list[0]).getMiejsce();
        }catch(Exception e1){
            Predicate<Pole> konkret = (Predicate<Pole>) warunek;
            Stream ciag=listaPol.stream().filter(pole1 -> (pole1.getMiejsce().odleglosc(pole1.getMiejsce())<=zwierze.szybkosc));
            Object[] list = ciag.filter(konkret).toArray();
            return (list.length==0)?new Koordy():((Pole)list[0]).getMiejsce();
        }
    }

    Koordy decyzjaGdzieIdzie(Zwierze zwierze){
        Koordy miejsce=new Koordy();
        if(zwierze.jedzenie<=zwierze.rozmiar) { //Krytycznie malo jedzenia
            if(zwierze.coJe!=Rosliny){
                miejsce=wyszukiwanie(zwierze,(Zwierze zwierze1)->((zwierze1).sila<zwierze.sila));
            }
            if(zwierze.coJe!=Mieso && miejsce.isNull()){
                miejsce=wyszukiwanie(zwierze,(Pole pole1)->(pole1.jedzenie>=zwierze.rozmiar));
            }
        } else if (zwierze.jedzenie>=2*zwierze.rozmiar&&zwierze.getClass()!=Mlode.class) { //Ma duzo jedzenia
            miejsce=wyszukiwanie(zwierze,mlode1->(mlode1.getClass()== Mlode.class)&&(((Mlode) mlode1).kolejnyEtap==((Dorosle)zwierze).gatunek.name()));
            if(miejsce.isNull()) {
                if (zwierze.getClass() == Czlowiek.class)
                    miejsce = wyszukiwanie(zwierze, czlowiek1 -> (czlowiek1.getClass() == Czlowiek.class) && (((Czlowiek) czlowiek1).plemie == ((Czlowiek) zwierze).plemie));
                else
                    miejsce = wyszukiwanie(zwierze, dorosle1 -> (dorosle1.getClass() != Mlode.class) && (((Dorosle) dorosle1).gatunek == ((Dorosle) zwierze).gatunek));
            }
        } else if (zwierze.coJe==Rosliny) // Jest roslinozerca
            miejsce=wyszukiwanie(zwierze,(Pole pole1)->(lista.stream().map(Zwierze::getMiejsce).anyMatch(miejsce1->!miejsce1.equals(pole1.getMiejsce()))));
        Random random=new Random();
        if(miejsce.isNull()) {
            Object[] wZasiegu = listaPol.stream().filter(pole1 -> (pole1.getMiejsce().odleglosc(zwierze.getMiejsce()) <= zwierze.szybkosc)).map(Pole::getMiejsce).toArray();
            miejsce=(Koordy) wZasiegu[random.nextInt(wZasiegu.length)];
        }if(zwierze.getClass()== Ryba.class && !polaWody.contains(miejsce))
            miejsce=zwierze.getMiejsce();
        return miejsce;
    }

    public void wykonajPetle(){
        lista.addAll(tymczasowa);
        tymczasowa.clear();
        usun();
        for (Zwierze zwierze:lista) {
            if(zwierze.getRozmiar()==0)
                continue;
            //Ruch
            zwierze.ruch(decyzjaGdzieIdzie(zwierze));

            //Akcja
            Object[] obecneZwierzeta=lista.stream().filter(zwierze1 -> zwierze1.getMiejsce()==zwierze.getMiejsce()).toArray();
            if(obecneZwierzeta.length==1) { //Jezeli zwierze jest samo na polu
                if(zwierze.coJe!=Mieso){
                    Pole pole=listaPol.get(zwierze.getMiejsce().x+zwierze.getMiejsce().y* kafelki.y);
                    zwierze.zdobadzJedzenie(pole.jedzenie);
                    pole.jedzenie=0;
                }
                continue;
            }
            Map<String,List<Zwierze>> liczebnosc=new HashMap<>();
            for (Object zwierze1: obecneZwierzeta){
                String klucz="null";
//                try{
                if (zwierze1.getClass()== Dorosle.class)
                    klucz= ((Dorosle)zwierze1).gatunek.name();
                if(zwierze1.getClass()== Czlowiek.class)
                    klucz=((Czlowiek)zwierze1).plemie.name();
//                }catch (Exception e1){
                if(zwierze1.getClass()== Mlode.class)
                    klucz=((Mlode)zwierze1).kolejnyEtap;
//                }
                if(!liczebnosc.containsKey(klucz)){
                    if(liczebnosc.size()==2)
                        continue;
                    liczebnosc.put(klucz, new ArrayList<>());
                }
                liczebnosc.get(klucz).add((Zwierze) zwierze1);
            }
            if(liczebnosc.size()>=2) {
                walka(liczebnosc);
                continue;
            }
            if(zwierze.getClass()!= Mlode.class && zwierze.jedzenie>=2*zwierze.rozmiar){ //Na polu jest jednostka tego samego gatunku
                for(Object zwierze1: obecneZwierzeta){
                    Zwierze zwierz=(Zwierze)zwierze1;
                    if(zwierz.jedzenie>=2*zwierz.rozmiar){
                        if(zwierz.getClass()== Mlode.class){ //Na polu jest mlode
                            nakarm((Dorosle) zwierze,(Mlode) zwierz);
                        }else{
                            reprodukcja((Dorosle) zwierze,(Dorosle)zwierz);
                        }
                    }
                }
            }
        }
    }
    void nakarm(Dorosle dorosle,Mlode mlode){
        mlode.zdobadzJedzenie(dorosle.rozmiar*1/2);
        dorosle.jedzenie-=1/2*dorosle.rozmiar;
    }
    void walka(Map<String,List<Zwierze>> walczacy) {
        String[] id=walczacy.keySet().toArray(new String[0]);
        Integer[] sila={walczacy.get(id[0]).stream().mapToInt(Zwierze::getSila).sum(),walczacy.get(id[1]).stream().mapToInt(Zwierze::getSila).sum()};
        List<Zwierze> zwyciezcy=walczacy.get(id[0]);
        List<Zwierze> przegrani=walczacy.get(id[1]);

        if (sila[0]!=sila[1]) {
            if(sila[0]<sila[1]){
                zwyciezcy=walczacy.get(id[1]);
                przegrani=walczacy.get(id[0]);
            }
            float jedzenie=(float)(przegrani.stream().mapToDouble(Zwierze::getJedzenie).sum()+przegrani.stream().mapToDouble(Zwierze::getRozmiar).sum());

            for (int i=1;i<przegrani.size();i++) {
                if(przegrani.get(i).getRozmiar()!=0) {
                    przegrani.get(i).smierc();
                    doUsuniecia.add(przegrani.get(i).id);
                }
            }
            if(zwyciezcy.get(0).coJe!=Rosliny){
                for (Zwierze zwyciezca:zwyciezcy) {
                    zwyciezca.zdobadzJedzenie(jedzenie/zwyciezcy.size());
                }
                przegrani.get(0).smierc();
                doUsuniecia.add(przegrani.get(0).id);
            }else if(przegrani.get(0).getRozmiar()!=0) {
                przegrani.get(0).jedzenie = jedzenie;
                przegrani.get(0).smierc();
            }
        }
    }
    void reprodukcja(Dorosle zwierze1,Dorosle zwierze2){
        zwierze1.jedzenie-=1/2*zwierze1.rozmiar;
        zwierze2.jedzenie-=1/2*zwierze2.rozmiar;
        if(zwierze1.getClass()==Czlowiek.class)
            stworzZwierze(zwierze1.miejsce,"MlodySsak",((Czlowiek) zwierze1).plemie.name());
        else {
            String nazwa=(zwierze1.getClass()== Gad.class || zwierze1.getClass()== Ptak.class)?"Jajko":"Mlody";
            stworzZwierze(zwierze1.miejsce, nazwa + zwierze1.getClass().getSimpleName(), zwierze1.gatunek.name());
        }
    }
    void usun(){
        for (Integer id:doUsuniecia) {
            lista.removeIf(zwierze -> zwierze.id==id);
        }
        doUsuniecia.clear();
    }
    void dorosnij(int zwierze){

    }

    public List<Zwierze> getLista() {
        return lista;
    }
}
