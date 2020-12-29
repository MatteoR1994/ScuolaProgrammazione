import java.time.LocalDate

class EdizioneCorso(var id: Int, var corso: Corso, var dataInizio: LocalDate, var dataFine: LocalDate, var codice: String,
                    var moduli: List<ModuliCorso> = listOf(), var numeroOre: Int, var costo: Double) {

    companion object Utils {
        fun parse(line: String, c: Corso): EdizioneCorso {
            val tokens = line.split(",")
            val edizione = EdizioneCorso(tokens[0].trim().toInt(),c,LocalDate.parse(tokens[1].trim()),LocalDate.parse(tokens[2].trim()),
                tokens[3], mutableListOf(),tokens[4].toInt(),tokens[5].toDouble())
            return edizione
        }
    }

    fun checkNumeroOre() : Boolean {
        var sommaOre = 0
        for(n in moduli) {
            sommaOre += n.numeroOre
        }
        /*if(sommaOre == numeroOre) {
            return true
        } else {
            return false
        }*/
        return sommaOre==numeroOre
    }

    fun toCsvLine() : String {
        return "$id,$dataInizio,$dataFine,$codice,$numeroOre,$costo"
    }

    // 256, 2020-12-14, 2021-03-12,"Z523M3",200,1800.0
}