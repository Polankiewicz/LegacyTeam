

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
	
	(slot neighbour0coordX (type INTEGER))
	(slot neighbour0coordY (type INTEGER))
	(slot neighbour0index (type INTEGER))
	(slot neighbour0field
		(type SYMBOL)
    	(allowed-symbols player enemy none)
	)
	(slot neighbour0iloscWoja (type INTEGER))
)

(deftemplate EnemyField
	(slot indexAI(type INTEGER))
	(slot indexEnemy(type INTEGER))
	(slot wojoAI (type INTEGER))
	(slot wojoEnemy (type INTEGER))
	(slot difference (type INTEGER))
)

(deftemplate EmptyField
	(slot indexAI(type INTEGER))
	(slot indexEnemy(type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate PlayerField
	(slot indexAI(type INTEGER))
	(slot indexEnemy(type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate kogoZaatakowac
	(slot indexAI(type INTEGER))
	(slot indexEnemy(type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate randomizeSelection
  (slot random
    (type SYMBOL)
    (allowed-symbols yes no))
)