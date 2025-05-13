package aufgabe.Datenstrukturen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlaecheTest {

    @Test
    public void testKonstruktorUndGetter() {
        Punkt punkt = new Punkt(10, 20, 1);
        Auftrag auftrag = new Auftrag(100, 50, 1, "Testauftrag");

        Flaeche flaeche = new Flaeche(punkt, auftrag);

        assertEquals(10, flaeche.getPosX());
        assertEquals(20, flaeche.getPosY());
        assertEquals(100, flaeche.getBreite());
        assertEquals(50, flaeche.getHoehe());
        assertEquals(1, flaeche.getAuftrag().getId());
    }

    @Test
    public void testUeberlapptMit() {
        Auftrag auftrag1 = new Auftrag(50, 50, 1, "Auftrag1");
        Auftrag auftrag2 = new Auftrag(50, 50, 2, "Auftrag2");

        Flaeche flaeche1 = new Flaeche(new Punkt(0, 0, 1), auftrag1);
        Flaeche flaeche2 = new Flaeche(new Punkt(30, 30, 2), auftrag2);

        assertTrue(flaeche1.ueberlapptMit(flaeche2));

        Flaeche flaeche3 = new Flaeche(new Punkt(100, 100, 3), auftrag2);

        assertFalse(flaeche1.ueberlapptMit(flaeche3));
    }

}