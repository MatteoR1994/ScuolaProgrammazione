import java.time.LocalDate

class MemoryRepository(val corsi: MutableList<Corso> = mutableListOf()) : CourseRepository {
    //val corsi: MutableList<Corso> = mutableListOf<Corso>()
    init {
        if(corsi.size==0) {
            val c1 = Corso(3657, "APP IOS", 200, 1800.0, "Imparare a programmare app Android",
                "programma del corso", "Difficile", "A2410"
            )
            val c2 = Corso(6987, "HTML-PHP-CSS", 400, 2500.0, "Programmazione web", "prova",
                "Intermedio", "I352N6"
            )
            val ed1 = EdizioneCorso(256, c1, LocalDate.parse("2020-12-14"), LocalDate.parse("2021-03-12"), "Z523M3",
                mutableListOf(), 200, 1800.0
            )
            val ed2 = EdizioneCorso(698, c1, LocalDate.parse("2020-11-10"), LocalDate.parse("2021-04-20"), "A358ZZ",
                mutableListOf(), 300, 2000.0
            )
            val ed3 = EdizioneCorso(123, c1, LocalDate.parse("2021-02-04"), LocalDate.parse("2021-06-16"), "C65H99",
                mutableListOf(), 250, 1000.0
            )
            val ed4 = EdizioneCorso(436, c2, LocalDate.parse("2020-03-10"), LocalDate.parse("2021-07-20"), "BB09TR",
                mutableListOf(), 500, 2500.0
            )
            val ed5 = EdizioneCorso(985, c2, LocalDate.parse("2020-12-09"), LocalDate.parse("2021-04-20"), "658GF9",
                mutableListOf(), 450, 1560.0
            )

            c1.edizioni.add(ed1)
            c1.edizioni.add(ed2)
            c1.edizioni.add(ed3)
            c2.edizioni.add(ed4)
            c2.edizioni.add(ed5)

            corsi.add(c1)
            corsi.add(c2)
        }
    }

    override fun readCourses(): List<Corso> {
        //var corsi = List<Corso>
        return corsi
    }

    override fun readCourseEditions(): MutableList<EdizioneCorso?> {
        val edizioniCorso = mutableListOf<EdizioneCorso?>()
        for(c in corsi) {
            edizioniCorso.addAll(c.edizioni)
        }
        return edizioniCorso
    }

    override fun courseById(id: Int): Corso? {
        for(c in corsi) {
            if(c.id==id) {
                return c
            }
        }
        return null
    }

    override fun courseEditionsByCourseId(id: Int): List<EdizioneCorso?>? {
        for(c in corsi) {
            if(c.id==id) {
                return c.edizioni
            }
        }
        return null
    }

    override fun add(corso: Corso) {
        corsi.add(corso)
    }

    override fun close() {

    }
}