package aufgabe.Util;

import aufgabe.Datenstrukturen.Auftrag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PermutationUtilTest {


    @Test
    public void testPermutiereLeereListe() {
        // Test mit einer leeren Liste
        List<Auftrag> auftraege = List.of();
        List<List<Auftrag>> permutations = PermutationUtil.permutiere(auftraege);

        assertEquals(1, permutations.size());
        assertTrue(permutations.get(0).isEmpty());
    }

    @Test
    public void testPermutiereEinzelnenAuftrag() {
        Auftrag auftrag = new Auftrag(100, 200, 1, "Testauftrag");
        List<Auftrag> auftraege = List.of(auftrag);
        List<List<Auftrag>> permutations = PermutationUtil.permutiere(auftraege);

        assertEquals(2, permutations.size());
        assertEquals(100, permutations.get(0).get(0).getBreite());
        assertEquals(200, permutations.get(0).get(0).getHoehe());
        assertEquals(200, permutations.get(1).get(0).getBreite());
        assertEquals(100, permutations.get(1).get(0).getHoehe());
    }

    @Test
    public void testPermutiereMehrereAuftraege() {

        Auftrag auftrag1 = new Auftrag(100, 200, 1, "Auftrag1");
        Auftrag auftrag2 = new Auftrag(300, 400, 2, "Auftrag2");

        List<Auftrag> auftraege = List.of(auftrag1, auftrag2);
        List<List<Auftrag>> permutations = PermutationUtil.permutiere(auftraege);

        // Erwartete Anzahl an Permutationen: 4! / 2 = 8 (wegen der Orientierungen)
        assertEquals(8, permutations.size());

        boolean foundOriginal = false;
        boolean foundRotated = false;

        for (List<Auftrag> perm : permutations) {
            if (perm.get(0).getId() == 1 && perm.get(1).getId() == 2) {
                if (perm.get(0).getBreite() == 100 && perm.get(1).getBreite() == 300) {
                    foundOriginal = true;
                } else if (perm.get(0).getBreite() == 200 && perm.get(1).getBreite() == 400) {
                    foundRotated = true;
                }
            }
        }

        assertTrue(foundOriginal);
        assertTrue(foundRotated);
    }

    @Test
    public void testPermutiereKorrekteOrientierungsaenderung() {
        int originalbreite = 150;
        int originalhoehe = 300;
        Auftrag auftrag = new Auftrag(originalbreite, originalhoehe, 1, "Testauftrag");
        List<Auftrag> auftraege = List.of(auftrag);

        List<List<Auftrag>> permutations = PermutationUtil.permutiere(auftraege);

        Auftrag original = permutations.get(0).get(0);
        Auftrag rotated = permutations.get(1).get(0);

        assertEquals(originalbreite, original.getBreite());
        assertEquals(originalhoehe, original.getHoehe());
        assertEquals(originalhoehe, rotated.getBreite());
        assertEquals(originalbreite, rotated.getHoehe());
    }

}