class Corso(var id: Int, var titolo: String, var numeroOre: Int, var costo: Double, var descrizione: String, var programma: String,
            var livello: String, var codice: String, var edizioni: MutableList<EdizioneCorso?> = mutableListOf()) {

    companion object Utils {
        fun parse(line: String): Corso {
            val tokens = line.split(",")
            val corso = Corso(tokens[0].toInt(),tokens[1],tokens[2].toInt(),tokens[3].toDouble(),tokens[4],tokens[5],tokens[6],tokens[7])
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
            risultatoReport += "Edizione: ${n.id} - Codice: ${n.codice} - Data inizio: ${n.dataInizio} - Data fine: ${n.dataFine} - Costo: ${n.costo} euro\n"
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


}