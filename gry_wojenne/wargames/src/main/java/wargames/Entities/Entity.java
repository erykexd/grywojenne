package wargames.Entities;

import wargames.Utilities.*;
public class Entity {
    protected String entityID;

    public void setEntityID(String entityType) {
        RandomIntGenerator rng = new RandomIntGenerator();
        switch (entityType) {
            case "General":
                String newGeneralID = "G" + rng.generateRandomInt();
                this.entityID = newGeneralID;
                break;
            case "Soldier":
                String newSoldierID = "S" + rng.generateRandomInt();
                this.entityID = newSoldierID;
                break;
            default:
                break;
        }
    }

    public String getEntityID() {
        return this.entityID;
    }

    public void ChangeEntityID(String entityID) {
        this.entityID = entityID;
    }
}
