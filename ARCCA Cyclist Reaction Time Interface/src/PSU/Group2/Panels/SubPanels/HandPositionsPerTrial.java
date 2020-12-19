package PSU.Group2.Panels.SubPanels;

import PSU.Group2.ActionListeners.SubPanelListeners.FlashesPerTrialListener;
import PSU.Group2.ActionListeners.SubPanelListeners.HandPositionsListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HandPositionsPerTrial {
    private int Trial;
    private JPanel panel;
    private HandPositionsListener Listener;

    public HandPositionsPerTrial(int Trial, HandPositionsListener Listener){
        this.Trial = Trial;
        this.Listener = Listener;
    }

    public JPanel HandPositionsPerTrialGUI(){


        panel = new JPanel(new GridLayout(13,1));
        panel.setBackground(Color.DARK_GRAY);
        Border Margin = new EmptyBorder(10, 50, 5, 50);
        panel.setBorder(Margin);

        JLabel Title = new JLabel("Set HandPosition for Trial: " + Trial);
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(Title);

        JLabel HandPositions = new JLabel("Hand Positions:", JLabel.CENTER);
        HandPositions.setForeground(Color.WHITE);
        HandPositions.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(HandPositions);

        String[] HandPositionsArray = {"Hand Positions", "Tops", "Hoods", "Drops"};
        JComboBox HandPositionB = new JComboBox(HandPositionsArray);
        HandPositionB.setName("HandPosition");
        HandPositionB.addActionListener(Listener);
        panel.add(HandPositionB);

        JButton SetHandPosition = new JButton("Set Hand Position");
        panel.add(SetHandPosition);
        SetHandPosition.addActionListener(Listener);


        return panel;
    }
}
