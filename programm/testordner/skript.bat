@echo off

:: Name der JAR-Datei
set JAR_FILE=Optimierungsproblem-1.0.jar

:: Prüfen, ob die JAR-Datei existiert
if not exist "%JAR_FILE%" (
    echo Fehler: JAR-Datei "%JAR_FILE%" nicht gefunden!
    exit /b 1
)

:: Testdateien
set TEST_FILES=test1 test2 test3 test4

:: Verzeichnis für Ergebnisse
set RESULT_DIR=results
if not exist "%RESULT_DIR%" mkdir "%RESULT_DIR%"

:: Tests ausführen
:: Alle .in-Dateien im aktuellen Verzeichnis verarbeiten
for %%F in (*.in) do (
    set TEST_NAME=%%~nF
    echo Fuehre Test "%%~nF" aus...
    java -jar "%JAR_FILE%" %%~nF "%RESULT_DIR%

    :: Verschieben der .out-Datei, falls vorhanden
    if exist %%~nF.out move %%~nF.out "%RESULT_DIR%\%%~nF.out"

    :: Verschieben der .gnu-Datei, falls vorhanden
    if exist %%~nF.gnu move %%~nF.gnu "%RESULT_DIR%\%%~nF.gnu"
)

echo Alle Tests abgeschlossen. Ergebnisse im Ordner "%RESULT_DIR%".