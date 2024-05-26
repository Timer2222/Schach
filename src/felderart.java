package src;
import java.awt.Color;

import src.klassen.*;
public class felderart 
{
    universell art[][];
    public felderart()
    {
        art = new universell[10][10];
        initialisieren();
    }

    public void initialisieren()
    {
        for(int y = 0; y < 10; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                art[x][y] = new frei();
                for(int i = 0; i < 10; i++)
                {
                    art[0][i] = new aussen();
                    art[9][i] = new aussen();
                    art[i][0] = new aussen();
                    art[i][9] = new aussen();
                }      
            }
        }
    }

    public universell[][] clearer(universell[][] artfeld)
    {
        for(int y = 1; y < 9; y++)
        {
            for(int x = 1; x < 9; x++)
            {
                artfeld[x][y] = new frei();
            }
        }
        return artfeld;
    }

    public universell[][] aktualisieren(boolean turn, universell[][] artfeld, universell[][] logikfeld)
    {
        artfeld = clearer(artfeld);
        universell figur;
        int[] angX, angY;
        for(int y = 0; y < 10; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                figur = logikfeld[x][y];
                if(!figur.giveID().equals("angriff") && !figur.giveID().equals("aussen") && !figur.giveID().equals("frei") && !figur.giveID().equals("unsichtbar"))
                {
                    if(turn == true && figur.giveColor().equals(Color.BLACK)) // wenn weiss dran, braucht man die schwarzen Angriffsfelder
                    {
                        angX = figur.giveAngriffX(logikfeld, art, x, y);
                        angY = figur.giveAngriffY(logikfeld, art, x, y);
                        for(int i = 0; i < angX.length; i++)
                        {
                            if(x + angX[i] < 0 || x + angX[i] > 9 || y + angY[i] < 0 || y + angY[i] > 9)
                            {

                            }
                            else if(logikfeld[x + angX[i]][y + angY[i]].giveID().equals("aussen"))
                            {

                            }
                            else
                            {
                                artfeld[x + angX[i]][y + angY[i]] = new angriffsfeld(figur);
                                System.out.println(figur.giveID() + " schwarz " + "x: " + (x + angX[i]) + " y: " + (y + angY[i]));
                            }
                        
                        }
                    }
                    else if(turn == false && figur.giveColor().equals(Color.WHITE))
                    {
                        angX = figur.giveAngriffX(logikfeld, art, x, y);
                        angY = figur.giveAngriffY(logikfeld, art, x, y);
                        for(int i = 0; i < angX.length; i++)
                        {
                            if(x + angX[i] < 0 || x + angX[i] > 9 || y + angY[i] < 0 || y + angY[i] > 9)
                            {

                            }
                            else if(logikfeld[x + angX[i]][y + angY[i]].giveID().equals("aussen"))
                            {
                                
                            }
                            else
                            {
                                artfeld[x + angX[i]][y + angY[i]] = new angriffsfeld(figur);
                                System.out.println(figur.giveID() + " weiss " + "x: " + (x + angX[i]) + " y: " + (y + angY[i]));
                            }
                        }
                    }
                }
            }
        }
        return art;
    }
}
