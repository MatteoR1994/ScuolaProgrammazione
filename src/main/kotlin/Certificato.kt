import java.time.LocalDate

class Certificato(var id: Int, var edizione: EdizioneCorso, val valutazioneFinale: Int, var dataConseguimento: LocalDate,
                  var dataScadenza: LocalDate) {
}