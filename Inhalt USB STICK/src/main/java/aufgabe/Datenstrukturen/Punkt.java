package aufgabe.Datenstrukturen;

/**
 * Datenstruktur zur Repräsentation eines Punktes im Koordinatensystem.
 * Ein Punkt hat eine X- und Y-Position und kann entweder ein Andockpunkt oder ein Ankerpunkt sein.
 * Zusätzlich wird der Punkt einem Besitzer (einem Auftrag) zugeordnet.
 */
public class Punkt implements Comparable<Punkt> {
    private final int posX;
    private int posY;

    private boolean andockpunkt;
    private int besitzer;

    /**
     * Konstruktor zur Initialisierung eines Punktes.
     *
     * @param posX       Die X-Position des Punktes.
     * @param posY       Die Y-Position des Punktes.
     * @param besitzerId Die ID des Besitzers des Punktes.
     */
    public Punkt(int posX, int posY, int besitzerId) {
        this.posX = posX;
        this.posY = posY;
        this.besitzer = besitzerId;
        this.andockpunkt = true;
    }

    /**
     * Gibt die X-Position des Punktes zurück.
     *
     * @return Die X-Position.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Gibt die Y-Position des Punktes zurück.
     *
     * @return Die Y-Position.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Setzt die Y-Position des Punktes.
     *
     * @param posY Die neue Y-Position.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Setzt die ID des Besitzers des Punktes.
     *
     * @param besitzer Die neue Besitzer-ID.
     */
    public void setBesitzer(int besitzer) {
        this.besitzer = besitzer;
    }

    /**
     * Gibt die ID des Besitzers des Punktes zurück.
     *
     * @return Die Besitzer-ID.
     */
    public int getBesitzer() {
        return besitzer;
    }

    /**
     * Gibt zurück, ob der Punkt ein Andockpunkt ist.
     *
     * @return {@code true}, wenn der Punkt ein Andockpunkt ist, sonst {@code false}.
     */
    public boolean isAndockpunkt() {
        return andockpunkt;
    }

    /**
     * Setzt den Status, ob der Punkt ein Andockpunkt ist.
     *
     * @param andockpunkt Der neue Status des Punktes.
     */
    public void setAndockpunkt(boolean andockpunkt) {
        this.andockpunkt = andockpunkt;
    }

    /**
     * Gibt eine String-Darstellung des Punktes im Ausgabedatei Format zurück.
     * Das Format ist "X Y".
     *
     * @return Eine String-Darstellung des Punktes.
     */
    @Override
    public String toString() {
        return posX + " " + posY;
    }

    /**
     * Vergleicht diesen Punkt mit einem anderen Punkt basierend auf der Y-Position
     * und, falls gleich, der X-Position.
     *
     * @param other Der andere Punkt, der verglichen wird.
     * @return Ein negativer Wert, wenn dieser Punkt kleiner ist, {@code 0}, wenn sie gleich sind,
     * oder ein positiver Wert, wenn dieser Punkt größer ist.
     */
    @Override
    public int compareTo(Punkt other) {
        int result = Integer.compare(this.posY, other.getPosY());
        if (result == 0) {
            result = Integer.compare(this.posX, other.getPosX());
        }
        return result;
    }
}
