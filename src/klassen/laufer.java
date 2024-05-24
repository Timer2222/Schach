package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class laufer implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;

    public laufer(Color farbeColor, String farbeString, String id)
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


    // Hier nur Platzhalter, damit Code funktioniert
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
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int[] y = new int[13];
        int zaehler = 1; // geht durch die 4 bereiche durch
        int yzaehler = -1;
        for(int i = 0; i < 13; i++)
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
                    for(int ii = i + 1; ii < 13; ii++)
                    {
                        y[ii] = 0;
                    }
                }
                else 
                {
                    for(int ii = i; ii < 13; ii++)
                    {
                        y[ii] = 0;
                    }
                }
            }
        }
        return y;
    }

    public int[] xmoglichesFeld(boolean first, universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeit = schauer(feld, art, eigX, eigY);
        int[] x = new int[13];
        int zaehler = 1; // geht durch die 4 bereiche durch
        int xzaehler = -1;
        for(int i = 0; i < 13; i++)
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
                    for(int ii = i + 1; ii < 13; ii++)
                    {
                        x[ii] = 0;
                    }
                }
                else 
                {
                    for(int ii = i; ii < 13; ii++)
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
        int[] moglichkeiten = new int[13]; // 13 als Maximal-Fall der abdeckbaren Felder
        int zaehler = 0;
        for(int i = 1; i < 8; i++) // links nach oben
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
        for(int i = 1; i < 8; i++) // rechts oben
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
        for(int i = 1; i < 8; i++) // rechts unten
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
        for(int i = 1; i < 8; i++) // links unten
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
        for(int i = zaehler; i < 13; i++)
        {
            moglichkeiten[i] = 2;
        }
        return moglichkeiten;
    }

    public int[] giveAngriffX()
    {
        int[] angX = new int[28]; // maximale Anzahl universell fur beide farben, wird dann von der feldenart abgefangen
        for(int i = 0; i < 7; i++) // links oben
        {
            angX[i] = -i - 1;
        }
        for(int i = 0; i < 7; i++) // rechts oben
        {
            angX[i + 7] = i + 1;
        }
        for(int i = 0; i < 7; i++) // rechts unten
        {
            angX[i + 14] = i + 1;
        }
        for(int i = 0; i < 7; i++) // links unten
        {
            angX[i + 21] = -i - 1;
        }
        return angX;
    }

    public int[] giveAngriffY()
    {
        int[] angY = new int[28]; // maximale Anzahl universell fur beide farben, wird dann von der feldenart abgefangen
        for(int i = 0; i < 7; i++) // links oben
        {
            angY[i] = -i - 1;
        }
        for(int i = 0; i < 7; i++) // rechts oben
        {
            angY[i + 7] = -i - 1;
        }
        for(int i = 0; i < 7; i++) // rechts unten
        {
            angY[i + 14] = i + 1;
        }
        for(int i = 0; i < 7; i++) // links unten
        {
            angY[i + 21] = i + 1;
        }
        return angY;
    }
}
