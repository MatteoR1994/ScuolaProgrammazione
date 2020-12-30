import java.io.File
import java.lang.Exception
import java.time.LocalDate

fun main() {
    //val r = MemoryRepository()
    val r = FileRepository()
    val ui = UI(r)
    ui.start()

}