interface CourseRepository {
    fun readCourses() : List<Corso>
    fun readCoursEditions() : List<EdizioneCorso>
    fun courseById(id: Int) : Corso?
    fun courseEditionsByCourseId(id: Int) : List<EdizioneCorso>?
    fun add(corso: Corso) : Unit
}