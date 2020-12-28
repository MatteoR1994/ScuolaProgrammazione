class UI(val memoryRepository: MemoryRepository) {
    val mainMenu = "Inserisci r per vedere il report dei corsi oppure q per uscire"
    //val memoryRepository = MemoryRepository()
    fun start() {
        do {
            println("$mainMenu")
            var input = readLine()
            when(input) {
                "q" -> println("Usciamo dal programma")
                "r" -> println("Stampiamo il report")
            }
        } while(input != "q")
    }
}