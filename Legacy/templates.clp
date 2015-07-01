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