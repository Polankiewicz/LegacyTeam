; je�eli w gracz info znajdziemy gracza po asercji, co ma wincyj ni� pincet woja to daje zna� o tym
; ale musimy te� utworzy� fakt! Wi�c jak rozumujemy na podstawie fakt�w przyk�adowo:
; Fakt 1 - masz 100z�
; Fakt 2 - dziewoja pod latarni� czeka na Ciebie by� jej zap�aci� za seks
; Fakt 3 wynikajcy z rozumowania - ta dziewczyna jest dziwk�.
; tak samo tutaj - musimy zamie�ci� wynik naszego rozumowania

(defrule hasMore
	(graczInfo (coordX ?coordX)(coordY ?coordY)(iloscWoja ?iloscwoja))
	(test (< ?iloscwoja 500))
=>
	(printout t "Wspolrzedne gracza majacego mniej niz 500 to " ?coordX " oraz " ?coordY crlf)
	(assert (kogoZaatakowac(coordX ?coordX)(coordY ?coordY)))
)