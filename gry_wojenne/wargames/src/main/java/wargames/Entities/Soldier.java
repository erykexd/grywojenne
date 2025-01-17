package wargames.Entities;

import wargames.Events.*;

public class Soldier extends Entity implements Comparable<Soldier> {
    
    private int rank;
    private int experience;
    private int strength;
    private Boolean isAlive;

    EventsManager eventsManager = new EventsManager();

    public Soldier(int rank) {

        setEntityID("Soldier");
        this.rank = rank;
        this.experience = 1;
        this.strength = this.rank * this.experience;
        this.isAlive = true;
    }

    public int getRank() {
        return this.rank;
    }
    public int getExperience() {
        return this.experience;
    }
    public int getStrength() {
        return this.strength;
    }
    public Boolean getStatus() {
        return this.isAlive;
    }
    
    public void setExperience(int experience) {
        this.experience = experience;
        if(this.rank < 4) {
          if(this.experience >= 5*this.rank) {
            Event onPromotionEvent = new PromotionEvent(this.entityID, this.rank);
            eventsManager.processEvent(onPromotionEvent);
            this.rank += 1;
            }
        }
        this.strength = this.rank*this.experience;
        if(this.experience <= 0) {
            this.isAlive = false;
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int compareTo(Soldier otherSoldier) {
        int rankComparison = Integer.compare(this.rank, otherSoldier.rank);
        if( rankComparison == 0) {
            return Integer.compare(this.experience, otherSoldier.experience);
        }
        return rankComparison;
    }
}
