(defrule hasMore
	(graczInfo (coordX ?coordX)(coordY ?coordY)(iloscWoja ?iloscwoja))
	(test (< ?iloscwoja 500))
=>
	(printout t "Wspolrzedne gracza majacego mniej niz 500 to " ?coordX " oraz " ?coordY crlf)
	(assert (kogoZaatakowac(coordX ?coordX)(coordY ?coordY)))
)