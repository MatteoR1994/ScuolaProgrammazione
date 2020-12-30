import java.io.File
import java.io.IOException
import kotlin.system.exitProcess

class FileRepository : CourseRepository {

    var corsi: MutableList<Corso> = mutableListOf()
    var memory: MemoryRepository

    init {
        try {
            var lines: List<String> = File("src/main/resources/corsi.csv").readLines()
            val iter = lines.iterator()
            while(iter.hasNext()) {
                val courseLine = iter.next()
                val corso = Corso.parse(courseLine)
                corsi.add(corso)
                val numEdizioni = corso.edizioni.size
                //println("Corso: ${corso.titolo}")
                for(i in 0 until numEdizioni) {
                    val lineeEdizione = iter.next()
                    // Crea nuova edizione da questa linea
                    val edizione = EdizioneCorso.parse(lineeEdizione,corso)
                    // Inserisci nuova edizione in posizione i delle edizioni del corso
                    //corso.edizioni.add(i,edizione)
                    corso.edizioni[i] = edizione
                }
            }
            memory = MemoryRepository(corsi)
        } catch(e: IOException) {
            println("La sorgente dei dati al momento non è disponibile, contattare l'amministratore.")
            exitProcess(1)
        } finally {
            //println("Lettura file finita.")
        }
    }

    override fun readCourses(): MutableList<Corso> {
        return memory.readCourses()
    }

    override fun readCourseEditions(): MutableList<EdizioneCorso?> {
        return memory.readCourseEditions()
    }

    override fun courseById(id: Int): Corso? {
        return memory.courseById(id)
    }

    override fun courseEditionsByCourseId(id: Int): List<EdizioneCorso?>? {
        return memory.courseEditionsByCourseId(id)
    }

    override fun add(corso: Corso) {
        return memory.add(corso)
    }

    override fun close() {
        var file: File = File("src/main/resources/corsi.csv")
        val pw = file.printWriter()
        //pw.println() // Permette di scrivere una linea di testo sul file, passando una stringa.
        for(c in corsi) {
            pw.println(c.toCsvLine())
            for(e in c.edizioni) {
                pw.println(e!!.toCsvLine())
            }
        }
    }
}