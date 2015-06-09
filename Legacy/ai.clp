(defrule hasMore
	(boardInfo (coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja))
	(test (< ?iloscWoja 50))
=>
	(printout t "Wspolrzedne gracza majacego mniej niz 50 to " ?coordX " oraz " ?coordY crlf)
;	(assert (kogoZaatakowac(indexAI ?index)(indexEnemy ?index)(iloscWoja ?iloscWoja)))
)
