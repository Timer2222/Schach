package src;
import java.awt.Color;

import src.klassen.*;
public class felderart 
{
    universell art[][], logikfeld[][];
    public felderart(universell[][] feld)
    {
        art = new universell[10][10];
        logikfeld = feld;
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
                    logikfeld[0][i] = new aussen();
                    logikfeld[9][i] = new aussen();
                    logikfeld[i][0] = new aussen();
                    logikfeld[i][9] = new aussen();
                }      
            }
        }
    }

    public universell[][] aktualisieren(boolean turn)
    {
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
                        angX = figur.giveAngriffX();
                        angY = figur.giveAngriffY();
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
                                art[x + angX[i]][y + angY[i]] = new angriffsfeld(figur);
                            }
                        
                        }
                    }
                    else if(turn == false && figur.giveColor().equals(Color.WHITE))
                    {
                        angX = figur.giveAngriffX();
                        angY = figur.giveAngriffY();
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
                                art[x + angX[i]][y + angY[i]] = new angriffsfeld(figur);
                            }
                        
                        }
                    }
                }
            }
        }
        return art;
    }
}
