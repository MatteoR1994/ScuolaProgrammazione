import java.time.LocalDate

class UI(val repository: CourseRepository) {
    val mainMenu = "Benvenuto, cosa vuoi fare?\nac = Aggiungere un nuovo corso\nae = Aggiungere una nuova edizione a un corso\n" +
            "dc = Cancellare un corso\nic = Vedere l'incasso totale della scuola\nem = Vedere l'edizione più costosa\n" +
            "es = Vedere l'edizione meno costosa\nevm = Stampare il costo medio delle edizioni\nr = Stampare il report dei corsi\n" +
            "emm = La moda (frequenza massima) dei costi delle edizioni del corso\neme = Mediana costi di tutte le edizioni\n" +
            "q = Esci (e sovrascrivi i dati)"
    // OK r = report / OK q = uscita
    // OK ac = aggiunta nuovo corso ->chiede dati e aggiunge corso
    // OK ae = aggiunge edizione a un corso -> chiede id corso, e dati edizione
    // OK dc = cancellare corso -> id corso da cancellare
    // OK ic = incasso totale della scuola (non ritorna niente), ogni edizione corso ha 10 iscritti (costo x10)
    // OK em = id dell'edizione corso che costa di più
    // OK es = id dell'edizione corso che costa di meno
    // OK evm = valore medio costi edizione corso
    // OK OPZIONALE: emm = la moda (frequenza massima) delle edizioni del corso
    // OK OPZIONALE: eme = la mediana (valore esattamente in mezzo) delle edizioni del corso
    //val memoryRepository = MemoryRepository()
    fun start() {
        try {
            val elencoCorsi = repository.readCourses()
            val elencoEdizioni = repository.readCourseEditions()
            do {
                println("$mainMenu")
                val input = readLine()
                when (input) {
                    "q" -> println("Usciamo dal programma")
                    "r" -> {
                        println("Numero corsi: ${elencoCorsi.size}")
                        for (c in elencoCorsi) {
                            println("********************************************")
                            println("${c.report()}")
                        }
                    }
                    "ac" -> {
                        aggiungiCorso()
                    }
                    "ae" -> {
                        aggiungiEdizione()
                    }
                    "dc" -> {
                        cancellaCorso(elencoCorsi)
                    }
                    "ic" -> {
                        val incasso = incassoTotale(elencoCorsi)
                        println("L'incasso, attuale, totale della scuola è: $incasso")
                    }
                    "em" -> {
                        val costoMassimoEdizioni = edizionePiuCostosa(elencoCorsi)
                        println("Il costo massimo di tutte le edizioni dei corsi attualmente registrati è: $costoMassimoEdizioni")
                    }
                    "es" -> {
                        val costoMinimoEdizioni = edizioneMenoCostosa(elencoCorsi)
                        println("Il costo minimo di tutte le edizioni dei corsi attualmente registrati è: $costoMinimoEdizioni")
                    }
                    "evm" -> {
                        costoMedioEdizioneDiOgniCorso(elencoCorsi)
                    }
                    "emm" -> {
                        modaCostiEdizioni(elencoEdizioni)
                    }
                    "eme" -> {
                        val mediana = medianaCostiEdizioni(elencoEdizioni)
                        println("Mediana dei costi di tutte le edizioni: $mediana")
                    }
                }
            } while (input != "q")
        } finally {
            repository.close()
        }
    }

    fun aggiungiCorso() { // ac
        val partiCorso = arrayOf("ID","Titolo","Numero di ore","Costo","Descrizione",
            "Programma","Livello","Codice","Numero delle sue edizioni")
        val partiCorsoInputUtente = Array<String>(9){ i -> ""}
        for(i in 0..8) {
            println("Inserire ${partiCorso[i]}: ")
            val input = readLine()
            if (input != null) {
                partiCorsoInputUtente[i] = input
            }
        }
        val corso = Corso(partiCorsoInputUtente[0].trim().toInt(),partiCorsoInputUtente[1].trim(),
            partiCorsoInputUtente[2].trim().toInt(),partiCorsoInputUtente[3].trim().toDouble(),
            partiCorsoInputUtente[4].trim(),partiCorsoInputUtente[5].trim(),
            partiCorsoInputUtente[6].trim(),partiCorsoInputUtente[7].trim())
        repository.add(corso)
    }

    fun aggiungiEdizione() { // ae
        println("Inserire l'ID del corso desiderato: ")
        val input = readLine()
        if (input != null) {
            val coursId = input!!.toInt()
            val c = repository.courseById(coursId)
            if(c != null) {
                println("\nIl corso selezionato ha attualmente ${c.edizioni.size} edizioni salvate, inseriscine una nuova.")
                val partiEdizione = arrayOf("ID","Data di inizio","Data di fine","Codice","Numero delle ore","Costo")
                val partiEdizioneInput = Array<String>(6) { i -> "" }
                for (p in 0..5) {
                    println("Inserire ${partiEdizione[p]}: ")
                    val input = readLine()
                    if (input != null) {
                        partiEdizioneInput[p] = input
                    }
                }
                val edizione = EdizioneCorso(partiEdizioneInput[0].toInt(),c,LocalDate.parse(partiEdizioneInput[1].trim()),
                    LocalDate.parse(partiEdizioneInput[2].trim()),partiEdizioneInput[3],listOf(),
                    partiEdizioneInput[4].toInt(),partiEdizioneInput[5].toDouble())
                c.edizioni.add(edizione)
            }
        }
    }

