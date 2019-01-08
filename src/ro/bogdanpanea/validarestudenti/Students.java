package ro.bogdanpanea.validarestudenti;

public interface Students {

    void addStudent(String firstName, String lastName, String dateOfBirth, String gender, String id) throws StudentRepositoryExceptions;

    void deleteStudent(String id) throws StudentRepositoryExceptions;

    void retrieveStudents(String age) throws StudentRepositoryExceptions;

    void listOrderedStudents(String studentsListOrder) throws StudentRepositoryExceptions;
}
