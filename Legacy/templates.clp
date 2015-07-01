(deftemplate boardInfo	 
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index (type INTEGER))
	(slot isCastleAI
		(type SYMBOL)
    	(allowed-symbols yes no))
    (slot isCastleEnemy
    	(type SYMBOL)
    	(allowed-symbols yes no))
    (slot isEnemy
    	(type SYMBOL)
    	(allowed-symbols yes no))
	(slot iloscWoja (type INTEGER))
)

(deftemplate fieldType
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index (type INTEGER))
	(slot category (allowed-values playera playerb noone))
	(slot playerType (allowed-values playera playerb))
)

(deftemplate AIFields
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index (type INTEGER))
	(slot iloscWoja (type INTEGER))
	
	;sloty dla s¹siada 0
	(slot neighbour0coordX (type INTEGER))
	(slot neighbour0coordX (type INTEGER))
	(slot neighbour0index (type integer))
	(slot neighbour0isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot neighbour1iloscWoja (type INTEGER))
	
	;sloty dla s¹siada 1
	(slot neighbour1coordX (type INTEGER))
	(slot neighbour1coordX (type INTEGER))
	(slot neighbour1index (type integer))
	(slot neighbour1isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot neighbour1iloscWoja (type INTEGER))
	
	;sloty dla s¹siada 2
	(slot neighbour2coordX (type INTEGER))
	(slot neighbour2coordX (type INTEGER))
	(slot neighbour2index (type integer))
	(slot neighbour2isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot neighbour2iloscWoja (type INTEGER))
	
	;sloty dla s¹siada 3
	(slot neighbour3coordX (type INTEGER))
	(slot neighbour3coordX (type INTEGER))
	(slot neighbour3index (type integer))
	(slot neighbour3isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot neighbour3iloscWoja (type INTEGER))
	
	;sloty dla s¹siada 4
	(slot neighbour4coordX (type INTEGER))
	(slot neighbour4co
	ordX (type INTEGER))
	(slot neighbour4index (type integer))
	(slot neighbour4isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot neighbour4iloscWoja (type INTEGER))
	
	;sloty dla s¹siada 5
	(slot neighbour5coordX (type INTEGER))
	(slot neighbour5coordX (type INTEGER))
	(slot neighbour5index (type integer))
	(slot neighbour5isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot neighbour5iloscWoja (type INTEGER))
)

(deftemplate neighbour
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index (type INTEGER))
	(slot isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot iloscWoja (type INTEGER))
)

(deftemplate neighbour2lvl
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index (type INTEGER))
	(slot isEnemy
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot iloscWoja (type INTEGER))
)

(deftemplate foundEnemy
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index(type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate EmptyField
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index(type INTEGER))
)

(deftemplate kogoZaatakowac
	(slot indexAI(type INTEGER))
	(slot indexEnemy(type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate konkluzja
	(slot tekst (type STRING))
)

(deftemplate randomizeSelection
  (slot answer
    (type SYMBOL)
    (allowed-symbols yes no)
;    (default no)
  )
)

(deftemplate pI
	(slot indexField(type INTEGER))
)