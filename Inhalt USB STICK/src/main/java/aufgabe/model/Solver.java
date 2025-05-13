package aufgabe.model;

import aufgabe.Datenstrukturen.*;
import aufgabe.Util.PermutationUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Die Klasse Solver implementiert den Algorithmus zur Optimierung von Aufträgen
 * auf einer begrenzten Materialrolle. Die Berechnungen werden parallelisiert,
 * um die Performance bei großen Eingabemengen zu verbessern.
 */
public class Solver {
    Auftragsliste auftragsliste;

    /**
     * Konstruktor zur Initialisierung des Solvers mit den Rohdaten.
     * Die Rohdaten werden in eine `Auftragsliste` umgewandelt.
     *
     * @param rohdaten Eine Liste von Strings, die die Eingabedaten repräsentieren.
     */
    public Solver(List<String> rohdaten) {
        this.auftragsliste = new Auftragsliste(rohdaten);
    }

    /**
     * Führt den Optimierungsalgorithmus aus und gibt das beste Ergebnis zurück.
     * Die Berechnung für die Teillisten erfolgt parallel, um die Performance zu steigern.
     *
     * @return Das kombinierte Ergebnis der Optimierung oder {@code null} bei einem Fehler.
     */
    public Ergebnis optimiere() throws Exception {
        if (auftragsliste.getOptimierungstiefe() > 8 && auftragsliste.getAuftraege().size() > 8) {
            throw new Exception("Optimierungstiefe zu groß, maximal 8");
        }
        if(auftragsliste.getAuftraege().isEmpty()){
            throw new Exception("keine Aufträge zum optimieren in der Eingabedatei");
        }
        if(auftragsliste.getOptimierungstiefe()<= 0){
            throw new Exception("Optimierungstiefe kann nicht kleiner 1 sein");
        }

        List<List<Auftrag>> teillisten = teileInTeillisten(auftragsliste.getAuftraege(), auftragsliste.getOptimierungstiefe());
        List<Future<Ergebnis>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            List<Ergebnis> teilergebnisse = new ArrayList<>();

            for (List<Auftrag> liste : teillisten) {
                futures.add(executor.submit(() -> berechneBestesErgebnis(liste)));
            }

            for (Future<Ergebnis> future : futures) {
                Ergebnis ergebnis = future.get();
                if (ergebnis != null) {
                    teilergebnisse.add(ergebnis);
                }
            }

            return kombiniereErgebnisse(teilergebnisse);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Erstellt ein Ergebnis basierend auf einer gegebenen Permutation von Aufträgen.
     * Platziert die Aufträge auf einer Materialrolle, falls möglich.
     *
     * @param aktuellePermutation Eine Permutation von Aufträgen, die optimiert werden soll.
     * @return Ein `Ergebnis`, falls alle Aufträge platziert werden konnten; sonst `null`.
     */
    private Ergebnis erstelleErgebnis(List<Auftrag> aktuellePermutation) {
        List<Punkt> verfuegbarePunkte = new ArrayList<>();
        verfuegbarePunkte.add(new Punkt(0, 0, 0));
        List<Flaeche> gesetzteFlaechen = new ArrayList<>();
        List<Punkt> ankerpunkte = new ArrayList<>();

        for (Auftrag aktuellerAuftrag : aktuellePermutation) {

            if (aktuellerAuftrag.getBreite() > auftragsliste.getMaterialbreite()) {
                return null;
            }

            Collections.sort(verfuegbarePunkte);

            boolean platziert = false;
            for (Punkt punkt : verfuegbarePunkte) {
                if (isAuftragValide(aktuellerAuftrag, gesetzteFlaechen, punkt)) {
                    punkt.setAndockpunkt(false);
                    punkt.setBesitzer(aktuellerAuftrag.getId());
                    verfuegbarePunkte.add(new Punkt(punkt.getPosX() + aktuellerAuftrag.getBreite(), punkt.getPosY(), aktuellerAuftrag.getId()));
                    verfuegbarePunkte.add(new Punkt(punkt.getPosX(), punkt.getPosY() + aktuellerAuftrag.getHoehe(), aktuellerAuftrag.getId()));
                    gesetzteFlaechen.add(new Flaeche(punkt, aktuellerAuftrag));
                    ankerpunkte.add(punkt);
                    verfuegbarePunkte.remove(punkt);
                    platziert = true;
                    break;
                }
            }
            if (!platziert) {
                return null;
            }

        }
        if (ankerpunkte.size() < aktuellePermutation.size()) {
            return null;
        }

        ArrayList<Punkt> allePunkte = new ArrayList<>();
        allePunkte.addAll(ankerpunkte);
        allePunkte.addAll(verfuegbarePunkte);

        return new Ergebnis(aktuellePermutation, allePunkte, this.auftragsliste.getMaterialbreite(), this.auftragsliste.getTitel());

    }

    /**
     * Überprüft, ob ein Auftrag an einer bestimmten Position valide platziert werden kann.
     *
     * @param auftrag       Der zu platzierende Auftrag.
     * @param flaechenliste Eine Liste der bereits platzierten Flächen.
     * @param andockpunkt   Der Punkt, an dem der Auftrag platziert werden soll.
     * @return {@code true}, wenn der Auftrag valide platziert werden kann; sonst {@code false}.
     */
    private boolean isAuftragValide(Auftrag auftrag, List<Flaeche> flaechenliste, Punkt andockpunkt) {
        if (!andockpunkt.isAndockpunkt() || andockpunkt.getPosX() + auftrag.getBreite() > auftragsliste.getMaterialbreite()) {
            return false;
        }

        Flaeche potentielleFlaeche = new Flaeche(andockpunkt, auftrag);
        for (Flaeche f : flaechenliste) {
            if (potentielleFlaeche.ueberlapptMit(f)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Teilt die Liste der Aufträge in Teillisten entsprechend der Optimierungstiefe.
     *
     * @param auftraege         Die ursprüngliche Liste der Aufträge.
     * @param optimierungstiefe Die maximale Anzahl von Aufträgen pro Teilliste.
     * @return Eine Liste von Teillisten mit Aufträgen.
     */
    private List<List<Auftrag>> teileInTeillisten(List<Auftrag> auftraege, int optimierungstiefe) {
        List<List<Auftrag>> teillisten = new ArrayList<>();

        for (int i = 0; i < auftraege.size(); i += optimierungstiefe) {
            List<Auftrag> teilliste = auftraege.subList(i, Math.min(i + optimierungstiefe, auftraege.size()));
            teillisten.add(new ArrayList<>(teilliste));
        }

        return teillisten;
    }

    /**
     * Berechnet das beste Ergebnis für eine gegebene Liste von Aufträgen.
     *
     * @param liste Die Liste der Aufträge, die optimiert werden sollen.
     * @return Das beste Ergebnis oder {@code null}, wenn keine gültige Anordnung gefunden wurde.
     */
    private Ergebnis berechneBestesErgebnis(List<Auftrag> liste) {
        Ergebnis bestesErgebnis = null;
        double besteHoehe = Double.MAX_VALUE;
        List<List<Auftrag>> permutationsliste = PermutationUtil.permutiere(liste);

        for (List<Auftrag> aktuellePermutation : permutationsliste) {
            Ergebnis tempErgebnis = erstelleErgebnis(aktuellePermutation);
            if (tempErgebnis == null) {
                continue;
            }

            if (tempErgebnis.getBenoetigteLaenge() < besteHoehe) {
                besteHoehe = tempErgebnis.getBenoetigteLaenge();
                bestesErgebnis = tempErgebnis;
            }
        }

        return bestesErgebnis;
    }

    private Ergebnis kombiniereErgebnisse(List<Ergebnis> teilErgebnisse) {
        int hoehe = 0;
        List<Auftrag> gesamtAuftraege = new ArrayList<>();
        List<Punkt> gesamtPunkte = new ArrayList<>();
        for (Ergebnis e : teilErgebnisse) {
            if (e == null) {
                continue;
            }

            gesamtAuftraege.addAll(e.getAuftraege());

            for (Punkt p : e.getPunkte()) {
                p.setPosY(p.getPosY() + hoehe);
                gesamtPunkte.add(p);
            }

            hoehe += (int) e.getBenoetigteLaenge();

        }

        return new Ergebnis(gesamtAuftraege, gesamtPunkte, auftragsliste.getMaterialbreite(), auftragsliste.getTitel());
    }

    public Auftragsliste getAuftragsliste() {
        return auftragsliste;
    }
}
