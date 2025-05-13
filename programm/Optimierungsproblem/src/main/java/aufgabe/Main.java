package aufgabe;

import aufgabe.Datenstrukturen.Ergebnis;
import aufgabe.model.Solver;
import aufgabe.view.DataManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> daten = DataManager.leseDaten(args[0]);

        if(daten.isEmpty()){
            DataManager.schreibeFehlerMeldung("leere Eingabedatei",args[0]);
        }

        Solver solver = new Solver(daten);


        try {
            Ergebnis loesung = null;
            long start = System.nanoTime();
            loesung = solver.optimiere();
            long ende = System.nanoTime();
            long dauer = ende - start;
            double sekunden = (double) dauer / 1_000_000_000;
            System.out.println("Dauer der Berechnung in sekunden: " + sekunden);
            if(loesung!= null){
                if (loesung.getPositionierung().isEmpty() && !solver.getAuftragsliste().getAuftraege().isEmpty()) {
                    schreibeFehler(args,"Keine Lösung möglich für die gegebene Auftragsliste");
                } else {
                    schreibeDaten(args,loesung);
                }
            }

        } catch (Exception e){
            schreibeFehler(args, e.getMessage());
        }
    }

    private static void schreibeFehler(String[] args, String e){
        if (args[1] != null) {
            String dateiname = Arrays.stream(args[0].split("/")).toList().getLast();
            DataManager.schreibeFehlerMeldung(e,args[1]+"/"+dateiname);
        } else
            DataManager.schreibeFehlerMeldung(e,args[0]);

    }

    private static void schreibeDaten(String[] args, Ergebnis loesung){
        if (args[1] != null) {
            String dateiname = Arrays.stream(args[0].split("/")).toList().getLast();
            DataManager.schreibeDaten(loesung, args[1] + "/" + dateiname);
        } else
            DataManager.schreibeDaten(loesung, args[0]);
    }
}
