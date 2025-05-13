reset
set term png size 900,7868
set output 'Beispiel4-verlaengert.png'
set xrange [0:900]
set yrange [0:7768]
set size ratio -1

set title "\
// Auftrag IHK3 verlängert\n\
Benötigte Länge: 776.8cm\n\
Genutzte Fläche: 89.85%"

set style fill transparent solid 0.5 border
set key noautotitle

$data <<EOD
# x_LU y_LU x_RO y_RO Auftragsbeschreibung ID
0 0 100 100 "Auftrag A" 1
0 100 880 400 "Auftrag D" 4
0 400 210 697 "Auftrag B" 2
210 400 630 697 "Auftrag C" 3
630 400 840 697 "Auftrag E" 5
100 0 200 100 "Auftrag F" 6
0 697 297 907 "Auftrag G" 7
297 697 594 1117 "Auftrag H" 8
594 697 804 994 "Auftrag J" 10
0 907 297 1117 "Auftrag L" 12
0 1117 880 1417 "Auftrag I" 9
594 994 694 1094 "Auftrag K" 11
0 1417 420 1714 "Auftrag M" 13
0 1714 880 2014 "Auftrag N" 14
420 1417 840 1714 "Auftrag R" 18
0 2014 297 2224 "Auftrag O" 15
297 2014 397 2114 "Auftrag P" 16
397 2014 694 2224 "Auftrag Q" 17
0 2224 300 3104 "Auftrag S" 19
300 2224 597 2434 "Auftrag T" 20
597 2224 897 3104 "Auftrag X" 24
300 2434 400 2534 "Auftrag U" 21
300 2534 597 2744 "Auftrag V" 22
300 2744 597 3164 "Auftrag W" 23
0 3164 297 3374 "Auftrag Y" 25
297 3164 507 3461 "Auftrag AA" 27
507 3164 804 3584 "Auftrag AB" 28
0 3374 297 3584 "Auftrag AD" 30
297 3461 397 3561 "Auftrag Z" 26
0 3584 880 3884 "Auftrag AC" 29
0 3884 100 3984 "Auftrag AE" 31
0 3984 880 4284 "Auftrag AH" 34
0 4284 210 4581 "Auftrag AF" 32
210 4284 630 4581 "Auftrag AG" 33
630 4284 840 4581 "Auftrag AI" 35
100 3884 200 3984 "Auftrag AJ" 36
0 4581 297 4791 "Auftrag AK" 37
297 4581 594 5001 "Auftrag AL" 38
594 4581 804 4878 "Auftrag AN" 40
0 4791 297 5001 "Auftrag AP" 42
0 5001 880 5301 "Auftrag AM" 39
594 4878 694 4978 "Auftrag AO" 41
0 5301 420 5598 "Auftrag AQ" 43
0 5598 880 5898 "Auftrag AR" 44
420 5301 840 5598 "Auftrag AV" 48
0 5898 297 6108 "Auftrag AS" 45
297 5898 397 5998 "Auftrag AT" 46
397 5898 694 6108 "Auftrag AU" 47
0 6108 300 6988 "Auftrag AW" 49
300 6108 597 6318 "Auftrag AX" 50
597 6108 897 6988 "Auftrag BB" 54
300 6318 400 6418 "Auftrag AY" 51
300 6418 597 6628 "Auftrag AZ" 52
300 6628 597 7048 "Auftrag BA" 53
0 7048 297 7258 "Auftrag BC" 55
297 7048 507 7345 "Auftrag BE" 57
507 7048 804 7468 "Auftrag BF" 58
0 7258 297 7468 "Auftrag BH" 60
297 7345 397 7445 "Auftrag BD" 56
0 7468 880 7768 "Auftrag BG" 59
EOD

$anchor <<EOD
# x y
880 100
840 400
0 697
210 697
630 697
200 0
100 100
804 697
297 907
297 1117
880 1117
0 1417
694 994
594 1094
840 1417
420 1714
880 1714
297 2114
0 2224
694 2014
397 2224
897 2224
400 2434
597 2534
0 3104
597 3104
597 2744
300 3164
804 3164
297 3374
397 3461
297 3561
507 3584
880 3584
0 3884
880 3984
840 4284
0 4581
210 4581
630 4581
200 3884
100 3984
804 4581
297 4791
297 5001
880 5001
0 5301
694 4878
594 4978
840 5301
420 5598
880 5598
297 5998
0 6108
694 5898
397 6108
897 6108
400 6318
597 6418
0 6988
597 6988
597 6628
300 7048
804 7048
297 7258
397 7345
297 7445
507 7468
880 7468
0 7768
EOD

plot \
'$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):(($3-$1)/2):(($4-$2)/2):6 \
              with boxxy linecolor var, \
'$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):5 \
              with labels font "arial,9", \
'$anchor' using 1:2 with circles lc rgb "red", \
'$data' using 1:2 with points lw 8 lc rgb "dark-green"
