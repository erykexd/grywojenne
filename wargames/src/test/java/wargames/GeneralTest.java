package wargames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import wargames.Entities.General;
import wargames.Entities.Soldier;

public class GeneralTest {
    @Test
    public void testGetGold() {
        General general = new General("Test General", 100);
        assertEquals(100, general.getGold(), 0.01);
    }

    @Test
    public void testGetArmy() {
        General general = new General("Test General", 100);
        assertNotNull(general.getArmy());
    }

    @Test
    public void testGetArmyStrength() {
        General general = new General("Test General", 100);
        Soldier soldier1 = new Soldier(1);
        Soldier soldier2 = new Soldier(2);
        general.addToAmry(soldier1);
        general.addToAmry(soldier2);
        assertEquals(3, general.getArmyStrength());
    }

    @Test
    public void testSetName() {
        General general = new General("Test General", 100);
        assertEquals("Test General", general.getName());
    }

    @Test
    public void testSetGold() {
        General general = new General("Test General", 100);
        general.setGold(150);
        assertEquals(150, general.getGold(), 0.01);
    }

    @Test
    public void testAddToArmy() {
        General general = new General("Test General", 100);
        Soldier soldier = new Soldier(1);
        general.addToAmry(soldier);
        assertTrue(general.getArmy().contains(soldier));
    }

    @Test
    public void testRemoveSoldier() {
        General general = new General("Test General", 100);
        Soldier soldier = new Soldier(1);
        general.addToAmry(soldier);
        general.removeSoldier(0);
        assertFalse(general.getArmy().contains(soldier));
    }

    @Test
    public void testBuySoldier() {
        General general = new General("Test General", 100);
        general.buySoldier(1);
        assertEquals(90, general.getGold(), 0.01);
        assertEquals(1, general.getArmy().size());
    }

    @Test
    public void testCommandDrill() {
        General general = new General("Test General", 100);
        Soldier soldier1 = new Soldier(1);
        Soldier soldier2 = new Soldier(2);
        general.addToAmry(soldier1);
        general.addToAmry(soldier2);
        general.commandDrill(1);
        assertEquals(2, soldier1.getExperience());
        assertEquals(1, soldier2.getExperience());
        assertEquals(99, general.getGold(), 0.01);
    }
}
