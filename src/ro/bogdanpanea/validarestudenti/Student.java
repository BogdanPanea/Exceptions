package ro.bogdanpanea.validarestudenti;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Student {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String id;

    public Student(String firstName, String lastName, String dateOfBirth, String gender, String id) {

        if (validateFirstName( firstName )) {
            this.firstName = firstName;

        } else {
        }

        if (validateLastName( lastName )) {
            this.lastName = lastName;

        } else {
        }


        if (checkDOB( dateOfBirth )) {
            this.dateOfBirth = dateOfBirth;

        } else {
        }


        if (checkGender( gender )) {

            this.gender = gender;
        } else {
        }
        if (! id.equals("")) {

            if (isNumeric( id )) {
                this.id = id;

            } else {

            }
        } else {
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
}

