package src.klassen;
import javax.swing.ImageIcon;
import java.awt.*;

public interface universell //brauchen wir um alle klassen nutzen zu können
{
     // In einem Interface kommen nur abstrakte Methoden rein, die alle Erbenden definieren muessen
    public ImageIcon bild(); // gibt das passende Bild zur jeweiligen Figur
    public String giveID(); // gibt den Namen mit der Farbe der Figur, um in der Logik daran arbeiten zu konnen
    public Color giveColor(); // gibt die Farbe der Figur
    public boolean giveFirst(); // wird eig. nur fur den Bauern und den Konig gebraucht um den Doppelschritt und die Rochade in seinen vollen Regeln zu ermöglichen
    public void setFirstfalse(); // braucht nur der Bauer und der Konig
    public int[] giveAngriffX(universell[][] feld, universell[][] art, int eigX, int eigY); // gibt die Info, in welcher X-Koordinate ein Angriffsfeld gelegt wird
    public int[] giveAngriffY(universell[][] feld, universell[][] art, int eigX, int eigY); // gibt die Info, in welcher Y-Koordinate ein Angriffsfeld gelegt wird
    public Color giveAbstammungColor(); // braucht nur die unsichtbar und die angriffsfeld klasse, um die Abstammung derer zu kennen
    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY); // schaut fur jede Figur, wo sie hin darf und wo nicht
    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY); // gibt an in welche Y-Koordinate sich eine Figur bewegen kann
    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY); // gibt an in welche X-Koordinate sich eine Figur bewegen kann
    
}  


