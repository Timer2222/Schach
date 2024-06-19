package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class home extends JFrame implements ActionListener, ChangeListener
{
    JLabel SpracheLabel, EndLabel, Hintergrund;
    JButton Beginn, Wiederholen, HomeButton, SpracheAnnehmen, MusikButton;
    JComboBox Sprache;
    JSlider LautstarkeRegler;
    sprache sprache;
    Clip musikClip;
    FloatControl Lautstaerke;

    public home()
    {
        sprache = new sprache("Deutsch");

        this.setSize(800, 800);
        this.setLayout(null);
        this.setTitle("Schach von Anton Klonig und Tim Weber");

        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);
        
        Hintergrund = new JLabel(new ImageIcon("lib/pic/background2.png"));
        Hintergrund.setBounds(0, 0, 800, 800);
        layeredPane.add(Hintergrund, Integer.valueOf(0));

        Beginn = new JButton("spielen");
        Beginn.setBounds(200, 200, 200, 50);
        Beginn.addActionListener(this);
        Beginn.setEnabled(false);
        layeredPane.add(Beginn, Integer.valueOf(1));

        SpracheLabel = new JLabel("Sprache waehlen");
        SpracheLabel.setBounds(450, 150, 200, 50);
        layeredPane.add(SpracheLabel, Integer.valueOf(1));

        // Dropdown-Menü für Sprachauswahl
        String[] sprachen = {"Deutsch", "English", "Русский", "Français"};
        Sprache = new JComboBox(sprachen);
        Sprache.setBounds(450, 200, 200, 30);
        layeredPane.add(Sprache, Integer.valueOf(1));

        SpracheAnnehmen = new JButton("Speichern");
        SpracheAnnehmen.setBounds(450, 250, 200, 50);
        SpracheAnnehmen.addActionListener(this);
        layeredPane.add(SpracheAnnehmen, Integer.valueOf(1));

        MusikButton = new JButton("Musik An/Aus");
        MusikButton.setBounds(50, 600, 200, 50);
        MusikButton.addActionListener(this);
        layeredPane.add(MusikButton, Integer.valueOf(1));

        LautstarkeRegler = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        LautstarkeRegler.setBounds(50, 650, 200, 50);
        LautstarkeRegler.addChangeListener(this);
        layeredPane.add(LautstarkeRegler, Integer.valueOf(1));

        this.setVisible(true);

        try 
        {
            File musikDatei = new File("lib/sound/HintergrundMusik.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musikDatei);
            musikClip = AudioSystem.getClip();
            musikClip.open(audioStream);
            Lautstaerke = (FloatControl) musikClip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(50);
            musikClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            e.printStackTrace();
        }
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

        if (event.getSource() == MusikButton) {
            if (musikClip.isRunning()) {
                musikClip.stop();
            } else {
                musikClip.start();
            }
        }
    }

    // Für Lautstaerke
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == LautstarkeRegler) {
            setVolume(LautstarkeRegler.getValue());
        }
    }

    private void setVolume(int value) {
        if (Lautstaerke != null) {
            float min = Lautstaerke.getMinimum();
            float max = Lautstaerke.getMaximum();
            float volume = min + (max - min) * (value / 100.0f);
            Lautstaerke.setValue(volume);
        }
    }

    public static void main(String[] args) 
    {
        home test = new home();
        test.setVisible(true);
    }
}