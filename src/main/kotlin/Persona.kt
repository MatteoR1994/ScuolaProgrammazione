import java.time.LocalDate

abstract class Persona(var id: Int, var nome: String, var cognome: String, var dataNascita: LocalDate, var codiceFiscale: String,
                       var email: String, var telefono: String) {

}