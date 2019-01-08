package ro.bogdanpanea.validarestudenti;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {

        String orderType = "byLastName";

        try {

            StudentRepository studentRepository = new StudentRepository();
            studentRepository.addStudent("Bobi", "Maniu", "12/04/2017", "M", "1234");
            studentRepository.addStudent("Ana", "Petrache", "28/08/1995", "f", "1235");
            studentRepository.listOrderedStudents(orderType);

        } catch (StudentRepositoryExceptions e) {

            LOGGER.log(Level.SEVERE, e.getMessage());

        }

    }
}
