package wargames.Events;

import java.io.IOException;

import wargames.EventLog.EventLog;

public class RemoveSoldierEvent implements Event{

    EventLog eventLog = new EventLog();

    private String generalName;
    private String soldierID;

    public RemoveSoldierEvent(String generalName, String soldierID) {
        this.generalName = generalName;
        this.soldierID = soldierID;
    }

    @Override
    public void execute() throws IOException {
        String message = "Soldier " + soldierID + " has been removed from general "  + generalName + "'s army.";
        eventLog.write(message);
    }
}
