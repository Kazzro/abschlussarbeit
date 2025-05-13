package aufgabe.Datenstrukturen;

/**
 * Datenstruktur zur Repräsentation einer Fläche, die einen Auftrag an einer bestimmten Position beschreibt.
 * Eine Fläche hat eine Position (x, y), eine Breite und eine Höhe, die von einem Auftrag abgeleitet werden.
 */
public class Flaeche {
    private final int posX;
    private final int posY;
    private final Auftrag auftrag;

    /**
     * Konstruktor zur Initialisierung einer Fläche basierend auf einem Punkt und einem Auftrag.
     *
     * @param punkt   Der Punkt, der die Position der Fläche definiert.
     * @param auftrag Der Auftrag, der die Dimensionen und andere Eigenschaften der Fläche liefert.
     */
    public Flaeche(Punkt punkt, Auftrag auftrag) {
        this.auftrag = auftrag;
        this.posX = punkt.getPosX();
        this.posY = punkt.getPosY();
    }

    /**
     * Gibt die X-Position der Fläche zurück.
     *
     * @return Die X-Position der Fläche.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Gibt die Y-Position der Fläche zurück.
     *
     * @return Die Y-Position der Fläche.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Gibt die Breite der Fläche zurück.
     *
     * @return Die Breite der Fläche.
     */
    public int getBreite() {
        return auftrag.getBreite();
    }

    /**
     * Gibt den zugehörigen Auftrag der Fläche zurück.
     *
     * @return Der Auftrag der Fläche.
     */
    public Auftrag getAuftrag() {
        return this.auftrag;
    }

    /**
     * Gibt die Höhe der Fläche zurück.
     *
     * @return Die Höhe der Fläche.
     */
    public int getHoehe() {
        return auftrag.getHoehe();
    }

    /**
     * Gibt den maximalen Y-Wert der Fläche zurück (Y-Position + Höhe).
     *
     * @return Der maximale Y-Wert der Fläche.
     */
    public int getMaxY() {
        return posY + auftrag.getHoehe();
    }

    /**
     * Gibt den maximalen X-Wert der Fläche zurück (X-Position + Breite).
     *
     * @return Der maximale X-Wert der Fläche.
     */
    public int getMaxX() {
        return posX + auftrag.getBreite();
    }

    /**
     * Berechnet und gibt die Fläche (Areal) der Fläche zurück.
     *
     * @return Die Fläche (Breite * Höhe).
     */
    public int getAreal() {
        return auftrag.breite * auftrag.getHoehe();
    }

    /**
     * Überprüft, ob diese Fläche mit einer anderen Fläche überlappt.
     *
     * @param andere Die Fläche, mit der die Überlappung geprüft werden soll.
     * @return {@code true}, wenn die Flächen überlappen, sonst {@code false}.
     */
    public boolean ueberlapptMit(Flaeche andere) {
        int ax1 = this.getPosX();
        int ay1 = this.getPosY();
        int ax2 = ax1 + this.getBreite();
        int ay2 = ay1 + this.getHoehe();


        int bx1 = andere.getPosX();
        int by1 = andere.getPosY();
        int bx2 = bx1 + andere.getBreite();
        int by2 = by1 + andere.getHoehe();

        // Überlappungslogik
        return ax1 < bx2 && ax2 > bx1 && ay1 < by2 && ay2 > by1;
    }

    /**
     * Gibt eine String-Darstellung der Fläche im Format der Ausgabedatei zurück.
     * Das Format enthält die Position (x, y), die Dimensionen (Breite und Höhe), die ID und Beschreibung des Auftrags.
     *
     * @return Eine String-Darstellung der Fläche für die Ausgabedatei.
     */
    @Override
    public String toString() {
        return posX + " " + posY + " " + auftrag.getBreite() + " " + auftrag.getHoehe()
                + " - " + auftrag.getId() + " - " + auftrag.getBeschreibung();
    }

}
