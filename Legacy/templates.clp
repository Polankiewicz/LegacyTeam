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
	(slot iloscWoja (type INTEGER))
)


(deftemplate kogoZaatakowac
	(slot coordXAI (type INTEGER))
	(slot coordYAI (type INTEGER))
	(slot coordXEnemy (type INTEGER))
	(slot coordYEnemy (type INTEGER))
	(slot iloscWoja (type INTEGER))
)

(deftemplate konkluzja
	(slot tekst (type STRING))
)