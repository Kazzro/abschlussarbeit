package aufgabe.Datenstrukturen;

import java.util.ArrayList;
import java.util.List;

/**
 * Datenstruktur zur Verwaltung einer Liste von Aufträgen sowie relevanter Metadaten.
 * Die Auftragsliste enthält Informationen über die Materialbreite, die Optimierungstiefe und
 * den Titel der Liste. Sie wird aus rohen Eingabedaten initialisiert.
 */
public class Auftragsliste {

    String titel;
    int optimierungstiefe;
    int materialbreite;
    List<Auftrag> auftraege;

    /**
     * Konstruktor zur Initialisierung einer Auftragsliste aus einer Liste von rohen Eingabedaten.
     * Die Eingabedaten müssen die Metadaten (Materialbreite und Optimierungstiefe) sowie die
     * Auftragsdetails enthalten.
     *
     * @param rohdaten Eine Liste von Strings, die die rohen Eingabedaten repräsentieren.
     * @throws IllegalArgumentException Falls die Metadatenzeile ungültig ist.
     */
    public Auftragsliste(List<String> rohdaten) {
        auftraege = new ArrayList<>();

        for (String s : rohdaten) {
            if (!s.startsWith("//")) {
                String[] ersteWerte = s.split("\\s+");
                if (ersteWerte.length == 2) {
                    this.materialbreite = Integer.parseInt(ersteWerte[0]);
                    this.optimierungstiefe = Integer.parseInt(ersteWerte[1]);
                } else {
                    throw new IllegalArgumentException("Ungültige Metadatenzeile: " + s);
                }
                break;
            } else {
                this.titel = s;
            }
        }


        for (String s : rohdaten) {
            String[] werte = s.trim().split(",");
            if (werte.length == 4) {
                try {
                    int breite = Integer.parseInt(werte[0].trim());
                    int hoehe = Integer.parseInt(werte[1].trim());
                    int id = Integer.parseInt(werte[2].trim());
                    String beschreibung = werte[3].trim();
                    auftraege.add(new Auftrag(breite, hoehe, id, beschreibung));
                } catch (NumberFormatException e) {
                    System.err.println("Ungültige Auftragszeile: " + s + " (" + e.getMessage() + ")");
                }
            }
        }
    }

    /**
     * Gibt die Optimierungstiefe der Auftragsliste zurück.
     *
     * @return Die Optimierungstiefe.
     */
    public int getOptimierungstiefe() {
        return optimierungstiefe;
    }

    /**
     * Gibt die Materialbreite der Auftragsliste zurück.
     *
     * @return Die Materialbreite.
     */
    public int getMaterialbreite() {
        return materialbreite;
    }

    /**
     * Gibt den Titel der Auftragsliste zurück.
     *
     * @return Der Titel der Liste.
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Gibt die Liste der Aufträge zurück.
     *
     * @return Eine Liste der Aufträge.
     */
    public List<Auftrag> getAuftraege() {
        return auftraege;
    }

}
