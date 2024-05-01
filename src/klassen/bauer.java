package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class bauer implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;

    public bauer(Color farbeColor, String farbeString, String id) 
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
    }

    public int[] xmoglichesFeld(boolean linksgegner, boolean rechtsgegner, boolean first) 
    {
        int[] x = new int[4];

        x[0] = 0;
        if (first == true) 
        {
            x[1] = 0;
        } 
        else 
        {
            x[1] = -187;
        }
        if (linksgegner == true) 
        {
            x[2] = -1;
        } 
        else 
        {
            x[2] = -187;
        }
        if (rechtsgegner == true) 
        {
            x[3] = 1;
        } 
        else 
        {
            x[3] = -187;
        }

        return x;
    }

    public int[] ymoglichesFeld(boolean linksgegner, boolean rechtsgegner, boolean first) 
    {
        int[] y = new int[4];
        if (eigeneFarbe == Color.BLACK) 
        {
            y[0] = 1;
            if (first == true) 
            {
                y[1] = 2;
            } 
            else 
            {
                y[1] = -187;
            }
            if (linksgegner == true) 
            {
                y[2] = 1;
            } 
            else 
            {
                y[2] = -187;
            }
            if (rechtsgegner == true) 
            {
                y[3] = 1;
            } 
            else 
            {
                y[3] = -187;
            }
        } 
        else 
        {
            y[0] = -1;
            if (first == true) 
            {
                y[1] = -2;
            } 
            else 
            {
                y[1] = -187;
            }
            if (linksgegner == true) 
            {
                y[2] = -1;
            } 
            else 
            {
                y[2] = -187;
            }
            if (rechtsgegner == true) 
            {
                y[3] = -1;
            } 
            else 
            {
                y[3] = -187;
            }
        }
        return y;
    }

    public String giveID()
    {
        return name;
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
