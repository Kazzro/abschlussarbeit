package aufgabe.view;

import aufgabe.Datenstrukturen.Ergebnis;
import aufgabe.Datenstrukturen.Flaeche;
import aufgabe.Datenstrukturen.Punkt;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility-Klasse zur Verwaltung von Ein- und Ausgabeoperationen für die Optimierungsergebnisse.
 * Diese Klasse enthält Methoden zum Lesen von Eingabedaten aus Dateien sowie zum Schreiben
 * von Ergebnis- und Skriptdateien.
 */
public class DataManager {

    /**
     * Liest die Eingabedaten aus einer Datei.
     *
     * @param dateipfad Der Pfad zur Eingabedatei (ohne die Erweiterung ".in").
     * @return Eine Liste von Strings, die die rohen Eingabedaten enthalten.
     * @throws IOException Falls ein Fehler beim Lesen der Datei auftritt.
     */
    public static List<String> leseDaten(String dateipfad) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dateipfad + ".in"))) {
            List<String> rohdaten = new ArrayList<>();
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                if (!zeile.trim().isEmpty()) {
                    rohdaten.add(zeile.trim());
                }
            }

            return rohdaten;
        }
    }

    /**
     * Schreibt die Optimierungsergebnisse in Ausgabedateien.
     * Erstellt sowohl eine `.out`-Datei mit den Ergebnissen als auch eine `.gnu`-Datei
     * für die Darstellung mit gnuplot.
     *
     * @param ergebnis  Das Ergebnis, das geschrieben werden soll.
     * @param dateiname Der Basisname der Ausgabedateien (ohne Erweiterung).
     * @return {@code true}, wenn beide Dateien erfolgreich geschrieben wurden, sonst {@code false}.
     */
    public static boolean schreibeDaten(Ergebnis ergebnis, String dateiname) {
        return schreibeOutDatei(ergebnis, dateiname) && schreibeSkriptDatei(ergebnis, dateiname);
    }

    /**
     * Schreibt die Ergebnisse in eine `.out`-Datei.
     *
     * @param ergebnis  Das Ergebnisobjekt, das die Positionierungen und verbleibenden Punkte enthält.
     * @param dateiname Der Basisname der Ausgabedatei (ohne Erweiterung).
     * @return {@code true}, wenn die Datei erfolgreich geschrieben wurde, sonst {@code false}.
     */
    private static boolean schreibeOutDatei(Ergebnis ergebnis, String dateiname) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dateiname + ".out"))) {
            List<Flaeche> positionierungen = ergebnis.getPositionierung();
            List<Punkt> verbleibendePunkte = ergebnis.getPunkte().stream().filter(Punkt::isAndockpunkt).toList();
            double hoehe = ergebnis.getBenoetigteLaenge() / 10;

            writer.println(ergebnis.getTitel());
            writer.println("Benötigte Länge: " + hoehe + " cm");
            BigDecimal areal = BigDecimal.valueOf(ergebnis.getGenutzteFlaeche()).setScale(2, RoundingMode.HALF_UP);
            writer.println("Benutzte Flaeche: " + areal + " %");
            writer.println();
            writer.println("Positionierung der Kundenaufträge:");
            for (Flaeche flaeche : positionierungen) {
                writer.println("    " + flaeche);
            }

            writer.println();
            writer.println("Verbleibende Andockpunkte:");
            for (Punkt p : verbleibendePunkte) {
                writer.println("    " + p);
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Ausgabe: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Schreibt die Ergebnisse in eine `.gnu`-Datei für die Darstellung mit gnuplot.
     *
     * @param ergebnis  Das Ergebnisobjekt, das die Positionierungen und verbleibenden Punkte enthält.
     * @param dateipfad Der Basisname der Ausgabedatei (ohne Erweiterung).
     * @return {@code true}, wenn die Datei erfolgreich geschrieben wurde, sonst {@code false}.
     */
    private static boolean schreibeSkriptDatei(Ergebnis ergebnis, String dateipfad) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dateipfad + ".gnu"))) {
            String dateiname = Arrays.stream(dateipfad.split("/")).toList().getLast();

            List<Flaeche> positionierungen = ergebnis.getPositionierung();
            List<Punkt> verbleibendePunkte = ergebnis.getPunkte().stream().filter(Punkt::isAndockpunkt).toList();
            double hoeheInCm = ergebnis.getBenoetigteLaenge() / 10;
            int hoehe = (int) ergebnis.getBenoetigteLaenge();
            int breite = ergebnis.getRollenbreite();
            int fensterhoehe = (int) ergebnis.getBenoetigteLaenge() + 100;
            BigDecimal areal = BigDecimal.valueOf(ergebnis.getGenutzteFlaeche()).setScale(2, RoundingMode.HALF_UP);
            String titel = ergebnis.getTitel();

            writer.printf("reset\n" +
                    "set term png size " + breite + "," + fensterhoehe + "\n" +
                    "set output '" + dateiname + ".png'\n" +
                    "set xrange [0:" + breite + "]\n" +
                    "set yrange [0:" + hoehe + "]\n" +
                    "set size ratio -1\n\n");
            writer.printf("set title \"\\\n" + titel + "\\n\\\n" +
                    "Benötigte Länge: " + hoeheInCm + "cm\\n\\\n" +
                    "Genutzte Fläche: " + areal + "%%\"\n\n");
            writer.printf("""
                    set style fill transparent solid 0.5 border
                    set key noautotitle

                    $data <<EOD
                    # x_LU y_LU x_RO y_RO Auftragsbeschreibung ID
                    """);

            for (Flaeche f : positionierungen) {
                String title = f.getAuftrag().getBeschreibung();
                int id = f.getAuftrag().getId();

                writer.println(f.getPosX() + " " + f.getPosY() + " " + f.getMaxX() + " " + f.getMaxY() + " \"" + title + "\" " + id);
            }
            writer.println("EOD\n");
            writer.printf("$anchor <<EOD\n# x y\n");
            for (Punkt p : verbleibendePunkte) {
                writer.println(p.getPosX() + " " + p.getPosY());
            }

            writer.printf("""
                    EOD

                    plot \\
                    '$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):(($3-$1)/2):(($4-$2)/2):6 \\
                                  with boxxy linecolor var, \\
                    '$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):5 \\
                                  with labels font "arial,9", \\
                    '$anchor' using 1:2 with circles lc rgb "red", \\
                    '$data' using 1:2 with points lw 8 lc rgb "dark-green"
                    """);

        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben des Skripts: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void schreibeFehlerMeldung(String meldung,String dateiname){
        try (PrintWriter writer = new PrintWriter(new FileWriter(dateiname + ".log"))){
            writer.printf(meldung);
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben des Skripts: " + e.getMessage());
        }

    }

}
