reset
set term png size 900,7604
set output 'Beispiel5-verlaengert-tiefe-maximal.png'
set xrange [0:900]
set yrange [0:7504]
set size ratio -1

set title "\
// Auftrag IHK3 verlängert maximale tiefe\n\
Benötigte Länge: 750.4cm\n\
Genutzte Fläche: 93.01%"

set style fill transparent solid 0.5 border
set key noautotitle

$data <<EOD
# x_LU y_LU x_RO y_RO Auftragsbeschreibung ID
0 0 297 210 "Auftrag B" 2
297 0 594 420 "Auftrag C" 3
594 0 894 880 "Auftrag D" 4
0 210 100 310 "Auftrag A" 1
0 310 297 520 "Auftrag E" 5
100 210 200 310 "Auftrag F" 6
297 420 594 840 "Auftrag H" 8
0 520 210 817 "Auftrag G" 7
0 880 880 1180 "Auftrag I" 9
0 1180 297 1390 "Auftrag J" 10
297 1180 507 1477 "Auftrag L" 12
507 1180 804 1600 "Auftrag M" 13
0 1390 297 1600 "Auftrag O" 15
297 1477 397 1577 "Auftrag K" 11
0 1600 880 1900 "Auftrag N" 14
397 1477 497 1577 "Auftrag P" 16
0 1900 297 2110 "Auftrag Q" 17
297 1900 597 2780 "Auftrag S" 19
597 1900 894 2110 "Auftrag T" 20
0 2110 297 2530 "Auftrag R" 18
597 2110 897 2990 "Auftrag X" 24
0 2530 100 2630 "Auftrag U" 21
0 2630 297 3050 "Auftrag W" 23
297 2780 594 2990 "Auftrag V" 22
0 3050 297 3260 "Auftrag Y" 25
297 3050 397 3150 "Auftrag Z" 26
397 3050 694 3260 "Auftrag AA" 27
0 3260 420 3557 "Auftrag AB" 28
0 3557 880 3857 "Auftrag AC" 29
420 3260 630 3557 "Auftrag AD" 30
694 3050 794 3150 "Auftrag AE" 31
630 3260 840 3557 "Auftrag AF" 32
0 3857 297 4277 "Auftrag AG" 33
297 3857 597 4737 "Auftrag AH" 34
597 3857 894 4067 "Auftrag AI" 35
597 4067 897 4947 "Auftrag AM" 39
0 4277 100 4377 "Auftrag AJ" 36
0 4377 297 4587 "Auftrag AK" 37
0 4587 297 5007 "Auftrag AL" 38
297 4737 594 4947 "Auftrag AN" 40
0 5007 297 5217 "Auftrag AP" 42
297 5007 594 5427 "Auftrag AQ" 43
594 5007 894 5887 "Auftrag AR" 44
0 5217 100 5317 "Auftrag AO" 41
0 5317 297 5527 "Auftrag AS" 45
100 5217 200 5317 "Auftrag AT" 46
297 5427 594 5847 "Auftrag AV" 48
0 5527 210 5824 "Auftrag AU" 47
0 5887 880 6187 "Auftrag AW" 49
0 6187 297 6397 "Auftrag AX" 50
297 6187 507 6484 "Auftrag AZ" 52
507 6187 804 6607 "Auftrag BA" 53
0 6397 297 6607 "Auftrag BC" 55
297 6484 397 6584 "Auftrag AY" 51
0 6607 880 6907 "Auftrag BB" 54
397 6484 497 6584 "Auftrag BD" 56
0 6907 210 7204 "Auftrag BE" 57
210 6907 630 7204 "Auftrag BF" 58
0 7204 880 7504 "Auftrag BG" 59
630 6907 840 7204 "Auftrag BH" 60
EOD

$anchor <<EOD
# x y
894 0
200 210
100 310
297 310
594 420
297 840
594 880
210 520
0 817
880 880
804 1180
297 1390
297 1577
507 1600
880 1600
0 1900
497 1477
397 1577
894 1900
297 2110
897 2110
100 2530
297 2630
597 2990
0 3050
594 2780
297 2990
794 3050
297 3150
694 3150
397 3260
420 3557
880 3557
0 3857
840 3260
630 3557
894 3857
897 4067
100 4277
297 4377
297 4587
597 4947
0 5007
594 4737
297 4947
894 5007
200 5217
100 5317
297 5317
594 5427
297 5847
594 5887
210 5527
0 5824
880 5887
804 6187
297 6397
297 6584
507 6607
880 6607
0 6907
497 6484
397 6584
210 7204
880 7204
0 7504
840 6907
630 7204
EOD

plot \
'$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):(($3-$1)/2):(($4-$2)/2):6 \
              with boxxy linecolor var, \
'$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):5 \
              with labels font "arial,9", \
'$anchor' using 1:2 with circles lc rgb "red", \
'$data' using 1:2 with points lw 8 lc rgb "dark-green"
