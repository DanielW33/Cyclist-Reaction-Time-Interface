package PSU.Group2.ActionListeners;

import PSU.Group2.CyclistReactionTime;
import PSU.Group2.Util.IO;
import PSU.Group2.Util.RunSettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ExistingTestsListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent Event) {
        try {
            String Command = Event.getActionCommand();

            switch (Command) {
                case "Return to Main Menu":
                    Write();
                    CyclistReactionTime.getPreSet().clear();
                    CyclistReactionTime.setPosition(0);
                    CyclistReactionTime.getGui().Navigation("MainMenu");
                    break;
                case "Next Pre-Set":
                    CyclistReactionTime.setPosition(CyclistReactionTime.getPosition() + 1);
                    CyclistReactionTime.getGui().Navigation("LoadPrevious");
                    break;
                case "Previous Pre-Set":
                    CyclistReactionTime.setPosition(CyclistReactionTime.getPosition() - 1);
                    CyclistReactionTime.getGui().Navigation("LoadPrevious");
                    break;
                case "Delete Entry":
                    CyclistReactionTime.getPreSet().remove(CyclistReactionTime.getPosition());
                    CyclistReactionTime.setPosition(CyclistReactionTime.getPosition() - 1);

                    if (CyclistReactionTime.getPosition() < 0) {
                        CyclistReactionTime.setPosition(0);
                    }
                    CyclistReactionTime.getGui().Navigation("LoadPrevious");
                    break;
                case "Select this Preset Test":
                    WritePreset();
                    CyclistReactionTime.getGui().Navigation("LoadPrevious");
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            try {
                e.printStackTrace();
                IO writeError = new IO();
                writeError.ERRORED(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void Write() throws IOException {
        IO setup = new IO();
        BufferedWriter writer = setup.setupWriter("RunInstructions.txt", true);

        CyclistReactionTime.getPreSet().forEach(var ->{
            try {
               WritetoFile(setup, var);
            }
            catch (IOException e) { e.printStackTrace(); }
        });
        writer.close();
    }

    public void WritePreset() throws IOException {
        IO setup = new IO();
        BufferedWriter writer = setup.setupWriter("trial.txt", false);

        int Position = CyclistReactionTime.getPosition();
        RunSettings settings = CyclistReactionTime.getPreSet().get(Position);

        WritetoFile(setup, settings);

        writer.append("Position:");
        writer.append(Integer.toString(Position));
        writer.newLine();

        writer.close();
    }

    public void WritetoFile(IO setup, RunSettings settings) throws IOException {
        setup.write(String.valueOf(settings.getNumberofTrials()));
        setup.write(setup.getEnvironment(settings.getEnvironment()));

        int Length = Integer.parseInt(settings.getNumberofTrials());

        for(int i = 0; i < Length; i++){
            String HP = settings.getHandPositions().get(i);
            String Flashes = settings.getNumberofFlashes().get(i);

            setup.write(HP);
            setup.write(Flashes);

        }
        setup.write(settings.getRunComments());
    }
    /////////////////////////////
    // Removed on 10/27/20 because random times are now generated on Arduino using Rand library.
    /*public void DeleteTimes() throws IOException {

        FileReader FReader = new FileReader("RandomTimes.txt");
        BufferedReader Reader = new BufferedReader(FReader);
        List<Integer> Times = new ArrayList<Integer>();
        String Line;

        while((Line = Reader.readLine()) != null){
            Times.add(Integer.parseInt(Line));
        }

        Reader.close();

        File Temp = new File("RandomTimes.txt");
        Temp.delete();

        FileOutputStream Fwriter = new FileOutputStream("RandomTimes.txt", true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Fwriter, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        int NumtoDelete = Integer.parseInt(CyclistReactionTime.getPreSet().get(CyclistReactionTime.getPosition()).getNumberofTrials());

        for(int i = 0; i < Times.size()-NumtoDelete; i++){
            writer.append(Integer.toString(Times.get(i)));
            writer.newLine();
        }
        writer.close(); Times.clear();
    }
    */

}
