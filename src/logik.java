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
        logikFeld[1][1] = new turm(Color.BLACK, "turmschwarz", "turm");
        logikFeld[8][1] = new turm(Color.BLACK, "turmschwarz", "turm");
        logikFeld[1][8] = new turm(Color.WHITE, "turmweiss", "turm");
        logikFeld[8][8] = new turm(Color.WHITE, "turmweiss", "turm");
        logikFeld[2][1] = new springer(Color.BLACK, "springerschwarz", "springer");
        logikFeld[7][1] = new springer(Color.BLACK, "springerschwarz", "springer");
        logikFeld[2][8] = new springer(Color.WHITE, "springerweiss", "springer");
        logikFeld[7][8] = new springer(Color.WHITE, "springerweiss", "springer");
        logikFeld[3][1] = new laufer(Color.BLACK, "lauferschwarz", "laufer");
        logikFeld[6][1] = new laufer(Color.BLACK, "lauferschwarz", "laufer");
        logikFeld[3][8] = new laufer(Color.WHITE, "lauferweiss", "laufer");
        logikFeld[6][8] = new laufer(Color.WHITE, "lauferweiss", "laufer");
        logikFeld[4][1] = new dame(Color.BLACK, "dameschwarz", "dame");
        logikFeld[4][8] = new dame(Color.WHITE, "dameweiss", "dame");
        logikFeld[5][1] = new konig(Color.BLACK, "konigschwarz", "konig");
        logikFeld[5][8] = new konig(Color.WHITE, "konigweiss", "konig");
        // Bauern initialisieren
        for(int i = 1; i < 9; i++)
        {
            logikFeld[i][2] = new bauer(Color.BLACK, "bauerschwarz", "bauer", true);
            logikFeld[i][7] = new bauer(Color.WHITE, "bauerweiss", "bauer", true);
        }
    }

    public int[] schauer(universell figur, int eigx, int eigy) // muss figur-abhangig sein um für jede figur ihre individuellen Schritte mitzugeben
    {
        int[] freieFelder;
        universell tester;
        int zaehler = 0;
        if(figur.giveID().equals("bauer"))
        {
            freieFelder = new int[187];

            for(int y = -1; y < 2; y++)
            {
                for(int x = -1; x < 2; x++)
                {
                    tester = logikFeld[eigx + x][eigy + y];
                    if(figur.giveColor().equals(Color.WHITE)) // 2 Cases, da verschiedene Farben
                    {
                        if(tester.giveColor().equals(Color.BLACK))
                        {
                            freieFelder[zaehler] = 1;
                        }
                        else if(tester.giveColor().equals(Color.WHITE) || tester.giveID().equals("aussen"))
                        {
                            freieFelder[zaehler] = 2;
                        }
                        else
                        {
                            freieFelder[zaehler] = 0;
                        }
                    }
                    else
                    {
                        if(tester.giveColor().equals(Color.WHITE))
                        {
                            freieFelder[zaehler] = 1;
                        }
                        else if(tester.giveColor().equals(Color.BLACK) || tester.giveID().equals("aussen"))
                        {
                            freieFelder[zaehler] = 2;
                        }
                        else
                        {
                            freieFelder[zaehler] = 0;
                        }
                    }
                    

                    zaehler++;
                }
            }
            return freieFelder;
        }
        int[] notfall = new int[1];
        return notfall;
    }

    public int[] getX(universell figur, int eigx, int eigy)
    {
        return figur.xmoglichesFeld(schauer(figur,eigx,eigy),figur.giveFirst());
    }

    public int[] getY(universell figur, int eigx, int eigy)
    {
        return figur.ymoglichesFeld(schauer(figur,eigx,eigy),figur.giveFirst());
    }
}
