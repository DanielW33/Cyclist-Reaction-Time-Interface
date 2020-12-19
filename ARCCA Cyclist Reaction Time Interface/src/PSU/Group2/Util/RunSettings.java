package PSU.Group2.Util;

import java.util.ArrayList;
import java.util.List;

public class RunSettings {
    private List<String> HandPositions;
    private String NumberofTrials;
    private List<String> NumberofFlashes;
    private String Environment;
    private String runComments;

    public RunSettings(){
        HandPositions = new ArrayList<>();
        NumberofTrials = "1";
        NumberofFlashes = new ArrayList<>();
        Environment = "Stationary Bicycle";
        runComments = "N/A";
    }
    //Setters and getters
    public String getNumberofTrials(){ return NumberofTrials; }
    public void setNumberofTrials(String NumberofTests){ this.NumberofTrials = NumberofTests; }
    public String getRunComments(){ return runComments; }
    public void setRunComments(String runComments){ this.runComments = runComments; }
    public List<String> getHandPositions(){ return HandPositions; }

    public void setHandPositions(String HandPositions){
        this.HandPositions.add(HandPositions);
    }
    public String getEnvironment(){ return Environment; }
    public void setEnvironment(String Environment){ this.Environment = Environment; }
    public List<String> getNumberofFlashes(){ return NumberofFlashes; }

    public void setNumberofFlashes(String NumberofFlashes){
        this.NumberofFlashes.add(NumberofFlashes);
    }

    public void EmptyHandPositions(){
        HandPositions.clear();
    }
    public void EmptyFlashes(){
        NumberofFlashes.clear();
    }
}
