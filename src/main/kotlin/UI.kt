class UI(val repository: CourseRepository) {
    val mainMenu = "Inserisci r per vedere il report dei corsi oppure q per uscire"
    // r = report / q = uscita
    // ac = aggiunta nuovo corso ->chiede dati e aggiunge corso
    // ae = aggiunge edizione a un corso -> chiede id corso, e dati edizione
    // dc = cancellare corso -> id corso da cancellare
    // ic = incasso totale della scuola (non ritorna niente), ogni edizione corso ha 10 iscritti (costo x10)
    // em = id dell'edizione corso che costa di più
    // es = id dell'edizione corso che costa di meno
    // evm = valore medio costi edizione corso
    // OPZIONALE: emm = la moda (frequenza massima) delle edizioni del corso
    // OPZIONALE: eme = la mediana (valore esattamente in mezzo) delle edizioni del corso
    //val memoryRepository = MemoryRepository()
    fun start() {
        do {
            println("$mainMenu")
            var input = readLine()
            when(input) {
                "q" -> println("Usciamo dal programma")
                "r" -> {
                    //println("Stampiamo il report")
                    val elencoCorsi = repository.readCourses()
                    for(c in elencoCorsi) {
                        println("${c.report()}")
                    }
                }
            }
        } while(input != "q")
    }
}