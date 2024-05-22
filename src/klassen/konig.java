package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class konig implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;

    public konig(Color farbeColor, String farbeString, String id)
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
    }

    public String giveID()
    {
        return name;
    }

    public void setFirstfalse()
    {
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
        int[] y = new int[8];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        for(int i = 0; i < moglichkeit.length; i++)
        {
            for(int ii = 0; ii < 3; ii++)
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
            for(int ii = 3; ii < 5; ii++)
            {
                y[ii] = 0;
            }
            for(int ii = 5; ii < 8; ii++)
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

        }
        
        return y;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] x = new int[8];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int zaehler = -1;
        for(int i = 0; i < moglichkeit.length; i++)
        {
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
                }
            }
            zaehler = 0;
            for(int ii = 3; ii < 5; ii++)
            {
                x[ii] = 0;
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
                }
            }

        }
        return x;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeiten = new int[8];
        int zaehler = 0;
        for(int y = 1; y < -2; y--)
        {
            for(int x = -1; x < 2; x++)
            {
                if(eigeneFarbe.equals(Color.WHITE))
                {
                    if(art[eigX + x][eigY + y].giveID().equals("angriff") && art[eigX + x][eigY + y].giveAbstammungColor().equals(Color.BLACK) || feld[eigX + x][eigY + y].giveColor().equals(Color.WHITE) || feld[eigX + x][eigY + y].giveID().equals("aussen"))
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
            }
        }
        return moglichkeiten;
    }

    public int[] giveAngriffX()
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

    public int[] giveAngriffY()
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
