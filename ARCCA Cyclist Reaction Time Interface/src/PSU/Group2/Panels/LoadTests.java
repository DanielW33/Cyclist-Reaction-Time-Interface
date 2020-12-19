package PSU.Group2.Panels;


import PSU.Group2.ActionListeners.ExistingTestsListener;
import PSU.Group2.CyclistReactionTime;
import PSU.Group2.Util.IO;
import PSU.Group2.Util.RunSettings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadTests {
    private JFrame GUIFrame;
    private JPanel GUIPanel;

    public LoadTests(JFrame guiframe, JPanel guipanel){
        this.GUIFrame = guiframe;
        this.GUIPanel = guipanel;
    }
    ////////////////////////////////
    //  loadTests function
    //  Listener: ExistingTestsListener
    //  Notes: This may be the only method with logic, Jlabels are set for presentation purposes.
    //
    public JPanel LoadTestsGUI() throws IOException {
        ExistingTestsListener PreLoaded = new ExistingTestsListener();
        GUIFrame.setTitle("Existing Tests");
        GUIPanel = null;
        GUIPanel = new JPanel(new GridLayout(13, 1));
        GUIPanel.setBackground(Color.DARK_GRAY);
        Border Margin = new EmptyBorder(10, 50, 5, 50);
        GUIPanel.setBorder(Margin);
        JButton Back = new JButton("Return to Main Menu");
        Back.addActionListener(PreLoaded);

        if (CyclistReactionTime.getPreSet().isEmpty()) {
            JLabel Errored = new JLabel("No Entries Available", JLabel.CENTER);
            Errored.setForeground(Color.WHITE);
            Errored.setFont(new Font("Serif", Font.BOLD, 30));
            GUIPanel.add(Errored);
            GUIPanel.add(Back);
        }
        else {
            int Position = CyclistReactionTime.getPosition();
            List<RunSettings> List = CyclistReactionTime.getPreSet();
            RunSettings Current = List.get(Position);
            List<String> HandPositions;

            JLabel PreSets = new JLabel("Existing Test: " + Position, JLabel.CENTER);
            PreSets.setForeground(Color.WHITE);
            PreSets.setFont(new Font("Serif", Font.BOLD, 30));

            JLabel HandPosition = new JLabel("Hand Position: " + getHandPositions(Current));
            HandPosition.setForeground(Color.WHITE);
            HandPosition.setFont(new Font("Serif", Font.BOLD, 20));

            JLabel NumberofTrials = new JLabel("Number of Trials: " + Current.getNumberofTrials());
            NumberofTrials.setForeground(Color.WHITE);
            NumberofTrials.setFont(new Font("Serif", Font.BOLD, 20));

            JLabel Environment = new JLabel("Environment: " + Current.getEnvironment());
            Environment.setForeground(Color.WHITE);
            Environment.setFont(new Font("Serif", Font.BOLD, 20));

            JLabel RunComments = new JLabel("Comments: " + Current.getRunComments());
            RunComments.setForeground(Color.WHITE);
            RunComments.setFont(new Font("Serif", Font.BOLD, 20));

            JLabel Flashes = new JLabel("Flashes per Trial: " + Current.getNumberofFlashes());
            Flashes.setForeground(Color.WHITE);
            Flashes.setFont(new Font("Serif", Font.BOLD, 20));

            JPanel BottomPanel = new JPanel();
            BottomPanel.setBackground(Color.DARK_GRAY);

            GUIPanel.add(PreSets);
            GUIPanel.add(NumberofTrials);
            GUIPanel.add(Environment);
            GUIPanel.add(Flashes);
            GUIPanel.add(HandPosition);
            GUIPanel.add(RunComments);

            JButton Previous = new JButton("Previous Pre-Set");
            Previous.addActionListener(PreLoaded);

            if (CyclistReactionTime.getPosition() != 0) {
                BottomPanel.add(Previous);
            }

            BottomPanel.add(Back);
            JButton next = new JButton("Next Pre-Set");
            next.addActionListener(PreLoaded);

            if (CyclistReactionTime.getPosition() != CyclistReactionTime.getPreSet().size() - 1) {
                BottomPanel.add(next);
            }

            GUIPanel.add(BottomPanel);

            JPanel SecondBottomPanel = new JPanel(new GridLayout(2,1));
            SecondBottomPanel.setBackground(Color.DARK_GRAY);

            JButton Delete = new JButton("Delete Entry");
            Delete.addActionListener(PreLoaded);
            SecondBottomPanel.add(Delete);

            if(Position != ParsePositionString()) {

                JButton SelectPreset = new JButton("Select this Preset Test");
                SelectPreset.addActionListener(PreLoaded);
                SecondBottomPanel.add(SelectPreset);
            }
            else{
                JLabel Selected = new JLabel("This Test is Currently Selected.");
                Selected.setForeground(Color.WHITE);
                Selected.setFont(new Font("Serif", Font.BOLD, 20));
                SecondBottomPanel.add(Selected);
            }

            GUIPanel.add(SecondBottomPanel);
        }
        GUIFrame.add(GUIPanel);
        GUIFrame.revalidate();

        return GUIPanel;
    }

    public int ParsePositionString() throws IOException {   //This may or may not remain in the code.
        int Position = 0;
        IO setup = new IO();

        BufferedReader Reader = setup.setupReader("trial.txt");

        String Line, Previous = "";

        while ((Line = Reader.readLine()) != null) {
            Previous = Line;
        }
        String[] ParsedLine = Previous.split(":");
        Position = Integer.parseInt(ParsedLine[1]);

        Reader.close();
        return Position;
    }

    public List<String> getHandPositions(RunSettings Current){
        List<String> L = new ArrayList<>();
        Current.getHandPositions().forEach(var ->{
            if(var.equalsIgnoreCase("0")){
                L.add("Tops");
            }
            else if (var.equalsIgnoreCase("1")){
                L.add("Hoods");
            }
            else{
                L.add("Drops");
            }
        });
        return L;
    }
}