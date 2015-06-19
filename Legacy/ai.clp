;==== ADDITIONALS ===

(defrule rollRandom
	(initial-fact)
	=>
	(bind ?roll1 (random 1 6))
	(bind ?roll2 (random 1 6))
	;(printout t "Wylosowano: " ?roll1 " oraz " ?roll2 crlf)
)

(defrule printAreas
	(fieldType (coordX ?coordX)(coordY ?coordY)(index ?index)(category ?category)(playerType ?playerType))
	(or(and(test (eq ?category playera))(test (eq ?playerType playera)))(and(test (eq ?category playerb))(test (eq ?playerType playerb))))
=>
	(if (eq ?playerType playera) then (bind ?pI ?index)(printout t "Player A - bind index" crlf))
	(if (eq ?playerType playerb) then (bind ?pI ?index)(printout t "Player B - bind index" crlf))	
	;(assert (pI ?pI))
)	

(defrule test1
(pI ?pI)
(test (= ?pI 0))
=>
(printout t "Suma jest rowna zero" crlf))

;=======
;============================================================
;=================== PROCESS DATA ===========================
;(defrule hasMore
;	(boardInfo (coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja))
;	(test (< ?iloscWoja 50))
;=>
;	(printout t "Wspolrzedne gracza majacego mniej niz 50 to " ?coordX " oraz " ?coordY crlf)
;	(assert (kogoZaatakowac(indexAI ?index)(indexEnemy ?index)(iloscWoja ?iloscWoja)))
;)
;
;(defrule isEnemyNear
;	(neighbour (coordX ?coordX)(coordY ?coordY)(index ?index)(isEnemy ?isEnemy)(iloscWoja ?iloscwoja))
;	(test (eq ?isEnemy yes))
;	=>
;	(printout t "Indeks: " ?index " wspó³rzêdne (" ?coordX ", " ?coordY ") ZNALEZIONO WROGA ")
;	(assert (foundEnemy(coordX ?coordX)(coordY ?coordY)))
;)
;
;(defrule isEmptyField
;	(neighbour (coordX ?coordX)(coordY ?coordY)(index ?index)(isEnemy ?isEnemy)(iloscWoja ?iloscwoja))
;	(test (eq ?isEnemy no))
;	=>
;	(printout t "Indeks: " ?index " wspó³rzêdne (" ?coordX ", " ?coordY ") ZNALEZIONO WROGA ")
;	(assert (EmptyField(coordX ?coordX)(coordY ?coordY)(index ?index)))
;)
;
;============================================================
;=================== PROCESS DATA ===========================
;(defrule findMaxWarriors
;	(AIFields(coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja)
	
