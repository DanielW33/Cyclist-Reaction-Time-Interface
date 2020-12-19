package PSU.Group2.Util;

import PSU.Group2.CyclistReactionTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestResults {

    public List<Double> LoadData(String FilePath) throws IOException {
        int Position = CyclistReactionTime.getPosition();

        File file = new File(String.valueOf(Position));
        List<Double> FileData = new ArrayList<>();

        if(file.exists()){
            IO setup = new IO();
            BufferedReader reader = setup.setupReader(FilePath);
            String line;

            while((line = reader.readLine()) != null){
                FileData.add(Double.valueOf(line));
            }
        }
        return FileData;
    }

    public double CalculateAverage(List<Double> Vals){
        double sum = Vals.stream().mapToDouble(var -> var).sum();   //Java Lambda Expression
        return sum/Vals.size();
    }
    public double CalculateStandardDeviation(List<Double> vals){
        double Average = CalculateAverage(vals);
        double Summation = vals.stream().mapToDouble(x -> Math.pow(x - Average, 2)).sum(); //Java Lambda Expression

        return Math.sqrt((Summation/(vals.size()-1)));
    }
    public double CalculateVariance(List<Double> val){
        return Math.pow(CalculateStandardDeviation(val), 2);
    }

}
