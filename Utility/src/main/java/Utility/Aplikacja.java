package Utility;

import Pomocnicze.Koordy;
import Zwierzeta.ListaZwierzat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.*;

import static Zwierzeta.ListaZwierzat.koncoweStatystyki;

/**
 * The type Aplikacja.
 */
public class Aplikacja extends JFrame {
    private final Menu menu;
    private final Koordy rozmiarOkna;
    private final Koordy kafelki;
    /**
     * The constant listaZwierzat.
     */
    public static ListaZwierzat listaZwierzat;
    private boolean menuZamkniete=false;
    private boolean pauza=false;
    private boolean czyZwloki = true;
    private Integer odswiezanie=1000;
    private Map<String,Integer> dane;
    /**
     * The Tura.
     */
    static int tura=0;
    private Timer petla;
    private Symulacja symulacja;
    private Statystyki statystyki;

    private JSlider predkoscOdswierzania;

    /**
     * Instantiates a new Aplikacja.
     *
     * @param rozmiarOknaX rozmiar okna w wzd&#x142;u&#x17C; osi OX, wyra&#x17C;ona w pixelach
     * @param rozmiarOknaY rozmiar okna w wzd&#x142;u&#x17C; osi OY, wyra&#x17C;ona w pixelach
     * @param kafelkiX     liczba kafelek na mapie wzd&#x142;u&#x17C; osi OX
     * @param kafelkiY     liczba kafelek na mapie wzd&#x142;u&#x17C; osi OY
     */
    public Aplikacja(int rozmiarOknaX,int rozmiarOknaY, int kafelkiX, int kafelkiY) {
        menu=new Menu();
        rozmiarOkna=new Koordy(rozmiarOknaX,rozmiarOknaY);
        kafelki=new Koordy(kafelkiX,kafelkiY);
    }

