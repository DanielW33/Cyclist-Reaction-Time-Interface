package PSU.Group2.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CombineTrials {
    private String Path;

    public void ExtractandSet() throws IOException {
        List<Double> Val;
        int Count = CountFiles();
        String dir = CheckFilePath("Combined", Count);
        File file = new File(dir);

        if(file.exists()) {
            String HRFilePath = dir + "/HRCombined.txt";    //String Concatenation like this works smoothly in Java.
            String PWRFilePath = dir + "/PWRCombined.txt";
            String MVMTFilePath = dir + "/MVMTCombined.txt";
            String PRTFilePath = dir + "/PRTCombined.txt";

            for (int i = 1; i < Count; i++) {     //File names searched for are created by the arduino
                //This will load the values from file into Val, then Val is set to it's combined file.
                Val = getResults(i + "/HR.txt");
                setResults(HRFilePath, Val);

                Val = getResults(i + "/PWR.txt");
                setResults(PWRFilePath, Val);

                Val = getResults(i + "/MVMT.txt");
                setResults(MVMTFilePath, Val);

                Val = getResults(i + "/PRT.txt");
                setResults(PRTFilePath, Val);
            }
        }
    }
    public int CountFiles(){    //This will count the number of Trials created then when the last file does not exist it returns.
        int Counter = 1;

        while (true) {      //Always true, exits via return
            File Temp = new File(String.valueOf(Counter));  //Checking for files named as 0, 1, 2, 3...n

            if(Temp.exists()){
                Counter++;
            }
            else{
                return Counter;
            }
        }
    }
    public List<Double> getResults(String FilePath) throws IOException {    //Gets vals from each filepath and sets them to the same file respectively.
                                                                            //This is iterated via for loop in Extracted() function.
        FileReader FReader = new FileReader(FilePath);
        BufferedReader Reader = new BufferedReader(FReader);

        List<Double> Vals = new ArrayList<>();
        String Line;

        while((Line = Reader.readLine()) != null){
            Vals.add(Double.parseDouble(Line));
        }
        Reader.close();
        return Vals;
    }
    public void setResults(String FilePath, List<Double> Vals) throws IOException {     //Writes list Vals to new file
        FileOutputStream FWriter = new FileOutputStream(FilePath, true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(FWriter, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);

        Vals.forEach(val->{
            try {
                writer.append(String.valueOf(val));
                writer.newLine();
            } catch (IOException e) { e.printStackTrace(); }
        });
        writer.close();
    }

    public String CheckFilePath(String FP, int Counter){
        String FilePath = FP;
        File Temp = new File(FilePath);
        int Count = 1;

        while(Temp.exists()){                   //This while loop checks to see if the file directory already exists, if it doesn't then it just returns.
                                                // Else it will change the file name
            if(!FilePath.contains("(")){        //This checks to see if the file name has already been modified in a previous iteration, if it hasn't then it adds (integer) to the end

                FilePath = FilePath + " (" + Count + ")";
                Temp = new File(FilePath);
            }
            else{                               //This simply replaces the previous integer with the next integer.
                FilePath = FilePath.replace(String.valueOf(Count), String.valueOf(Count = Count + 1)); //Counter = Counter + 1 so counter is set aswell
                Temp = new File(FilePath);
            }
        }
        if(Counter != 0)
            Temp.mkdir();       //The Code above already verifies that the file directory is not used, so this will never fail. (So long as it is not a miss click)

        this.Path = FilePath;   //Setting Path for getFilePath function. added 11/26/20
        return FilePath;
    }

    public void DeleteTests(){  //Java is unable to delete non-empty directories. Therefore the directory must be emptied before being deleted.
        int Count = CountFiles();

        for(int i = 0; i < Count; i++){
            String Directory = String.valueOf(i);
            File hr = new File(Directory + "/HR.txt");
            File pwr = new File(Directory + "/PWR.txt");
            File mvmt = new File(Directory + "/MVMT.txt");
            File prt = new File(Directory + "/PRT.txt");
            File dir = new File(Directory);
            hr.delete();
            pwr.delete();
            mvmt.delete();
            prt.delete();
            dir.delete();
        }
    }
    public String getPath(){
        return this.Path;
    }
}
