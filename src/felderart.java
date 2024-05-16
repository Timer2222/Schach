package src;
import src.klassen.*;
public class felderart 
{
    universell art[][], test[][];
    public felderart(universell[][] feld)
    {
        art = new universell[10][10];
        test = feld;
        aktualisieren();
    }

    public void aktualisieren()
    {
        universell figur;
        int[] angX, angY;
        for(int y = 0; y < 10; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                art[x][y] = new frei();
                for(int i = 0; i < 10; i++)
                {
                    test[0][i] = new aussen();
                    test[9][i] = new aussen();
                    test[i][0] = new aussen();
                    test[i][9] = new aussen();
                }

                figur = test[x][y];
                if(!figur.giveID().equals("angriff") && !figur.giveID().equals("aussen") && !figur.giveID().equals("frei"))
                {
                    angX = figur.giveAngriffX();
                    angY = figur.giveAngriffY();
                    for(int i = 0; i < angX.length; i++)
                    {
                        if(art[x + angX[i]][y + angY[i]].giveID().equals("aussen"))
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
}
