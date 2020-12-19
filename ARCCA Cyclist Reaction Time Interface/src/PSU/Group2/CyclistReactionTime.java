package PSU.Group2;

import PSU.Group2.Util.GUIHandler;
import PSU.Group2.Util.RunSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CyclistReactionTime {
    private static GUIHandler guiHandler;
    private static List<RunSettings> PreSet;
    private static int Position;

    public static void main(String[] args) throws IOException {
        guiHandler = new GUIHandler();
        Position = 0;
        PreSet = new ArrayList<>();
        guiHandler.Navigation("MainMenu");
    }

    public static void setGui(GUIHandler guiHandler) { CyclistReactionTime.guiHandler = guiHandler; }
    public static GUIHandler getGui() { return CyclistReactionTime.guiHandler; }
    public static void setPreSet(List<RunSettings> PreSet) { CyclistReactionTime.PreSet = PreSet; }
    public static List<RunSettings> getPreSet() { return CyclistReactionTime.PreSet; }
    public static void setPosition(int position) { CyclistReactionTime.Position = position; }
    public static int getPosition() { return CyclistReactionTime.Position; }

}
