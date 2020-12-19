package PSU.Group2.Panels;

import PSU.Group2.ActionListeners.MainMenuListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu {
    private JFrame GUIFrame;
    private JPanel GUIPanel;

    public MainMenu(JFrame guiframe, JPanel guipanel){
        this.GUIFrame = guiframe;
        this.GUIPanel = guipanel;
    }
    /////////////////////////////////////
    //  MainMenu Function:
    //  Listener: MainMenuListener
    //  Notes: This GUI Panel opens by default when GUI class is allocated.
    //
    public JPanel MainMenuGUI() {
        MainMenuListener MML = new MainMenuListener();
        GUIFrame.setTitle("Main Menu");
        GUIPanel = null;
        //Setting up JPanel, Container for all components of Menu Screen.
        GUIPanel = new JPanel();
        GUIPanel.setLayout(new GridLayout(10, 1));
        GUIPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        Border Margin = new EmptyBorder(10, 50, 5, 50);
        GUIPanel.setBorder(Margin);
        GUIPanel.setBackground(Color.DARK_GRAY);

        //Creating Title Message for Main Menu Page
        JLabel WelcomeMessage = new JLabel("Cyclist Reaction Time", JLabel.CENTER);
        WelcomeMessage.setFont(new Font("Serif", Font.BOLD, 30));
        WelcomeMessage.setForeground(Color.WHITE);

        //Creating Buttons and adding Action Listeners.
        JButton New = new JButton("Create New Tests");
        New.addActionListener(MML);
        JButton Load = new JButton("View Existing Tests");
        Load.addActionListener(MML);
        JButton Analysis = new JButton("View Analyzed Data");
        Analysis.addActionListener(MML);
        JButton exit = new JButton("Exit");
        exit.addActionListener(MML);

        //Creating spacers for appearance purposes.
        JLabel Spacer = new JLabel(" ", JLabel.CENTER);
        JLabel Spacer1 = new JLabel(" ", JLabel.CENTER);
        JLabel Spacer2 = new JLabel(" ", JLabel.CENTER);
        JLabel Spacer3 = new JLabel(" ", JLabel.CENTER);

        //Adding components to JPanel
        GUIPanel.add(WelcomeMessage);
        GUIPanel.add(New);
        GUIPanel.add(Spacer1);
        GUIPanel.add(Load);
        GUIPanel.add(Spacer);
        GUIPanel.add(Analysis);
        GUIPanel.add(Spacer3);
        GUIPanel.add(exit);
        GUIPanel.add(Spacer2);

        GUIFrame.getContentPane().add(GUIPanel);
        GUIFrame.revalidate();
        return GUIPanel;
    }
}
