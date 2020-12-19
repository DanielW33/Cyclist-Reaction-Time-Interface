package PSU.Group2.Util;

import PSU.Group2.Panels.Analysis;
import PSU.Group2.Panels.CreateNewTests;
import PSU.Group2.Panels.LoadTests;
import PSU.Group2.Panels.MainMenu;

import javax.swing.*;
import java.io.IOException;

public class GUIHandler {
    private JFrame GUIFrame;
    private JPanel GUIPanel;

    ///////////////////////////////
    //  Default constructor:
    //
    //  Notes: Initializing variables used throughout
    //
    public GUIHandler() {
        GUIFrame = new JFrame("Null");
        GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUIFrame.setSize(600, 800);
        GUIPanel = new JPanel();
        GUIFrame.setVisible(true);
    }

    /////////////////////////////////
    //  Navigation Function:
    //
    //  Notes: Handles navigation between panels, Removes GUIPanel frame for it to be reallocated
    //
    public void Navigation(String Nav) throws IOException {
        if (Nav.equalsIgnoreCase("MainMenu")) {
            GUIFrame.remove(GUIPanel);
            MainMenu MainMenu = new MainMenu(GUIFrame, GUIPanel);
            GUIPanel = MainMenu.MainMenuGUI();
        }
        else if (Nav.equalsIgnoreCase("CreateNewTests")) {
            GUIFrame.remove(GUIPanel);
            CreateNewTests NewTestsMenu = new CreateNewTests(GUIFrame, GUIPanel);
            GUIPanel = NewTestsMenu.CreateNewTestsGUI();
        }
        else if (Nav.equalsIgnoreCase("LoadPrevious")) {
            GUIFrame.remove(GUIPanel);
            LoadTests LoadTestsMenu = new LoadTests(GUIFrame, GUIPanel);
            GUIPanel = LoadTestsMenu.LoadTestsGUI();
        }
        else if(Nav.equalsIgnoreCase("Analysis")){
            GUIFrame.remove(GUIPanel);
            Analysis AnalysisMenu = new Analysis(GUIFrame, GUIPanel);
            GUIPanel = AnalysisMenu.AnalysisFrame();
        }
    }
}