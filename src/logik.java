package src;
import java.awt.Color;
import src.klassen.*;

public class logik 
{
    universell[][] logikFeld;
    felderart art;
    universell current;
    public logik()
    {
        logikFeld = new universell[10][10];
        initialisieren();
        art = new felderart();
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

    public boolean fastCheckForSchach(int konigX, int konigY, universell[][] artfeld, boolean turn)
    {
        if(turn == true && artfeld[konigX][konigY].giveAbstammungColor().equals(Color.BLACK))
        {
            return true;
        }
        else if(turn == false && artfeld[konigX][konigY].giveAbstammungColor().equals(Color.WHITE))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    public boolean CheckForSchach(universell[][] logikfeld, universell[][] artfeld, boolean turn)
    {
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(logikfeld[x][y].giveID().equals("konig"))
                {
                    if(turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE))
                    {
                        if(artfeld[x][y].giveID().equals("angriff"))
                        {
                            return true;
                        }
                    }
                    else if(turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK))
                    {
                        if(artfeld[x][y].giveID().equals("angriff"))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean CheckForMatt(universell[][] logikfeld, universell[][] artfeld, boolean turn)
    {
        boolean konigunfahig = false;
        int konigX = 0;
        int konigY = 0;
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(logikfeld[x][y].giveID().equals("konig"))
                {
                    if(turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE))
                    {
                        konigX = x;
                        konigY = y;
                        int[] xs = logikfeld[x][y].xmoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        int[] ys = logikfeld[x][y].ymoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        int nullzaehler = 0; // zahlt die Schritte, die er nicht machen kann
                        for(int i = 0; i < xs.length; i++)
                        {
                            if(xs[i] == 0 && ys[i] == 0)
                            {
                                nullzaehler++;
                            }
                        }
                        if(nullzaehler == xs.length)
                        {
                            konigunfahig = true;
                        }
                    }
                    else if(turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK))
                    {
                        konigX = x;
                        konigY = y;
                        int[] xs = logikfeld[x][y].xmoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        int[] ys = logikfeld[x][y].ymoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        int nullzaehler = 0; // zahlt die Schritte, die er nicht machen kann
                        for(int i = 0; i < xs.length; i++)
                        {
                            if(xs[i] == 0 && ys[i] == 0)
                            {
                                nullzaehler++;
                            }
                        }
                        if(nullzaehler == xs.length)
                        {
                            konigunfahig = true;
                        }
                    }
                }
            }
        }
        if(konigunfahig == true)
        {
            universell probierFigur; // die Figur mit der alles probiert wird
            universell gegnerFigur; // die gegnerische Figur, die beim Probieren vielleicht geschlagen werden muss, um ein Matt zu vermeiden(wenn nicht, wird diese zuruckgesetzt)
            int gegnerX, gegnerY;
            boolean check;
            // alle Figuren prufen auf die Moglichkeit des Helfens
            for(int y = 1; y < 9; y++)
            {
                for(int x = 1; x < 9; x++)
                {
                    if(turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE))
                    {
                        int[] xs = logikfeld[x][y].xmoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        int[] ys = logikfeld[x][y].ymoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        probierFigur = logikfeld[x][y];
                        for(int i = 0; i < xs.length; i++) // alle moglichkeiten durchgehen
                        {
                            logikfeld[x][y] = new frei(); // wegmachen, um Logik des Spiels zu erhalten
                            gegnerX = x + xs[i];
                            gegnerY = y + ys[i];
                            gegnerFigur = logikfeld[gegnerX][gegnerY];
                            logikfeld[gegnerX][gegnerY] = probierFigur;
                            // Schach checken
                            check = fastCheckForSchach(konigX, konigY, artfeld, turn);
                            if(check == true)
                            {
                                logikfeld[gegnerX][gegnerY] = gegnerFigur;
                            }
                            else 
                            {
                                logikfeld[x][y] = probierFigur;
                                logikfeld[gegnerX][gegnerY] = gegnerFigur;
                                return false;
                            }
                        }
                    }
                    else if(turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK))
                    {
                        int[] xs = logikfeld[x][y].xmoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        int[] ys = logikfeld[x][y].ymoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                        probierFigur = logikfeld[x][y];
                        for(int i = 0; i < xs.length; i++) // alle moglichkeiten durchgehen
                        {
                            logikfeld[x][y] = new frei(); // wegmachen, um Logik des Spiels zu erhalten
                            gegnerX = x + xs[i];
                            gegnerY = y + ys[i];
                            gegnerFigur = logikfeld[gegnerX][gegnerY];
                            logikfeld[gegnerX][gegnerY] = probierFigur;
                            // Schach checken
                            check = fastCheckForSchach(konigX, konigY, artfeld, turn);
                            if(check == true)
                            {
                                logikfeld[gegnerX][gegnerY] = gegnerFigur;
                            }
                            else 
                            {
                                logikfeld[x][y] = probierFigur;
                                logikfeld[gegnerX][gegnerY] = gegnerFigur;
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean CheckForPatt(universell[][] logikfeld, universell[][] artfeld, boolean turn)
    {
        int figuren = 0; // figuren auf dem Feld
        int nullerfiguren = 0; // figuren auf dem Feld, die sich nicht bewegen konnen
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE))
                {
                    figuren++;
                    int[] xs = logikfeld[x][y].xmoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                    int[] ys = logikfeld[x][y].ymoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                    int nullzaehler = 0;
                    for(int i = 0; i < xs.length; i++)
                    {
                        if(xs[i] == 0 && ys[i] == 0)
                        {
                            nullzaehler++;
                        }
                    }
                    if(nullzaehler == xs.length)
                    {
                        nullerfiguren++;
                    }
                }
                else if(turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK))
                {
                    figuren++;
                    int[] xs = logikfeld[x][y].xmoglichesFeld(turn, logikfeld, artfeld, x, y);
                    int[] ys = logikfeld[x][y].ymoglichesFeld(turn, logikfeld, artfeld, x, y);
                    int nullzaehler = 0;
                    for(int i = 0; i < xs.length; i++)
                    {
                        if(xs[i] == 0 && ys[i] == 0)
                        {
                            nullzaehler++;
                        }
                    }
                    if(nullzaehler == xs.length)
                    {
                        nullerfiguren++;
                    }
                }
            }
        }
        if(nullerfiguren == figuren)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int[] getX(universell figur, int eigx, int eigy)
    {
        return figur.xmoglichesFeld(figur.giveFirst(), logikFeld, art.art, eigx, eigy);
    }

    public int[] getY(universell figur, int eigx, int eigy)
    {
        return figur.ymoglichesFeld(figur.giveFirst(), logikFeld, art.art, eigx, eigy);
    }

    public void freisetzer(int x, int y)
    {
        logikFeld[x][y] = new frei();
    }
}
