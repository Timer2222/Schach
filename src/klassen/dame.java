package src.klassen;

import javax.swing.ImageIcon;
import java.awt.*;

public class dame implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;

    public dame(Color farbeColor, String farbeString, String id)
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
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
