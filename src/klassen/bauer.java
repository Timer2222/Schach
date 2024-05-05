package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class bauer implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;
    public boolean erstemal;

    public bauer(Color farbeColor, String farbeString, String id, boolean first) 
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
        erstemal = first;
    }

    public int[] xmoglichesFeld(int[] moglichkeiten, boolean first) 
    {
        int[] x = new int[4];
        if(eigeneFarbe == Color.WHITE)
        {
            if(moglichkeiten[0] == 1)
            {
                x[0] = -1;
            }
            else
            {
                x[0] = 187;
            }
            if(moglichkeiten[1] == 0)
            {
                x[1] = 0;
            }
            else
            {
                x[1] = 187;
            }
            if(moglichkeiten[2] == 1)
            {
                x[2] = 1;
            }
            else
            {
                x[2] = 187;
            }
            if(first == true)
            {
                x[3] = 0;
            }
            else
            {
                x[3] = 187;
            }
        }
        else
        {
            if(moglichkeiten[5] == 1)
            {
                x[0] = -1;
            }
            else
            {
                x[0] = 187;
            }
            if(moglichkeiten[6] == 0)
            {
                x[1] = 0;
            }
            else
            {
                x[1] = 187;
            }
            if(moglichkeiten[7] == 1)
            {
                x[2] = 1;
            }
            else
            {
                x[2] = 187;
            }
            if(first == true)
            {
                x[3] = 0;
            }
            else
            {
                x[3] = 187;
            }
        }
        return x;
    }

    public int[] ymoglichesFeld(int[] moglichkeiten, boolean first) 
    {
        int[] y = new int[4];
        if(eigeneFarbe == Color.WHITE)
        {
            if(moglichkeiten[0] == 1)
            {
                y[0] = -1; // minus weil im Feld nach oben
            }
            else
            {
                y[0] = 187;
            }
            if(moglichkeiten[1] == 0)
            {
                y[1] = -1;
            }
            else
            {
                y[1] = 187;
            }
            if(moglichkeiten[2] == 1)
            {
                y[2] = -1;
            }
            else
            {
                y[2] = 187;
            }
            if(first == true)
            {
                y[3] = -2;
            }
            else
            {
                y[3] = 187;
            }
        }
        else
        {
            if(moglichkeiten[5] == 1)
            {
                y[0] = 1;
            }
            else
            {
                y[0] = 187;
            }
            if(moglichkeiten[6] == 0)
            {
                y[1] = 1;
            }
            else
            {
                y[1] = 187;
            }
            if(moglichkeiten[7] == 1)
            {
                y[2] = 1;
            }
            else
            {
                y[2] = 187;
            }
            if(first == true)
            {
                y[3] = 2;
            }
            else
            {
                y[3] = 187;
            }
        }
        return y;
    }

    public String giveID()
    {
        return name;
    }

    public boolean giveFirst()
    {
        return erstemal;
    }

    public Color giveColor()
    {
        return eigeneFarbe;
    }

    public ImageIcon bild()
    {
        String pfad = "lib\\pic\\" + eigeneFarbenLink + ".png";
        ImageIcon icon = new ImageIcon(pfad); // Pfade zum Bild ersetzen
        Image image = icon.getImage(); // Bild als Image-Objekt erhalten
        Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Bild skalieren
        return new ImageIcon(scaledImage);
    }
    
}
