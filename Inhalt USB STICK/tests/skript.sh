#!/bin/bash

# Name der JAR-Datei
JAR_FILE="Optimierungsproblem.jar"

if [[ ! -f "$JAR_FILE" ]]; then
  JAR_FILE="../Optimierungsproblem.jar"
fi

# Prüfen, ob die JAR-Datei existiert
if [[ ! -f "$JAR_FILE" ]]; then
  echo "Fehler: JAR-Datei '$JAR_FILE' nicht gefunden!"
  exit 1
fi

# Verzeichnis für Ergebnisse
RESULT_DIR="results"
mkdir -p "$RESULT_DIR"

# Alle .in-Dateien im aktuellen Verzeichnis verarbeiten
for INPUT_FILE in *.in; do
  TEST_NAME="${INPUT_FILE%.in}"
  echo "Führe Test '${TEST_NAME}' aus..."
  
  # Test ausführen und den Ergebnisordner übergeben
  java -jar "$JAR_FILE" "$TEST_NAME" "$RESULT_DIR"

  # Überprüfen und Verschieben der .out-Datei, falls sie noch im aktuellen Verzeichnis ist
  if [[ -f "${TEST_NAME}.out" ]]; then
    mv "${TEST_NAME}.out" "$RESULT_DIR/${TEST_NAME}.out"
  fi

  # Überprüfen und Verschieben der .gnu-Datei, falls sie noch im aktuellen Verzeichnis ist
  if [[ -f "${TEST_NAME}.gnu" ]]; then
    mv "${TEST_NAME}.gnu" "$RESULT_DIR/${TEST_NAME}.gnu"
  fi
done

echo "Alle Tests abgeschlossen. Ergebnisse im Ordner '$RESULT_DIR'."