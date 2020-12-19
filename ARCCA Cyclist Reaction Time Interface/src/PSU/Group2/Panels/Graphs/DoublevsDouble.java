package PSU.Group2.Panels.Graphs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DoublevsDouble extends JPanel {
    private List<Double> x;
    private List<Double> y;
    private int PaddingTop = 30, PaddingLeft = 40, PaddingBottom = 50, PaddingRight = 30;
    private Color lineColor = Color.LIGHT_GRAY;

    int xScale, yScale, xNumWeight, yNumWeight, xStart, yStart, xRange, yRange;

    public DoublevsDouble(List<Double> x, List<Double> y){
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g){

        //Drawing outer rectangle.
        g.drawRect(PaddingLeft, PaddingTop, getWidth() - PaddingLeft - PaddingRight, getHeight() - PaddingBottom - PaddingTop);

        xScale = (getWidth() - PaddingRight - PaddingLeft) / (x.size() * 2);
        yScale = (getHeight() - PaddingTop - PaddingBottom) / (y.size() * 2);

        xRange = (int) Math.round(getMax(x) - getMin(x));
        yRange = (int) Math.round(getMax(y) - getMin(y));

        xNumWeight = xRange / (x.size() * 2);
        yNumWeight = yRange / (y.size() * 2);

        if(xNumWeight <= 1)
            xNumWeight = 2;
        if(yNumWeight <= 1)
            yNumWeight = 2;

        DrawXLines(g);
        DrawYLines(g);
        DrawPoints(g);
    }

    public void DrawPoints(Graphics g){
        g.setColor(Color.BLUE);
        int xpos;
        int ypos;

        //x and y must have the same number of vars.
        for(int i = 0; i < x.size(); i++){
            xpos = PaddingLeft + (int) Math.round(((x.get(i) - getMin(x)) / xNumWeight) * xScale)  - 5;
            ypos = getHeight() - PaddingBottom - ((int) Math.round((((y.get(i) - getMin(y)) / yNumWeight)) * yScale))  -5;

            g.fillOval(xpos,ypos,10,10);
        }
    }
    //Draw vertical lines and add numbers on bottom
    public void DrawXLines(Graphics g){
        int xPixels;
        xStart = (int) Math.round((getMin(x)));

        for(int i = 0; i < x.size() * 2+ 1; i++){
            xPixels = PaddingLeft + i * xScale;
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(xStart), xPixels, getHeight() - PaddingBottom / 2);
            xStart += xNumWeight;
            g.setColor(lineColor);
            g.drawLine(xPixels, getHeight() - PaddingBottom, xPixels, getHeight() - (getHeight() - PaddingTop));
        }
    }
    //Draw horizontal lines and add numbers on left.
    public void DrawYLines(Graphics g){
        int yPixels;
        yStart = (int) Math.round((getMin(y)));

        for(int i = 0; i < y.size() * 2 + 1; i++){
            yPixels = getHeight() - PaddingBottom - i * yScale;
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(yStart), 1, yPixels);
            yStart += yNumWeight;
            g.setColor(lineColor);
            g.drawLine(PaddingLeft, yPixels,getWidth() - PaddingRight, yPixels);
        }
    }
  //returns the largest double
    public double getMax(List<Double> L){
        double max = L.get(0);
        for(Double var : L){
            if(var > max){
                max = var;
            }
        }
        return max;
    }
    //returns the smallest double
    public double getMin(List<Double> L){
        double min = L.get(0);
        for(Double var : L){
            if(var < min){
                min = var;
            }
        }
        return min;
    }
    public void CreateGUI(String Label){
        DoublevsDouble graph = new DoublevsDouble(x,y);
        JFrame frame = new JFrame(Label);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(graph);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
