package src;


public class sprache 
{
    String gewahlteSprache;
    public sprache(String wahl)
    {
        gewahlteSprache = wahl;
    }
    // deutsch
    public String startenButton()
    {
        if(gewahlteSprache.equals("deutsch"))
        return "Spiel starten";

        else if(gewahlteSprache.equals("russisch"))
        return "начать игру";

        else if(gewahlteSprache.equals("englisch"))
        return "start game";

        else if(gewahlteSprache.equals("latein"))
        return "satus ludum";

        else if(gewahlteSprache.equals("franzosisch"))
        return "démarrer jeu";
        
        else 
        return "Fehler, keine Ubersetzung";
        
    }

    public String Patt()
    {
        if(gewahlteSprache.equals("deutsch"))
        return "Patt";

        else if(gewahlteSprache.equals("russisch"))
        return "пат";

        else if(gewahlteSprache.equals("englisch"))
        return "Patt";

        else if(gewahlteSprache.equals("franzosisch"))
        return "Patt";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String Matt()
    {
        if(gewahlteSprache.equals("deutsch"))
        return "Schachmatt";

        else if(gewahlteSprache.equals("russisch"))
        return "Шах и мат";

        else if(gewahlteSprache.equals("englisch"))
        return "checkmate";

        else if(gewahlteSprache.equals("franzosisch"))
        return "échec et mat";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String weissSieg()
    {
        if(gewahlteSprache.equals("deutsch"))
        return "Gewonnen hat Weiss.";

        else if(gewahlteSprache.equals("russisch"))
        return "Белые победили.";

        else if(gewahlteSprache.equals("englisch"))
        return "White won.";

        else if(gewahlteSprache.equals("franzosisch"))
        return "Les blancs ont gagné.";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String schwarzSieg()
    {
        if(gewahlteSprache.equals("deutsch"))
        return "Gewonnen hat Schwarz.";

        else if(gewahlteSprache.equals("russisch"))
        return "Черные победили.";

        else if(gewahlteSprache.equals("englisch"))
        return "Black won.";

        else if(gewahlteSprache.equals("franzosisch"))
        return "Les noirs ont gagné.";
        
        else 
        return "Fehler, keine Ubersetzung";
    }
}
