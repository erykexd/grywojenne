package wargames.Events;

import java.io.IOException;

import wargames.EventLog.EventLog;

public class AddToArmyEvent implements Event{
    
    EventLog eventLog = new EventLog();

    String soldierID;
    String generalName;

    public AddToArmyEvent(String soldierID, String generalName) {
        this.soldierID = soldierID;
        this.generalName = generalName;
    }


    @Override
    public void execute() throws IOException {
        String message = "Soldier " + this.soldierID + " has been added to general " + this.generalName + "'s army.";
        eventLog.write(message);
    }
}


