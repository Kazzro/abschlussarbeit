package aufgabe.Util;

import aufgabe.Datenstrukturen.Auftrag;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility-Klasse zur Generierung aller Permutationen einer Liste von Aufträgen.
 * Berücksichtigt dabei auch die Rotationen der Aufträge.
 */
public class PermutationUtil {

    /**
     * Generiert alle möglichen Permutationen der gegebenen Liste von Aufträgen.
     * Jede Permutation berücksichtigt sowohl die ursprüngliche als auch die gedrehte Orientierung
     * jedes Auftrags.
     *
     * @param auftraege Die Liste der Aufträge, für die Permutationen generiert werden sollen.
     * @return Eine Liste aller möglichen Permutationen. Jede Permutation ist eine Liste von Aufträgen,
     * bei denen jeder Auftrag entweder in seiner Originalorientierung oder rotiert enthalten ist.
     */
    public static List<List<Auftrag>> permutiere(List<Auftrag> auftraege) {
        List<List<Auftrag>> result = new ArrayList<>();
        if (auftraege.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }

        for (int i = 0; i < auftraege.size(); i++) {
            Auftrag current = auftraege.get(i);
            Auftrag currentRotiert = new Auftrag(current.getBreite(), current.getHoehe(), current.getId(), current.getBeschreibung());
            currentRotiert.aendereOrientierung();
            List<Auftrag> remaining = new ArrayList<>(auftraege);
            remaining.remove(i);

            List<List<Auftrag>> subPermutations = permutiere(remaining);

            for (List<Auftrag> subPermutation : subPermutations) {

                List<Auftrag> original = new ArrayList<>(subPermutation);
                original.add(0, current);
                result.add(original);

                List<Auftrag> rotierteListe = new ArrayList<>(subPermutation);
                rotierteListe.add(0, currentRotiert);
                result.add(rotierteListe);
            }
        }
        return result;

    }
}
