class Corso(var id: Int, var titolo: String, var numeroOre: Int, var costo: Double, var descrizione: String, var programma: String,
            var livello: String, var codice: String, var edizioni: MutableList<EdizioneCorso> = mutableListOf()) {
    fun report() { // 1: Stampa codice - data inizio/fine - costo; 2: Costo maggiore e minore delle edizioni
        var costoMaggiore = 0.0
        //var costoMinore = 0.0
        var costoMinore = edizioni[0].costo
        for(n in edizioni) {
            println("Edizione: ${n.id} - Codice: ${n.codice} - Data inizio: ${n.dataInizio} - Data fine: ${n.dataFine} - Costo: ${n.costo} euro\n")
            if(n.costo>costoMaggiore) {
                costoMaggiore=n.costo
            } else if(n.costo<costoMinore) {
                costoMinore=n.costo
            }
        }
        println("Costo maggiore: $costoMaggiore - Costo minore: $costoMinore")
    }
}