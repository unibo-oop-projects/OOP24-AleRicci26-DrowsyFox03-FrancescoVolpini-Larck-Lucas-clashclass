# Clash Class
Progetto per il corso di Programmazione ad Oggetti, anno 2024/25

## E-Mail dei componenti:
- alessandro.ricci47@studio.unibo.it
- lucasantonio.leonte@studio.unibo.it
- francesco.volpini2@studio.unibo.it
- lorenzo.bulfoni@studio.unibo.it

## Guida Utente

All'avvio del programma, dopo il caricamento del villaggio utente, sarà possibile interagire con due pulsanti:
- Shop: aprirà l'interfaccia utente dedicata al negozio, in cui è possibile acquistare nuovi edifici
- Battle: farà entrare il gioco in modalità "battaglia", da cui seguono i prossimi comandi

In modalità "battaglia", verranno visualizzate, in basso, le truppe possedute.\\
L'unico input possibile sarà il click del mouse, prima per selezionare un tipo di truppa (barbari o arcieri), poi sul villaggio per posizionare il tipo di truppa selezionata (ad ogni click corrisponde la creazione di un'istanza di truppa). Sarà possibile posizionare le truppe solo in celle appartenenti al villaggio (ad eccezione quindi della parte esterna più scura) e solo in celle che non siano già occupate da edifici.

**Importante**: il gioco tenterà di leggere i dati del villaggio dell'utente da un file con percorso "/Villages-Data/player-village.csv", relativo alla stessa cartella in cui si trova il fat-jar dell'applicazione.\\
Se questo file non è presente, il gioco caricherà inizialmente un villaggio di default dalle risorse del progetto, per poi scrivere salvare lo stesso file su disco appena possibile.\\
Il programma deve quindi avere i permessi di scrittura di file su disco per comportarsi nel modo atteso, altrimenti caricherà sempre il villaggio di default dalle risorse.\\
In caso le truppe possedute finiscano (perché le si è schierate tutte in battaglia), e non sia quindi più possibile effettuare una battaglia, è necessario modificare manualmente il numero di truppe possedute nel file di salvataggio, impostato i valori di "BARBARIAN" e "ARCHER" (settima e ottava riga del file) ad un numero superiore a zero.
Finita la battaglia, si aprirà un report con le statistiche relative alla battaglia stessa, e, tramite la pressione del pulsante "Go Back to Village", sarà possibile tornare al proprio villaggio, ricominciando dal punto di partenza.