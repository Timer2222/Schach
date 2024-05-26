package src;

import javax.swing.*;
import java.awt.event.*;

public class ende  extends JFrame implements ActionListener
{
    JLabel EndLabel;
    JButton Wiederholen, HomeButton;
    public ende(String art)
    {
        this.setSize(800, 800);
        this.setLayout(null);
        this.setTitle("Spielthis");

        EndLabel = new JLabel(art);
        EndLabel.setBounds(300, 50, 800, 50);
        this.add(EndLabel);

        Wiederholen = new JButton("Wiederholen");
        Wiederholen.setBounds(200, 200, 200, 50);
        Wiederholen.addActionListener(this);
        this.add(Wiederholen);

        HomeButton = new JButton("Home");
        HomeButton.setBounds(450, 200, 200, 50);
        HomeButton.addActionListener(this);
        this.add(HomeButton);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {

    }
}
