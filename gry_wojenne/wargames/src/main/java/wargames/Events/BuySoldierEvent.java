package wargames.Events;

import java.io.IOException;

import wargames.EventLog.EventLog;

public class BuySoldierEvent implements Event {

    EventLog eventLog = new EventLog();

    private String generalName;
    private String soldierID;
    private double soldierCost;

    public BuySoldierEvent(String generalName, String soldierID, double soldierCost) {
        this.generalName = generalName;
        this.soldierID = soldierID;
        this.soldierCost = soldierCost;
    }

    @Override
    public void execute() throws IOException{
        String message = "General " + generalName + " bought soldier " + soldierID + " for " + soldierCost + " gold.";
        eventLog.write(message);
    }

}
