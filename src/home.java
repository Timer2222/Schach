package src;

import javax.swing.*;
import java.awt.event.*;
public class home extends JFrame implements ActionListener
{
    JLabel SpracheLabel, EndLabel;
    JButton Beginn, Wiederholen, HomeButton, SpracheAnnehmen;
    JComboBox Sprache;
    sprache sprache;

    public home()
    {
        sprache = new sprache("Deutsch");

        this.setSize(800, 800);
        this.setLayout(null);
        this.setTitle("Schach von Anton Klonig und Tim Weber");

        // JLabel background = new JLabel(new ImageIcon(
        // "GUI_Element/background.jpg")); // muss noch repariert werden
        // background.setBounds(0, 0, 800, 800);
        // this.setContentPane(background);
        this.setLayout(null);

        Beginn = new JButton("spielen");
        Beginn.setBounds(200, 200, 200, 50);
        Beginn.addActionListener(this);
        Beginn.setEnabled(false);
        this.add(Beginn);

        SpracheLabel = new JLabel("Sprache wählen");
        SpracheLabel.setBounds(450, 150, 200, 50);
        this.add(SpracheLabel);

        // Dropdown-Menü für Sprachauswahl
        String[] sprachen = {"Deutsch", "English", "Русский", "Français"};
        Sprache = new JComboBox(sprachen);
        Sprache.setBounds(450, 200, 200, 30);
        this.add(Sprache);

        SpracheAnnehmen = new JButton("Speichern");
        SpracheAnnehmen.setBounds(450, 250, 200, 50);
        SpracheAnnehmen.addActionListener(this);
        this.add(SpracheAnnehmen);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == Beginn)
        {
            gui gui = new gui(sprache.gewahlteSprache);
            this.setVisible(false);
            gui.setVisible(true);
        }

        if(event.getSource() == SpracheAnnehmen)
        {
            sprache = new sprache((String) Sprache.getSelectedItem());
            Beginn.setEnabled(true);
            SpracheAnnehmen.setText(sprache.Speichern());
            SpracheLabel.setText(sprache.SpracheWahlen());
            Beginn.setText(sprache.Spielen());
            this.setTitle(sprache.Titel());
        }
    }

    public static void main(String[] args) 
    {
        home test = new home();
        test.setVisible(true);
    }
}


