package wargames.Events;

import java.io.IOException;
import java.util.List;

import wargames.Entities.Soldier;
import wargames.EventLog.EventLog;

public class DrillEvent implements Event {

    EventLog eventLog = new EventLog();

    String generalName;
    List<Soldier> soldiers;

    public DrillEvent(String generalName, List<Soldier> soldiers) {
        this.generalName = generalName;
        this.soldiers = soldiers;
    }

    @Override
    public void execute() throws IOException {
        String message = "General " + generalName + " commanded a drill of " + soldiers.size() + " soldiers:";
        eventLog.write(message);
        for(Soldier soldier : soldiers) {
            message = "   Soldier " + soldier.getEntityID();
            eventLog.write(message);
        }
        message = "Every soldier taking part in drill gained 1 experience.";
        eventLog.write(message);
    }
}
