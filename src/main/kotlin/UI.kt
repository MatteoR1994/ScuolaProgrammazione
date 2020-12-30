class UI(val repository: CourseRepository) {
    var mainMenu = "Benvenuto, cosa vuoi fare?\nac = Aggiungere un nuovo corso\nae = Aggiungere una nuova edizione a un corso\n" +
            "dc = Cancellare un corso\nic = Vedere l'incasso totale della scuola\nem = Vedere l'edizione più costosa\n" +
            "es = Vedere l'edizione meno costosa\nevm = Stampare il costo medio delle edizioni\nr = Stampare il report dei corsi\nq = Esci"
    // OK r = report / OK q = uscita
    // ac = aggiunta nuovo corso ->chiede dati e aggiunge corso
    // ae = aggiunge edizione a un corso -> chiede id corso, e dati edizione
    // OK dc = cancellare corso -> id corso da cancellare
    // OK ic = incasso totale della scuola (non ritorna niente), ogni edizione corso ha 10 iscritti (costo x10)
    // OK em = id dell'edizione corso che costa di più
    // OK es = id dell'edizione corso che costa di meno
    // OK evm = valore medio costi edizione corso
    // OPZIONALE: emm = la moda (frequenza massima) delle edizioni del corso
    // OPZIONALE: eme = la mediana (valore esattamente in mezzo) delle edizioni del corso
    //val memoryRepository = MemoryRepository()
    fun start() {
        do {
            println("$mainMenu")
            var input = readLine()
            val elencoCorsi = repository.readCourses()
            when(input) {
                "q" -> println("Usciamo dal programma")
                "r" -> {
                    //val elencoCorsi = repository.readCourses()
                    println("Numero corsi: ${elencoCorsi.size}")
                    for(c in elencoCorsi) {
                        println(c)
                        println("********************************************")
                        println("${c.report()}")
                    }
                }
                "dc" -> {
                    //val elencoCorsi = repository.readCourses()
                    println("Inserisci l'id del corso che vuoi cancellare, per piacere:")
                    var input = readLine()
                    if(input != null) {
                        val dropId: Int = input!!.toInt()
                        //println(dropId)
                        var corso: Corso? = null
                        /*var found = elencoCorsi.find(){
                            //pippo -> pippo.id==dropId
                            it.id==dropId // it = elemento su cui stiamo lavorando, ovvero il parametro ricevuto dalla funzione
                        }*/
                        var found = elencoCorsi.find(){ it.id==dropId }
                        /*for (c in elencoCorsi) {
                            if (c.id == dropId) {
                                corso = c
                                //elencoCorsi.remove(c)
                                break
                            }
                        }*/
                        //elencoCorsi.remove(corso)
                        elencoCorsi.remove(found)
                        for(cx in elencoCorsi) {
                            if(found!!.equals(cx)) {
                                // ...
                            }
                        }
                        for (c1 in elencoCorsi) {
                            println("${c1.titolo}")
                        }
                    }
                }
                "ic" -> {
                    //val elencoCorsi = repository.readCourses()
                    var costoTotale = 0.0
                    for(c in elencoCorsi) {
                        for(e in c.edizioni) {
                            costoTotale += e!!.costo*10
                        }
                    }
                    println("L'incasso, attuale, totale della scuola è: $costoTotale")
                }
                "em" -> {
                    //val elencoCorsi = repository.readCourses()
                    var costoMassimo = 0.0
                    for(c in elencoCorsi) {
                        for(e in c.edizioni) {
                            if(e!!.costo > costoMassimo) {
                                costoMassimo = e!!.costo
                            }
                        }
                    }
                    println("Il costo massimo di tutte le edizioni dei corsi attualmente registrati è: $costoMassimo")
                }
                "es" -> {
                    //val elencoCorsi = repository.readCourses()
                    var costoMinimo = elencoCorsi[0].edizioni[0]!!.costo
                    for(c in elencoCorsi) {
                        for(e in c.edizioni) {
                            if(e!!.costo < costoMinimo) {
                                costoMinimo = e!!.costo
                            }
                        }
                    }
                    println("Il costo minimo di tutte le edizioni dei corsi attualmente registrati è: $costoMinimo")
                }
                "evm" -> {
                    //val elencoCorsi = repository.readCourses()
                    var mediaEdizioni = 0.0
                    for(c in elencoCorsi) {
                        var totaleCostiEdizioniCorso = 0.0
                        println("Corso: ${c.titolo}")
                        for(e in c.edizioni) {
                            totaleCostiEdizioniCorso += e!!.costo
                        }
                        mediaEdizioni = totaleCostiEdizioniCorso / c.edizioni.size
                        println("Media costo edizioni: $mediaEdizioni\n")
                    }
                }
            }
        } while(input != "q")
    }
}