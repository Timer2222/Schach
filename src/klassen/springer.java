package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class springer implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;

    public springer(Color farbeColor, String farbeString, String id)
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

    public Color giveAbstammungColor()
    {
        return Color.PINK;
    }

    // Hier nur Platzhalter, damit Code funktioniert
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
        for(int i = 0; i < 8; i++)
        {
            if(moglichkeit[i] == 1 || moglichkeit[i] == 0)
            {
                y[i] = YHilfe(i);
            }
            else
            {
                y[i] = 0;
            }
        }
        return y;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] x = new int[8];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        for(int i = 0; i < 8; i++)
        {
            if(moglichkeit[i] == 1 || moglichkeit[i] == 0)
            {
                x[i] = XHilfe(i);
            }
            else
            {
                x[i] = 0;
            }
        }
        return x;
    }

    public int XHilfe(int i)
    {
        if(i == 0 || i == 7)
        {
            return -2;
        }
        else if(i == 1 || i == 6)
        {
            return -1;
        }
        else if(i == 2 || i == 5)
        {
            return 1;
        }
        else if(i == 3 || i == 4)
        {
            return 2;
        }
        return 0;
    }

    public int YHilfe(int i)
    {
        if(i == 0 || i == 3)
        {
            return -1;
        }
        else if(i == 1 || i == 2)
        {
            return -2;
        }
        else if(i == 4 || i == 7)
        {
            return 1;
        }
        else if(i == 5 || i == 6)
        {
            return 2;
        }
        return 0;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeiten = new int[8];
        moglichkeiten[0] = schauerHilfe(feld, eigX - 2, eigY - 1);
        moglichkeiten[1] = schauerHilfe(feld, eigX - 1, eigY - 2);
        moglichkeiten[2] = schauerHilfe(feld, eigX + 1, eigY - 2);
        moglichkeiten[3] = schauerHilfe(feld, eigX + 2, eigY - 1);
        moglichkeiten[4] = schauerHilfe(feld, eigX + 2, eigY + 1);
        moglichkeiten[5] = schauerHilfe(feld, eigX + 1, eigY + 2);
        moglichkeiten[6] = schauerHilfe(feld, eigX - 1, eigY + 2);
        moglichkeiten[7] = schauerHilfe(feld, eigX - 2, eigY + 1);
        return moglichkeiten;
    }

    public int schauerHilfe(universell[][] feld, int testX, int testY)
    {
        // alle moglichkeiten werden durchgegangen, damit es nich in "schauer" nicht so kompliziert ist
        if(testX < 0 || testX > 9 || testY < 0 || testY > 9) // falls ausserhabl der "aussen" - Felder
        {
            return 2;
        }
        else if(feld[testX][testY].giveID().equals("frei"))
        {
            return 0;
        }
        else if(eigeneFarbe.equals(Color.WHITE) && feld[testX][testY].giveColor().equals(Color.BLACK))
        {
            return 1;
        }
        else if(eigeneFarbe.equals(Color.BLACK) && feld[testX][testY].giveColor().equals(Color.WHITE))
        {
            return 1;
        }
        else if(eigeneFarbe.equals(Color.WHITE) && feld[testX][testY].giveColor().equals(Color.WHITE))
        {
            return 2;
        }
        else if(eigeneFarbe.equals(Color.BLACK) && feld[testX][testY].giveColor().equals(Color.BLACK))
        {
            return 2;
        }
        else if(feld[testX][testY].giveID().equals("aussen"))
        {
            return 2;
        }
        else // nur damit kein ERROR kommt
        {
            return 3;
        }
    }

    public int[] giveAngriffX()
    {
        int[] angX = new int[8];
        angX[0] = -2;
        angX[1] = -1;
        angX[2] = 1;
        angX[3] = 2;
        angX[4] = 2;
        angX[5] = 1;
        angX[6] = -1;
        angX[7] = -2;
        return angX;
    }

    public int[] giveAngriffY()
    {
        int[] angY = new int[8];
        angY[0] = 1;
        angY[1] = 2;
        angY[2] = 2;
        angY[3] = 1;
        angY[4] = -1;
        angY[5] = -2;
        angY[6] = -2;
        angY[7] = -1;
        return angY;
    }
}
