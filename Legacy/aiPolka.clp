;==== ADDITIONALS ===

;(defrule rollRandom
;	(initial-fact)
;	=>
;	(bind ?roll1 (random 1 6))
;	(bind ?roll2 (random 1 6))
	;(printout t "Wylosowano: " ?roll1 " oraz " ?roll2 crlf)
;)

;(defrule printAreas
;	(fieldType (coordX ?coordX)(coordY ?coordY)(index ?index)(category ?category)(playerType ?playerType))
;	(or(and(test (eq ?category playera))(test (eq ?playerType playera)))(and(test (eq ?category playerb))(test (eq ?playerType playerb))))
;=>
;	(if (eq ?playerType playera) then (bind ?indexField ?index)(printout t "Player A - bind index" crlf))
;	(if (eq ?playerType playerb) then (bind ?indexField ?index)(printout t "Player B - bind index" crlf))	
;	(assert (pI(indexField ?indexField)))
;)	


;============================================================
;=================== PROCESS DATA ===========================

(defrule freeField 
		(AIFields 
			(coordX ?coordX)
			(coordY ?coordY)
			(index ?index)
			(iloscWoja ?iloscWoja)
			
			(neighbour0coordX ?neighbour0coordX)
			(neighbour0coordY ?neighbour0coordY)
			(neighbour0index ?index0)
			(neighbour0field ?neighbour0field)
			(neighbour0iloscWoja ?wojo0)
		)
		(test (eq ?neighbour0field none))
		=>
		(bind ?noweWojo (- ?iloscWoja 1))
		(printout t "EmptyField indexAI" ?index " indexEnemy " ?index0 " iloscWoja " ?iloscWoja crlf)
		(assert (kogoZaatakowac(indexAI ?index)(indexEnemy ?index0)(iloscWoja ?noweWojo)))
)

(defrule ownField 
		(AIFields 
			(coordX ?coordX)
			(coordY ?coordY)
			(index ?index)
			(iloscWoja ?iloscWoja)
			
			(neighbour0coordX ?neighbour0coordX)
			(neighbour0coordY ?neighbour0coordY)
			(neighbour0index ?index0)
			(neighbour0field ?neighbour0field)
			(neighbour0iloscWoja ?wojo0)
		)
		(test (eq ?neighbour0field player))
		=>
		(printout t "PlayerField indexAI" ?index " indexEnemy " ?index0 " iloscWoja " ?iloscWoja crlf)
		(assert (PlayerField(indexAI ?index)(indexEnemy ?index0)(iloscWoja ?iloscWoja)))
)