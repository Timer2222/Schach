package src;
import javax.swing.*;

import src.klassen.frei;
import src.klassen.turm;
// import src.klassen.bauer;
import src.klassen.universell;
import src.klassen.unsichtbar;

import java.awt.event.*;
// import java.applet.*; // für Audio
// import java.io.*;
// import java.net.*;
import java.awt.*;

public class gui extends JFrame implements ActionListener
{
    boolean turn; //wenn true ist weiss dran, wenn false ist schwarz dran
    JButton[][] graphFeld;
    JButton starten;
    boolean schwarz = false;
    Color lightGreen, darkGreen, red;
    logik logik;
    universell aktuelleFigur;
    int currentX, currentY;
    public gui()
    {
        this.setSize(800,800);
        this.setLayout(null);
        this.setTitle("Schach von Anton Klonig und Tim Weber");
        turn = true;
        graphFeld = new JButton[10][10];
        logik = new logik();
        lightGreen = new Color(115, 240, 135);
        darkGreen = new Color(5, 105, 20);
        red = new Color(200,30,30);
        initialisieren();
        figurenEinfugen();
    }

    public void Feldfaerben()
    {
        for(int i = 1; i < 9; i++)
        {
            for(int ii = 1; ii < 9; ii++)
            {
                if(schwarz == true)
                {
                    graphFeld[i][ii].setBackground(Color.GRAY);
                    schwarz = false;
                }
                else
                {
                    graphFeld[i][ii].setBackground(Color.WHITE);
                    schwarz = true;
                }
            }
            if(schwarz == true)
            {
                schwarz = false;
            }
            else
            {
                schwarz = true;
            }
        }
    }
    
    public void initialisieren()
    {
        // Button nur fuer Anton und mich
        starten = new JButton("Test starten");
        starten.setBounds(600, 300, 100, 100);
        starten.addActionListener(this);
        this.add(starten);

        int yzaehler = 0;
        for(int i = 0; i < 10; i++)
        {
            int xzaehler = 0;
            
            for(int ii = 0; ii < 10; ii++)
            {
                graphFeld[ii][i] = new JButton();
                graphFeld[ii][i].setEnabled(false);
                graphFeld[ii][i].setBounds(50 + xzaehler,50 + yzaehler,60,60);
                graphFeld[ii][i].addActionListener(this);
                this.add(graphFeld[ii][i]);
                xzaehler = xzaehler + 60;
            }
            yzaehler = yzaehler + 60;
        }
        Feldfaerben();
        // Die beiden For-Schleifen um die Äußeren zu entfernen
        for(int i = 0; i < 10; i++)
        {
            graphFeld[0][i].setVisible(false);
            graphFeld[9][i].setVisible(false);
            graphFeld[i][0].setVisible(false);
            graphFeld[i][9].setVisible(false);
        }
    }

    public void nachsteRunde()
    {
        logik.art.art = logik.art.aktualisieren(turn);
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(turn == true)
                {
                    if(logik.logikFeld[x][y].giveID().equals("unsichtbar") && logik.logikFeld[x][y].giveAbstammungColor().equals(Color.WHITE))
                    {
                        logik.logikFeld[x][y] = new frei();
                    }
                    else
                    {
                        if(logik.logikFeld[x][y].giveColor().equals(Color.WHITE) && !logik.logikFeld[x][y].giveID().equals("unsichtbar"))
                        {
                            graphFeld[x][y].setEnabled(true);
                        }
                        else
                        {
                            graphFeld[x][y].setEnabled(false);
                        }
                    }
                  
                }
                else
                {
                    if(logik.logikFeld[x][y].giveID().equals("unsichtbar") && logik.logikFeld[x][y].giveAbstammungColor().equals(Color.BLACK))
                    {
                        logik.logikFeld[x][y] = new frei();
                    }
                    else
                    {
                        if(logik.logikFeld[x][y].giveColor().equals(Color.BLACK) && !logik.logikFeld[x][y].giveID().equals("unsichtbar"))
                        {
                            graphFeld[x][y].setEnabled(true);
                        }
                        else
                        {
                            graphFeld[x][y].setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    public void figurenEinfugen()
    {
        universell figur;
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                figur = logik.logikFeld[x][y];
                ImageIcon bild = figur.bild();
                graphFeld[x][y].setIcon(bild);
            }
        }
    }

    public void neueEntscheidung() // falls man sich dch fur eine andere Figur entscheidet, werden alle enabled felder wieder disabled
    {
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(turn == true && logik.logikFeld[x][y].giveColor().equals(Color.WHITE) && !logik.logikFeld[x][y].giveID().equals("unsichtbar"))
                {
                    graphFeld[x][y].setEnabled(true);
                }
                else if(turn == false && logik.logikFeld[x][y].giveColor().equals(Color.BLACK) && !logik.logikFeld[x][y].giveID().equals("unsichtbar"))
                {
                    graphFeld[x][y].setEnabled(true);
                }
                else
                {
                    graphFeld[x][y].setEnabled(false);
                }
            }
        }
        
    }


