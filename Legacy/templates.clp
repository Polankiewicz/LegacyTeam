; <- tym krzaczkiem siê komentuje.
; poni¿sze sloty to odpowiednie pola dla naszej zmiennej graczInfo. Wszystko to jest umowne,
; piszê to tak by ³atwiej by³o skojarzyæ fakty z tym co ju¿ wiemy do tej pory.
; czyli tak slot -> nazwazmiennej -> typ zmiennej -> opcjonalnie dodatkowe warunki, tak jak jest to przy polu "czyAI"

(deftemplate graczInfo
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
	(slot czyAI 
		(type SYMBOL)
    	(allowed-symbols yes no)
	)
	(slot iloscWoja (type INTEGER))
)

(deftemplate konkluzja
	(slot tekst (type STRING))
)

(deftemplate kogoZaatakowac
	(slot coordX (type INTEGER))
	(slot coordY (type INTEGER))
)