import java.time.LocalDate

class EdizioneCorso(var id: Int, var corso: Corso, var dataInizio: LocalDate, var dataFine: LocalDate, var codice: String,
                    var moduli: List<ModuliCorso>, var numeroOre: Int, var costo: Double) {
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
}