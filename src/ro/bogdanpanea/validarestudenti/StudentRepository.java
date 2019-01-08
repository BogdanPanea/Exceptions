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
    public void addStudent(String firstName, String lastName, String dateOfBirth, String gender, String id) throws StudentRepositoryExceptions {

        Student student = new Student(firstName, lastName, dateOfBirth, gender, id);
        students.add(student);

    }

    @Override
    public void deleteStudent(String id) throws StudentRepositoryExceptions {

        if (!id.equals("")) {

            if (isNumeric(id)) {
                boolean exist = false;
                Student student = null;
                for (Student s : students) {
                    if (s.getId().equals(id)) {
                        exist = true;
                        student = s;
                    }
                }
                if (exist) {

                    students.remove(student);
                    LOGGER.info("A fost sters.");
                } else {

                    throw new StudentRepositoryExceptions("Nu exista nicio persoana cu acest CNP !", "CNP inexistent.");

                }

            } else {

                throw new StudentRepositoryExceptions("CNP- ul trebuie sa contina doar cifre !", "CNP incorect.");

            }
        } else {

            throw new StudentRepositoryExceptions("CNP-ul nu poate fi vid !", "CNP vid.");
        }

    }

    @Override
    public void retrieveStudents(String age) throws StudentRepositoryExceptions {

        if (isNumeric(age)) {

            if (Integer.parseInt(age) >= 0) {

                LocalDate today = LocalDate.now();

                for (Student s : students) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate birthday = LocalDate.parse(s.getDateOfBirth(), formatter);

                    Period p = Period.between(birthday, today);

                    if (p.getYears() == Integer.parseInt(age)) {

                        LOGGER.info(s.toString());
                    }
                }

            } else {

                throw new StudentRepositoryExceptions("Varsta trebuie sa fie un numar pozitiv !", "Varsta negativa.");

            }
        } else {

            throw new StudentRepositoryExceptions("Varsta trebuie sa fie un numar !", "Varsta incorecta.");

        }

    }

    public void retrieveStudents() {

        for (Student s : students) {

            LOGGER.info(s.toString());
        }

    }

    @Override
    public void listOrderedStudents(String studentsListOrder) throws StudentRepositoryExceptions {


        if (!studentsListOrder.equals("")) {

            if (checkGender(studentsListOrder)) {

                if (studentsListOrder.equals(SortingTypes.valueOf("byDOB"))) {
                    Collections.sort(students, Student.getCompByBirthDate());

                } else {

                    Collections.sort(students, Student.getCompByLastName());
                }

                for (Student student : students) {
                    LOGGER.info(student.toString());
                }
            } else {

                throw new StudentRepositoryExceptions("Alegeti una dintre optiunile : byLastName sau byDOB ! ", "Criteriu de ordanare inexistent.");


            }
        } else {

            throw new StudentRepositoryExceptions("Criteriul de ordonare nu poate fi vid !", "Criteriu de ordanare vid.");

        }

    }

    public static boolean isNumeric(String id) {
        try {
            double d = Double.parseDouble(id);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean checkGender(String gender) {
        for (SortingTypes t : SortingTypes.values()) {
            if (t.name().equals(gender)) {
                return true;
            }
        }

        return false;
    }


}
