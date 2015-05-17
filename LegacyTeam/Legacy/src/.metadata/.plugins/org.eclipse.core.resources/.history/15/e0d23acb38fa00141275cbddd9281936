(deftemplate pytanie
	(slot id (type NUMBER))
	(slot tekst (type STRING))
)

(deftemplate odpowiedz
	(slot id (type NUMBER))
	(slot tekst (type STRING))
	(slot token (type STRING))
)	 

(deftemplate konkluzja
	(slot tekst (type STRING))
)

(deffacts start

	(licznik_oznak 0)
	(pytanie	(id 0)	(tekst "Wybierz metodę obserwacji"))
	(odpowiedz	(id 0)	(tekst "obserwacja powierzchni wody")			(token "obserwacja woda"))
	(odpowiedz	(id 0)	(tekst "obserwacja na lądzie")				(token "obserwacja lad"))
)



;====================================================================================================================================================================
;============================================================================ WIATR =================================================================================
;====================================================================================================================================================================

(defrule rule_obserwacja
	(obserwacja woda)
=>
	(assert 
		(pytanie	(id 2)	(tekst "Opisz falę"))
		(odpowiedz 	(id 2)	(tekst "brak fali")					(token "wielkosc_fali brak"))
		(odpowiedz 	(id 2)	(tekst "mała fala")					(token "wielkosc_fali mala"))
		(odpowiedz 	(id 2)	(tekst "średnia fala")					(token "wielkosc_fali srednia"))
		(odpowiedz 	(id 2)	(tekst "wysoka fala")					(token "wielkosc_fali wysoka"))
	)
)

(defrule rule_wielkosc_fali_brak
	(obserwacja woda)
	(wielkosc_fali brak)
=>
	(assert 
		(pytanie	(id 3)	(tekst "Czy na powierzchni wody występują zmarszczki?"))
		(odpowiedz 	(id 3)	(tekst "tak")						(token "zmarszczki_wody tak"))
		(odpowiedz 	(id 3)	(tekst "nie")						(token "zmarszczki_wody nie"))
	)
)

(defrule rule_zmarszczki_wody_tak
	(obserwacja woda)
	(wielkosc_fali brak)
	(zmarszczki_wody tak)
=>
	(assert 
		(beaufort 1)
		(pedersen 1)
	)
)

(defrule rule_zmarszczki_wody_nie
	(obserwacja woda)
	(wielkosc_fali brak)
	(zmarszczki_wody nie)
=>
	(assert 
		(beaufort 0)
		(pedersen 0)
	)
)

(defrule rule_wielkosc_fali_mala
	(obserwacja woda)
	(wielkosc_fali mala)
=>
	(assert 
		(pytanie	(id 4)	(tekst "Jaki charakter mają grzbiety fal?"))
		(odpowiedz 	(id 4)	(tekst "grzbiety są szkliste")					(token "grzbiety_fal szkliste"))
		(odpowiedz 	(id 4)	(tekst "grzbiety załamują się")					(token "grzbiety_fal zalamane"))
	)
)

(defrule rule_grzbiety_fal_szkliste
	(obserwacja woda)
	(wielkosc_fali mala)
	(grzbiety_fal szkliste)
=>
	(assert 
		(beaufort 2)
		(pedersen 2)
	)
)

(defrule rule_grzbiety_fal_zalamane
	(obserwacja woda)
	(wielkosc_fali mala)
	(grzbiety_fal zalamane)
=>
	(assert 
		(beaufort 3)
		(pedersen 2)
	)
)


(defrule rule_wielkosc_fali_srednia
	(obserwacja woda)
	(wielkosc_fali srednia)
=>
	(assert 
		(pytanie	(id 5)	(tekst "Czy na powierzchni występują bryzgi?"))
		(odpowiedz 	(id 5)	(tekst "tak")						(token "bryzgi_fal tak"))
		(odpowiedz 	(id 5)	(tekst "nie")						(token "bryzgi_fal nie"))
	)
)

(defrule rule_bryzgi_fal_tak
	(obserwacja woda)
	(wielkosc_fali srednia)
	(bryzgi_fal tak)
=>
	(assert 
		(beaufort 5)
		(pedersen 4)
	)
)

(defrule rule_bryzgi_fal_nie
	(obserwacja woda)
	(wielkosc_fali srednia)
	(bryzgi_fal nie)
=>
	(assert 
		(beaufort 4)
		(pedersen 3)
	)
)

(defrule rule_wielkosc_fali_wysoka
	(obserwacja woda)
	(wielkosc_fali wysoka)
=>
	(assert 
		(pytanie	(id 6)	(tekst "Czy na powierzchni wody występuje piana?"))
		(odpowiedz 	(id 6)	(tekst "tak")						(token "piana tak"))
		(odpowiedz 	(id 6)	(tekst "nie")						(token "piana nie"))
	)
)

