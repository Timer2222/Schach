package src.klassen;
import javax.swing.ImageIcon;
import java.awt.*;

public interface universell //brauchen wir um alle klassen nutzen zu können
{
    public ImageIcon bild(); // In einem Interface kommen nur abstrakte Methoden rein, die alle Erbenden definieren muessen
    public String giveID();
    public Color giveColor();
    public boolean giveFirst();
    public void setFirstfalse(); // braucht nur der Bauer
    public int[] giveAngriffX();
    public int[] giveAngriffY();
    public Color giveAbstammungColor();
    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY);
    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY); // first nur für bauer
    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY); // first nur für bauer
    
}  


