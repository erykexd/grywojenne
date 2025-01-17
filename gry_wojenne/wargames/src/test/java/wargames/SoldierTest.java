package wargames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import wargames.Entities.Soldier;

public class SoldierTest {
    @Test
    public void testSoldierInitialization() {
        Soldier soldier = new Soldier(1);

        assertEquals(1, soldier.getRank());
        assertEquals(1, soldier.getExperience());
        assertEquals(1, soldier.getStrength());
        assertTrue(soldier.getStatus());
    }

    @Test
    public void testSetExperience() {
        Soldier soldier = new Soldier(2);
        
        soldier.setExperience(10);

        assertEquals(10, soldier.getExperience());
        assertEquals(30, soldier.getStrength());
        assertTrue(soldier.getStatus());
    }

    @Test
    public void testPromotionEvent() {
        Soldier soldier = new Soldier(3);

        soldier.setExperience(15);

        assertEquals(4, soldier.getRank());
        assertEquals(60, soldier.getStrength());
        assertTrue(soldier.getStatus());
    }

    @Test
    public void testDeathEvent() {
        Soldier soldier = new Soldier(4);

        soldier.setExperience(0);

        assertEquals(4, soldier.getRank());
        assertEquals(0, soldier.getStrength());
        assertFalse(soldier.getStatus());
    }

    @Test
    public void testSoldierComparison() {
        Soldier soldier1 = new Soldier(2);
        Soldier soldier2 = new Soldier(3);

        soldier1.setExperience(10);
        soldier2.setExperience(15);

        assertTrue(soldier1.compareTo(soldier2) < 0);
    }
}
