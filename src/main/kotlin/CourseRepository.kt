interface CourseRepository {
    //fun readCourses() : List<Corso>
    fun readCourses() : MutableList<Corso>
    //fun readCoursEditions() : List<EdizioneCorso>
    fun readCourseEditions() : MutableList<EdizioneCorso?>
    fun courseById(id: Int) : Corso?
    //fun courseEditionsByCourseId(id: Int) : List<EdizioneCorso>?
    fun courseEditionsByCourseId(id: Int) : List<EdizioneCorso?>?
    fun add(corso: Corso) : Unit
    fun close()
}