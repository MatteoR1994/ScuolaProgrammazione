import java.io.File
import java.lang.Exception
import java.time.LocalDate

fun main() {
    /*val mr = MemoryRepository()
    val ui = UI(mr)
    ui.start()*/
    try {
        var lines: List<String> = File("src/main/resources/corsi.csv").readLines()
        //lines.forEach { line -> println(line) }
        //println(lines[0])

        val tokens: List<String> = lines[0].split(",") // Divide una stringa in una lista
        val numeroEdizioni = tokens[tokens.size-1]
        //println(numeroEdizioni)
        val iter = lines.iterator()
        while(iter.hasNext()) {
            val courseLine = iter.next()
            val corso = Corso.parse(courseLine)
            val numEdizioni = corso.edizioni.size
            println("Corso: ${corso.titolo}")
            for(i in 0 until numEdizioni) {
                val lineeEdizione = iter.next()
                // Crea nuova edizione da questa linea
                val pezzi = lineeEdizione.split(",")
                val edizione = EdizioneCorso(pezzi[0].toInt(),corso,LocalDate.parse("2020-11-10"),LocalDate.parse("2020-11-10"),pezzi[4], mutableListOf(),pezzi[6].toInt(),pezzi[7].toDouble())
                // Inserisci nuova edizione in posizione i delle edizioni del corso
                corso.edizioni.add(i,edizione)
                //corso.report()
                println("${corso.edizioni[i]!!.costo}")
            }
            println("")
        }
    } catch(e:Exception) {
        e.printStackTrace()
    } finally {
        println("Lettura file finita.")
    }
}