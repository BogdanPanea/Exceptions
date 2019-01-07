package ro.bogdanpanea.validarestudenti;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.logging.Logger;

public class Student {

    private static Logger LOGGER = Logger.getLogger( Main.class.getName() );

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String id;

    public Student(String firstName, String lastName, String dateOfBirth, String gender, String id) throws StudentRepositoryExceptions {

        if (!firstName.equals( "" )) {

            if (validateFirstName( firstName )) {
                this.firstName = firstName;

            } else {
                throw new StudentRepositoryExceptions( "Prenumele trebuia sa contina doar litere !", "Prenume incorect." );
            }
        } else {
            throw new StudentRepositoryExceptions( "Prenumele trebuie sa contina macar un caracter !", "Prenume vid." );
        }

        if (!lastName.equals( "" )) {


            if (validateLastName( lastName )) {
                this.lastName = lastName;

            } else {

                throw new StudentRepositoryExceptions( "Numele trebuia sa contina doar litere sau caracterul - !", "Nume incorect." );
            }
        } else {

            throw new StudentRepositoryExceptions( "Numele trebuie sa contina macar un caracter !", "Nume vid." );

        }

        if (checkDOB( dateOfBirth )) {
            this.dateOfBirth = dateOfBirth;

        } else {

            throw new StudentRepositoryExceptions("Data nasterii trebuie sa fie intre anul 1900 si anul curent !", "Data nasterii incorecta." );
        }


        if (checkGender( gender )) {

            this.gender = gender;
        } else {

            throw new StudentRepositoryExceptions( "Genul trebuie ales din urmatoarea lista : F, M, f, m !", "Gen incorect." );
        }
        if (!id.equals( "" )) {

            if (isNumeric( id )) {
                this.id = id;

            } else {

                throw new StudentRepositoryExceptions( "CNP - ul trebuie sa fie numeric !", "CNP incorect." );

            }
        } else {

            throw new StudentRepositoryExceptions( "CNP - ul  nu poate fi vid !", "CNP vid." );
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }


    public static boolean validateFirstName(String firstName) {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    public static boolean validateLastName(String lastName) {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    }

    public static boolean checkGender(String gender) {
        for (Gender c : Gender.values()) {
            if (c.name().equals( gender )) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNumeric(String id) {
        try {
            double d = Double.parseDouble( id );
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean checkDOB(String dateOfBirth) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "d/MM/yyyy" );
        LocalDate today = LocalDate.now();
        LocalDate birthYear = LocalDate.parse( dateOfBirth, formatter );
        return (birthYear.getYear() >= 1900) && (birthYear.getYear() <= 2018) && birthYear.isBefore( today );
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static Comparator<Student> getCompByLastName() {
        Comparator comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getLastName().compareTo( s2.getLastName() );
            }
        };
        return comp;
    }

    public static Comparator<Student> getCompByBirthDate() {
        Comparator comp = new Comparator<Student>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "d/MM/yyyy" );

            public int compare(Student s1, Student s2) {
                return LocalDate.parse( s1.getDateOfBirth(), formatter ).compareTo( LocalDate.parse( s2.getDateOfBirth(), formatter ) );
            }
        };
        return comp;
    }
}

