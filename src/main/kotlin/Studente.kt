import java.time.LocalDate

class Studente(id: Int, nome: String, cognome: String, dataNascita: LocalDate, codiceFiscale: String,
               email: String, telefono: String, var livello: String, var abilita: List<Abilita>) : Persona(id,nome, cognome,
               dataNascita, codiceFiscale, email, telefono) {
}