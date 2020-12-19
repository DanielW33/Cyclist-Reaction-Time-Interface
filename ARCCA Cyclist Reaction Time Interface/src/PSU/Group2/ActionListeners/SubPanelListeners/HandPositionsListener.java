package PSU.Group2.ActionListeners.SubPanelListeners;

import PSU.Group2.Util.RunSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;



public class HandPositionsListener implements ActionListener {
    private String HandPosition = "0";
    private JFrame HandPositionsPerTrial;
    private List<JPanel> Panels;
    private RunSettings settings;

    public HandPositionsListener(JFrame frame, List<JPanel> Panels, RunSettings CurrentSettings) {
        this.HandPositionsPerTrial = frame;
        this.Panels = Panels;
        this.settings = CurrentSettings;
    }

    @Override
    public void actionPerformed(ActionEvent Event) {
        String Command = Event.getActionCommand();
        if (Command.equalsIgnoreCase("Set Hand Position")) {
            JPanel current = (JPanel) ((JButton) Event.getSource()).getParent();

            int loc = Panels.indexOf(current);

            if (loc < Panels.size() - 1) {
                loc += 1;
                HandPositionsPerTrial.remove(current);
                HandPositionsPerTrial.add(Panels.get(loc));
                HandPositionsPerTrial.repaint();
                HandPositionsPerTrial.revalidate();
                settings.setHandPositions(HandPosition);
            }
            else if (loc == Panels.size() - 1) {
                HandPositionsPerTrial.dispose();
                settings.setHandPositions(HandPosition);
            }

        }
        else if (Event.getActionCommand().equalsIgnoreCase("comboBoxChanged")) {
            JComboBox ComboBox = (JComboBox) Event.getSource();
            HandPosition = getHandPosition(ComboBox.getSelectedItem().toString());
        }
    }

    public String getHandPosition(String HandPosition) { //Positions "Tops", "Hoods", "Drops"
        switch (HandPosition) {
            case "Tops":
                return "0";
            case "Hoods":
                return "1";
            default: //Drops
                return "2";
        }
    }
}