(defrule rule_piana_nie
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana nie)
=>
	(assert 
		(beaufort 6)
		(pedersen 5)
	)
)

(defrule rule_piana_tak
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
=>
	(assert 
		(pytanie	(id 7)	(tekst "Czy w powietrzy jest wyczuwalny pył wodny?"))
		(odpowiedz 	(id 7)	(tekst "tak")						(token "pyl_wodny tak"))
		(odpowiedz 	(id 7)	(tekst "nie")						(token "pyl_wodny nie"))
	)
)

(defrule rule_piana_tak2
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
=>
	(assert 
		(pytanie	(id 8)	(tekst "Opisz stan widoczności - czy widać sąsiednie statki?"))
		(odpowiedz 	(id 8)	(tekst "tak")						(token "widocznosc tak"))
		(odpowiedz 	(id 8)	(tekst "nie")						(token "widocznosc nie"))
	)
)

(defrule rule_piana_tak_pyl_tak_widocznosc_tak
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny tak)
	(widocznosc tak)
=>
	(assert 
		(beaufort 11)
		(pedersen 9)
	)
)

(defrule rule_piana_tak_pyl_tak_widocznosc_nie
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny tak)
	(widocznosc nie)
=>
	(assert 
		(beaufort 12)
		(pedersen 9)
	)
)

(defrule rule_piana_tak_pyl_nie_widocznosc_tak
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny nie)
	(widocznosc tak)
=>
	(assert 
		(pytanie	(id 10)	(tekst "Czy wierzchołki fal odrywają się?"))
		(odpowiedz 	(id 10)	(tekst "tak")						(token "odrywane_wierzcholki tak"))
		(odpowiedz 	(id 10)	(tekst "nie")						(token "odrywane_wierzcholki nie"))
	)
)

(defrule rule_piana_tak_pyl_nie_widocznosc_nie
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny nie)
	(widocznosc nie)
=>
	(assert 
		(pytanie	(id 9)	(tekst "Czy cała powierzchnia wody jest biała od piany?"))
		(odpowiedz 	(id 9)	(tekst "tak")						(token "woda_biala tak"))
		(odpowiedz 	(id 9)	(tekst "nie")						(token "woda_biala nie"))
	)
)

(defrule rule_piana_tak_pyl_nie_widocznosc_tak_odrywane_wierzcholki_tak
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny nie)
	(widocznosc tak)
	(odrywane_wierzcholki tak)
=>
	(assert 
		(beaufort 8)
		(pedersen 7)
	)
)

(defrule rule_piana_tak_pyl_nie_widocznosc_tak_odrywane_wierzcholki_nie
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny nie)
	(widocznosc tak)
	(odrywane_wierzcholki nie)
=>
	(assert 
		(beaufort 7)
		(pedersen 6)
	)
)

(defrule rule_piana_tak_pyl_nie_widocznosc_nie_woda_biala_tak
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny nie)
	(widocznosc nie)
	(woda_biala tak)
=>
	(assert 
		(beaufort 10)
		(pedersen 8)
	)
)

(defrule rule_piana_tak_pyl_nie_widocznosc_nie_woda_biala_nie
	(obserwacja woda)
	(wielkosc_fali wysoka)
	(piana tak)
	(pyl_wodny nie)
	(widocznosc nie)
	(woda_biala nie)
=>
	(assert 
		(beaufort 9)
		(pedersen 8)
	)
)

(defrule rule_co_sie_porusza
	(obserwacja lad)
=>
	(assert
		(pytanie	(id 15) (tekst "Jak duze galezie porusza wiatr?"))
		(odpowiedz	(id 15) (tekst "brak ruchu")				(token "galezie brak"))
		(odpowiedz	(id 15) (tekst "male galezie")				(token "galezie male"))
		(odpowiedz	(id 15) (tekst "duze galezie")				(token "galezie duze"))
		(odpowiedz	(id 15) (tekst "pnie")					(token "galezie pnie"))
		(odpowiedz	(id 15) (tekst "wyrywa drzewa")				(token "galezie drzewa"))
	)
)

(defrule rule_czy_dym
	(obserwacja lad)
	(galezie brak)
=>
	(assert
		(pytanie	(id 16)	(tekst "Czy unosi się dym?"))
		(odpowiedz	(id 16) (tekst "tak")			(token "dym tak"))
		(odpowiedz	(id 16) (tekst "nie")			(token "dym nie"))
	)
)

