package aufgabe.Datenstrukturen;

/**
 * Datenstruktur zur Repräsentation eines Auftrags.
 * Ein Auftrag beschreibt ein Rechteck mit einer Breite, Höhe, einer eindeutigen ID und einer Beschreibung.
 */
public class Auftrag {

    int breite;
    int hoehe;
    int id;
    String beschreibung;

    /**
     * Konstruktor zur Initialisierung eines Auftrags.
     *
     * @param breite       Die Breite des Auftrags.
     * @param hoehe        Die Höhe des Auftrags.
     * @param id           Die eindeutige ID des Auftrags.
     * @param beschreibung Eine Beschreibung des Auftrags.
     */
    public Auftrag(int breite, int hoehe, int id, String beschreibung) {
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
        this.beschreibung = beschreibung;
    }

    /**
     * Ändert die Orientierung des Auftrags, indem Breite und Höhe getauscht werden.
     */
    public void aendereOrientierung() {
        int temp = getBreite();
        setBreite(getHoehe());
        setHoehe(temp);
    }

    /**
     * Gibt die Breite des Auftrags zurück.
     *
     * @return Die Breite des Auftrags.
     */
    public int getBreite() {
        return breite;
    }

    /**
     * Setzt die Breite des Auftrags.
     *
     * @param breite Die neue Breite des Auftrags.
     */
    public void setBreite(int breite) {
        this.breite = breite;
    }

    /**
     * Gibt die Höhe des Auftrags zurück.
     *
     * @return Die Höhe des Auftrags.
     */
    public int getHoehe() {
        return hoehe;
    }

    /**
     * Setzt die Höhe des Auftrags.
     *
     * @param hoehe Die neue Höhe des Auftrags.
     */
    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    /**
     * Gibt die eindeutige ID des Auftrags zurück.
     *
     * @return Die ID des Auftrags.
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt die Beschreibung des Auftrags zurück.
     *
     * @return Die Beschreibung des Auftrags.
     */
    public String getBeschreibung() {
        return beschreibung;
    }

}
