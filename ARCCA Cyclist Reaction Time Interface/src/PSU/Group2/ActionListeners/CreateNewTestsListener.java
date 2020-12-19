package PSU.Group2.ActionListeners;

import PSU.Group2.ActionListeners.SubPanelListeners.FlashesPerTrialListener;
import PSU.Group2.ActionListeners.SubPanelListeners.HandPositionsListener;
import PSU.Group2.CyclistReactionTime;
import PSU.Group2.Panels.SubPanels.FlashesPerTrial;
import PSU.Group2.Panels.SubPanels.HandPositionsPerTrial;
import PSU.Group2.Util.GUIHandler;
import PSU.Group2.Util.IO;
import PSU.Group2.Util.RunSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateNewTestsListener implements ActionListener, KeyListener {
    private RunSettings CurrentTest;

    public CreateNewTestsListener(){
        CurrentTest = new RunSettings();
    }

    @Override
    public void actionPerformed(ActionEvent Event) {
        try {
            int Trials;
            switch (Event.getActionCommand()) {
                case "comboBoxChanged":
                    JComboBox ComboBox = (JComboBox) Event.getSource();
                    String Var = ComboBox.getSelectedItem().toString();

                    if (ComboBox.getName().equalsIgnoreCase("Environment")) {
                        CurrentTest.setEnvironment(Var);
                    }
                    break;

                case "Set Number of Flashes:":
                    Trials = Integer.parseInt(CurrentTest.getNumberofTrials());
                    JFrame FlashesPT = new JFrame("Set Flashes Per Trial");
                    FlashesPT.setSize(600, 600);
                    List<JPanel> panel = new ArrayList<>();

                    if (CurrentTest.getNumberofFlashes().size() > 0) {
                        CurrentTest.EmptyFlashes();
                    }

                    FlashesPerTrialListener Listener = new FlashesPerTrialListener(FlashesPT, panel, CurrentTest);

                    for (int i = 0; i < Trials; i++) {
                        FlashesPerTrial gui = new FlashesPerTrial(i, Listener);
                        panel.add(gui.FlashesPerTrialGUI());
                    }

                    FlashesPT.add(panel.get(0));
                    FlashesPT.setVisible(true);

                    break;
                case "Set Hand Positions":

                    Trials = Integer.parseInt(CurrentTest.getNumberofTrials());
                    JFrame HandPositionsPT = new JFrame("Set Flashes Per Trial");
                    HandPositionsPT.setSize(600, 600);
                    List<JPanel> panels = new ArrayList<>();

                    if(CurrentTest.getHandPositions().size() > 0){
                        CurrentTest.EmptyHandPositions();
                    }
                    HandPositionsListener Listeners = new HandPositionsListener(HandPositionsPT, panels, CurrentTest);

                    for (int i = 0; i < Trials; i++) {
                        HandPositionsPerTrial gui = new HandPositionsPerTrial(i, Listeners);
                        panels.add(gui.HandPositionsPerTrialGUI());
                    }

                    HandPositionsPT.add(panels.get(0));
                    HandPositionsPT.setVisible(true);
                    break;

                case "Save and Exit":
                    setdefaults();
                    WritetoFile();
                    CyclistReactionTime.setGui(null);

                    CurrentTest = null;
                    System.exit(0);
                    break;
                case "Save and Continue":
                    setdefaults();
                    WritetoFile();
                    CyclistReactionTime.getGui().Navigation("CreateNewTests");
                    break;

                case "Back to Main Menu":
                    CurrentTest = null;
                    GUIHandler guiHandler = CyclistReactionTime.getGui();
                    guiHandler.Navigation("MainMenu");

                    break;
                default:
                    CyclistReactionTime.getGui().Navigation("CreateNewTests");
                    break;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void setdefaults(){
        if(CurrentTest.getHandPositions().isEmpty()) {
            for (int i = 0; i < Integer.parseInt(CurrentTest.getNumberofTrials()); i++) {
                CurrentTest.setHandPositions("0");
            }
        }
        if(CurrentTest.getNumberofFlashes().isEmpty()){
            for (int i = 0; i < Integer.parseInt(CurrentTest.getNumberofTrials()); i++) {
                CurrentTest.setNumberofFlashes("1");
            }
        }
    }

    //This is needed to get information from the textfields
    @Override
    public void keyReleased(KeyEvent Event) {
        JTextField TextField = (JTextField) Event.getSource();
        String Var = TextField.getText();

        if(TextField.getName().equalsIgnoreCase("NumberofTrials") && Var.length() < 8) {
            if (!Var.equals("")) {
                if (CheckforChar(Var)) {
                    int Tests = Integer.parseInt(Var);
                    CurrentTest.setNumberofTrials(Integer.toString(Tests));
                }
            }
        }
        else if(TextField.getName().equalsIgnoreCase("Comments")){
            CurrentTest.setRunComments(Var);
        }
    }
    ///////////////////////////////////////////////
    //  Verifies that the text entered in NumberofTests is only integers. This has been included to avoid parsing errors.
    //
    //
    //
    //
    public boolean CheckforChar(String Var) {

        for (char c : Var.toCharArray()) {
            if (Character.isLetter(c) || c == ' ') {
                return false;
            }
        }
        if(Var.charAt(0) == '0'){
            return false;
        }
        return true;
    }

    //////////////////////////////////
    //  Writes entered text to file
    //
    //
    //
    //
    public void WritetoFile() throws IOException {

        IO setup = new IO();
        BufferedWriter writer = setup.setupWriter("RunInstructions.txt",true);

        WriteConfig(setup);

        writer.close();

        checkInit();
    }
    //trial.txt
    //number of trials(int)
    //environment
    //trial 1 hand position (0 (tops), 1 (Hoods), 2 (Drops))
    //Number of flashes in trial 1
    //trial 2 hand position (0 (tops), 1 (Hoods), 2 (Drops))
    //Number of flashes in trial 2

    //2 #Number of trials
    //0 # Road Test
    //0 #Tops hand position Trial 1
    //3 #Number of flashes trial 1
    //1 #Hoods hand position trial 2
    //3 #Number of flashes trial 2

    ////////////////////////////////////
    //Checking before tests are creates this does not exist, this is a way to initialize the RunCfg.txt file.
    //
    //
    public void checkInit() throws IOException {

        File CheckSelected = new File("trial.txt");

        if(!CheckSelected.exists()){
            IO setup = new IO();
            BufferedWriter writer = setup.setupWriter("trial.txt",false);

            WriteConfig(setup);

            writer.append("Position:");                  //Needs to be changed when trial.txt is adjusted.
            writer.append(Integer.toString(0));
            writer.newLine();
            writer.close();
        }
    }

    public void WriteConfig(IO setup) throws IOException {
        setup.write(String.valueOf(CurrentTest.getNumberofTrials()));
        setup.write(setup.getEnvironment(CurrentTest.getEnvironment()));

        int Length = Integer.parseInt(CurrentTest.getNumberofTrials());

        for(int i = 0; i < Length; i++){
            String HP = CurrentTest.getHandPositions().get(i);
            String Flashes = CurrentTest.getNumberofFlashes().get(i);

            setup.write(HP);
            setup.write(Flashes);

        }
        setup.write(CurrentTest.getRunComments());
    }
    /////////////////////////////////////////
    //  Removed on 10/27/20
    //  Random times are now generated on Arduino using rand library.
    //
    //
    //Random Times are generated with the intention of being in Milliseconds.
    /*public void GenerateRandomTimes() throws IOException {

        FileOutputStream Fwriter = new FileOutputStream("RandomTimes.txt", true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Fwriter, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        int Tests = Integer.parseInt(CurrentTest.getNumberofTrials());

        Random rand = new Random();

        for(int i = 0; i < Tests; i++){

            writer.append(Integer.toString(rand.nextInt(300000)));  //Time between 0 - 5 minutes. Choosing Rand in Milliseconds
            writer.newLine();
        }

        writer.close();
    }*/
    //Lines 38...
    //Removed on 10/27/20 and has been replaced by a 5 second hold
    //
                /*else if (ComboBox.getName().equalsIgnoreCase("Warmup")) {

                    if (Var.equalsIgnoreCase("None")) {
                        CurrentTest.setWarmupTime("0");

                    }
                    else {
                        String[] Parsed = Var.split(" ");  //Setting Initial Delay in milliseconds
                        int Delay = Integer.parseInt(Parsed[0]) * 60 * 1000;
                        CurrentTest.setWarmupTime(Integer.toString(Delay));
                    }
                }*/

    /*
    //Removed on 11/07/20 to be replaced with Flashes per trial

      else if(TextField.getName().equalsIgnoreCase("NumberofFlashes") && Var.length() < 8){
            if (!Var.equals("")) {
                if (CheckforChar(Var)) {
                    int Tests = Integer.parseInt(Var);
                    CurrentTest.setNumberofFlashes(Integer.toString(Tests));
                }
            }
        }
     */
    //Required overrides that are not used.
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}

}