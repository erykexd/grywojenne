package wargames.Events;

import java.io.IOException;

import wargames.EventLog.EventLog;

public class PromotionEvent implements Event{
    
    EventLog eventLog = new EventLog();

    String soldierID;
    int rank;

    public PromotionEvent(String soldierID, int rank) {
        this.soldierID = soldierID;
        this.rank = rank;
    }

    @Override
    public void execute() throws IOException {
        String message = "Soldier " + soldierID + " promoted from rank " + rank + " to rank " + (rank+1) + ".";
        eventLog.write(message);
    }
}