package src.klassen;
import javax.swing.ImageIcon;
import java.awt.*;


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

    // Hier nur Platzhalter, damit Code funktioniert
    public Color giveColor()
    {
        return Color.PINK;
    }

    public boolean giveFirst()
    {
        return false;
    }

    public int[] ymoglichesFeld(boolean first, universell[][] feld, int eigX, int eigY)
    {
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, int eigX, int eigY)
    {
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] schauer(universell[][] feld, int eigX, int eigY)
    {
        int[] platzhalter = new int[1];
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] giveAngriffX()
    {
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] giveAngriffY()
    {
        int[] platzhalter = new int[1];
        platzhalter[0] = 0; 
        return platzhalter;
    }
}
