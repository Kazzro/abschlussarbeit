package aufgabe.Datenstrukturen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PunktTest {

    @Test
    public void testKonstruktorUndGetter() {
        Punkt punkt = new Punkt(10, 20, 1);

        assertEquals(10, punkt.getPosX());
        assertEquals(20, punkt.getPosY());
        assertTrue(punkt.isAndockpunkt());
        assertEquals(1, punkt.getBesitzer());
    }

    @Test
    public void testCompareTo() {
        Punkt punkt1 = new Punkt(10, 20, 1);
        Punkt punkt2 = new Punkt(15, 20, 2);
        Punkt punkt3 = new Punkt(10, 25, 3);

        assertTrue(punkt1.compareTo(punkt2) < 0);
        assertTrue(punkt3.compareTo(punkt1) > 0);
    }
}