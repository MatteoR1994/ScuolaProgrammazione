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
        println(lines[0])

        val tokens: List<String> = lines[0].split(",") // Divide una stringa in una lista
        val numeroEdizioni = tokens[tokens.size-1]
        println(numeroEdizioni)
        val iter = lines.iterator()
        while(iter.hasNext()) {
            val courseLine = iter.next()
            val corso = Corso.parse(courseLine)
            val numEdizioni = corso.edizioni.size
            for(i in 0 until numEdizioni) {
                val lineeEdizione = iter.next()
                // Crea nuova edizione da questa linea
                // Inserisci nuova edizione in posizione i delle edizioni del corso
            }
        }
    } catch(e:Exception) {
        e.printStackTrace()
    } finally {
        println("Lettura file finita.")
    }
}