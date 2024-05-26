package src;


public class sprache 
{
    String gewahlteSprache;
    public sprache(String wahl)
    {
        gewahlteSprache = wahl;
    }
    // Deutsch
    public String startenButton()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Spiel starten";

        else if(gewahlteSprache.equals("Русский"))
        return "начать игру";

        else if(gewahlteSprache.equals("English"))
        return "start game";

        else if(gewahlteSprache.equals("Français"))
        return "démarrer jeu";
        
        else 
        return "Fehler, keine Ubersetzung";
        
    }

    public String Patt()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Patt";

        else if(gewahlteSprache.equals("Русский"))
        return "пат";

        else if(gewahlteSprache.equals("English"))
        return "Patt";

        else if(gewahlteSprache.equals("Français"))
        return "Patt";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String Matt()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Schachmatt";

        else if(gewahlteSprache.equals("Русский"))
        return "Шах и мат";

        else if(gewahlteSprache.equals("English"))
        return "checkmate";

        else if(gewahlteSprache.equals("Français"))
        return "échec et mat";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String weissSieg()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Gewonnen hat Weiss.";

        else if(gewahlteSprache.equals("Русский"))
        return "Белые победили.";

        else if(gewahlteSprache.equals("English"))
        return "White won.";

        else if(gewahlteSprache.equals("Français"))
        return "Les blancs ont gagné.";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String schwarzSieg()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Gewonnen hat Schwarz.";

        else if(gewahlteSprache.equals("Русский"))
        return "Черные победили.";

        else if(gewahlteSprache.equals("English"))
        return "Black won.";

        else if(gewahlteSprache.equals("Français"))
        return "Les noirs ont gagné.";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String Speichern()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Speichern";

        else if(gewahlteSprache.equals("Русский"))
        return "Сохранять";

        else if(gewahlteSprache.equals("English"))
        return "Save";

        else if(gewahlteSprache.equals("Français"))
        return "Sauvegarder";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String SpracheWahlen()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Sprache wählen";

        else if(gewahlteSprache.equals("Русский"))
        return "выбрать язык";

        else if(gewahlteSprache.equals("English"))
        return "choose language";

        else if(gewahlteSprache.equals("Français"))
        return "choisissez votre langue";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String Spielen()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "spielen";

        else if(gewahlteSprache.equals("Русский"))
        return "играть";

        else if(gewahlteSprache.equals("English"))
        return "play";

        else if(gewahlteSprache.equals("Français"))
        return "jouer";
        
        else 
        return "Fehler, keine Ubersetzung";
    }

    public String Titel()
    {
        if(gewahlteSprache.equals("Deutsch"))
        return "Schach von Anton Klonig und Tim Weber";

        else if(gewahlteSprache.equals("Русский"))
        return "Шахматы Антона Клонига и Тима Вебера";

        else if(gewahlteSprache.equals("English"))
        return "Chess by Anton Klonig and Tim Weber";

        else if(gewahlteSprache.equals("Français"))
        return "Les échecs d'Anton Klonig et Tim Weber";
        
        else 
        return "Fehler, keine Ubersetzung";
    }
}
