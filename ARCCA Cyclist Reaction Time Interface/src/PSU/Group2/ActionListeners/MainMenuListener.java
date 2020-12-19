package PSU.Group2.ActionListeners;

import PSU.Group2.CyclistReactionTime;
import PSU.Group2.Util.IO;
import PSU.Group2.Util.RunSettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class MainMenuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent Event) {
        try {
            switch (Event.getActionCommand()) {
                case "Create New Tests":
                    CyclistReactionTime.getGui().Navigation("CreateNewTests");

                    break;
                case "View Existing Tests":
                    File file = new File("RunInstructions.txt");
                    if(file.exists()) {
                        PreparePreSets();
                    }
                    CyclistReactionTime.getGui().Navigation("LoadPrevious");
                    break;
                case "View Analyzed Data":
                    CyclistReactionTime.setPosition(1);
                    CyclistReactionTime.getGui().Navigation("Analysis");
                    break;
                default:
                    CyclistReactionTime.setGui(null);
                    CyclistReactionTime.setPreSet(null);
                    System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void PreparePreSets() throws IOException {

        if (CyclistReactionTime.getPreSet().isEmpty()) {
            IO setup = new IO();        //Setting up Input output streams

            BufferedReader Reader = setup.setupReader("RunInstructions.txt");

            String Line;

            Line = Reader.readLine();
            if (Line != null) {
                while (true) {
                    RunSettings TempSettings = new RunSettings();
                    int Trials = Integer.parseInt(Line);

                    TempSettings.setNumberofTrials(Line);
                    TempSettings.setEnvironment(setup.RevertEnvironment(Reader.readLine()));

                    for (int i = 0; i < Trials; i++) {
                        TempSettings.setHandPositions(Reader.readLine());
                        TempSettings.setNumberofFlashes(Reader.readLine());
                    }

                    TempSettings.setRunComments(Reader.readLine());
                    CyclistReactionTime.getPreSet().add(TempSettings);
                    if ((Line = Reader.readLine()) == null) {
                        break;
                    }
                }
                Reader.close();
                File Temp = new File("RunInstructions.txt");
                Temp.delete();
            }
        }
    }
}