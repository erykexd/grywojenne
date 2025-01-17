package wargames.EventLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class EventLog {
    public void write(String message) throws IOException {
        Files.write(Paths.get("EventLog.txt"), (message + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
