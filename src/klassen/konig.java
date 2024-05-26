package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class konig implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;
    public boolean first;

    public konig(Color farbeColor, String farbeString, String id)
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
        first = true;
    }

    public String giveID()
    {
        return name;
    }

    public void setFirstfalse()
    {
        first = false;
    }

    public ImageIcon bild()
    {
        String pfad = "lib\\pic\\" + eigeneFarbenLink + ".png";
        ImageIcon icon = new ImageIcon(pfad); // Pfade zum Bild ersetzen
        Image image = icon.getImage(); // Bild als Image-Objekt erhalten
        Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Bild skalieren
        return new ImageIcon(scaledImage);
    }

    // Hier nur Platzhalter, damit Code funktioniert
    public Color giveAbstammungColor()
    {
        return Color.PINK;
    }
    public Color giveColor()
    {
        return eigeneFarbe;
    }

    public boolean giveFirst()
    {
        return false;
    }

    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] y = new int[10];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        for(int ii = 0; ii < 3; ii++)
        {
            if(moglichkeit[ii] == 0 || moglichkeit[ii] == 1)
            {
                y[ii] = -1;            
            }
            else
            {
                y[ii] = 0;
            }
        }
        for(int ii = 3; ii < 5; ii++)
        {
            y[ii] = 0;
        }
        for(int ii = 5; ii < 8; ii++)
        {
            if(moglichkeit[ii] == 0 || moglichkeit[ii] == 1)
            {        
                y[ii] = 1;
            }
            else
            {
                y[ii] = 0;
            }
        }
        //Rochaden
        if(moglichkeit[8] == 0)
        {
            y[8] = 0;
        }
        if(moglichkeit[9] == 0)
        {
            y[9] = 0;
        }
        return y;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] x = new int[10];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int zaehler = -1;
        for(int ii = 0; ii < 3; ii++)
        {
            if(moglichkeit[ii] == 0 || moglichkeit[ii] == 1)
            {
                x[ii] = zaehler;
                zaehler++;
            }
            else
            {
                x[ii] = 0;
                zaehler++;
            }
        }
        zaehler = -1;
        if(moglichkeit[3] == 0 || moglichkeit[3] == 1)
        {
            x[3] = -1;
        }
        else
        {
            x[3] = 0;
        }
        if(moglichkeit[4] == 0 || moglichkeit[4] == 1)
        {
            x[4] = 1;
        }
        else
        {
            x[4] = 0;
        }
        for(int ii = 5; ii < 8; ii++)
        {
            if(moglichkeit[ii] == 0 || moglichkeit[ii] == 1)
            {
                x[ii] = zaehler;
                zaehler++;
            }
            else
            {
                x[ii] = 0;
                zaehler++;
            }
        }
        //Rochaden
        if(moglichkeit[8] == 0)
        {
            x[8] = 2;
        }
        if(moglichkeit[9] == 0)
        {
            x[9] = -2;
        }
        return x;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeiten = new int[10];
        int zaehler = 0;
        for(int y = -1; y < 2; y++)
        {
            for(int x = -1; x < 2; x++)
            {
                if(y == 0 && x == 0)
                {
                    x = 1; // eigene pos uberspringen
                }
                if(eigeneFarbe.equals(Color.WHITE))
                {
                    if((art[eigX + x][eigY + y].giveID().equals("angriff") && art[eigX + x][eigY + y].giveAbstammungColor().equals(Color.BLACK)) || feld[eigX + x][eigY + y].giveColor().equals(Color.WHITE) || feld[eigX + x][eigY + y].giveID().equals("aussen"))
                    {
                        moglichkeiten[zaehler] = 2;
                    }
                    else if(feld[eigX + x][eigY + y].giveColor().equals(Color.BLACK))
                    {
                        moglichkeiten[zaehler] = 1;
                    }
                    else
                    {
                        moglichkeiten[zaehler] = 0;
                    }
                }
                else
                {
                    if(art[eigX + x][eigY + y].giveID().equals("angriff") && art[eigX + x][eigY + y].giveAbstammungColor().equals(Color.WHITE) || feld[eigX + x][eigY + y].giveColor().equals(Color.BLACK) || feld[eigX + x][eigY + y].giveID().equals("aussen"))
                    {
                        moglichkeiten[zaehler] = 2;
                    }
                    else if(feld[eigX + x][eigY + y].giveColor().equals(Color.WHITE))
                    {
                        moglichkeiten[zaehler] = 1;
                    }
                    else
                    {
                        moglichkeiten[zaehler] = 0;
                    }
                }
                zaehler++;
            }
        }

        // Hier Rochade

        // kleine Rochade:
        // logik-check
        if(first == true && feld[eigX + 3][eigY].giveFirst() == true && feld[eigX + 1][eigY].giveID().equals("frei") && feld[eigX + 2][eigY].giveID().equals("frei"))
        {
            // feldenart-check
            if(art[eigX + 1][eigY].giveID().equals("frei") && art[eigX + 2][eigY].giveID().equals("frei"))
            {
                moglichkeiten[8] = 0;
            }
        }
        else
        {
            moglichkeiten[8] = 2;
        }

        // grosse Rochade
        // logik-check
        if(first == true && feld[eigX - 4][eigY].giveFirst() == true && feld[eigX - 1][eigY].giveID().equals("frei") && feld[eigX - 2][eigY].giveID().equals("frei") && feld[eigX - 3][eigY].giveID().equals("frei"))
        {
            // feldenart-check
            if(art[eigX - 1][eigY].giveID().equals("frei") && art[eigX - 2][eigY].giveID().equals("frei"))
            {
                moglichkeiten[9] = 0;
            }
        }
        else
        {
            moglichkeiten[9] = 2;
        }
        return moglichkeiten;
    }

    public int[] giveAngriffX(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] angX = new int[8]; 
        angX[0] = -1;
        angX[1] = 0;
        angX[2] = 1;
        angX[3] = -1; 
        angX[4] = 1;
        angX[5] = -1;
        angX[6] = 0;
        angX[7] = -1;
        return angX;
    }

    public int[] giveAngriffY(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] angY = new int[8];
        for(int i = 0; i < 3; i++)
        {
            angY[i] = 1; 
        }
        for(int i = 3; i < 5; i++)
        {
            angY[i] = 0; 
        }
        for(int i = 5; i < 8; i++)
        {
            angY[i] = -1; 
        }
        return angY;
    }
}
