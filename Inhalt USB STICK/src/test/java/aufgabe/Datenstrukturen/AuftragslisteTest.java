package aufgabe.Datenstrukturen;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuftragslisteTest {

    @Test
    public void testAuftragslisteKonstruktor() {
        List<String> rohdaten = List.of(
                "// Testtitel",
                "1000 3",
                "200,300,1,Testauftrag1",
                "150,200,2,Testauftrag2"
        );

        Auftragsliste auftragsliste = new Auftragsliste(rohdaten);

        assertEquals("// Testtitel", auftragsliste.getTitel());
        assertEquals(1000, auftragsliste.getMaterialbreite());
        assertEquals(3, auftragsliste.getOptimierungstiefe());
        assertEquals(2, auftragsliste.getAuftraege().size());
    }

}