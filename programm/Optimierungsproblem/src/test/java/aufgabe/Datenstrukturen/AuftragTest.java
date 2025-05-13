package aufgabe.Datenstrukturen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuftragTest {

    @Test
    public void testKonstruktorUndGetter() {
        Auftrag auftrag = new Auftrag(100, 200, 1, "Testauftrag");

        assertEquals(100, auftrag.getBreite());
        assertEquals(200, auftrag.getHoehe());
        assertEquals(1, auftrag.getId());
        assertEquals("Testauftrag", auftrag.getBeschreibung());
    }

    @Test
    public void testAendereOrientierung() {
        Auftrag auftrag = new Auftrag(100, 200, 1, "Testauftrag");

        auftrag.aendereOrientierung();

        assertEquals(200, auftrag.getBreite());
        assertEquals(100, auftrag.getHoehe());
    }

}