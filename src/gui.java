package src;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*; // für Audio
import java.io.*;
import java.net.*;
import java.awt.*;

public class gui extends JFrame implements ActionListener
{
    JButton[][] graphFeld;
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
    }
    
    public void initialisieren()
    {
        int yzaehler = 0;
        for(int i = 0; i < 10; i++)
        {
            int xzaehler = 0;
            
            for(int ii = 0; ii < 10; ii++)
            {
                graphFeld[i][ii] = new JButton();
                graphFeld[i][ii].setEnabled(false);
                graphFeld[i][ii].setBounds(50 + xzaehler,50 + yzaehler,60,60);
                graphFeld[i][ii].addActionListener(this);
                this.add(graphFeld[i][ii]);
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

    public void actionPerformed(ActionEvent event)
    {

    }


    public static void main(String[] args) 
    {
        gui test = new gui();
        test.setVisible(true);
    }
}