    fun cancellaCorso(elenco: MutableList<Corso>) { // dc
        println("Inserisci l'id del corso che vuoi cancellare, per piacere:")
        val input = readLine()
        if (input != null) {
            val dropId: Int = input!!.toInt()
            val found = elenco.find() { it.id == dropId }
            elenco.remove(found)
            for (c1 in elenco) {
                println("${c1.titolo}")
            }
        }
    }

    fun incassoTotale(elenco: MutableList<Corso>): Double { // ic
        var costoTotale = 0.0
        for (c in elenco) {
            for (e in c.edizioni) {
                costoTotale += e!!.costo * 10
            }
        }
        return costoTotale
    }

    fun edizionePiuCostosa(elenco: MutableList<Corso>): Double { // em
        var costoMassimo = 0.0
        for (c in elenco) {
            for (e in c.edizioni) {
                if (e!!.costo > costoMassimo) {
                    costoMassimo = e!!.costo
                }
            }
        }
        return costoMassimo
    }

    fun edizioneMenoCostosa(elenco: MutableList<Corso>): Double { // es
        var costoMinimo = elenco[0].edizioni[0]!!.costo
        for (c in elenco) {
            for (e in c.edizioni) {
                if (e!!.costo < costoMinimo) {
                    costoMinimo = e!!.costo
                }
            }
        }
        return costoMinimo
    }

    fun costoMedioEdizioneDiOgniCorso(elenco: MutableList<Corso>) { // evm
        var mediaEdizioni: Double
        for (c in elenco) {
            var totaleCostiEdizioniCorso = 0.0
            println("Corso: ${c.titolo}")
            for (e in c.edizioni) {
                totaleCostiEdizioniCorso += e!!.costo
            }
            mediaEdizioni = totaleCostiEdizioniCorso / c.edizioni.size
            println("Media costo edizioni: $mediaEdizioni\n")
        }
    }

    fun modaCostiEdizioni(elenco: MutableList<EdizioneCorso?>) { // emm
        val valori: MutableList<Double> = mutableListOf()
        val listaRipetizioni: MutableList<Int> = mutableListOf()
        val listaMode: MutableList<Double> = mutableListOf()
        var ultimoMassimo = 0.0
        for(e in elenco) {
            valori.add(e!!.costo)
        }
        valori.sort()
        val listaSenzaRipetizioni = valori.distinct()
        for(el in listaSenzaRipetizioni) {
            listaRipetizioni.add(ripetizioni(el,valori))
        }
        val massimaRipetizione = findMax(listaRipetizioni)
        if(massimaRipetizione > 1) {
            for (e in listaSenzaRipetizioni) {
                if (ripetizioni(e, valori) > massimaRipetizione) {
                    listaMode.add(e)
                    ultimoMassimo = e
                } else if (ripetizioni(e, valori) == massimaRipetizione) {
                    if (e != ultimoMassimo) {
                        listaMode.add(e)
                    }
                }
            }
            //println("Lista sorgente senza ripetizioni:\n" + listaSenzaRipetizioni.joinToString(" - ") + "\n")
            //println("Lista ripetizioni:\n" + listaRipetizioni.joinToString(" - ") + "\n")
            println("Le mode sono:\n" + listaMode.joinToString(" - "))
        } else {
            println("Nessun elemento si ripete più di una volta.")
        }
    }

    fun medianaCostiEdizioni(elenco: MutableList<EdizioneCorso?>): Double { // eme
        val valori: MutableList<Double> = mutableListOf()
        for(e in elenco) {
            valori.add(e!!.costo)
        }
        valori.sort()
        val dimensioneLista = valori.size
        val mediana = if(dimensioneLista %2 == 0) {
            ( valori[((dimensioneLista-1)/2)] + valori[(dimensioneLista/2)]) / 2
        } else {
            valori[(dimensioneLista/2)]
        }
        return mediana
    }

    fun ripetizioni(element: Double, elenco: List<Double>): Int {
        var numeroRipetizioni = 0
        for(e in 0 until elenco.size) {
            if(elenco[e] == element) {
                numeroRipetizioni++
            }
        }
        return numeroRipetizioni
    }

    fun findMax(ripetizioni: MutableList<Int>): Int {
        var massimo = 0
        for(r in ripetizioni) {
            if(r > massimo) {
                massimo = r
            }
        }
        return massimo
    }
}