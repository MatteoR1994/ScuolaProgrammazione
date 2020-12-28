import java.time.LocalDate

class Docente(id: Int, nome: String, cognome: String, dataNascita: LocalDate, codiceFiscale: String,
              email: String, telefono: String, var costoOrario: Double, var partitaIva: String, var abilita: MutableList<Abilita>) : Persona(id,nome,
              cognome, dataNascita, codiceFiscale, email, telefono) {
                  fun aggiungiAbilita(abilita: Abilita) {
                      this.abilita.add(abilita)
                  }
}