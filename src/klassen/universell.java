package src.klassen;
import javax.swing.ImageIcon;
import java.awt.*;

public interface universell //brauchen wir um alle klassen nutzen zu können
{
   
    
    public ImageIcon bild(); // In einem Interface kommen nur abstrakte Methoden rein, die alle Erbenden definieren muessen
    public String giveID();
    public Color giveColor();
    public boolean giveFirst();
    public int[] ymoglichesFeld(int[] moglichkeiten, boolean first); //doppelt & first nur für bauer
    public int[] xmoglichesFeld(int[] moglichkeiten, boolean first); //doppelt & first nur für bauer
    
}  


