class Corso(var id: Int, var titolo: String, var numeroOre: Int, var costo: Double, var descrizione: String, var programma: String,
            var livello: String, var codice: String, var edizioni: MutableList<EdizioneCorso?> = mutableListOf()) {

    companion object Utils {
        fun parse(line: String): Corso {
            val tokens = line.split(",")
            val corso = Corso(tokens[0].trim().toInt(),tokens[1].trim(),tokens[2].trim().toInt(),tokens[3].trim().toDouble(),tokens[4].trim(),tokens[5].trim(),tokens[6].trim(),tokens[7].trim())
            corso.edizioni = MutableList(tokens[8].toInt()){i -> null}
            return corso
        }
    }

    fun report(): String { // 1: Stampa codice - data inizio/fine - costo; 2: Costo maggiore e minore delle edizioni
        var costoMaggiore = 0.0
        //var costoMinore = 0.0
        var costoMinore = edizioni[0]!!.costo
        var risultatoReport = ""
        for(e in edizioni) {
            val n: EdizioneCorso = e!!
            risultatoReport += "Corso: ${e.corso.titolo}, Edizione: ${n.id} - Codice: ${n.codice} - Data inizio: ${n.dataInizio} - Data fine: ${n.dataFine} - Costo: ${n.costo} euro\n"
            //println("Edizione: ${n.id} - Codice: ${n.codice} - Data inizio: ${n.dataInizio} - Data fine: ${n.dataFine} - Costo: ${n.costo} euro\n")
            if(n.costo>costoMaggiore) {
                costoMaggiore=n.costo
            } else if(n.costo<costoMinore) {
                costoMinore=n.costo
            }
        }
        //println("Costo maggiore: $costoMaggiore - Costo minore: $costoMinore")
        risultatoReport += "Costo maggiore: $costoMaggiore - Costo minore: $costoMinore"
        return risultatoReport
    }

    fun toCsvLine() : String {
        return "$id,$titolo,$numeroOre,$costo,$descrizione,$programma,$livello,$codice,${edizioni.size}"
    }

    // 3657,"APP Android",200,1800.0,"Imparare a programmare app Android","programma del corso","Difficile","A2410",3
}