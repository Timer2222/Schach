package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class aussen implements universell
{
    public aussen()
    {

    }

    public String giveID()
    {
        return "aussen";
    }

    public ImageIcon bild()
    {
        String pfad = "lib\\pic\\" + "nichts" + ".png";
        ImageIcon icon = new ImageIcon(pfad); // Pfade zum Bild ersetzen
        Image image = icon.getImage(); // Bild als Image-Objekt erhalten
        Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Bild skalieren
        return new ImageIcon(scaledImage);
    }

    public Color giveColor()
    {
        return Color.PINK;
    }

    public Color giveAbstammungColor()
    {
        return Color.PINK;
    }

    public boolean giveFirst()
    {
        return false;
    }

    public void setFirstfalse()
    {
    }

    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] platzhalter = new int[1];
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] giveAngriffX(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] giveAngriffY(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] platzhalter = new int[1];
        platzhalter[0] = 0; 
        return platzhalter;
    }
    
}
