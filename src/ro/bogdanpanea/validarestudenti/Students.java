package ro.bogdanpanea.validarestudenti;

public interface Students {

    void addStudent(String firstName, String lastName, String dateOfBirth, String gender, String id);

    void deleteStudent(String id);

    void retrieveStudents(String age);

    void listOrderedStudents(String studentsListOrder);
}
