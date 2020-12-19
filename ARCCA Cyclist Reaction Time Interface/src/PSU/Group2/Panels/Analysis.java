package PSU.Group2.Panels;

import PSU.Group2.ActionListeners.AnalysisListener;
import PSU.Group2.CyclistReactionTime;
import PSU.Group2.Util.CombineTrials;
import PSU.Group2.Util.TestResults;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Analysis {
    private JFrame GUIFrame;
    private JPanel GUIPanel;
    private int Position = 1;

    public Analysis( JFrame guiframe, JPanel guipanel){
        this.GUIFrame = guiframe;
        this.GUIPanel = guipanel;
    }

    public JPanel AnalysisFrame() throws IOException {
        CombineTrials combineTrials = new CombineTrials();
        JMenuBar menuBar = new JMenuBar();
        AnalysisListener AL = new AnalysisListener(GUIFrame, menuBar);

        GUIFrame.setTitle("Analysis");
        GUIPanel = null;
        GUIPanel = new JPanel(new GridLayout(19,1));
        GUIPanel.setBackground(Color.DARK_GRAY);

        if(combineTrials.CountFiles() == 1){
            JLabel Title = new JLabel("There are no Test Files available", JLabel.CENTER);
            Title.setForeground(Color.WHITE);
            Title.setFont(new Font("Serif", Font.BOLD, 20));

            JButton RtM = new JButton("Return to Menu");
            RtM.addActionListener(AL);
            GUIPanel.add(Title);
            GUIPanel.add(RtM);
        }
        else {
            menuBar.setBackground(Color.DARK_GRAY);
            JMenu FileSettings = new JMenu("File Settings");

            FileSettings.setForeground(Color.WHITE);

            JMenu Graphs = new JMenu("Graphs");
            Graphs.setForeground(Color.WHITE);

            menuBar.add(FileSettings);
            menuBar.add(Graphs);

            JMenuItem combine = new JMenuItem("Combine data files");
            JMenuItem Cleanup = new JMenuItem("Delete test files");
            combine.addActionListener(AL);
            Cleanup.addActionListener(AL);


            JMenuItem prtvshandposition = new JMenuItem("Perception Reaction Time vs Hand Position");
            JMenuItem MovementTimevsHandPosition = new JMenuItem("Movement Time vs Hand Position");
            JMenuItem PWRVSHANDPOSITION = new JMenuItem("Power vs Hand Position");
            JMenuItem PWRVSPRT = new JMenuItem("Perception Reaction Time vs Power");
            JMenuItem PWRVSMVMT = new JMenuItem("Movement Time vs Power");
            JMenuItem PWRVSHR = new JMenuItem("Power vs Heart Rate");
            JMenuItem PRTVSHR = new JMenuItem("Perception Reaction Time vs Heart Rate");

            prtvshandposition.addActionListener(AL);
            MovementTimevsHandPosition.addActionListener(AL);
            PWRVSHANDPOSITION.addActionListener(AL);
            PWRVSPRT.addActionListener(AL);
            PWRVSMVMT.addActionListener(AL);
            PWRVSHR.addActionListener(AL);
            PRTVSHR.addActionListener(AL);

            Graphs.add(prtvshandposition);
            Graphs.add(MovementTimevsHandPosition);
            Graphs.add(PWRVSHANDPOSITION);
            Graphs.add(PWRVSPRT);
            Graphs.add(PWRVSMVMT);
            Graphs.add(PWRVSHR);
            Graphs.add(PRTVSHR);

            JMenuItem C_Graphs = new JMenu("Combined Data Graphs");
            C_Graphs.setForeground(Color.WHITE);

            JMenuItem C_prtvshandposition = new JMenuItem("Combined Perception Reaction Time vs Hand Position");
            JMenuItem C_MovementTimevsHandPosition = new JMenuItem("Combined Movement Time vs Hand Position");
            JMenuItem C_PWRVSHANDPOSITION = new JMenuItem("Combined Power vs Hand Position");
            JMenuItem C_PWRVSPRT = new JMenuItem("Combined Perception Reaction Time vs Power");
            JMenuItem C_PWRVSMVMT = new JMenuItem("Combined Movement Time vs Power");
            JMenuItem C_PWRVSHR = new JMenuItem("Combined Power vs Heart Rate");
            JMenuItem C_PRTVSHR = new JMenuItem("Combined Perception Reaction Time vs Heart Rate");

            C_prtvshandposition.addActionListener(AL);
            C_MovementTimevsHandPosition.addActionListener(AL);
            C_PWRVSHANDPOSITION.addActionListener(AL);
            C_PWRVSPRT.addActionListener(AL);
            C_PWRVSMVMT.addActionListener(AL);
            C_PWRVSHR.addActionListener(AL);
            C_PRTVSHR.addActionListener(AL);

            C_Graphs.add(C_prtvshandposition);
            C_Graphs.add(C_MovementTimevsHandPosition);
            C_Graphs.add(C_PWRVSHANDPOSITION);
            C_Graphs.add(C_PWRVSPRT);
            C_Graphs.add(C_PWRVSMVMT);
            C_Graphs.add(C_PWRVSHR);
            C_Graphs.add(C_PRTVSHR);

            menuBar.add(C_Graphs);

            FileSettings.add(combine);
            FileSettings.add(Cleanup);

            GUIFrame.setJMenuBar(menuBar);
            Border Margin = new EmptyBorder(10, 50, 5, 50);
            GUIPanel.setBorder(Margin);

            Position = CyclistReactionTime.getPosition(); //////////Begin Changing panel
            TestResults TR = new TestResults();
            List<Double> HR = TR.LoadData(Position + "/HR.txt");
            List<Double> MVMT = TR.LoadData(Position + "/MVMT.txt");
            List<Double> PRT = TR.LoadData(Position + "/PRT.txt");
            List<Double> PWR = TR.LoadData(Position + "/PWR.txt");

            createJLabel("Test Results for Trial: " + Position, JLabel.CENTER, 20, Color.white);

            setData(PRT, "Perception Reaction Time");
            setData(MVMT, "Movement Time");
            setData(HR, "Heart Rate");
            setData(PWR, "Power");

            JPanel BottomofPage = new JPanel();
            BottomofPage.setBackground(Color.darkGray);

            JButton Previous = new JButton("Previous Trial");
            Previous.addActionListener(AL);

            JButton Next = new JButton("Next Trial");
            Next.addActionListener(AL);

            if(Position != 1){
                BottomofPage.add(Previous);
            }
            JButton RtM = new JButton("Return to Menu");
            RtM.addActionListener(AL);
            BottomofPage.add(RtM);

            CombineTrials ct = new CombineTrials();

            if(Position != ct.CountFiles() - 1){
                BottomofPage.add(Next);
            }


            GUIPanel.add(BottomofPage);

            JPanel Results = new JPanel();
            Results.setBackground(Color.DARK_GRAY);
        }
        GUIFrame.add(GUIPanel);
        GUIFrame.revalidate();
        return GUIPanel;
    }
    public void createJLabel(String Text, int Alignment, int FontSize, Color color){
        JLabel var = new JLabel(Text, Alignment);
        var.setForeground(color);
        var.setFont(new Font("Serif", Font.BOLD, FontSize));
        GUIPanel.add(var);
    }

    public void setData(List<Double> L, String Parameter){
        TestResults TR = new TestResults();
        createJLabel(Parameter + ": ", JLabel.LEFT, 16, Color.CYAN);
        createJLabel("Standard Deviation: " + Math.round(TR.CalculateStandardDeviation(L)), JLabel.LEFT, 14, Color.white);
        createJLabel("Variance: " + Math.round(TR.CalculateVariance(L)), JLabel.LEFT, 14, Color.white);
        createJLabel("Average: " + Math.round(TR.CalculateAverage(L)), JLabel.LEFT, 14, Color.white);
    }
}
