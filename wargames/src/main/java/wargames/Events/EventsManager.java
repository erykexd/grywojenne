package wargames.Events;

import java.io.IOException;

public class EventsManager {
    public void processEvent(Event event) {
        try {
            event.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