    /**
     * Metoda wywo&#x142;ywana po naci&#x15B;ni&#x119;ciu przycisku "Start" w menu, kopiuje dane zebrane przez menu oraz na ich podstawie wywu&#x142;uje generacje mapy, tworzenie zwierz&#x105;t oraz wy&#x15B;wietla okno z symulacj&#x105;.
     */
    private void start(){
        kafelki.x=dane.remove("Szerokosc");
        kafelki.y=dane.remove("Wysokosc");
        rozmiarOkna.x=dane.remove("RozdzielczoscX");
        rozmiarOkna.y=dane.remove("RozdzielczoscY");
        float jedzenie = dane.remove("Glod")/10f;

        for(String stat: new String[]{"Smierci z glodu", "Smierci w walce", "Smierci ze starosci", "Zjedzone rosliny", "Zjedzone mieso", "Maksymalna populacja", "Najliczniejszy gatunek"})
            koncoweStatystyki.put(stat,0F);

        symulacja=new Symulacja(rozmiarOkna,kafelki);
        listaZwierzat=new ListaZwierzat(kafelki,symulacja.listaPol,symulacja.polaWody,jedzenie);
        for (Object wpis: dane.entrySet().toArray()) {
            String[] pomoc =wpis.toString().split("=");
            for(int i = 0; i<Integer.parseInt(pomoc[1]); i++) {
                listaZwierzat.stworzZwierze(new Koordy().losoweMiejsce(kafelki.x, kafelki.y),pomoc[0]);
            }
        }
        statystyki=new Statystyki(rozmiarOkna);

        predkoscOdswierzania=new JSlider(0,6,3);
        predkoscOdswierzania.setMaximumSize(new Dimension((int)(rozmiarOkna.x*0.3),40));
        predkoscOdswierzania.setMajorTickSpacing(1);
        Hashtable<Integer, JLabel> podpisy = new Hashtable<>();
        podpisy.put(0, new JLabel("0.1s"));
        podpisy.put(1, new JLabel("0.2s"));
        podpisy.put(2, new JLabel("0.5s"));
        podpisy.put(3, new JLabel("1s"));
        podpisy.put(4, new JLabel("2s"));
        podpisy.put(5, new JLabel("5s"));
        podpisy.put(6, new JLabel("10s"));
        predkoscOdswierzania.setLabelTable(podpisy);
        predkoscOdswierzania.setPaintLabels(true);
        predkoscOdswierzania.addChangeListener(e -> {
            float[] predkosci= {1/10F,1/5F,1/2F,1,2,5,10};
            System.out.println((int)(predkosci[predkoscOdswierzania.getValue()]*1000));
            petla.cancel();
            ustawPetle((int)(predkosci[predkoscOdswierzania.getValue()]*1000));
        });

        JLabel opisPredkosci =new JLabel("Czas miedzy turami:");
        opisPredkosci.setMaximumSize(new Dimension(500,10));
        opisPredkosci.setMinimumSize(new Dimension(500,10));
        opisPredkosci.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton stop=new JButton("STOP");
        stop.setMargin(new Insets(0,0,0,0));
        stop.setFont(new Font("Arial",Font.BOLD,15));
        stop.setMaximumSize(new Dimension(100,50));
        stop.setPreferredSize(new Dimension(80,40));
        stop.addActionListener(e -> {
            stop.setText((Objects.equals(stop.getText(), "STOP"))?"START":"STOP");
            stop();
        });

        Image grafika = new ImageIcon("Utility/src/main/resources/smierc.png").getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JButton zwloki=new JButton(new ImageIcon(grafika));
        zwloki.setMaximumSize(new Dimension(50,50));
        zwloki.setPreferredSize(new Dimension(50,50));

        zwloki.addActionListener(e -> czyZwloki=!czyZwloki);

        JPanel panelBoczny = new JPanel();
        panelBoczny.setLayout(new BoxLayout(panelBoczny,BoxLayout.Y_AXIS));
        panelBoczny.revalidate();
        panelBoczny.add(statystyki);
        panelBoczny.add(opisPredkosci);
        panelBoczny.add(predkoscOdswierzania);
        JPanel przyciski = new JPanel();
        przyciski.setMaximumSize(new Dimension(Integer.MAX_VALUE,60));
        przyciski.add(stop);
        przyciski.add(zwloki);
        panelBoczny.add(przyciski);

        setLayout(new BorderLayout());
        add(symulacja,BorderLayout.WEST);
        add(panelBoczny);

        pack();
        setSize(rozmiarOkna.x,rozmiarOkna.y);
        setTitle("Plemiona Amazonki");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                koniec();
            }
        });
    }

    /**
     * Zmienia stan zmiennej pauza - odpowiadaj&#x105;cej za nie wykonywanie si&#x119; p&#x119;tli Aplikacji
     */
    private void stop(){
        pauza=!pauza;
    }

    /**
     * Metoda wywo&#x142;ywana co ka&#x17C;de wykonanie si&#x119; p&#x119;tli, sprawdza czy menu zosta&#x142;o zamkni&#x119;te (je&#x17C;eli tak startuje symulacj&#x119;) albo nakazuje wykonanie p&#x119;tli przez ListaZwierzat oraz Symulacja, jak i r&oacute;wie&#x17C; malowanie w JFrame symulacji oraz statystyk.
     */
    public void rysuj() {
        if(!pauza){
            if (!menuZamkniete && !menu.isVisible()) {
                menuZamkniete = true;
                this.dane = menu.dane;
                menu.dispose();
                start();
            }else if (menuZamkniete) {
                tura+=1;
                listaZwierzat.wykonajPetle();
                symulacja.wykonajPetle(tura,czyZwloki);
                symulacja.repaint();
                statystyki.repaint();
                int sumaZwierzat= ListaZwierzat.iloscZwierzat.values().stream().mapToInt(Integer::intValue).sum();
                if(sumaZwierzat==0 || sumaZwierzat>=10000)
                    koniec();
            }
        }
    }

    /**
     * Ustawia p&#x119;tle Aplikacji, je&#x17C;eli wej&#x15B;ciowe osiwe&#x17C;anie r&oacute;&#x17C;ni sie od aktualnego, usuwa stary Timer i tworzy nowy. P&#x119;tla wykonuje metod&#x119; rysuj() co okre&#x15B;lony czas od&#x15B;wierzania, wyra&#x17C;ony w mikrosektunach.
     *
     * @param odswiezanie1 nowe od&#x15B;wierzanie p&#x119;tli wyra&#x17C;one w mikrosekundach
     */
    public void ustawPetle(Integer odswiezanie1) {
        petla=new Timer();
        petla.schedule(new TimerTask() {
            public void run() {
                rysuj();
                if(!odswiezanie1.equals(odswiezanie)) {
                    petla.cancel();
                    odswiezanie = odswiezanie1;
                    ustawPetle(odswiezanie1);
                }
            }
        }, 0, odswiezanie);
    }

    /**
     * Metoda stopuje symulacj&#x119;, tworzy nowe okno - Podsumowanie, zawierajace ko&#x144;coweStatystki, po czym usuwa aplikacj&#x119;.
     */
    private void koniec(){
        System.out.println("nice");
        stop();
        koncoweStatystyki.put("Tury",Float.parseFloat(Integer.toString(tura)));
        new Podsumowanie(koncoweStatystyki);
        dispose();
    }

}