(defrule rule_powiew
	(obserwacja lad)	
	(galezie brak)
	(dym tak)
=>
	(assert
		(pytanie	(id 17) (tekst "Czy powiew wiatru jest odczuwalny?"))
		(odpowiedz	(id 17) (tekst "tak")			(token "powiew tak"))
		(odpowiedz	(id 17) (tekst "nie")			(token "powiew nie"))
	)
)

(defrule rule_liscie_ruch
	(obserwacja lad)	
	(galezie brak)
	(dym nie)
=>
	(assert
		(pytanie	(id 17) (tekst "Czy liscie sie poruszaja?"))
		(odpowiedz	(id 17) (tekst "tak")				(token "liscie_ruch tak"))
		(odpowiedz	(id 17) (tekst "nie")				(token "liscie_ruch nie"))
	)
)

(defrule rule_galezie_brak_dym_tak_powiew_tak
	(obserwacja lad)
	(galezie brak)
	(dym tak)
	(powiew tak)
=>
	(assert
		(beaufort 1)
		(pedersen 1)
	)
)

(defrule rule_galezie_brak_dym_tak_powiew_nie
	(obserwacja lad)
	(galezie brak)
	(dym tak)
	(powiew nie)
=>
	(assert
		(beaufort 0)
		(pedersen 0)
	)
)

(defrule rule_galezie_brak_dym_nie_liscie_ruch_tak
	(obserwacja lad)
	(galezie brak)
	(dym nie)
	(liscie_ruch tak)
=>
	(assert
		(beaufort 3)
		(pedersen 2)
	)
)

(defrule rule_galezie_brak_dym_nie_liscie_ruch_nie
	(obserwacja lad)
	(galezie brak)
	(dym nie)
	(liscie_ruch nie)
=>
	(assert
		(beaufort 2)
		(pedersen 2)
	)
)

(defrule rule_galezie_male
	(obserwacja lad)
	(galezie male)
=>
	(assert
		(beaufort 4)
		(pedersen 3)
	)
)

(defrule rule_swist
	(obserwacja lad)
	(galezie duze)
=>
	(assert
		(pytanie	(id 18) (tekst "Czy slychac swist?"))
		(odpowiedz	(id 18) (tekst "tak")			(token "swist tak"))
		(odpowiedz	(id 18) (tekst "nie")			(token "swist nie"))
	)
)

(defrule rule_galezie_duze_swist_tak
	(obserwacja lad)	
	(galezie duze)
	(swist tak)
=>
	(assert
		(beaufort 6)
		(pedersen 5)
	)
)

(defrule rule_galezie_duze_swist_nie
	(obserwacja lad)
	(galezie duze)
	(swist nie)
=>
	(assert
		(beaufort 5)
		(pedersen 4)
	)
)

(defrule rule_lamie_galezie
	(obserwacja lad)
	(galezie pnie)
=>
	(assert
		(pytanie 	(id 19)	(tekst "Czy wiatr lamie galezie?"))
		(odpowiedz	(id 19) (tekst "tak")				(token "lamie_galezie tak"))
		(odpowiedz	(id 19) (tekst "nie")				(token "lamie_galezie nie"))
	)
)

(defrule rule_przedmioty
	(obserwacja lad)
	(galezie pnie)
	(lamie_galezie tak)
=>
	(assert
		(pytanie	(id 20) (tekst "Czy wiatr unosi przedmioty?"))
		(odpowiedz	(id 20) (tekst "tak")				(token "przedmioty tak"))
		(odpowiedz	(id 20) (tekst "nie")				(token "przedmioty nie"))
	)
)

(defrule rule_galezie_pnie_lamie_tak_przedmioty_tak
	(obserwacja lad)
	(galezie pnie)
	(lamie_galezie tak)
	(przedmioty tak)
=>
	(assert
		(beaufort 9)
		(pedersen 8)
	)
)

(defrule rule_galezie_pnie_lamie_tak_przedmioty_nie
	(obserwacja lad)
	(galezie pnie)
	(lamie_galezie tak)
	(przedmioty nie)
=>
	(assert
		(beaufort 8)
		(pedersen 7)
	)
)

(defrule rule_galezie_pnie_lamie_nie
	(obserwacja lad)
	(galezie pnie)
	(lamie_galezie nie)
=>
	(assert
		(beaufort 7)
		(pedersen 6)
	)
)

(defrule rule_uszkodzenia_budynkow
	(obserwacja lad)
	(galezie drzewa)
=>
	(assert
		(pytanie	(id 31) (tekst "Opisz uszkodzenia infrastruktury"))
		(odpowiedz	(id 31) (tekst "brak")					(token "budynki brak"))
		(odpowiedz	(id 31) (tekst "drobne")				(token "budynki drobne"))
		(odpowiedz 	(id 31) (tekst "ogromne")				(token "budynki ogromne"))
	)
)

