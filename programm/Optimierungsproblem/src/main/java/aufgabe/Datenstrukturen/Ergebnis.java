package aufgabe.Datenstrukturen;

import java.util.ArrayList;
import java.util.List;

/**
 * Datenstruktur zur Repräsentation des Ergebnisses der Optimierung.
 * Enthält die berechnete benötigte Länge, die genutzte Fläche, die Positionierung der
 * Aufträge sowie die verbleibenden Punkte.
 */
public class Ergebnis {
    private double benoetigteLaenge;
    private final double genutzteFlaeche;
    private final List<Auftrag> auftraege;
    private final List<Flaeche> positionierung;
    private final List<Punkt> punkte;
    private final String titel;
    private final int rollenbreite;

    /**
     * Konstruktor zur Erstellung eines Ergebnisobjekts.
     * Berechnet die genutzte Fläche, die benötigte Länge und die Positionierung der Aufträge
     * basierend auf den übergebenen Punkten und Aufträgen.
     *
     * @param auftraege        Eine Liste der Aufträge, die im Ergebnis berücksichtigt werden.
     * @param verwendetePunkte Eine Liste der verwendeten Punkte, einschließlich Anker- und Andockpunkten.
     * @param rollenBreite     Die Breite der Materialrolle.
     * @param titel            Der Titel der ursprünglichen Auftragsliste.
     */
    public Ergebnis(List<Auftrag> auftraege, List<Punkt> verwendetePunkte, int rollenBreite, String titel) {
        this.positionierung = new ArrayList<>();
        this.punkte = new ArrayList<>();
        this.auftraege = auftraege;
        this.titel = titel;
        this.rollenbreite = rollenBreite;

        List<Punkt> ankerpunkte = new ArrayList<>();
        for (Punkt punkt : verwendetePunkte) {
            if (!punkt.isAndockpunkt()) {
                ankerpunkte.add(punkt);
            }
            punkte.add(punkt);
        }



        for (Punkt p : ankerpunkte) {
            for (Auftrag a : auftraege) {
                if (p.getBesitzer() == a.getId()) {
                    positionierung.add(new Flaeche(p, a));
                }
            }
        }

        int kumuliertesArealAuftraege = 0;
        for (Flaeche flaeche : positionierung) {
            if (flaeche.getMaxY() > this.benoetigteLaenge) {
                this.benoetigteLaenge = flaeche.getMaxY();
            }
            kumuliertesArealAuftraege += flaeche.getAreal();
        }

        if (rollenBreite > 0 && benoetigteLaenge > 0) {
            this.genutzteFlaeche = (kumuliertesArealAuftraege / (rollenBreite * benoetigteLaenge)) * 100;
        } else {
            this.genutzteFlaeche = 0;
        }
    }

    /**
     * Gibt die benötigte Länge der Materialrolle zurück.
     *
     * @return Die benötigte Länge in der Materialrolle.
     */
    public double getBenoetigteLaenge() {
        return benoetigteLaenge;
    }

    /**
     * Gibt die genutzte Fläche in Prozent zurück.
     *
     * @return Die genutzte Fläche als Prozentsatz.
     */
    public double getGenutzteFlaeche() {
        return genutzteFlaeche;
    }

    /**
     * Gibt die Liste der Positionierungen der Aufträge zurück.
     *
     * @return Eine Liste der Flächen, die die Positionierungen repräsentieren.
     */
    public List<Flaeche> getPositionierung() {
        return positionierung;
    }

    /**
     * Gibt die Liste der Punkte (verwendete und verbleibende) zurück.
     *
     * @return Eine Liste der Punkte.
     */
    public List<Punkt> getPunkte() {
        return punkte;
    }

    /**
     * Gibt die Breite der Materialrolle zurück.
     *
     * @return Die Rollenbreite.
     */
    public int getRollenbreite() {
        return rollenbreite;
    }

    /**
     * Gibt die Liste der Aufträge zurück, die optimiert wurden.
     *
     * @return Eine Liste der Aufträge.
     */
    public List<Auftrag> getAuftraege() {
        return auftraege;
    }

    /**
     * Gibt den Titel der ursprünglichen Auftragsliste zurück.
     *
     * @return Der Titel der ursprünglichen Auftragsliste.
     */
    public String getTitel() {
        return titel;
    }
}
