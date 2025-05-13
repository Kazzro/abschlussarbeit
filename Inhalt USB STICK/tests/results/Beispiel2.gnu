reset
set term png size 900,980
set output 'Beispiel2.png'
set xrange [0:900]
set yrange [0:880]
set size ratio -1

set title "\
// Auftrag IHK2\n\
Benötigte Länge: 88.0cm\n\
Genutzte Fläche: 92.98%"

set style fill transparent solid 0.5 border
set key noautotitle

$data <<EOD
# x_LU y_LU x_RO y_RO Auftragsbeschreibung ID
0 0 297 210 "Auftrag B" 2
297 0 594 420 "Auftrag C" 3
594 0 894 880 "Auftrag D" 4
0 210 100 310 "Auftrag A" 1
0 310 297 520 "Auftrag E" 5
297 420 594 840 "Auftrag H" 8
0 520 297 817 "Auftrag J" 10
EOD

$anchor <<EOD
# x y
894 0
100 210
297 310
594 420
297 840
594 880
297 520
0 817
EOD

plot \
'$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):(($3-$1)/2):(($4-$2)/2):6 \
              with boxxy linecolor var, \
'$data' using (($3-$1)/2+$1):(($4-$2)/2+$2):5 \
              with labels font "arial,9", \
'$anchor' using 1:2 with circles lc rgb "red", \
'$data' using 1:2 with points lw 8 lc rgb "dark-green"
