package wargames.Events;

import java.io.IOException;

import wargames.EventLog.EventLog;

public class AttackEvent implements Event{
    
    EventLog eventlog = new EventLog();

    String general_1_name;
    String general_2_name;
    int result;
    double spoils;

    public AttackEvent(String general_1_name, String general_2_name, int result, double spoils) {
        this.general_1_name = general_1_name;
        this.general_2_name = general_2_name;
        this.result = result;
        this.spoils = spoils;
    }

    @Override
    public void execute() throws IOException {
        String message = "General " + general_1_name + " attacked general " + general_2_name + ":";
        eventlog.write(message);

        switch (result) {
            case 1:
                message = "General " + general_1_name + " won.";
                eventlog.write(message);
                message = "General " + general_1_name + "gained " + spoils + " gold from general " + general_2_name + ".";
                eventlog.write(message);
                message = "Every General " + general_1_name + " soldier gained 1 experience.";
                eventlog.write(message);
                message = "Every General " + general_1_name + " soldier lost 1 experience.";
                eventlog.write(message);
                break;
            case 0:
                message = "Draw.";
                eventlog.write(message);
                message = "Every general removed one of his soldiers. ";
                eventlog.write(message);
                break;
            case 2:
                message = "General " + general_2_name + " won.";
                eventlog.write(message);
                message = "General " + general_2_name + "gained " + spoils + " gold from general " + general_1_name + ".";
                eventlog.write(message);
                message = "Every General " + general_2_name + " soldier gained 1 experience.";
                eventlog.write(message);
                message = "Every General " + general_2_name + " soldier lost 1 experience.";
                eventlog.write(message);
                break;
            default:
                break;
        }
    }
}
