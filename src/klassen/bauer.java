package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class bauer implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public boolean erstemal;

    public bauer(Color farbeColor, String farbeString, String id, boolean first) 
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
        erstemal = first;
    }

    
    public Color giveAbstammungColor() 
    {
        return Color.PINK;
    }


    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY) // 2 = eigener oder aussen, 1 = gegner, 0 = frei
    {
        int[] moglichkeiten = new int[4];
        universell tester;
        if(eigeneFarbe.equals(Color.WHITE))
        {
            tester = feld[eigX - 1][eigY - 1];
            if(tester.giveColor().equals(Color.BLACK) || tester.giveAbstammungColor().equals(Color.BLACK))
            {
                moglichkeiten[0] = 1;
            }
            tester = feld[eigX][eigY - 1];
            if(tester.giveID().equals("frei"))
            {
                moglichkeiten[1] = 0;
            }
            else if(tester.giveColor().equals(Color.BLACK))
            {
                moglichkeiten[1] = 1;
                moglichkeiten[3] = 1;
            }
            else if(tester.giveColor().equals(Color.WHITE))
            {
                moglichkeiten[1] = 2;
                moglichkeiten[3] = 2;
            }
            tester = feld[eigX + 1][eigY - 1];
            if(tester.giveColor().equals(Color.BLACK) || tester.giveAbstammungColor().equals(Color.BLACK))
            {
                moglichkeiten[2] = 1;
            }
            tester = feld[eigX][eigY - 2];
            if(tester.giveID().equals("frei") && moglichkeiten[1] == 0)
            {
                moglichkeiten[3] = 0;
            }
            else if(tester.giveColor().equals(Color.BLACK))
            {
                moglichkeiten[3] = 1;
            }
        }
        else
        {
            tester = feld[eigX - 1][eigY + 1];
            if(tester.giveColor().equals(Color.WHITE) || tester.giveAbstammungColor().equals(Color.WHITE))
            {
                moglichkeiten[0] = 1;
            }
            tester = feld[eigX][eigY + 1];
            if(tester.giveID().equals("frei"))
            {
                moglichkeiten[1] = 0;
            }
            else if(tester.giveColor().equals(Color.WHITE))
            {
                moglichkeiten[1] = 1;
                moglichkeiten[3] = 1;
            }
            else if(tester.giveColor().equals(Color.BLACK))
            {
                moglichkeiten[1] = 2;
                moglichkeiten[3] = 2;
            }
            tester = feld[eigX + 1][eigY + 1];
            if(tester.giveColor().equals(Color.WHITE) || tester.giveAbstammungColor().equals(Color.WHITE))
            {
                moglichkeiten[2] = 1;
            }
            tester = feld[eigX][eigY + 2];
            if(tester.giveID().equals("frei") && moglichkeiten[1] == 0)
            {
                moglichkeiten[3] = 0;
            }
            else if(tester.giveColor().equals(Color.WHITE))
            {
                moglichkeiten[3] = 1;
            }
        }
        return moglichkeiten;
    }

    public int[] giveAngriffX(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] angriffe = new int[2];
        angriffe[0] = -1;
        angriffe[1] = 1;
        return angriffe;
    }

    public int[] giveAngriffY(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] angriffe = new int[2];
        if(eigeneFarbe.equals(Color.WHITE))
        {
            angriffe[0] = -1;
            angriffe[1] = -1;
        }
        else
        {
            angriffe[0] = 1;
            angriffe[1] = 1;
        }
        return angriffe;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY) 
    {
        int[] x = new int[4];
        int[] moglichkeit;
        moglichkeit = schauer(feld, art, eigX, eigY);
        // Hier ist die X-Richtung für beide Farbe dieselbe
        if(moglichkeit[0] == 1) // wenn was nicht geht, wird der wert auf die eigene Position gestellt
        {
            x[0] = -1;
        }
        else
        {
            x[0] = 0;
        }
        if(moglichkeit[1] == 0)
        {
            x[1] = 0;
        }
        else
        {
            x[1] = 0;
        }
        if(moglichkeit[2] == 1)
        {
            x[2] = 1;
        }
        else
        {
            x[2] = 0;
        }
        if((moglichkeit[3] == 0 && first == true))
        {
            x[3] = 0;
        }
        else
        {
            x[3] = 0;
        }
        return x;
    }

    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY) 
    {
        int[] y = new int[4];
        int[] moglichkeit;
        moglichkeit = schauer(feld, art, eigX, eigY);
        if(eigeneFarbe == Color.WHITE)
        {
            if(moglichkeit[0] == 1)
            {
                y[0] = -1; // minus weil im Feld nach oben
            }
            else
            {
                y[0] = 0;
            }
            if(moglichkeit[1] == 0)
            {
                y[1] = -1;
            }
            else
            {
                y[1] = 0;
            }
            if(moglichkeit[2] == 1)
            {
                y[2] = -1;
            }
            else
            {
                y[2] = 0;
            }
            if(first == true && moglichkeit[3] == 0)
            {
                y[3] = -2;
            }
            else
            {
                y[3] = 0;
            }
        }
        else
        {
            if(moglichkeit[0] == 1)
            {
                y[0] = 1;
            }
            else
            {
                y[0] = 0;
            }
            if(moglichkeit[1] == 0)
            {
                y[1] = 1;
            }
            else
            {
                y[1] = 0;
            }
            if(moglichkeit[2] == 1)
            {
                y[2] = 1;
            }
            else
            {
                y[2] = 0;
            }
            if(first == true && moglichkeit[3] == 0)
            {
                y[3] = 2;
            }
            else
            {
                y[3] = 0;
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

    public void setFirstfalse()
    {
        erstemal = false;
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
