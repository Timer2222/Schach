package src;
import java.awt.Color;

import src.klassen.*;
public class logik 
{
    universell[][] logikFeld;
    public logik()
    {
        logikFeld = new universell[10][10];
    }

    public void initialisieren()
    {
        //erst alle aussen-Felder erstellen
        for(int i = 0; i < 10; i++)
        {
            logikFeld[0][i] = new aussen();
            logikFeld[9][i] = new aussen();
            logikFeld[i][0] = new aussen();
            logikFeld[i][9] = new aussen();
        }
        // freie Felder initialisieren
        for(int x = 1; x < 9; x++)
        {
            for(int y = 3; y < 7; y++)
            {
                logikFeld[x][y] = new frei();
            }
        }
        // alle Figuren initialisieren
        logikFeld[1][1] = new turm();
        logikFeld[8][1] = new turm();
        logikFeld[1][8] = new turm();
        logikFeld[8][8] = new turm();
        logikFeld[2][1] = new springer();
        logikFeld[7][1] = new springer();
        logikFeld[2][8] = new springer();
        logikFeld[7][8] = new springer();
        logikFeld[3][1] = new laufer();
        logikFeld[6][1] = new laufer();
        logikFeld[3][8] = new laufer();
        logikFeld[6][8] = new laufer();
        logikFeld[4][1] = new dame();
        logikFeld[4][8] = new dame();
        logikFeld[5][1] = new konig();
        logikFeld[5][8] = new konig();
        // Bauern initialisieren
        for(int i = 1; i < 9; i++)
        {
            logikFeld[i][2] = new bauer(Color.BLACK, "schwarz");
            logikFeld[i][7] = new bauer(Color.WHITE, "weiss");
        }

    }
}
