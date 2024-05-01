package src;
import java.awt.Color;

import src.klassen.*;
public class logik 
{
    universell[][] logikFeld;
    public logik()
    {
        logikFeld = new universell[10][10];
        initialisieren();
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
        for(int y = 3; y < 7; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                logikFeld[x][y] = new frei();
            }
        }
        // alle Figuren initialisieren
        logikFeld[1][1] = new turm(Color.BLACK, "turmschwarz", "figur");
        logikFeld[8][1] = new turm(Color.BLACK, "turmschwarz", "figur");
        logikFeld[1][8] = new turm(Color.WHITE, "turmweiss", "figur");
        logikFeld[8][8] = new turm(Color.WHITE, "turmweiss", "figur");
        logikFeld[2][1] = new springer(Color.BLACK, "springerschwarz", "figur");
        logikFeld[7][1] = new springer(Color.BLACK, "springerschwarz", "figur");
        logikFeld[2][8] = new springer(Color.WHITE, "springerweiss", "figur");
        logikFeld[7][8] = new springer(Color.WHITE, "springerweiss", "figur");
        logikFeld[3][1] = new laufer(Color.BLACK, "lauferschwarz", "figur");
        logikFeld[6][1] = new laufer(Color.BLACK, "lauferschwarz", "figur");
        logikFeld[3][8] = new laufer(Color.WHITE, "lauferweiss", "figur");
        logikFeld[6][8] = new laufer(Color.WHITE, "lauferweiss", "figur");
        logikFeld[4][1] = new dame(Color.BLACK, "dameschwarz", "figur");
        logikFeld[4][8] = new dame(Color.WHITE, "dameweiss", "figur");
        logikFeld[5][1] = new konig(Color.BLACK, "konigschwarz", "figur");
        logikFeld[5][8] = new konig(Color.WHITE, "konigweiss", "figur");
        // Bauern initialisieren
        for(int i = 1; i < 9; i++)
        {
            logikFeld[i][2] = new bauer(Color.BLACK, "bauerschwarz", "figur");
            logikFeld[i][7] = new bauer(Color.WHITE, "bauerweiss", "figur");
        }

    }
}
