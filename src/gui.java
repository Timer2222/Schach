package src;
import javax.swing.*;
// import src.klassen.bauer;
import src.klassen.universell;

import java.awt.event.*;
import java.applet.*; // für Audio
import java.io.*;
import java.net.*;
import java.awt.*;

public class gui extends JFrame implements ActionListener
{
    JButton[][] graphFeld;
    JButton starten;
    boolean schwarz = false;
    logik logik;
    public gui()
    {
        this.setSize(800,800);
        this.setLayout(null);
        this.setTitle("Schach von Anton Klonig und Tim Weber");
        graphFeld = new JButton[10][10];
        logik = new logik();
        initialisieren();
        figurenEinfugen();
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
        // Die beiden For-Schleifen um die Äußeren zu entfernen
        for(int i = 0; i < 10; i++)
        {
            graphFeld[0][i].setVisible(false);
            graphFeld[9][i].setVisible(false);
            graphFeld[i][0].setVisible(false);
            graphFeld[i][9].setVisible(false);
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

    public void actionPerformed(ActionEvent event)
    {
        universell figur;
        int[] moglichkeitX, moglichkeitY;
        if(event.getSource() == starten)
        {
            
            starten.setVisible(false);
            for(int y = 5; y < 10; y++)
            {
                for(int x = 0; x < 10; x++)
                {
                    figur = logik.logikFeld[x][y];
                    if(figur.giveID().equals("figur"))
                    {
                        graphFeld[x][y].setEnabled(true);
                    }
                }
            }
        }

        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                if(event.getSource() == graphFeld[x][y])
                {
                    // ab hier logik der Bewegung
                }
            }
        }
    }


    public static void main(String[] args) 
    {
        gui test = new gui();
        test.setVisible(true);
    }
}
