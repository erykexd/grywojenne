package wargames.Events;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import wargames.Entities.Soldier;

public class RememberSoldierEvent implements Event{
    
    private Soldier soldier;
    private String generalName;

    LocalDateTime currDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    String formattedDateTime = currDateTime.format(formatter);

    public RememberSoldierEvent(Soldier soldier, String generalName) {
        this.soldier = soldier;
        this.generalName = generalName;
    }

    @Override
    public void execute() throws IOException {
        String rank = "";
        switch (soldier.getRank()) {
            case 1:
                rank = "Private";
                break;
            case 2:
                rank = "Corporal";
                break;
            case 3:
                rank = "Major";
                break;
            case 4:
                rank = "Captain";
                break;
            default:
                break;
        }
        String message = rank + " " + soldier.getEntityID() + " soldier of general " + generalName + " died on " + currDateTime;
        Files.write(Paths.get("deadSoldiers.txt"), (message + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
