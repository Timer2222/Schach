package src.klassen;
import javax.swing.ImageIcon;
import java.awt.*;

public class dame implements universell
{
    // in dieser Klasse wird grundsatzlich so vorgegangen, dass der Code vom Turm und Laufer verbunden wird, um Zeit und Energie zu sparen
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;

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

    public void setFirstfalse()
    {
    }

    public ImageIcon bild()
    {
        String pfad = "lib\\pic\\" + eigeneFarbenLink + ".png";
        ImageIcon icon = new ImageIcon(pfad); // Pfade zum Bild ersetzen
        Image image = icon.getImage(); // Bild als Image-Objekt erhalten
        Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Bild skalieren
        return new ImageIcon(scaledImage);
    }

    public Color giveColor()
    {
        return eigeneFarbe;
    }
    
    public Color giveAbstammungColor()
    {
        return Color.PINK;
    }

    public boolean giveFirst()
    {
        return false;
    }

    public int[] ymoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        // hier wird genauso vorgegangen wie beim Schauer
        int[] y = new int[27];
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
                    zaehler = 5;
                    for(int ii = i + 1; ii < 14; ii++)
                    {
                        y[ii] = 0;
                    }
                }
                else 
                {
                    zaehler = 5;
                    for(int ii = i; ii < 14; ii++)
                    {
                        y[ii] = 0;
                    }
                }
            }
        }
        // Turm-Part fertig, jetzt Laufer
        zaehler = 1; // geht durch die 4 bereiche durch
        int yzaehler = -1;
        for(int i = 14; i < 27; i++)
        {
            if(zaehler == 1) // links oben
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = yzaehler;
                    yzaehler--;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = yzaehler;
                    yzaehler = -1;
                    zaehler = 2;
                }
                else 
                {
                    y[i] = 0;
                    yzaehler = -1;
                    zaehler = 2;
                }
            }
            else if(zaehler == 2) // rechts oben
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = yzaehler;
                    yzaehler--;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = yzaehler;
                    yzaehler = 1;
                    zaehler = 3;
                }
                else 
                {
                    y[i] = 0;
                    yzaehler = 1;
                    zaehler = 3;
                }
            }
            else if(zaehler == 3) // rechts unten
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = yzaehler;
                    yzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = yzaehler;
                    yzaehler = 1;
                    zaehler = 4;
                }
                else 
                {
                    y[i] = 0;
                    yzaehler = 1;
                    zaehler = 4;
                }
            }
            else if(zaehler == 4) // links unten
            {
                if(moglichkeit[i] == 0)
                {
                    y[i] = yzaehler;
                    yzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = yzaehler;
                    zaehler = 5;
                    for(int ii = i + 1; ii < 27; ii++)
                    {
                        y[ii] = 0;
                    }
                }
                else 
                {
                    zaehler = 5;
                    for(int ii = i; ii < 27; ii++)
                    {
                        y[ii] = 0;
                    }
                    i = 27;
                }
            }
        }
        return y;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {   
        // hier wird genauso vorgegangen wie beim Schauer
        int[] x = new int[27];
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int zaehler = 1; // dafur da, um die 4 Seiten abzudecken
        int hochzaehler = -1;
        for(int i = 0; i < 14; i++)
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
                    zaehler = 5;
                    for(int ii = i + 1; ii < 14; ii++)
                    {
                        x[ii] = 0;
                    }
                }
                else 
                {
                    zaehler = 5;
                    for(int ii = i; ii < 14; ii++)
                    {
                        x[ii] = 0;
                    }
                    i = 14;
                }
            }
        }
        // Turm-Part fertig, jetzt Laufer
        zaehler = 1; // geht durch die 4 bereiche durch
        int xzaehler = -1;
        for(int i = 14; i < 27; i++)
        {
            if(zaehler == 1) // links oben
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = xzaehler;
                    xzaehler--;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = xzaehler;
                    xzaehler = 1;
                    zaehler = 2;
                }
                else 
                {
                    x[i] = 0;
                    xzaehler = 1;
                    zaehler = 2;
                }
            }
            else if(zaehler == 2) // rechts oben
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = xzaehler;
                    xzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = xzaehler;
                    xzaehler = 1;
                    zaehler = 3;
                }
                else 
                {
                    x[i] = 0;
                    xzaehler = 1;
                    zaehler = 3;
                }
            }
            else if(zaehler == 3) // rechts unten
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = xzaehler;
                    xzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = xzaehler;
                    xzaehler = -1;
                    zaehler = 4;
                }
                else 
                {
                    x[i] = 0;
                    xzaehler = -1;
                    zaehler = 4;
                }
            }
            else if(zaehler == 4) // links unten
            {
                if(moglichkeit[i] == 0)
                {
                    x[i] = xzaehler;
                    xzaehler--;
                }
                else if(moglichkeit[i] == 1)
                {
                    x[i] = xzaehler;
                    zaehler = 5;
                    for(int ii = i + 1; ii < 27; ii++)
                    {
                        x[ii] = 0;
                    }
                }
                else 
                {
                    zaehler = 5;
                    for(int ii = i; ii < 27; ii++)
                    {
                        x[ii] = 0;
                    }
                }
            }
        }
        return x;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeiten = new int[31]; // hier wird der Code vom Turm und Laufer nacheinander in der selben Reihenfolge verbunden
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
        // Turm-Part fertig, jetzt Laufer
        int zaehler = 14;
        for(i = 1; i < 8; i++) // links nach oben
        {
            if(feld[eigX - i][eigY - i].giveID().equals("frei"))
            {
                moglichkeiten[zaehler] = 0;
                zaehler++;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX - i][eigY - i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX - i][eigY - i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(feld[eigX - i][eigY - i].giveID().equals("aussen"))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX - i][eigY - i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX - i][eigY - i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
        }
        for(i = 1; i < 8; i++) // rechts oben
        {
            if(feld[eigX + i][eigY - i].giveID().equals("frei"))
            {
                moglichkeiten[zaehler] = 0;
                zaehler++;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX + i][eigY - i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX + i][eigY - i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(feld[eigX + i][eigY - i].giveID().equals("aussen"))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX + i][eigY - i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX + i][eigY - i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
        }
        for(i = 1; i < 8; i++) // rechts unten
        {
            if(feld[eigX + i][eigY + i].giveID().equals("frei"))
            {
                moglichkeiten[zaehler] = 0;
                zaehler++;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX + i][eigY + i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX + i][eigY + i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(feld[eigX + i][eigY + i].giveID().equals("aussen"))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX + i][eigY + i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX + i][eigY + i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
        }
        for(i = 1; i < 8; i++) // links unten
        {
            if(feld[eigX - i][eigY + i].giveID().equals("frei"))
            {
                moglichkeiten[zaehler] = 0;
                zaehler++;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX - i][eigY + i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX - i][eigY + i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 1;
                zaehler++;
                i = 10;
            }
            else if(feld[eigX - i][eigY + i].giveID().equals("aussen"))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.WHITE) && feld[eigX - i][eigY + i].giveColor().equals(Color.WHITE))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
            else if(eigeneFarbe.equals(Color.BLACK) && feld[eigX - i][eigY + i].giveColor().equals(Color.BLACK))
            {
                moglichkeiten[zaehler] = 2;
                zaehler++;
                i = 10;
            }
        }
        // for(i = zaehler; i < 27; i++)
        // {
        //     moglichkeiten[i] = 2;
        // }
        return moglichkeiten;
    }

    public int[] giveAngriffX(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        // wie Schauer
        int[] angX = new int[31]; // 31 weil Turm + Laufer
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        // Turm
        int Xzaehler = -1;
        int zaehler = 1;
        for(int i = 0; i < 14; i++)
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
            else if(zaehler == 4) // unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = 0;
                    for(int ii = i; ii < 14; ii++)
                    {
                        angX[ii] = angX[1];
                    }
                    i = 14;
                }
                else
                {
                    angX[i] = 0;
                }
            }
        }
        // Laufer
        zaehler = 1;
        Xzaehler = -1;
        for(int i = 14; i < moglichkeit.length; i++)
        {
            if(zaehler == 1) // links oben
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
            else if(zaehler == 2) // rechts oben
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = Xzaehler;
                    zaehler = 3;
                    Xzaehler = 1;
                }
                else
                {
                    angX[i] = Xzaehler;
                    Xzaehler++;
                }
            }
            else if(zaehler == 3) // rechts unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = Xzaehler;
                    zaehler = 4;
                    Xzaehler = -1;
                }
                else
                {
                    angX[i] = Xzaehler;
                    Xzaehler++;
                }
            }
            else if(zaehler == 4) // links unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angX[i] = Xzaehler;
                    for(int ii = i; ii < moglichkeit.length; ii++)
                    {
                        angX[ii] = angX[1];
                    }
                    i = moglichkeit.length;
                }
                else
                {
                    angX[i] = Xzaehler;
                    Xzaehler--;
                }
            }
        }
        return angX;
    }

    public int[] giveAngriffY(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        // wie Schauer
        int[] angY = new int[31]; // alle Seiten maximal, in Logik wir dann gestoppt mithilfe der "aussen"-Felder
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        // Turm
        int Yzaehler = -1;
        int zaehler = 1;
        for(int i = 0; i < 14; i++)
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
            else if(zaehler == 4) // unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = Yzaehler;
                    for(int ii = i; ii < 14; ii++)
                    {
                        angY[ii] = angY[1];
                    }
                    i = 14;
                }
                else
                {
                    angY[i] = Yzaehler;
                    Yzaehler++;
                }
            }
        }
        
        // Laufer
        zaehler = 1;
        Yzaehler = -1;
        for(int i = 14; i < moglichkeit.length; i++)
        {
            if(zaehler == 1) // links oben
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = Yzaehler;
                    zaehler = 2;
                    Yzaehler = -1;
                }
                else
                {
                    angY[i] = Yzaehler;
                    Yzaehler--;
                }
            }
            else if(zaehler == 2) // rechts oben
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
            else if(zaehler == 3) // rechts unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = Yzaehler;
                    zaehler = 4;
                    Yzaehler = 1;
                }
                else
                {
                    angY[i] = Yzaehler;
                    Yzaehler++;
                }
            }
            else if(zaehler == 4) // links unten
            {
                if(moglichkeit[i] == 1 || moglichkeit[i] == 2)
                {
                    angY[i] = Yzaehler;
                    for(int ii = i; ii < moglichkeit.length; ii++)
                    {
                        angY[ii] = angY[1];
                    }
                    i = moglichkeit.length;
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
