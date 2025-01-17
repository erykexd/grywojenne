package wargames.Entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import wargames.Events.*;
import wargames.Observers.Observable;
import wargames.Observers.Observer;

public class General extends Entity implements Observer,Observable{
    
    EventsManager eventsManager = new EventsManager();

    private String name;
    private double gold;
    private List<Soldier> army = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public General(String name, double gold) {
        setEntityID("General");
        this.name = name;
        this.gold = gold;
    }

    public double getGold() {
        return this.gold;
    }

    public List<Soldier> getArmy() {
        return this.army;
    }

    public int getArmyStrength() {
        int armyStrength = 0;
        for(Soldier soldier : this.army) {
            armyStrength += soldier.getStrength();
        }
        return armyStrength;
    }

    public String getName() {
        return this.name;
    }

    public void setGold(double gold) {
        Event onChangeGoldEvent = new ChangeGoldEvent(this.name, this.gold, gold);
        eventsManager.processEvent(onChangeGoldEvent);
        this.gold = gold;
    }

    public void addToAmry(Soldier soldier) {
        this.army.add(soldier);
        Event onAddToArmyEvent = new AddToArmyEvent(soldier.getEntityID(), this.name);
        eventsManager.processEvent(onAddToArmyEvent);
    }

    public void removeSoldier(int index) {
        Soldier soldier =  army.get(index);
        this.army.remove(index);
        
        Event onRemoveSoldierEvent = new RemoveSoldierEvent(this.name, soldier.getEntityID());
        eventsManager.processEvent(onRemoveSoldierEvent);

        Event rememberSoldierEvent = new RememberSoldierEvent(soldier, this.name);
        eventsManager.processEvent(rememberSoldierEvent);
    }

    public void buySoldier(int rank) {
        if(gold >= 10*rank) {
            Soldier soldier = new Soldier(rank);

            Event onBuySoldierEvent = new BuySoldierEvent(this.name, soldier.getEntityID(), 10*rank);
            eventsManager.processEvent(onBuySoldierEvent);
            
            this.army.add(soldier);
            this.setGold(this.gold - 10*rank);
        }
        else {
            System.out.println("Not enough gold.");
        }
        notifyObservers();
    }

    public void commandDrill(int soldierNumber) {
        Collections.sort(army);
        int cost = 0;
        for(int i=0; i<soldierNumber; i++) {
            Soldier soldier = army.get(i);
            cost += soldier.getRank();
        }
        if(this.gold >= cost) {
            List<Soldier> soldiers = new ArrayList<>();
            for(int i=0; i<soldierNumber; i++) {
                Soldier soldier = army.get(i);
                soldiers.add(soldier);
                int soldierEXP = soldier.getExperience();
                soldier.setExperience(soldierEXP+1);
            
            }
            
            Event onDrillEvent = new DrillEvent(this.name, soldiers);
            eventsManager.processEvent(onDrillEvent);

            this.setGold(this.gold-cost);
        }
        notifyObservers();
    }

    public void attack(General enemyGeneral) {
        int armyStrength = this.getArmyStrength();
        int enemyArmyStrength = enemyGeneral.getArmyStrength();
        int result = 0;
        if(armyStrength > enemyArmyStrength) {
            result = 1;
            double enemyGold = enemyGeneral.getGold();
            double spoils = enemyGold*0.1;
            enemyGeneral.setGold(enemyGold - spoils);
            this.setGold(this.gold + spoils);

            for(Soldier enemySoldier : enemyGeneral.getArmy()) {
                int enemySoldierExp = enemySoldier.getExperience();
                enemySoldier.setExperience(enemySoldierExp - 1);
            }

            for(Soldier soldier : this.army) {
                int soldierExp = soldier.getExperience();
                soldier.setExperience(soldierExp + 1);
            }
            Event onAttackEvent = new AttackEvent(this.name, enemyGeneral.getName(), result, spoils);
            eventsManager.processEvent(onAttackEvent);
        }
        else if(armyStrength == enemyArmyStrength) {
            Random rand = new Random();
            int max = this.army.size()-1;
            int min = 0;
            int randomIndex = rand.nextInt((max-min) +1) + min;

            List<Soldier> enemyArmy = enemyGeneral.getArmy();
            int enemyMax = enemyArmy.size()-1;
            randomIndex = rand.nextInt((enemyMax - min) + 1) + min;

            Event onAttackEvent = new AttackEvent(this.name, enemyGeneral.getName(), result, 0);
            eventsManager.processEvent(onAttackEvent);
            
            this.removeSoldier(randomIndex);
            enemyGeneral.removeSoldier(randomIndex);
        }
        else {
            result = 2;
            double enemyGold = enemyGeneral.getGold();
            double spoils = this.gold*0.1;    
            enemyGeneral.setGold(enemyGold + spoils);
            this.setGold(this.gold - spoils);

            for(Soldier enemySoldier : enemyGeneral.getArmy()) {
                int enemySoldierExp = enemySoldier.getExperience();
                enemySoldier.setExperience(enemySoldierExp + 1);
            }

            for(Soldier soldier : this.army) {
                int soldierExp = soldier.getExperience();
                soldier.setExperience(soldierExp - 1);
            }

            Event onAtackEvent = new AttackEvent(this.name, enemyGeneral.getName(), result, spoils);
            eventsManager.processEvent(onAtackEvent);
        }
        notifyObservers();
    }

    public void saveArmy() throws IOException {
        for(Soldier soldier : army) {
            String soldierInfo = soldier.getEntityID() + " " + soldier.getRank() + " " + soldier.getExperience();
            String path = this.name + "_army.txt";
            Files.write(Paths.get(path), (soldierInfo + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    public void loadArmy() throws IOException{
        String fileName = this.name + "_army.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                if(tokens.length == 3) {
                    Soldier soldier = new Soldier(1);
                    soldier.ChangeEntityID(tokens[0]);
                    soldier.setRank(Integer.parseInt(tokens[1]));
                    soldier.setExperience(Integer.parseInt(tokens[2]));
                    this.addToAmry(soldier);
                }
            }
        }
    }

    @Override
    public void update() {
        int strength = getArmyStrength();
        List<Soldier> army = getArmy();

        System.out.println("Total army strength: " + strength);
        System.out.println("Soldiers: ");
        for(Soldier soldier : army) {
            System.out.println("rank: " + soldier.getRank() + " ID: " + soldier.getEntityID() + " Experience: " + soldier.getExperience());
        }
        System.out.println("--------------------------");

    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update();
        }
    }
}
