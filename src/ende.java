package src;

import javax.swing.*;
import java.awt.event.*;

public class ende extends JFrame implements ActionListener
{
    JLabel EndLabel;
    JButton Wiederholen, HomeButton;
    sprache sprache;
    String labelText;
    public ende(int art, String spracheWahl) // 1 ist schwarz-sieg, 2 ist weiss-sieg, 3 ist patt
    {
        sprache = new sprache(spracheWahl);

        this.setSize(800, 800);
        this.setLayout(null);
        this.setTitle(sprache.spielende());

        if(art == 1)
        {
            // man kann scheinbar html texte verwenden...
            labelText = "<html>" +
                "<p>"+sprache.Matt()+"</p>" +
                "<p>"+sprache.schwarzSieg()+"</p>" +
                "</html>";
        }
        else if(art == 2)
        {
            // man kann scheinbar html texte verwenden...
            labelText = "<html>" +
                "<p>"+sprache.Matt()+"</p>" +
                "<p>"+sprache.weissSieg()+"</p>" +
                "</html>";
        }
        else if(art == 3)
        {
            labelText = sprache.Patt();
        }

        EndLabel = new JLabel(labelText);
        EndLabel.setBounds(300, 50, 800, 50);
        this.add(EndLabel);

        Wiederholen = new JButton(sprache.wiederholen());
        Wiederholen.setBounds(200, 200, 200, 50);
        Wiederholen.addActionListener(this);
        this.add(Wiederholen);

        HomeButton = new JButton(sprache.home());
        HomeButton.setBounds(450, 200, 200, 50);
        HomeButton.addActionListener(this);
        this.add(HomeButton);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == Wiederholen)
        {
            gui gui = new gui(sprache.gewahlteSprache);
            this.setVisible(false);
            gui.setVisible(true);
        }
        else if(event.getSource() == HomeButton)
        {
            home home = new home();
            this.setVisible(false);
            home.setVisible(true);
        }
    }
}
