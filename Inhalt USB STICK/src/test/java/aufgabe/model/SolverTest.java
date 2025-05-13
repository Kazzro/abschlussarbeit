package aufgabe.model;

import aufgabe.Datenstrukturen.Ergebnis;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    public void testOptimiere() {
        List<String> rohdaten = List.of(
                "// Testtitel",
                "1000 2",
                "200,300,1,Testauftrag1",
                "150,200,2,Testauftrag2"
        );

        Solver solver = new Solver(rohdaten);
        Ergebnis ergebnis = null;
        try {
            ergebnis = solver.optimiere();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(ergebnis);
        assertEquals("// Testtitel", ergebnis.getTitel());
        assertTrue(ergebnis.getBenoetigteLaenge() > 0);
        assertTrue(ergebnis.getGenutzteFlaeche() > 0);
    }

    @Test
    public void testOptimiereKeineLoesung() {
        List<String> rohdaten = List.of(
                "// Testtitel",
                "500 2",
                "600,600,1,Testauftrag1",
                "600,600,2,Testauftrag2"
        );

        Solver solver = new Solver(rohdaten);
        Ergebnis ergebnis = null;
        try {
            ergebnis = solver.optimiere();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(ergebnis);
        assertEquals("// Testtitel", ergebnis.getTitel());
        assertEquals(0,ergebnis.getBenoetigteLaenge());
    }

}