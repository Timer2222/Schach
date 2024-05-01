package src.klassen;
import java.awt.Image;
import javax.swing.ImageIcon;

public class angriffsfeld implements universell
{
    public angriffsfeld()
    {

    }

    public String giveID()
    {
        return "angriff";
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