(defrule rule_galezie_drzewa_budynki_brak
	(obserwacja lad)	
	(galezie drzewa)	
	(budynki brak)
=>
	(assert
		(beaufort 10)
		(pedersen 8)
	)
)

(defrule rule_galezie_drzewa_budynki_drobne
	(obserwacja lad)	
	(galezie drzewa)	
	(budynki drobne)
=>
	(assert
		(beaufort 11)
		(pedersen 9)
	)
)

(defrule rule_galezie_drzewa_budynki_ogromne
	(obserwacja lad)	
	(galezie drzewa)	
	(budynki ogromne)
=>
	(assert
		(beaufort 12)
		(pedersen 9)
	)
)

(defrule rule_pedersen_0_beaufort_0
	(pedersen 0)
	(beaufort 0)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 0 stopni w skali Pedersena (0 w skali Beauforta)"))
		(konkluzja (tekst "Woda jest spokojna, tafla wody gladka, nie przewiduje sie zadnej fali"))
		(konkluzja (tekst "Predkosc wiatru znikoma"))
	)
)

(defrule rule_pedersen_1_beaufort_1
	(pedersen 1)
	(beaufort 1)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 1 stopnia w skali Pedersena (1 w skali Beauforta)"))
		(konkluzja (tekst "Fala osiagnie dlugosc do 5 metrow, jej wysokosc bedzie z przedzialu 10-25 cm"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 0.3 - 1.5 m/s"))
	)
)

(defrule rule_pedersen_2
	(pedersen 2)
	(pedersen_go2)
=>
	(assert
		(konkluzja (tekst "Sila wiatru w skali Pedersena wyniesie 2 stopnie"))
		(konkluzja (tekst "Dlugosc fali do 25 metrow, jej wysokosc bedzie z przedzialu 25-75 cm"))
	)
)

(defrule rule_perdersen_2_beaufort_2
	(beaufort 2)
=>
	(assert
		(pedersen_go2)
		(konkluzja (tekst "W skali Beauforta wiatr osiagnie 2 stopnie"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 1.6 - 3.3 m/s"))
	)
)

(defrule rule_pedersen_2_beaufort_3
	(beaufort 3)
=>
	(assert
		(pedersen_go2)
		(konkluzja (tekst "W skali Beauforta wiatr osiagnie 3 stopnie"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 3.4 - 5.4 m/s"))
	)
)

(defrule rule_pedersen_3_beaufort_4
	(beaufort 4)
	(pedersen 3)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 3 stopni w skali Pedersena (4 w skali Beauforta)"))
		(konkluzja (tekst "Dlugosc fali do 50 metrow, jej wysokosc bedzie z przedzialu 0.75 - 1.25 metra"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 5.5 - 7.9 m/s"))
	)
)

(defrule rule_pedersen_4_beaufort_5
	(beaufort 5)
	(pedersen 4)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 4 stopni w skali Pedersena (5 w skali Beauforta)"))
		(konkluzja (tekst "Dlugosc fali do 75 metrow, jej wysokosc bedzie z przedzialu 1.3 - 2.0 metrow"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 8.0 - 10.7 m/s"))
	)
)

(defrule rule_pedersen_5_beaufort_6
	(beaufort 6)
	(pedersen 5)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 5 stopni w skali Pedersena (6 w skali Beauforta)"))
		(konkluzja (tekst "Dlugosc fali do 100 metrow, jej wysokosc bedzie z przedzialu 2.0- 3.5 metrow"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 10.8 - 13.8 m/s"))
	)
)

(defrule rule_pedersen_6_beaufort_7
	(beaufort 7)
	(pedersen 6)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 6 stopni w skali Pedersena (7 w skali Beauforta)"))
		(konkluzja (tekst "Dlugosc fali do 135 metrow, jej wysokosc bedzie z przedzialu 3.5- 6.0 metrow"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 13,9 - 17.1 m/s"))
		(konkluzja (tekst "Uwaga - silny wiatr!"))
	)
)

(defrule rule_pedersen_7_beaufort_8
	(beaufort 8)
	(pedersen 7)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 7 stopni w skali Pedersena (8 w skali Beauforta)"))
		(konkluzja (tekst "Dlugosc fali do 200 metrow, jej wysokosc bedzie z przedzialu 6.0- 8.5 metrow"))
		(konkluzja (tekst "Przewidywana predkosc wiatru to 17.2 - 20.7 m/s"))
	)
)

(defrule rule_pedersen_8
	(pedersen 8)
	(pedersen_go8)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 8 stopni w skali Pedersena"))
		(konkluzja (tekst "Dlugosc fali wyniesie do 250 metrow, jej dlugosc bedzie z przedzialu 8.5 - 11.0 metrow"))
	)
)

(defrule rule_pedersen_8_beaufort_9
	(beaufort 9)
=>
	(assert
		(pedersen_go8)
		(konkluzja (tekst "W skali Beauforta sila wiatru okreslana jest na 9 stopni"))
		(konkluzja (tekst "Silny wiatr wieje z predkoscia 20.8 - 24.4 m/s"))
	)
)

(defrule rule_pedersen_8_beaufort_10
	(beaufort 10)
=>
	(assert
		(pedersen_go8)
		(konkluzja (tekst "W skali Beauforta sila wiatru okreslana jest na 10 stopni"))
		(konkluzja (tekst "Silny wiatr wieje z predkoscia 24.5 - 28.4 m/s"))
	)
)

(defrule rule_pedersen_9
	(pedersen 9)
	(pedersen_go9)
=>
	(assert
		(konkluzja (tekst "Wiatr wieje z sila 9 stopni w skali Pedersena"))
		(konkluzja (tekst "Wiatr tak silny, ze fale osiagaja dlugosc nawet powyzej 300 metrow, przy wysokosci powyzej 11 metrow"))
	)
)

(defrule rule_pedersen_9_beaufort_11
	(beaufort 11)
=>
	(assert
		(pedersen_go9)
		(konkluzja (tekst "W skali Beauforta sila wiatru okreslana jest na 11 stopni"))
		(konkluzja (tekst "Przewidywana predskosc wiatru to 28.5 - 32.6 m/s"))
	)
)

(defrule rule_pedersen_9_beaufort_12
	(beaufort 12)
=>
	(assert
		(pedersen_go9)
		(konkluzja (tekst "W skali Beauforta sila wiatru okreslana jest na 12 stopni"))
		(konkluzja (tekst "Predkosc wiatru nawet powyzej 32.6 m/s"))
	)
)
		
(defrule rule_za_silny_wiatr
	(beaufort ?x)
	(test (> ?x 7))
=>
	(assert
		(konkluzja (tekst "Wiatr jest bardzo silny. Wyplywanie w tej chwili nie jest najlepszym pomyslem!"))
	)
)

;====================================================================================================================================================================
;============================================================================ CHMURY ================================================================================
;====================================================================================================================================================================

(defrule rule_jest_wiatr
	(pedersen ?)
=>
	(assert
		(pytanie	(id 1)	(tekst "Opisz ksztalt chmur"))
		(odpowiedz	(id 1)	(tekst "brak chmur")					(token "budowa_chmury brak"))
		(odpowiedz	(id 1)	(tekst "chmury przypominają nitki")			(token "budowa_chmury nitki"))
		(odpowiedz	(id 1)	(tekst "chmury przypominają kłębki")			(token "budowa_chmury klebki"))
		(odpowiedz	(id 1)	(tekst "chmury mają charakter warstwowy")		(token "budowa_chmury warstwy"))
	)
)

(defrule rule_sa_chmury
	(pedersen ?)
	(budowa_chmury ~brak)
=>
	(assert
		(sa_chmury)
	)
)


(defrule rule_pokrycie_nieba1
	(sa_chmury)
=>
	(assert
		(pytanie	(id 12) (tekst "Opisz pokrycie nieba przez chmury."))
		(odpowiedz	(id 12) (tekst "Czesciowe")				(token "pokrycie_nieba czesciowe"))
		(odpowiedz	(id 12) (tekst "Pelne")					(token "pokrycie_nieba pelne"))
	)
)	
	
(defrule rule_pokrycie_nieba2
	(budowa_chmury ~warstwy)
	(sa_chmury)
=>
	(assert
		(odpowiedz	(id 12) (tekst "Rybie luski")				(token "pokrycie_nieba luski"))
	)
)

(defrule rule_zapytaj_o_wysokosc1
	(budowa_chmury klebki)
	(pokrycie_nieba czesciowe)
=>
	(assert
		(zapytaj_wysokosc)
		(odpowiedz	(id 13) (tekst "Chmury maja pionowa budowe")		(token "wysokosc_chmur pionowo"))
		(odpowiedz	(id 13) (tekst "Srednio wysoko")			(token "wysokosc_chmur srednio"))
	)
)

(defrule rule_zapytaj_o_wysokosc2
	(budowa_chmury warstwy)
	(pokrycie_nieba czesciowe)
=>
	(assert
		(zapytaj_wysokosc)
		(odpowiedz	(id 13) (tekst "Wysoko")			(token "wysokosc_chmur wysoko"))
	)
)

(defrule rule_zapytaj_o_wysokosc3	
	(budowa_chmury warstwy)
	(pokrycie_nieba pelne)
=>
	(assert
		(zapytaj_wysokosc)
		(odpowiedz	(id 13) (tekst "Chmury maja pionowa budowe")		(token "wysokosc_chmur pionowo"))
		(odpowiedz	(id 13) (tekst "Wysoko")				(token "wysokosc_chmur wysoko"))
		(odpowiedz	(id 13) (tekst "Srednio wysoko")			(token "wysokosc_chmur srednio"))
	)
)

;wspolna czesc pytania dla wszystkich przypadkow
(defrule rule_wysokosc_chmury
	(zapytaj_wysokosc)
=>	
	(assert
		(pytanie	(id 13) (tekst "Na jakiej wysokosci sa chmury?"))
		(odpowiedz	(id 13) (tekst "Nisko")				(token "wysokosc_chmur nisko"))
	)
)

(defrule rule_przejrzystosc
	(budowa_chmury warstwy)
	(wysokosc_chmur  srednio | nisko)
	(pokrycie_nieba pelne)
=>
	(assert
		(pytanie	(id 14) (tekst "Ocen przejrzystosc chmur."))
		(odpowiedz	(id 14) (tekst "Zadna")				(token "przejrzystosc_chmur zadna"))
		(odpowiedz	(id 14) (tekst "Niska")				(token "przejrzystosc_chmur niska"))
	)
)

(defrule chmura_brak
	(budowa_chmury brak)
=>
	(assert
		(chmura brak)
	)
)

(defrule chmura_cirrus
	(budowa_chmury nitki)
	(pokrycie_nieba pelne)
=>
	(assert
		(chmura cirrus)
	)
)

(defrule chmura_cirrostratus
	(budowa_chmury nitki)
	(pokrycie_nieba czesciowe)
=>
	(assert
		(chmura cirrostratus)
	)
)

(defrule chmura_cirrostratus_2
	(budowa_chmury warstwy)
	(wysokosc_chmur wysoko)
=>
	(assert
		(chmura cirrostratus)
	)
)

(defrule chmura_cirrocumulus
	(pokrycie_nieba luski)
=>
	(assert
		(chmura cirrocumulus)
	)
)

(defrule chmura_cumulus
	(wysokosc_chmur pionowo)
	(pokrycie_nieba czesciowe)
;	(budowa_chmury klebki)
=>
	(assert
		(chmura cumulus)
	)
)

(defrule chmura_altocumulus
	(wysokosc_chmur srednio)
	(pokrycie_nieba czesciowe)
;	(budowa_chmury klebki)
=>
	(assert
		(chmura altocumulus)
	)
)

(defrule chmura_stratocumulus
	(wysokosc_chmur nisko)
	(pokrycie_nieba czesciowe)
=>
	(assert
		(chmura stratocumulus)
	)
)

(defrule chmura_cumulonimbus
	(pokrycie_nieba pelne)
	(budowa_chmury klebki)
=>
	(assert
		(chmura cumulonimbus)
	)
)

(defrule chmura_cumulonimbus2
	(pokrycie_nieba pelne)
	(budowa_chmury warstwy)
	(wysokosc_chmur pionowo)
=>
	(assert
		(chmura cumulonimbus)
	)
)

(defrule chmura_nimbostratus
	(przejrzystosc_chmur zadna)
=>
	(assert
		(chmura nimbostratus)
	)
)

(defrule chmura_altostratus
	(przejrzystosc_chmur niska)
	(wysokosc_chmur srednio)
=>
	(assert
		(chmura altostratus)
	)
)

(defrule chmura_stratus
	(przejrzystosc_chmur niska)
	(wysokosc_chmur nisko)
=>
	(assert 
		(chmura stratus)
	)
)

(defrule rule_zmiana_pogody
	(chmura cirrus | cirrocumulus | altocumulus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie przewidywane jest nadejscie chlodnego frontu i gwaltowne zalamanie pogody."))
	)
)

(defrule rule_mozliwa_zmiana_pogody
	(chmura stratocumulus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie mozliwe jest zalamanie pogody."))
	)
)

(defrule rule_bedzie_silny_wiatr
	(chmura cirrus)
=>
	(assert
		(konkluzja (tekst "Prognoza dlugoterminowa: Cirrusy na niebie sygnalizuja nadejscie bardzo silnego wiatru."))
	)
)

(defrule rule_pogodnie
	(chmura cirrostratus | cumulus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie powinno byc pogodnie."))
	)
)

(defrule rule_wiatr_cumulusowy
	(chmura cumulus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie powinien wystepowac umiarkowany, porywisty wiatr. Idealny dla zeglarzy!"))
		(konkluzja (tekst "Moga wystepowac szkwaly."))
		(konkluzja (tekst "Nalezy obserwowac chmury, jesli zaczna sie laczyc i zageszczac moze wystapic nagly opad."))
	)
)

(defrule rule_burza
	(chmura cumulonimbus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie przewidywane sa burze z blyskawicami. Nalezy spodziewac sie obfitych opadow deszczu."))
		(konkluzja (tekst "Mozliwe wystapowanie szotrmow."))
		(konkluzja (tekst "Wiatr bardzo silny, niebezpieczny."))
	)
)

(defrule rule_opady
	(chmura altostratus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie przewidywane sa opady deszczu lub sniegu."))
	)
)

(defrule rule_trzydniowka
	(chmura stratus)
=>
	(assert
		(konkluzja (tekst "Stratusy na niebie sygnalizuja trzydniowke."))
	)
)

(defrule rule_umiarkowany_wiatr
	(chmura stratus)
	(chmura cirrocumulus)
=>
	(assert
		(konkluzja (tekst "Wiatr bedzie umiarkowany, ze sklonnoscia do porywow."))
	)
)

(defrule rule_przelotne_opady
	(chmura stratocumulus)
=>
	(assert
		(konkluzja (tekst "Przewidywane sa przelotne opady."))
	)
)

(defrule rule_dlugi_opad
	(chmura nimbostratus)
=>
	(assert
		(konkluzja (tekst "W najblizszym czasie nalezy sie spodziewac ciaglych, obfitych i dlugotrwalych opadow deszczu."))
	)
)

;====================================================================================================================================================================
;============================================================================ OZNAKi ================================================================================
;====================================================================================================================================================================


(defrule rule_dodaj
	?i <- (dodaj)
	?j <- (licznik_oznak ?x)
=>
	(retract ?i ?j)
	(assert 
		(licznik_oznak (+ ?x 1))
	)
)

(defrule rule_odejmij
	?i <- (odejmij)
	?j <- (licznik_oznak ?x)
=>
	(retract ?i ?j)
	(assert 
		(licznik_oznak (- ?x 1))
	)
)

(defrule rule_void
	?i <- (bez_zmian)
=>
	(retract ?i)
)

(defrule rule_loty_ptakow
	(chmura ?)
=>
	(assert
		(pytanie	(id 21)	(tekst "Okresl wysokosc na jakiej lataja ptaki."))
		(odpowiedz	(id 21) (tekst "nisko")						(token "odejmij"))
		(odpowiedz	(id 21) (tekst "wysoko")					(token "dodaj"))
		(odpowiedz	(id 21) (tekst "nie wiem")					(token "bez_zmian"))
		(go_oznaki2)
	)
)


(defrule rule_pszczoly
	?i <- (go_oznaki2)
=>
	(retract ?i)
	(assert
		(pytanie	(id 22)	(tekst "Okresl widocznosc szczytow gorskich."))
		(odpowiedz	(id 22) (tekst "Doskonala")					(token "odejmij"))
		(odpowiedz	(id 22) (tekst "Szczyty gorskie sa zamglone")			(token "dodaj"))
		(odpowiedz	(id 22) (tekst "nie wiem")					(token "bez_zmian"))	
		(go_oznaki3)
	)
)

(defrule rule_rosa
	?i <- (go_oznaki3)
=>
	(retract ?i)
	(assert
		(pytanie	(id 23)	(tekst "Okresl obecnosc rosy na trawie."))
		(odpowiedz	(id 23) (tekst "brak")						(token "odejmij"))
		(odpowiedz	(id 23) (tekst "obfita")					(token "dodaj"))
		(odpowiedz	(id 23) (tekst "nie wiem")					(token "bez_zmian"))	
		(go_oznaki4)
	)
)

(defrule rule_niebo_noca
	?i <- (go_oznaki4)
=>
	(retract ?i)
	(assert
		(pytanie	(id 24)	(tekst "Okresl charakter nieba noca."))
		(odpowiedz	(id 24) (tekst "Gwiazdy migocza, a ksiezyc otacza ruda poswiata.")				(token "odejmij"))
		(odpowiedz	(id 24) (tekst "Ksiezyc swieci jasnym swiatlem, a gwiazdy nie migocza.")			(token "dodaj"))
		(odpowiedz	(id 24) (tekst "nie wiem")									(token "bez_zmian"))	
		(go_oznaki5)
	)
)

(defrule rule_odcien_nieba
	?i <- (go_oznaki5)
=>
	(retract ?i)
	(assert
		(pytanie	(id 25)	(tekst "Okresl zachodzace slonce."))
		(odpowiedz	(id 25) (tekst "Duze czerwonokrwiste.")						(token "odejmij"))
		(odpowiedz	(id 25) (tekst "Zlocisto-czerwone i otoczone zolta lub rozowa zorza.")		(token "dodaj"))
		(odpowiedz	(id 25) (tekst "nie wiem")							(token "bez_zmian"))	
		(go_oznaki6)
	)
)

(defrule rule_tecza
	?i <- (go_oznaki6)
=>
	(retract ?i)
	(assert
		(pytanie	(id 30)	(tekst "Okresl obecnosc teczy w ciagu dnia."))
		(odpowiedz	(id 30) (tekst "Obecna ranem lub przedpoludniem")		(token "odejmij"))
		(odpowiedz	(id 30) (tekst "Obecna popoludniu")				(token "dodaj"))
		(odpowiedz	(id 30) (tekst "Brak / nie wiem")				(token "bez_zmian"))	
		(go_oznaki7)
	)
)

(defrule rule_dzwieki
	?i <- (go_oznaki7)
=>
	(retract ?i)
	(assert
		(pytanie	(id 26)	(tekst "Czy oddalone dzwieki sa dobrze slyszalne?"))
		(odpowiedz	(id 26) (tekst "tak")						(token "odejmij"))
		(odpowiedz	(id 26) (tekst "nie")						(token "bez_zmian"))	
		(go_oznaki8)
	)
)

(defrule rule_zmiana_wiatru
	?i <- (go_oznaki8)
=>
	(retract ?i)
	(assert
		(pytanie	(id 27)	(tekst "Czy wiatr nagle zmienia swoj kierunek?"))
		(odpowiedz	(id 27) (tekst "tak")						(token "odejmij"))
		(odpowiedz	(id 27) (tekst "nie")						(token "bez_zmian"))	
		(go_oznaki9)
	)
)

(defrule rule_mocniejszy_poludniowy_wiatr
	?i <- (go_oznaki9)
=>
	(retract ?i)
	(assert
		(pytanie	(id 28)	(tekst "Czy wiatr sie wzmaga w poludnie?"))
		(odpowiedz	(id 28) (tekst "tak")						(token "dodaj"))
		(odpowiedz	(id 28) (tekst "nie")						(token "bez_zmian"))	
		(go_oznaki10)
	)
)

(defrule rule_chlodna_trawa
	?i <- (go_oznaki10)
=>
	(retract ?i)
	(assert
		(pytanie	(id 29)	(tekst "Czy o zmierzchu trawa jest chlodniejsza od powietrza ?"))
		(odpowiedz	(id 29) (tekst "tak")						(token "dodaj"))
		(odpowiedz	(id 29) (tekst "nie")						(token "bez_zmian"))	
		(oznaki_koniec)
	)
)

(defrule rule_koniec
	?i <- (oznaki_koniec)
=>
	(retract ?i)
	(assert
		(pytanie	(id 40) (tekst "Wygenerowac prognoze pogody?"))
		(odpowiedz	(id 40) (tekst "Tak")	(token "przelicz_oznaki"))
	)
)

;<-8;8> - przedzial oznak
(defrule rule_oznaki_raczej_plus
	(przelicz_oznaki)
	(licznik_oznak ?x)
	(test (> ?x 2))
	(test (< ?x 6))
=>
	(assert
		(konkluzja (tekst "Ogolna prognoza pogody na podstawie popularnych zjawisk sugeruje prawdopodobna poprawe pogody"))
	)
)

(defrule rule_oznaki_na_pewno_plus
	(przelicz_oznaki)
	(licznik_oznak ?x)
	(test (> ?x 5))
=>
	(assert
		(konkluzja (tekst "Ogolna prognoza pogody na podstawie popularnych zjawisk sugeruje znaczaca poprawe pogody"))
	)
)

(defrule rule_raczej_minus
	(przelicz_oznaki)
	(licznik_oznak ?x)
	(test (> ?x -6))
	(test (< ?x -2))
=>
	(assert
		(konkluzja (tekst "Ogolna prognoza pogody na podstawie popularnych zjawisk sugeruje prawdopodobne pogorszenie pogody"))
	)
)

(defrule rule_na_pewno_minus
	(przelicz_oznaki)
	(licznik_oznak ?x)
	(test (< ?x -5))
=>
	(assert
		(konkluzja (tekst "Ogolna prognoza pogody na podstawie popularnych zjawisk sugeruje znaczace pogorszenie pogody"))
	)
)



(defrule rule_answered
	?x <- (answered ?q)
	?y <- (pytanie (id ?q) (tekst ?))
=>
	(retract ?x ?y)
)