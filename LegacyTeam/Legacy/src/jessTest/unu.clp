(deffacts set-de-fapte
(culoare alb)
(culoare violet)
(culoare verde)
)
(reset)  /*erases the facts from the working memory, adding the above ones */
(defrule print-colors
(culoare ?x)
=>
(printout t :”culoare: “ ?x)
)
(defrule retract-colors-not-violet
?address <- (color ?x)   /* locates a fact and stores it’s address */
(test (neq ?x violet))  ; if ?x is not violet */
=>
(retract ?address)  /* retracts the fact that matches the condition
)
(run) /* matches the facts with the rules , thus “running” the program */
(facts)