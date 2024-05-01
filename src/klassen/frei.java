package src.klassen;

import javax.swing.ImageIcon;
import java.awt.Image;


public class frei implements universell
{
    public frei()
    {

    }

    public String giveID()
    {
        return "frei";
    }

    public ImageIcon bild()
    {
        String pfad = "lib\\pic\\" + "nichts" + ".png";
        ImageIcon icon = new ImageIcon(pfad); // Pfade zum Bild ersetzen
        Image image = icon.getImage(); // Bild als Image-Objekt erhalten
        Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Bild skalieren
        return new ImageIcon(scaledImage);
    }
}
