package wargames.Events;

import java.io.IOException;

import wargames.EventLog.EventLog;

public class ChangeGoldEvent implements Event{

    EventLog eventLog = new EventLog();

    private String generalName;
    private double goldBefore;
    private double goldAfter;

    public ChangeGoldEvent(String generalName, double goldBefore, double goldAfter) {
        this.generalName = generalName;
        this.goldBefore = goldBefore;
        this.goldAfter = goldAfter;
    }

    @Override
    public void execute() throws IOException {
        String message = "General " + generalName + "'s gold has changed from " + goldBefore + " by " + (goldBefore-goldAfter) + " to " + goldAfter + ".";
        eventLog.write(message);
    }

}