    public void actionPerformed(ActionEvent event)
    {
        Feldfaerben();
        universell figur;
        if(event.getSource() == starten)
        {
            starten.setVisible(false);
            nachsteRunde();
        }

        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(event.getSource() == graphFeld[x][y])
                {
                    figur = logik.logikFeld[x][y];
                    if(!logik.logikFeld[x][y].giveID().equals("aussen") && !logik.logikFeld[x][y].giveID().equals("frei") && !logik.logikFeld[x][y].giveColor().equals(Color.PINK))
                    {
                        if(turn == true && figur.giveColor().equals(Color.WHITE))
                        {
                            neueEntscheidung();
                            int[] xs = logik.getX(figur, x, y);
                            int[] ys = logik.getY(figur, x, y);
                            aktuelleFigur = figur;
                            currentX = x;
                            currentY = y;
                            faerber(x, xs, y, ys);
                        }
                        else if(turn == false && figur.giveColor().equals(Color.BLACK))
                        {
                            neueEntscheidung();
                            int[] xs = logik.getX(figur, x, y);
                            int[] ys = logik.getY(figur, x, y);
                            aktuelleFigur = figur;
                            currentX = x;
                            currentY = y;
                            faerber(x, xs, y, ys);
                        }
                        else if((aktuelleFigur.giveColor().equals(Color.WHITE) && figur.giveColor().equals(Color.BLACK)) || (aktuelleFigur.giveColor().equals(Color.BLACK) && figur.giveColor().equals(Color.WHITE)) || aktuelleFigur.giveID().equals("bauer") && figur.giveID().equals("unsichtbar")) // hier wird angegriffen
                        {
                            aktuelleFigur.setFirstfalse();
                            if(logik.logikFeld[x][y].giveID().equals("unsichtbar"))
                            {
                                if(logik.logikFeld[x][y].giveAbstammungColor().equals(Color.WHITE))
                                {
                                    logik.freisetzer(x, y - 1); // hier wird der echte Bauer geloscht
                                    ImageIcon frei = logik.logikFeld[x][y - 1].bild();
                                    graphFeld[x][y - 1].setIcon(frei);
                                }
                                else
                                {
                                    logik.freisetzer(x, y + 1);
                                    ImageIcon frei = logik.logikFeld[x][y + 1].bild();
                                    graphFeld[x][y + 1].setIcon(frei);
                                }
                            }
                            logik.logikFeld[x][y] = aktuelleFigur;
                            ImageIcon bild = logik.logikFeld[x][y].bild();
                            graphFeld[x][y].setIcon(bild);
                            logik.freisetzer(currentX, currentY);
                            ImageIcon frei = logik.logikFeld[currentX][currentY].bild();
                            graphFeld[currentX][currentY].setIcon(frei);
                            turn = !turn;
                            nachsteRunde();
                        }
                    }
                    else
                    {
                        // Moglich-Machung des En passant
                        if(aktuelleFigur.giveID().equals("bauer"))
                        {
                            if(turn == true)
                            {
                                if(y == currentY - 2) // wird geschaut, ob der Bauer seinen "Sprung" gemacht hat
                                {
                                    logik.logikFeld[x][y + 1] = new unsichtbar(Color.WHITE);
                                }
                            }
                            else
                            {
                                if(y == currentY + 2) // wird geschaut, ob der Bauer seinen "Sprung" gemacht hat
                                {
                                    logik.logikFeld[x][y - 1] = new unsichtbar(Color.BLACK);
                                }
                            }
                        }

                        aktuelleFigur.setFirstfalse();
                        logik.logikFeld[x][y] = aktuelleFigur;
                        ImageIcon bild = logik.logikFeld[x][y].bild();
                        graphFeld[x][y].setIcon(bild);
                        logik.freisetzer(currentX, currentY);
                        ImageIcon frei = logik.logikFeld[currentX][currentY].bild();
                        graphFeld[currentX][currentY].setIcon(frei);
                        if(checkForRochade(aktuelleFigur, x, currentX, y) == 1)
                        {
                            bild = logik.logikFeld[x - 1][y].bild();
                            graphFeld[x - 1][y].setIcon(bild);
                            logik.freisetzer(x + 1, y);
                            frei = logik.logikFeld[x + 1][y].bild();
                            graphFeld[x + 1][y].setIcon(frei);
                        }
                        else if(checkForRochade(aktuelleFigur, x, currentX, y) == 2)
                        {
                            bild = logik.logikFeld[x + 1][y].bild();
                            graphFeld[x + 1][y].setIcon(bild);
                            logik.freisetzer(x - 1, y);
                            frei = logik.logikFeld[x - 2][y].bild();
                            graphFeld[x - 2][y].setIcon(frei);
                        }
                        turn = !turn;
                        nachsteRunde();
                    }
                }
            }
        }
    }

    public int checkForRochade(universell figur, int newX, int currentX, int y)
    {
        if(figur.giveID().equals("konig"))
        {
            String farbeString;
            // kleine
            if(newX == currentX + 2)
            {
                logik.logikFeld[newX + 1][y] = new frei();
                if(figur.giveColor().equals(Color.WHITE))
                {
                    farbeString = "turmweiss";
                }
                else
                {
                    farbeString = "turmschwarz";
                }
                logik.logikFeld[newX - 1][y] = new turm(figur.giveColor(), farbeString, "turm");
                return 1;
            }
            // grosse
            else if(newX == currentX - 2)
            {
                logik.logikFeld[newX - 2][y] = new frei();
                if(figur.giveColor().equals(Color.WHITE))
                {
                    farbeString = "turmweiss";
                }
                else
                {
                    farbeString = "turmschwarz";
                }
                logik.logikFeld[newX + 1][y] = new turm(figur.giveColor(), farbeString, "turm");
                return 2;
            }
        }
        return 0;
    }

    public void faerber(int x, int[] xs, int y, int[] ys)
    {
        for(int i = 0; i < xs.length; i++)
        {
            if(graphFeld[x + xs[i]][y + ys[i]].getBackground().equals(Color.WHITE) && logik.logikFeld[x + xs[i]][y + ys[i]].giveColor().equals(Color.PINK))
            {
                graphFeld[x + xs[i]][y + ys[i]].setBackground(lightGreen);
            }
            else if(graphFeld[x + xs[i]][y + ys[i]].getBackground().equals(Color.GRAY) && logik.logikFeld[x + xs[i]][y + ys[i]].giveColor().equals(Color.PINK))
            {
                graphFeld[x + xs[i]][y + ys[i]].setBackground(darkGreen);
            }
            else if(logik.logikFeld[x + xs[i]][y + ys[i]].giveColor().equals(Color.BLACK) && turn == true)
            {
                graphFeld[x + xs[i]][y + ys[i]].setBackground(red);
            }
            else if(logik.logikFeld[x + xs[i]][y + ys[i]].giveColor().equals(Color.WHITE) && turn == false)
            {
                graphFeld[x + xs[i]][y + ys[i]].setBackground(red);
            }
            graphFeld[x + xs[i]][y + ys[i]].setEnabled(true);
        }
    }


    public static void main(String[] args) 
    {
        gui test = new gui();
        test.setVisible(true);
    }
}
