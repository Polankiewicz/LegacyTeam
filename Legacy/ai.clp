;====
(assert (
	randomizeSelection(random yes))
)

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
	(if (eq ?playerType playera) then (bind ?indexField ?index)(printout t "Player A - bind index" crlf))
	(if (eq ?playerType playerb) then (bind ?indexField ?index)(printout t "Player B - bind index" crlf))	
	(assert (pI(indexField ?indexField)))
)	

;=======
;============================================================
;=================== PROCESS DATA ===========================
=======
;(defrule hasMore
;	(boardInfo (coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja))
;	(test (< ?iloscWoja 50))
;=>
;	(printout t "Wspolrzedne gracza majacego mniej niz 50 to " ?coordX " oraz " ?coordY crlf)
;	(assert (kogoZaatakowac(indexAI ?index)(indexEnemy ?index)(iloscWoja ?iloscWoja)))
;)


;============================================================
;=================== PROCESS DATA ===========================



;============================================================
;=================== PROCESS DATA ===========================
;(defrule findMaxWarriors
;	(AIFields(coordX ?coordX)(coordY ?coordY)(index ?index)(iloscWoja ?iloscWoja)

