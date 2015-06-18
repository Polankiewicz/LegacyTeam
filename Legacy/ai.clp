;(defrule hasMore
;	(boardInfo (coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja))
;	(test (< ?iloscWoja 50))
;=>
;	(printout t "Wspolrzedne gracza majacego mniej niz 50 to " ?coordX " oraz " ?coordY crlf)
;	(assert (kogoZaatakowac(indexAI ?index)(indexEnemy ?index)(iloscWoja ?iloscWoja)))
;)

;(defrule isEnemyNear
;	(neighbour (coordX ?coordX)(coordY ?coordY)(index ?index)(isEnemy ?isEnemy)(iloscWoja ?iloscwoja))
;	(test (eq ?isEnemy yes))
;	=>
;	(printout t "Indeks: " ?index " wspó³rzêdne (" ?coordX ", " ?coordY ") ZNALEZIONO WROGA ")
;	;(assert (foundEnemy(coordX ?coordX)(coordY ?coordY)))
;)

(defrule rollRandom
	(initial-fact)
	=>
	(bind ?roll1 (random 1 6))
	(bind ?roll2 (random 1 6))
	(printout t "Wylosowano: " ?roll1 " oraz " ?roll2 crlf)
)