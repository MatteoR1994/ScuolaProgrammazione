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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Corso

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true // Controllo se ho passato me stesso
//        if(other==null) {
//            return false
//        }
//        if(this.javaClass!=other.javaClass) {
//            return false
//        }
//        other as Corso // Considera other come se fosse di classe Corso = cast
//        return other.id==id
//    }



}