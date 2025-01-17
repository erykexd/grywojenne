package wargames;

import java.io.IOException;

import wargames.Entities.General;
import wargames.Entities.Soldier;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        General general1 = new General("Patryk", 1000);
        general1.buySoldier(1);
        general1.buySoldier(3);
        general1.buySoldier(1);
        general1.buySoldier(2);

        general1.commandDrill(4);

        General general2 = new General("Janusz", 1000);
        general2.buySoldier(1);
        general2.buySoldier(3);
        general2.buySoldier(1);
        general2.buySoldier(2);

        general2.commandDrill(4);

        general1.attack(general2);

        System.out.println(" ");
        System.out.println("Army of " + general1.getEntityID() + ":");
        for(Soldier soldier : general1.getArmy()) {
            String id = soldier.getEntityID();
            String rank = new String();
            switch (soldier.getRank()) {
                case 1:
                    rank = "Private";
                    break;
                case 2:
                    rank = "Corporal";
                    break;
                case 3:
                    rank = "Captain";
                    break;
                case 4:
                    rank = "Major";
                default:
                    break;
            }
            int experience = soldier.getExperience();
            
            System.out.println(rank + " " + id + " experience: " + experience);
        }

        general1.update();
        general2.update();
    }
}
