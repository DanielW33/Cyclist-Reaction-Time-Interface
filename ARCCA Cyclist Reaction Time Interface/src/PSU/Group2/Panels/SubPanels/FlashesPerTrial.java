package PSU.Group2.Panels.SubPanels;

import PSU.Group2.ActionListeners.SubPanelListeners.FlashesPerTrialListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FlashesPerTrial {
    private int Trial;
    private JPanel panel;
    private FlashesPerTrialListener Listener;

    public FlashesPerTrial(int Trial, FlashesPerTrialListener Listener){
        this.Trial = Trial;
        this.Listener = Listener;
    }

    public JPanel FlashesPerTrialGUI(){


        panel = new JPanel(new GridLayout(13,1));
        panel.setBackground(Color.DARK_GRAY);
        Border Margin = new EmptyBorder(10, 50, 5, 50);
        panel.setBorder(Margin);

        JLabel Title = new JLabel("Set Flashes for Trial: " + Trial);
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(Title);

        JLabel NumberofFlashes = new JLabel("Number of Flashes:", JLabel.CENTER);
        NumberofFlashes.setForeground(Color.WHITE);
        NumberofFlashes.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(NumberofFlashes);

        JTextField NumberofFlashesTF = new JTextField(8);
        NumberofFlashesTF.setName("NumberofFlashes");
        NumberofFlashesTF.addKeyListener(Listener);
        panel.add(NumberofFlashesTF);

        JButton SetFlashes = new JButton("Set Flashes");
        panel.add(SetFlashes);
        SetFlashes.addActionListener(Listener);


        return panel;
    }
}
