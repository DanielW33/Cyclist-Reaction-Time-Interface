package PSU.Group2.ActionListeners;

import PSU.Group2.CyclistReactionTime;
import PSU.Group2.Panels.Graphs.DoublevsDouble;
import PSU.Group2.Panels.Graphs.Point;
import PSU.Group2.Panels.Graphs.ValuevsString;
import PSU.Group2.Util.CombineTrials;
import PSU.Group2.Util.IO;
import PSU.Group2.Util.TestResults;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisListener implements ActionListener {
    private JFrame frame;
    private JMenuBar menuBar;

    private List<Double> MVMT;
    private List<Double> PRT;
    private List<Double> PWR;
    private List<Double> HR;

    private List<Integer> FPT; //Flashes per trial
    public AnalysisListener(JFrame frame, JMenuBar menubar) {
        this.frame = frame;
        this.menuBar = menubar;
    }

    @Override
    public void actionPerformed(ActionEvent Event) {
        String Command = Event.getActionCommand();
        int Position = CyclistReactionTime.getPosition();

        try {
            TestResults TR = new TestResults();

            List<Integer> HandPositions;
            List<Point> Points;
            ValuevsString ValvsString;
            DoublevsDouble dvd;
            CombineTrials combineTrials;

            switch (Command) {
                case "Combine data files":
                    combineTrials = new CombineTrials();
                    combineTrials.ExtractandSet();
                    break;
                case "Delete test files":
                    combineTrials = new CombineTrials();
                    combineTrials.DeleteTests();
                    break;
                case "Next Trial":
                    Position = CyclistReactionTime.getPosition() + 1;
                    CyclistReactionTime.setPosition(Position);
                    CyclistReactionTime.getGui().Navigation("Analysis");
                    break;
                case "Previous Trial":
                    Position = CyclistReactionTime.getPosition() - 1;
                    CyclistReactionTime.setPosition(Position);
                    CyclistReactionTime.getGui().Navigation("Analysis");
                    break;
                case "Perception Reaction Time vs Hand Position":
                    HandPositions = getSingleHandPosition();
                    PRT = TR.LoadData(Position + "/PRT.txt");

                    Points = getPoints(PRT, HandPositions);
                    ValvsString = new ValuevsString(Points);
                    ValvsString.CreateGUI("Perception Reaction Time (y) vs Hand Position (x)");
                    break;

                case "Movement Time vs Hand Position":
                    HandPositions = getSingleHandPosition();
                    MVMT = TR.LoadData(Position + "/MVMT.txt");

                    Points = getPoints(MVMT, HandPositions);
                    ValvsString = new ValuevsString(Points);
                    ValvsString.CreateGUI("Movement Time (y) vs Hand Position (x)");
                    break;

                case "Power vs Hand Position":
                    HandPositions = getSingleHandPosition();
                    PWR = TR.LoadData(Position + "/PWR.txt");

                    Points = getPoints(PWR, HandPositions);

                    ValvsString = new ValuevsString(Points);
                    ValvsString.CreateGUI("Power (y) vs Hand Position (x)");
                    break;

                case "Perception Reaction Time vs Power":
                    PRT = TR.LoadData(Position + "/PRT.txt");
                    PWR = TR.LoadData(Position + "/PWR.txt");

                    dvd = new DoublevsDouble(PWR,PRT);
                    dvd.CreateGUI("Perception Reaction Time (y) vs Power (x)");
                    break;
                case "Movement Time vs Power":
                    MVMT = TR.LoadData(Position + "/MVMT.txt");
                    PWR = TR.LoadData(Position + "/PWR.txt");

                    dvd = new DoublevsDouble(PWR, MVMT);
                    dvd.CreateGUI("Movement Time (y) vs Power (x)");
                    break;
                case "Power vs Heart Rate":
                    PWR = TR.LoadData(Position + "/PWR.txt");
                    HR = TR.LoadData(Position + "/HR.txt");

                    dvd = new DoublevsDouble(PWR, HR);
                    dvd.CreateGUI("Power (x) vs Heart Rate (y)");
                    break;

                case "Perception Reaction Time vs Heart Rate":
                    PRT = TR.LoadData(Position + "/PRT.txt");
                    HR = TR.LoadData(Position + "/HR.txt");

                    dvd = new DoublevsDouble(HR, PRT);
                    dvd.CreateGUI("Perception Reaction Time (y) vs Heart Rate (x)");
                    break;
                case "Combined Perception Reaction Time vs Hand Position":
                    HandPositions = getHandPositions();
                    Points = C_getPoints(HandPositions, "/PRT.txt");

                    ValvsString = new ValuevsString(Points);
                    ValvsString.CreateGUI("Combined Perception Reaction Time (y) vs Hand Position (x)");
                    break;

                case "Combined Movement Time vs Hand Position":
                    HandPositions = getHandPositions();
                    Points = C_getPoints(HandPositions, "/MVMT.txt");
                    ValvsString = new ValuevsString(Points);
                    ValvsString.CreateGUI("Combined Movement Time (y) vs Hand Position (x)");
                    break;
                case "Combined Power vs Hand Position":
                    HandPositions = getHandPositions();
                    Points = C_getPoints(HandPositions, "/PWR.txt");
                    ValvsString = new ValuevsString(Points);
                    ValvsString.CreateGUI("Combined Power (y) vs Hand Position (x)");
                    break;

                case "Combined Perception Reaction Time vs Power":
                    PWR = C_getVals("/PWR.txt");
                    PRT = C_getVals("/PRT.txt");

                    dvd = new DoublevsDouble(PWR,PRT);
                    dvd.CreateGUI("Combined Perception Reaction Time (y) vs Power (x)");
                    break;

                case "Combined Movement Time vs Power":
                    PWR = C_getVals("/PWR.txt");
                    MVMT = C_getVals("/MVMT.txt");
                    dvd = new DoublevsDouble(PWR,MVMT);
                    dvd.CreateGUI("Combined Movement Time (y) vs Power (x)");
                    break;

                case "Combined Power vs Heart Rate":
                    PWR = C_getVals("/PWR.txt");
                    HR = C_getVals("/HR.txt");
                    dvd = new DoublevsDouble(HR,PWR);
                    dvd.CreateGUI("Combined Power (y) vs Heart Rate (x)");
                    break;

                case "Combined Perception Reaction Time vs Heart Rate":
                    PRT = C_getVals("/PRT.txt");
                    HR = C_getVals("/HR.txt");

                    dvd = new DoublevsDouble(HR,PRT);
                    dvd.CreateGUI("Combined Perception Reaction Time (y) vs Heart Rate (x)");
                    break;

                case "Return to Menu":
                    menuBar.removeAll();
                    CyclistReactionTime.setPosition(0);
                    CyclistReactionTime.getGui().Navigation("MainMenu");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Double> C_getVals(String Path) throws IOException {
        List<Double> l = new ArrayList<>();
        IO io = new IO();
        CombineTrials ct = new CombineTrials();
        String line;

        for(int i = 1; i < ct.CountFiles(); i++) {
            BufferedReader reader = io.setupReader(i + Path);
            while((line = reader.readLine()) != null){
                l.add(Double.parseDouble(line));
            }
            reader.close();
        }

        return l;
    }
    private List<Point> C_getPoints( List<Integer> HandPositions, String path) throws IOException {
        List<String> var = new ArrayList<>();
        CombineTrials ct = new CombineTrials();
        IO setup = new IO();
        String line;
        int n = ct.CountFiles();
        for(int i = 1; i < n; i++){
            BufferedReader reader = setup.setupReader(i + path);
            while ((line = reader.readLine()) != null){ //Will add all of the content in the files to first arraylist
                var.add(line);
            }
            reader.close();
        }

        List<Point> points = new ArrayList<>();
        int counter = 0;
        for(int i = 0; i < FPT.size(); i++){
            for(int j = 0; j < FPT.get(i); j++){
                Point point = new Point();
                point.setString( ConvertHandPosition( HandPositions.get(i) ) );
                point.setValue(Double.parseDouble(var.get(counter)));
                points.add(point);
                counter++;
            }
        }

        return points;

    }
    private List<Integer> getHandPositions() throws IOException {
        FPT = new ArrayList<>();
        IO setup = new IO();        //Setting up Input output streams
        BufferedReader Reader = setup.setupReader("trial.txt");

        String line = Reader.readLine();
        int Trials = Integer.parseInt(line);

        Reader.readLine(); // Throwing out environment

        List<Integer> HandPosition = new ArrayList<>();

        for(int i = 0; i < Trials; i++){
            HandPosition.add(Integer.parseInt(Reader.readLine())); //Hand Position for Flashes per trial
            FPT.add(Integer.parseInt(Reader.readLine())); //Flashes per trial
        }
        Reader.close();
        return HandPosition;
    }

    private List<Integer> getSingleHandPosition() throws IOException {
        IO setup = new IO();        //Setting up Input output streams
        BufferedReader Reader = setup.setupReader("trial.txt");

        String line = Reader.readLine();
        int Trials = Integer.parseInt(line);

        Reader.readLine(); // Throwing out environment

        List<Integer> HandPosition = new ArrayList<>();
        int HP = 0;
        int Position = CyclistReactionTime.getPosition();

        for(int i = 0; i < Trials; i++){
            Reader.readLine(); //Throwing out hand positions
            Reader.readLine(); //Throwing out flashes per trial.
            if(i + 1 == Position){
                HP = Integer.parseInt(Reader.readLine());
                break;
            }
        }
        Reader.close();
        for(int i = 0; i < Trials; i++){
            HandPosition.add(HP);
        }
        return HandPosition;
    }

    private List<Point> getPoints(List<Double> y, List<Integer> HandPosition){
        List<Point> Point = new ArrayList<>();

        for(int i = 0; i < y.size(); i++){
            Point point  = new Point();
            point.setValue(y.get(i));
            point.setString(ConvertHandPosition(HandPosition.get(CyclistReactionTime.getPosition())));
            Point.add(point);
        }
        return Point;

    }

    private String ConvertHandPosition(int HandPosition){
        switch(HandPosition){
            case 0:
                return "Tops";
            case 1:
                return "Hoods";
            default:
                return "Drops";
        }
    }

}
