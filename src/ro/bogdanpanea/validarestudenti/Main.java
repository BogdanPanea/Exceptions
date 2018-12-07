package ro.bogdanpanea.validarestudenti;

import java.time.LocalDate;
import java.util.logging.Logger;

public class Main {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.addStudent( "Bobi", "Maniu", "12/04/2017", "M", "1234" );
        studentRepository.addStudent( "Ana", "Petrache", "28/08/1995", "f", "1235" );
        studentRepository.retrieveStudents("1");

        //System.out.println( studentRepository.getStudents().get(1).getGender() );
//        LocalDate today = LocalDate.now();
//        System.out.println( today );

//        studentRepository.deleteStudent( "1235" );
//        studentRepository.retrieveStudents();
        studentRepository.retrieveStudents();
    }
}
