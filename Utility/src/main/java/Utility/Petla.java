package Utility;

import java.util.TimerTask;

public class Petla extends TimerTask{
    private Aplikacja app;
    public Petla(Aplikacja app) {
        this.app = app;

    }
    @Override
    public void run() {
        app.rysuj();
    }

}
