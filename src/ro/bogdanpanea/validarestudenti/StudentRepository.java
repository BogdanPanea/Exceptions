package ro.bogdanpanea.validarestudenti;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentRepository implements Students {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void addStudent(String firstName, String lastName, String dateOfBirth, String gender, String id) {

        Student student = new Student( firstName, lastName, dateOfBirth, gender, id );
        students.add( student );

    }

    @Override
    public void deleteStudent(String id) {

        if (id != "") {

            if (isNumeric( id )) {
                boolean exist = false;
                Student student = null;
                for (Student s : students) {
                    if (s.getId().equals( id )) {
                        exist = true;
                        student = s;
                    }
                }
                if (exist) {

                    students.remove( student );
                    System.out.println( "A fost sters." );
                } else {

                }

            } else {

            }
        } else {
        }

    }

    @Override
    public void retrieveStudents(String age) {

        if (isNumeric( age )) {

            if (Integer.parseInt( age ) >= 0) {

                LocalDate today = LocalDate.now();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "d/MM/yyyy" );

                for (Student s : students) {

                    LocalDate birthday = LocalDate.parse( age, formatter );
                    Period p = Period.between( birthday, today );

                    if (p.getYears() == Integer.parseInt( age )) {

                        System.out.println( s );
                    }
                }

            } else {

            }
        } else {

        }

    }

    public void retrieveStudents() {

        for (Student s : students) {

            LOGGER.info( s.toString() );
        }

    }

    @Override
    public <T> void listOrderedStudents(T t) {

    }

    public static boolean isNumeric(String id) {
        try {
            double d = Double.parseDouble( id );
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }


}
