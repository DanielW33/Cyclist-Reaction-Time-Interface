package PSU.Group2.ActionListeners.SubPanelListeners;


import PSU.Group2.Util.RunSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FlashesPerTrialListener implements ActionListener, KeyListener {
    private String Flashes = "1";
    private JFrame FlashesPerTrial;
    private List<JPanel> Panels;
    private RunSettings settings;

    public FlashesPerTrialListener(JFrame frame, List<JPanel> Panels, RunSettings CurrentSettings){
        this.FlashesPerTrial = frame;
        this.Panels = Panels;
        this.settings = CurrentSettings;
    }

    @Override
    public void actionPerformed(ActionEvent Event) {
        String Command = Event.getActionCommand();
        if(Command.equalsIgnoreCase("Set Flashes")){
            JPanel current = (JPanel) ((JButton)Event.getSource()).getParent();

            int loc = Panels.indexOf(current);

            if(loc < Panels.size() - 1){
                loc += 1;
                FlashesPerTrial.remove(current);
                FlashesPerTrial.add(Panels.get(loc));
                FlashesPerTrial.repaint();
                FlashesPerTrial.revalidate();
                settings.setNumberofFlashes(Flashes);
            }
            else if (loc == Panels.size() - 1){
                FlashesPerTrial.dispose();
                settings.setNumberofFlashes(Flashes);
            }

        }
    }


    @Override
    public void keyReleased(KeyEvent Event) {
        JTextField TextField = (JTextField) Event.getSource();
        String Var = TextField.getText();

        if (TextField.getName().equalsIgnoreCase("NumberofFlashes") && Var.length() < 8) {
            if (!Var.equals("")) {
                if (CheckforChar(Var)) {
                    int Tests = Integer.parseInt(Var);
                    Flashes = Integer.toString(Tests);
                }
            }
        }
    }

    public boolean CheckforChar(String Var) {

        for (char c : Var.toCharArray()) {
            if (Character.isLetter(c) || c == ' ') {
                return false;
            }
        }
        if (Var.charAt(0) == '0') {
            return false;
        }
        return true;
    }

    public String getFlashes(){
        return Flashes;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
