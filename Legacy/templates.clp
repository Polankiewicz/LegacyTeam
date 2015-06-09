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

(deftemplate foundEnemy
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot index(type INTEGER))
	(slot iloscWoja (type INTEGER))
)


(deftemplate kogoZaatakowac
	(slot indexAI(type INTEGER))
	(slot indexEnemy(type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate konkluzja
	(slot tekst (type STRING))
)