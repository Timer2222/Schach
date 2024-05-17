package src.klassen;
import java.awt.*;
import javax.swing.ImageIcon;

public class turm implements universell
{
    public Color eigeneFarbe;
    public String eigeneFarbenLink, name;
    public int eigenePos;


    public turm(Color farbeColor, String farbeString, String id)
    {
        name = id;
        eigeneFarbe = farbeColor;
        eigeneFarbenLink = farbeString;
    }

    public String giveID()
    {
        return name;
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
        return false;
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
        int hochzaehler = 1;
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
                    hochzaehler++;
                }
                else if(moglichkeit[i] == 1)
                {
                    y[i] = hochzaehler;
                    hochzaehler = -1;
                    zaehler = 3;
                }
                else 
                {
                    zaehler = 3;
                    hochzaehler = -1;
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
                    hochzaehler--;
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
        int[] platzhalter = new int[1]; 
        platzhalter[0] = 0; 
        return platzhalter;
    }

    public int[] schauer(universell[][] feld, universell[][] art, int eigX, int eigY)
    {
        int[] moglichkeiten = new int[14];
        for(int i = 0; i < 14; i++)
        {
            for(int x = eigX - 1; x >= 0; x--) // alles vom Turm nach links
            {
                if(eigeneFarbe.equals(Color.WHITE))
                {
                    if(feld[eigY][x].giveColor().equals(Color.BLACK))
                    {
                        moglichkeiten[i] = 1;
                        x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[eigY][x].giveColor().equals(Color.WHITE) || feld[eigY][x].giveID().equals("aussen"))
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
                    if(feld[eigY][x].giveColor().equals(Color.WHITE))
                    {
                        moglichkeiten[i] = 1;
                        x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[eigY][x].giveColor().equals(Color.BLACK) || feld[eigY][x].giveID().equals("aussen"))
                    {
                        moglichkeiten[i] = 2;
                        x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else
                    {
                        moglichkeiten[i] = 0;
                    }
                }
            }
            for(int y = eigY - 1; y >= 0; y--) // alles uber dem Turm
            {
                if(eigeneFarbe.equals(Color.WHITE))
                {
                    if(feld[y][eigX].giveColor().equals(Color.BLACK))
                    {
                        moglichkeiten[i] = 1;
                        y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[y][eigX].giveColor().equals(Color.WHITE) || feld[y][eigX].giveID().equals("aussen"))
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
                    if(feld[y][eigX].giveColor().equals(Color.WHITE))
                    {
                        moglichkeiten[i] = 1;
                        y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[y][eigX].giveColor().equals(Color.BLACK) || feld[y][eigX].giveID().equals("aussen"))
                    {
                        moglichkeiten[i] = 2;
                        y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else
                    {
                        moglichkeiten[i] = 0;
                    }
                }
            }
            for(int x = eigX + 1; x <= 9; x++) // alles rechts vom Turm
            {
                if(eigeneFarbe.equals(Color.WHITE))
                {
                    if(feld[eigY][x].giveColor().equals(Color.BLACK))
                    {
                        moglichkeiten[i] = 1;
                        x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[eigY][x].giveColor().equals(Color.WHITE) || feld[eigY][x].giveID().equals("aussen"))
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
                    if(feld[eigY][x].giveColor().equals(Color.WHITE))
                    {
                        moglichkeiten[i] = 1;
                        x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[eigY][x].giveColor().equals(Color.BLACK) || feld[eigY][x].giveID().equals("aussen"))
                    {
                        moglichkeiten[i] = 2;
                        x = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else
                    {
                        moglichkeiten[i] = 0;
                    }
                }
            }
            for(int y = eigY + 1; y <= 9; y++) // alles unter dem Turm
            {
                if(eigeneFarbe.equals(Color.WHITE))
                {
                    if(feld[y][eigX].giveColor().equals(Color.BLACK))
                    {
                        moglichkeiten[i] = 1;
                        y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[y][eigX].giveColor().equals(Color.WHITE) || feld[y][eigX].giveID().equals("aussen"))
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
                    if(feld[y][eigX].giveColor().equals(Color.WHITE))
                    {
                        moglichkeiten[i] = 1;
                        y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else if(feld[y][eigX].giveColor().equals(Color.BLACK) || feld[y][eigX].giveID().equals("aussen"))
                    {
                        moglichkeiten[i] = 2;
                        y = -2; // brauche ich, um aus der For-Schleife rauszukommen
                    }
                    else
                    {
                        moglichkeiten[i] = 0;
                    }
                }
            }
        }
        return moglichkeiten;
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
