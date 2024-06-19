package src;

import java.awt.Color;

import javax.swing.JButton;
import javax.xml.bind.annotation.W3CDomHandler;

import src.klassen.*;

public class logik {
    universell[][] logikFeld;
    felderart art;
    universell current;

    public logik() {
        logikFeld = new universell[10][10];
        initialisieren();
        art = new felderart();
    }

    public void initialisieren() {
        // erst alle aussen-Felder erstellen
        for (int i = 0; i < 10; i++) {
            logikFeld[0][i] = new aussen();
            logikFeld[9][i] = new aussen();
            logikFeld[i][0] = new aussen();
            logikFeld[i][9] = new aussen();
        }
        // freie Felder initialisieren
        for (int y = 3; y < 7; y++) 
        {
            for (int x = 1; x < 9; x++) 
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
        for (int i = 1; i < 9; i++) 
        {
            logikFeld[i][2] = new bauer(Color.BLACK, "bauerschwarz", "bauer", true);
            logikFeld[i][7] = new bauer(Color.WHITE, "bauerweiss", "bauer", true);
        }
    }

    public boolean anfangsCheckForSchach(universell[][] logikfeld, universell[][] artfeld)
    {
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(logikfeld[x][y].giveID().equals("konig"))
                {
                    if(logikfeld[x][y].giveColor().equals(Color.WHITE) && artfeld[x][y].giveAbstammungColor().equals(Color.BLACK))
                    {
                        return true;
                    }
                    else if(logikfeld[x][y].giveColor().equals(Color.BLACK) && artfeld[x][y].giveAbstammungColor().equals(Color.WHITE))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean fastCheckForSchach(universell[][] logikfeld, universell[][] artfeld, boolean turn) 
    {
        boolean schach = false; // false = nichts, true = schach
        boolean fertig = false; // dafur da um aus den for-schleifen rauszukommen
        for (int y = 1; y < 9; y++) 
        {
            for (int x = 1; x < 9; x++) 
            {
                if (logikfeld[x][y].giveID().equals("konig")) 
                {
                    if (turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE)) 
                    {
                        if (artfeld[x][y].giveID().equals("angriff") && artfeld[x][y].giveAbstammungColor().equals(Color.BLACK)) 
                        {
                            schach = true;
                            fertig = true;
                            break;
                        }
                    } 
                    else if (turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK)) 
                    {
                        if (artfeld[x][y].giveID().equals("angriff") && artfeld[x][y].giveAbstammungColor().equals(Color.WHITE)) 
                        {
                            schach = true;
                            fertig = true;
                            break;
                        }
                    }
                }
            }
            if(fertig)
            {
                break;
            }
        }
        return schach;
    }

    public int CheckForSchach(universell[][] logikfeld, universell[][] artfeld, JButton[][] graphfeld, boolean turn) 
    {
        int schach = 0; // 0 = nichts, 1 = schach, 2 = matt
        boolean fertig = false; // dafur da um aus den for-schleifen rauszukommen
        for (int y = 1; y < 9; y++) 
        {
            for (int x = 1; x < 9; x++) 
            {
                if (logikfeld[x][y].giveID().equals("konig")) 
                {
                    if (turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE)) 
                    {
                        if (artfeld[x][y].giveID().equals("angriff") && artfeld[x][y].giveAbstammungColor().equals(Color.BLACK)) 
                        {
                            schach = 1;
                            fertig = true;
                            break;
                        }
                    } 
                    else if (turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK)) 
                    {
                        if (artfeld[x][y].giveID().equals("angriff") && artfeld[x][y].giveAbstammungColor().equals(Color.WHITE)) 
                        {
                            schach = 1;
                            fertig = true;
                            break;
                        }
                    }
                }
            }
            if(fertig)
            {
                break;
            }
        }
        if(schach == 1) // hier wird nach Matt geschaut
        {
            int gesamt = 0; // zahlt die insgesamt figuren der gefragten farbe
            int nichtVerfugbar = 0; // zahlt die Figuren, die man im Schach nicht nutzen kann
            int nichtsnutz = 0; // zahlt wie oft die bewegung der probierfigur nichts bringt
            boolean check;
            universell probierfigur, gegnerfigur; // probierfigur geht durch alle durch und gegnerfigur speichert den theorethisch gegangen Schritt
            if(turn == true)
            {
                for(int yy = 1; yy < 9; yy++)
                {
                    for(int xx = 1; xx <9; xx++)
                    {
                        if(logikfeld[xx][yy].giveColor().equals(Color.WHITE))
                        {
                            gesamt++;
                        }
                    }
                }
            }
            else if(turn == false)
            {
                for(int yy = 1; yy < 9; yy++)
                {
                    for(int xx = 1; xx <9; xx++)
                    {
                        if(logikfeld[xx][yy].giveColor().equals(Color.BLACK))
                        {
                            gesamt++;
                        }
                    }
                }
            
            }
            
            for(int y = 1; y < 9; y++)
            {
                for(int x = 1; x < 9; x++)
                {
                    if(turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE))
                    {
                        probierfigur = logikfeld[x][y];
                        int[] xs = probierfigur.xmoglichesFeld(probierfigur.giveFirst(), logikfeld, artfeld, x, y);
                        int[] ys = probierfigur.ymoglichesFeld(probierfigur.giveFirst(), logikfeld, artfeld, x, y);
                        for(int i = 0; i < xs.length; i++)
                        {
                            gegnerfigur = logikfeld[x + xs[i]][y + ys[i]];
                            logikfeld[x][y] = new frei();
                            logikfeld[x + xs[i]][y + ys[i]] = probierfigur;
                            check = fastCheckForSchach(logikfeld, artfeld, turn);
                            if(check)
                            {
                                logikfeld[x + xs[i]][y + ys[i]] = gegnerfigur;
                                logikfeld[x][y] = probierfigur;
                                nichtsnutz++;
                            }
                        }
                        if (nichtsnutz == xs.length)
                        {
                            graphfeld[x][y].setEnabled(false);
                            nichtVerfugbar++;
                        }
                    }
                    else if(turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK))
                    {
                        probierfigur = logikfeld[x][y];
                        int[] xs = probierfigur.xmoglichesFeld(probierfigur.giveFirst(), logikfeld, artfeld, x, y);
                        int[] ys = probierfigur.ymoglichesFeld(probierfigur.giveFirst(), logikfeld, artfeld, x, y);
                        for(int i = 0; i < xs.length; i++)
                        {
                            gegnerfigur = logikfeld[x + xs[i]][y + ys[i]];
                            logikfeld[x][y] = new frei();
                            logikfeld[x + xs[i]][y + ys[i]] = probierfigur;
                            artfeld = art.aktualisieren(turn, artfeld, logikfeld);
                            check = fastCheckForSchach(logikfeld, artfeld, turn);
                            if(check)
                            {
                                logikfeld[x + xs[i]][y + ys[i]] = gegnerfigur;
                                logikfeld[x][y] = probierfigur;
                                artfeld = art.aktualisieren(turn, artfeld, logikfeld);
                                nichtsnutz++;
                            }
                            else
                            {
                                logikfeld[x + xs[i]][y + ys[i]] = gegnerfigur;
                                logikfeld[x][y] = probierfigur;
                                artfeld = art.aktualisieren(turn, artfeld, logikfeld);
                            }
                        }
                        if (nichtsnutz == xs.length)
                        {
                            graphfeld[x][y].setEnabled(false);
                            nichtVerfugbar++;
                        }
                        nichtsnutz = 0;
                    }
                }
            }
            if(nichtVerfugbar == gesamt)
            {
                schach = 2;
            }
        }
        return schach;
    }


    public boolean CheckForPatt(universell[][] logikfeld, universell[][] artfeld, boolean turn) {
        int figuren = 0; // figuren auf dem Feld
        int nullerfiguren = 0; // figuren auf dem Feld, die sich nicht bewegen konnen
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                if (turn == true && logikfeld[x][y].giveColor().equals(Color.WHITE)) {
                    figuren++;
                    int[] xs = logikfeld[x][y].xmoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                    int[] ys = logikfeld[x][y].ymoglichesFeld(logikfeld[x][y].giveFirst(), logikfeld, artfeld, x, y);
                    int nullzaehler = 0;
                    for (int i = 0; i < xs.length; i++) {
                        if (xs[i] == 0 && ys[i] == 0) {
                            nullzaehler++;
                        }
                    }
                    if (nullzaehler == xs.length) {
                        nullerfiguren++;
                    }
                } else if (turn == false && logikfeld[x][y].giveColor().equals(Color.BLACK)) {
                    figuren++;
                    int[] xs = logikfeld[x][y].xmoglichesFeld(turn, logikfeld, artfeld, x, y);
                    int[] ys = logikfeld[x][y].ymoglichesFeld(turn, logikfeld, artfeld, x, y);
                    int nullzaehler = 0;
                    for (int i = 0; i < xs.length; i++) {
                        if (xs[i] == 0 && ys[i] == 0) {
                            nullzaehler++;
                        }
                    }
                    if (nullzaehler == xs.length) {
                        nullerfiguren++;
                    }
                }
            }
        }
        if (nullerfiguren == figuren) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getX(universell figur, int eigx, int eigy) {
        return figur.xmoglichesFeld(figur.giveFirst(), logikFeld, art.art, eigx, eigy);
    }

    public int[] getY(universell figur, int eigx, int eigy) {
        return figur.ymoglichesFeld(figur.giveFirst(), logikFeld, art.art, eigx, eigy);
    }

    public void freisetzer(int x, int y) {
        logikFeld[x][y] = new frei();
    }
}
