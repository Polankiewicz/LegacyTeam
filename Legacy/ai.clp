;(defrule hasMore
;	(boardInfo (coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja))
;	(test (< ?iloscWoja 50))
;=>
;	(printout t "Wspolrzedne gracza majacego mniej niz 50 to " ?coordX " oraz " ?coordY crlf)
;	(assert (kogoZaatakowac(indexAI ?index)(indexEnemy ?index)(iloscWoja ?iloscWoja)))
;)

(defrule isEnemyNear
	(neighbour (coordX ?coordX)(coordY ?coordY)(index ?index)(isEnemy ?isEnemy)(iloscWoja ?iloscwoja))
	(test (eq ?isEnemy yes))
	=>
	(printout t "Indeks: " ?index " wsp�rz�dne (" ?coordX ", " ?coordY ") ZNALEZIONO WROGA ")
	;(assert (foundEnemy(coordX ?coordX)(coordY ?coordY)))
)

