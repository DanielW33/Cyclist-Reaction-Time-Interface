package PSU.Group2.Panels.Graphs;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class ValuevsString extends JPanel {
    //Visual Variables.
    private int PaddingLeft = 30, PaddingTop = 15, PaddingBottom = 40, PaddingRight = 30;
    private Color lineColor = Color.LIGHT_GRAY;

    //Data Variables.
    int NumWeight;
    private List<Point> y;
    int xScale;
    int yScale;
    //This only takes in 1 list because the x axis is words associated.
    public ValuevsString(List<Point> y){
        this.y = y;
    }


    @Override
    protected void paintComponent(Graphics g){

        //Drawing Outer Edge:
        g.drawRect(PaddingLeft,PaddingTop,getWidth() - PaddingRight - PaddingLeft,getHeight() - PaddingBottom - PaddingTop);

        setLines(g);
        PlotPoints(g);
    }

    public void PlotPoints(Graphics g){
        xScale = ((getWidth() - PaddingRight - PaddingLeft));
        yScale = ((getHeight() - PaddingTop - PaddingBottom)/(y.size()*2));

        g.setColor(Color.BLUE);

        y.forEach( point ->{
            String str = point.getString();
            int x;

            int BottomofGraph = getHeight() - PaddingBottom;
            int pixelsperSection = (getHeight() - PaddingBottom - PaddingTop) / ((y.size() * 2));
            int WeightperSection =  (int) Math.round((getMax(y) - getMin(y)) / ((y.size() * 2)));

            int para = (int) Math.round((((point.getValue() - getMin(y))/ WeightperSection) * pixelsperSection));

            System.out.println("Para: " + para);
            int ycoord = BottomofGraph - para - 5;



            if(str.equalsIgnoreCase("Hoods")){
                 x = PaddingLeft + (xScale / 4) - 5;    //-5 has been applied because that is half of the radius of the circles, making them centered.
            }
            else if(str.equalsIgnoreCase("Drops")){
                x = PaddingLeft + 2 * (xScale / 4) - 5;
            }
            else{
                x = PaddingLeft + 3 * (xScale/4) - 5;
            }
            g.fillOval(x,ycoord,10,10);
        });
    }

    //Drawing vertical and horizontal lines in their appropriate positions.
    public void setLines(Graphics g){
        yScale = ((getHeight() - PaddingBottom - PaddingTop)/((y.size() * 2))); //Half as many pixels for more accuracy.

        NumWeight = (int) Math.round( (getMax(y) - getMin(y)) / ((y.size() * 2)));
        System.out.println("NumWeight: " + NumWeight);
        int PreviousHeight = (int) Math.round(getMin(y));
        //Draw y

        for(int i = 0; i < (y.size() * 2 + 1); i++){
            int y = getHeight() - PaddingBottom - i * yScale;
            int x = PaddingLeft / 4;
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(PreviousHeight), 0, y);
            PreviousHeight = PreviousHeight + NumWeight;
            g.setColor(lineColor);
            g.drawLine(x,y, getWidth() - PaddingRight, y );
        }

        //Draw x
        int xScale = ((getWidth() - PaddingRight - PaddingLeft));

        for(int i = 1; i <= 3; i++){
            int x = PaddingLeft + i * (xScale / 4);
            g.setColor(Color.BLACK);
            if(i == 1){
                g.drawString("Hoods", x, getHeight() - PaddingTop + 5);
            }
            else if(i == 2){
                g.drawString("Drops", x, getHeight() - PaddingTop + 5);

            }
            else{
                g.drawString("Tops", x, getHeight() - PaddingTop + 5);

            }
            g.setColor(lineColor);
            g.drawLine(x, getHeight() - PaddingTop, x, PaddingTop);
        }
    }
    //gets max value in list
    public double getMax(List<Point> L){
        double max = 0;
        for(Point val : L){
            if(val.getValue() > max){
                max = val.getValue();
            }
        }
        return max;
    }
    //gets min value in list.
    public double getMin(List <Point> L){
        double min = L.get(0).getValue();
        for(Point val : L){
            if(val.getValue() < min){
                min = val.getValue();
            }
        }
        return min;
    }

    public void CreateGUI(String label){

        ValuevsString graph = new ValuevsString(y);
        JFrame frame = new JFrame(label);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(graph);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
