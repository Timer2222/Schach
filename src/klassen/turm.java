package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class turm implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;
    public boolean first;

    public turm(Color farbeColor, String farbeString, String id)
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
        first = true;
    }

    public String giveID()
    {
        return name;
    }

    public void setFirstfalse()
    {
        first = false;
    }

    public ImageIcon bild()
    {
        String pfad = "lib\\pic\\" + eigeneFarbenLink + ".png";
        ImageIcon icon = new ImageIcon(pfad); // Pfade zum Bild ersetzen
        Image image = icon.getImage(); // Bild als Image-Objekt erhalten
        Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Bild skalieren
        return new ImageIcon(scaledImage);
    }

    // Hier nur Platzhalter, damit Code funktioniert
    public Color giveColor()
    {
        return eigeneFarbe;
    }

    public boolean giveFirst()
    {
        return first;
    }
    
    public Color giveAbstammungColor()
    {
        return Color.PINK;
    }

    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] y = new int[14];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int zaehler = 1; // dafur da, um die 4 Seiten abzudecken
        int hochzaehler = -1;
        for(int i = 0; i < moglichkeit.length; i++)
        {
            if(zaehler == 1) // alles links vom Turm
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = 0;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = 0;
                    zaehler = 2;
                }
                else 
                {
                    zaehler = 2;
                }
            }
            else if(zaehler == 2) // alles uber dem Turm
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = hochzaehler;
                    hochzaehler--;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = hochzaehler;
                    hochzaehler = 1;
                    zaehler = 3;
                }
                else 
                {
                    zaehler = 3;
                    hochzaehler = 1;
                }
            }
            else if(zaehler == 3) // alles rechts vom Turm
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = 0;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = 0;
                    zaehler = 4;
                }
                else 
                {
                    zaehler = 4;
                }
            }
            else if(zaehler == 4) // alles unter dem Turm
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = hochzaehler;
                    hochzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = hochzaehler;
                    i = 14; // raus aus der For-Schleife
                }
                else 
                {
                    i = 14; // raus aus der For-Schleife
                }
            }
        }
        return y;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] x = new int[14];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int zaehler = 1; // dafur da, um die 4 Seiten abzudecken
        int hochzaehler = -1;
        for(int i = 0; i < moglichkeit.length; i++)
        {
            if(zaehler == 1) // alles links vom Turm
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = hochzaehler;
                    hochzaehler--;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = hochzaehler;
                    hochzaehler = 1;
                    zaehler = 2;
                }
                else 
                {
                    zaehler = 2;
                    hochzaehler = 1;
                }
            }
            else if(zaehler == 2) // alles uber dem Turm
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = 0;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = 0;
                    zaehler = 3;
                }
                else 
                {
                    zaehler = 3;
                }
            }
            else if(zaehler == 3) // alles rechts vom Turm
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = hochzaehler;
                    hochzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = hochzaehler;
                    zaehler = 4;
                }
                else 
                {
                    zaehler = 4;
                }
            }
            else if(zaehler == 4) // alles unter dem Turm
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = 0;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = 0;
                    i = 14; // raus aus der For-Schleife
                }
                else 
                {
                    i = 14; // raus aus der For-Schleife
                }
            }
        }
        return x;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeiten = new int[14];
        int i = 0;
        for(int x = eigX - 1; x >= 0; x--) // alles vom Turm nach links
        {
            if(eigeneFarbe.equals(Color.WHITE))
            {
                if(feld[x][eigY].giveColor().equals(Color.BLACK))
                {
                    moglichkeiten[i] = 1;
                    x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[x][eigY].giveColor().equals(Color.WHITE) || feld[x][eigY].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            else
            {
                if(feld[x][eigY].giveColor().equals(Color.WHITE))
                {
                    moglichkeiten[i] = 1;
                    x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[x][eigY].giveColor().equals(Color.BLACK) || feld[x][eigY].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            i++;
        }
        for(int y = eigY - 1; y >= 0; y--) // alles uber dem Turm
        {
            if(eigeneFarbe.equals(Color.WHITE))
            {
                if(feld[eigX][y].giveColor().equals(Color.BLACK))
                {
                    moglichkeiten[i] = 1;
                    y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[eigX][y].giveColor().equals(Color.WHITE) || feld[eigX][y].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            else
            {
                if(feld[eigX][y].giveColor().equals(Color.WHITE))
                {
                    moglichkeiten[i] = 1;
                    y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[eigX][y].giveColor().equals(Color.BLACK) || feld[eigX][y].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            i++;
        }
        for(int x = eigX + 1; x <= 9; x++) // alles rechts vom Turm
        {
            if(eigeneFarbe.equals(Color.WHITE))
            {
                if(feld[x][eigY].giveColor().equals(Color.BLACK))
                {
                    moglichkeiten[i] = 1;
                    x = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[x][eigY].giveColor().equals(Color.WHITE) || feld[x][eigY].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    x = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                moglichkeiten[i] = 0;
                }
            }
            else
            {
                if(feld[x][eigY].giveColor().equals(Color.WHITE))
                {
                    moglichkeiten[i] = 1;
                    x = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[x][eigY].giveColor().equals(Color.BLACK) || feld[x][eigY].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    x = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            i++;
        }
        for(int y = eigY + 1; y <= 9; y++) // alles unter dem Turm
        {
            if(eigeneFarbe.equals(Color.WHITE))
            {
                if(feld[eigX][y].giveColor().equals(Color.BLACK))
                {
                    moglichkeiten[i] = 1;
                    y = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[eigX][y].giveColor().equals(Color.WHITE) || feld[eigX][y].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    y = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            else
            {
                if(feld[eigX][y].giveColor().equals(Color.WHITE))
                {
                    moglichkeiten[i] = 1;
                    y = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else if(feld[eigX][y].giveColor().equals(Color.BLACK) || feld[eigX][y].giveID().equals("aussen"))
                {
                    moglichkeiten[i] = 2;
                    y = 10; // brauche ich, um aus der For-Schleife rauszukommen
                }
                else
                {
                    moglichkeiten[i] = 0;
                }
            }
            i++;
        }
        return moglichkeiten;
    }

    public int[] giveAngriffX(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] angX = new int[14]; 
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int Xzaehler = -1;
        int zaehler = 1;
        for(int i = 0; i < moglichkeit.length; i++)
        {
            if(zaehler == 1) // links
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = Xzaehler;
                    zaehler = 2;
                    Xzaehler = 1;
                }
                else
                {
                    angX[i] = Xzaehler;
                    Xzaehler--;
                }
            }
            else if(zaehler == 2) // oben
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = 0;
                    zaehler = 3;
                }
                else
                {
                    angX[i] = 0;
                }
            }
            else if(zaehler == 3) // rechts
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = Xzaehler;
                    zaehler = 4;
                }
                else
                {
                    angX[i] = Xzaehler;
                    Xzaehler++;
                }
            }
            else if(zaehler == 2) // unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = 0;
                    i = moglichkeit.length;
                }
                else
                {
                    angX[i] = 0;
                }
            }
        }
        return angX;
    }

    public int[] giveAngriffY(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] angY = new int[14];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int Yzaehler = -1;
        int zaehler = 1;
        for(int i = 0; i < moglichkeit.length; i++)
        {
            if(zaehler == 1) // links
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = 0;
                    zaehler = 2;
                }
                else
                {
                    angY[i] = 0;
                }
            }
            else if(zaehler == 2) // oben
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = Yzaehler;
                    zaehler = 3;
                    Yzaehler = 1;
                }
                else
                {
                    angY[i] = Yzaehler;
                    Yzaehler--;
                }
            }
            else if(zaehler == 3) // rechts
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = 0;
                    zaehler = 4;
                }
                else
                {
                    angY[i] = 0;
                }
            }
            else if(zaehler == 2) // unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = Yzaehler;
                    i =moglichkeit.length;
                }
                else
                {
                    angY[i] = Yzaehler;
                    Yzaehler++;
                }
            }
        }
        return angY;
    }
}
