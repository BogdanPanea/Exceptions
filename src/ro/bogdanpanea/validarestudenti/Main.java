package ro.bogdanpanea.validarestudenti;

public class Main {

       public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.addStudent( "Bobi", "Maniu", "12/04/2017", "t", "1234" );
        studentRepository.addStudent( "Ana", "Petrache", "28/08/1995", "f", "1235" );
        studentRepository.listOrderedStudents("by last name");

    }
}
