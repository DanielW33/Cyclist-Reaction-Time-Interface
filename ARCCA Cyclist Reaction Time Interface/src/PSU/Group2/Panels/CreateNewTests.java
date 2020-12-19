package PSU.Group2.Panels;

import PSU.Group2.ActionListeners.CreateNewTestsListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateNewTests {
    private JFrame GUIFrame;
    private JPanel GUIPanel;

    public CreateNewTests(JFrame guiframe, JPanel guipanel){
        this.GUIFrame = guiframe;
        this.GUIPanel = guipanel;
    }
    //////////////////////////////////////
    //  CreateNewTests Function:
    //  Listener: CreateNewTestsListener
    //
    //
    public JPanel CreateNewTestsGUI(){
        CreateNewTestsListener NewTestsListener = new CreateNewTestsListener();     //Creating one listener for CreateNewTests Function
        GUIFrame.setTitle("Create New Tests");
        //Components Needed for creating tests: Label + Hand Position Drop Down, Label + Number of Tests, Label + Random activation time or not, Environment
        GUIPanel = null;
        GUIPanel = new JPanel(new GridLayout(13, 1));
        GUIPanel.setBackground(Color.DARK_GRAY);
        JLabel Title = new JLabel("Testing Parameters", JLabel.CENTER);
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Serif", Font.BOLD, 20));
        Border Margin = new EmptyBorder(10, 50, 5, 50);
        GUIPanel.setBorder(Margin);

        //Label + Hand Position Drop Down
        JLabel HandPositionL = new JLabel("Hand Position:", JLabel.LEFT);
        HandPositionL.setForeground(Color.WHITE);
        HandPositionL.setFont(new Font("Serif", Font.BOLD, 20));

        JButton HandPositionB = new JButton("Set Hand Positions");
        HandPositionB.addActionListener(NewTestsListener);

        //Number of tests
        JLabel NumberofTrials = new JLabel("Number of Trials:", JLabel.LEFT);
        NumberofTrials.setForeground(Color.WHITE);
        NumberofTrials.setFont(new Font("Serif", Font.BOLD, 20));

        JTextField NumberofTrialsTF = new JTextField(8);
        NumberofTrialsTF.setName("NumberofTrials");
        NumberofTrialsTF.addKeyListener(NewTestsListener);

        JLabel Flashes = new JLabel("Flashes: ");
        Flashes.setForeground(Color.WHITE);
        Flashes.setFont(new Font("Serif", Font.BOLD, 20));

        JButton NumberofFlashes = new JButton("Set Number of Flashes:");
        NumberofFlashes.addActionListener(NewTestsListener);

        //Testing Environment
        JLabel TestingEnvironment = new JLabel("Testing Environment:", JLabel.LEFT);
        TestingEnvironment.setForeground(Color.WHITE);
        TestingEnvironment.setFont(new Font("Serif", Font.BOLD, 20));

        String[] Environment = {"Stationary Bicycle", "Road Test"};
        JComboBox EnvironmentB = new JComboBox(Environment);
        EnvironmentB.setName("Environment");
        EnvironmentB.addActionListener(NewTestsListener);

        //Tests Comments for when the test is saved for later.
        JLabel TestComments = new JLabel("Test Comments:", JLabel.LEFT);
        TestComments.setForeground(Color.WHITE);
        TestComments.setFont(new Font("Serif", Font.BOLD, 20));

        JTextField TestCommentsTF = new JTextField(500);
        TestCommentsTF.setName("Comments");
        TestCommentsTF.addKeyListener(NewTestsListener);

        JLabel Spacer = new JLabel(" ");
        //Creating Bottom Panel
        JPanel BottomPanel = new JPanel();
        BottomPanel.setBackground(Color.DARK_GRAY);
        JButton SaveandContinue = new JButton("Save and Continue");
        SaveandContinue.setName("SaveandContinue");
        SaveandContinue.addActionListener(NewTestsListener);

        JButton SaveandExit = new JButton("Save and Exit");
        SaveandExit.setName("SaveandExit");
        SaveandExit.addActionListener(NewTestsListener);

        JButton Back = new JButton("Back to Main Menu");
        Back.setName("Back");
        Back.addActionListener(NewTestsListener);

        //Adding Everything to GUI
        BottomPanel.add(SaveandContinue);
        BottomPanel.add(SaveandExit);
        BottomPanel.add(Back);

        GUIPanel.add(Title);

        GUIPanel.add(NumberofTrials);
        GUIPanel.add(NumberofTrialsTF);
        GUIPanel.add(HandPositionL);
        GUIPanel.add(HandPositionB);
        GUIPanel.add(Flashes);
        GUIPanel.add(NumberofFlashes); //Number of flashes has replaced warm up period

        GUIPanel.add(TestingEnvironment);
        GUIPanel.add(EnvironmentB);
        GUIPanel.add(TestComments);
        GUIPanel.add(TestCommentsTF);
        GUIPanel.add(Spacer);
        GUIPanel.add(BottomPanel);

        GUIFrame.add(GUIPanel);
        GUIFrame.revalidate();

        return GUIPanel;
    }
}
//Removed this code on 10/27/20 because it is no longer needed, It has been replaced by a 5 second hold to begin trials.
//
        /*
        //Warm up period is the time before testing will randomly begin. This is to allow the rider to get used to riding.
        JLabel WarmUpPeriodL = new JLabel("Warm up Period:", JLabel.LEFT);
        WarmUpPeriodL.setForeground(Color.WHITE);
        WarmUpPeriodL.setFont(new Font("Serif", Font.BOLD, 20));


        On arduino, The time will be measured in MilliSeconds when using the delay() Function.
        String[] WarmupPeriod = {"None", "1 Minute", "2 Minutes", "3 Minutes", "5 Minutes", "10 Minutes", "20 Minutes"};
        JComboBox WarmupB = new JComboBox(WarmupPeriod);
        WarmupB.setName("Warmup");
        WarmupB.addActionListener(NewTestsListener);
        */
