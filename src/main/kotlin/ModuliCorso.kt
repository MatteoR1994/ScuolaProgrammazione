class ModuliCorso(var id: Int, var nome: String, var descrizione: String, var edizioneCorso: EdizioneCorso,
                  numeroOre: Int, var docente: Docente) {
    var numeroOre = numeroOre
    get() = field
    set(value) {
        if(value>8) {
            numeroOre = field
        }
    }
}