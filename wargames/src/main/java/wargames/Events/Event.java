package wargames.Events;

import java.io.IOException;

public interface Event {
    void execute() throws IOException;
}