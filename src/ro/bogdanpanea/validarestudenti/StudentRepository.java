package ro.bogdanpanea.validarestudenti;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository implements Students {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void addStudent(String firstName, String lastName, String dateOfBirth, String gender, String id) {

        try {
            Student student = new Student( firstName, lastName, dateOfBirth, gender, id );
            students.add( student );

        } catch (StudentRepositoryExceptions e) {

            LOGGER.log( Level.SEVERE, e.getMessage());
        }
    }
    @Override
    public void deleteStudent(String id) {

        try {
            if (!id.equals( "" )) {

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
                        LOGGER.info( "A fost sters." );
                    } else {

                        throw new StudentRepositoryExceptions( "Nu exista nicio persoana cu acest CNP !", "CNP inexistent." );

                    }

                } else {

                    throw new StudentRepositoryExceptions( "CNP- ul trebuie sa contina doar cifre !", "CNP incorect." );

                }
            } else {

                throw new StudentRepositoryExceptions( "CNP-ul nu poate fi vid !", "CNP vid." );
            }
        } catch (StudentRepositoryExceptions e) {

            LOGGER.log( Level.SEVERE, e.getMessage());
        }

    }

    @Override
    public void retrieveStudents(String age) {

        try {
            if (isNumeric( age )) {

                if (Integer.parseInt( age ) >= 0) {

                    LocalDate today = LocalDate.now();

                    for (Student s : students) {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "d/MM/yyyy" );
                        LocalDate birthday = LocalDate.parse( s.getDateOfBirth(), formatter );

                        Period p = Period.between( birthday, today );

                        if (p.getYears() == Integer.parseInt( age )) {

                            LOGGER.info( s.toString() );
                        }
                    }

                } else {

                    throw new StudentRepositoryExceptions( "Varsta trebuie sa fie un numar pozitiv !", "Varsta negativa." );

                }
            } else {

                throw new StudentRepositoryExceptions( "Varsta trebuie sa fie un numar !", "Varsta incorecta." );

            }
        } catch (StudentRepositoryExceptions e) {

            LOGGER.log( Level.SEVERE, e.getMessage());

        }


    }

    public void retrieveStudents() {

        for (Student s : students) {

            LOGGER.info( s.toString() );
        }

    }

    @Override
    public void listOrderedStudents (String studentsListOrder) {

        try {

            if(!studentsListOrder.equals( "" )) {

                if(studentsListOrder.equals( "by last name" ) || studentsListOrder.equals( "by date of birth" )) {

                    if (studentsListOrder.equals( "by date of birth" )) {
                        Collections.sort( students, Student.getCompByBirthDate() );

                    } else {

                        Collections.sort( students, Student.getCompByLastName() );
                    }

                    for (Student student : students) {
                        LOGGER.info( student.toString() );
                    }
                } else  {

                    throw new StudentRepositoryExceptions( "Alegeti una dintre optiunile : by last name sau by date of birth ! ", "Criteriu de ordanare inexistent." );


                }
            } else {

                throw new StudentRepositoryExceptions( "Criteriul de ordonare nu poate fi vid !", "Criteriu de ordanare vid." );

            }

        } catch (StudentRepositoryExceptions e) {

            LOGGER.log( Level.SEVERE, e.getMessage() );
        }
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
