package lt.viko.eif.d.svyrydenko.spring.rest.Models;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;

/**
 * This class represents a patient object in a hospital management system.
 * It is annotated with @Entity and @Table, indicating it can be persisted in a database table.
 * The table name is "patient".
 * It also includes several fields such as id, firstName, surname, age, phoneNumber,
 * email, hospitalRoom, and illnessesList.
 * The class includes constructors, getter and setter methods for each field, and a toString method
 * that returns a formatted string representation of a patient's information.
 */
@Entity
public class Patient extends RepresentationModel<Patient> {
    /**
     * The unique identifier for a patient.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The first name of the patient.
     */
    private String firstName;

    /**
     * The surname of the patient.
     */
    private String surname;

    /**
     * The age of the patient.
     */
    private int age;

    /**
     * The phone number of the patient.
     */
    private String phoneNumber;

    /**
     * The email address of the patient.
     */
    private String email;

    /**
     * Default constructor for the patient class.
     */
    public Patient() {
    }

    /**
     * Constructor for the patient class that sets all fields except for the unique identifier.
     * @param firstName The first name of the patient.
     * @param surname The surname of the patient.
     * @param age The age of the patient.
     * @param phoneNumber The phone number of the patient.
     * @param email The email address of the patient.
     */
    public Patient(String firstName, String surname, int age, String phoneNumber, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Returns the ID of the patient.
     * @return the ID of the patient
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the patient.
     * @param id the ID to be assigned to the patient
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first name of the patient.
     * @return the first name of the patient
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the patient.
     * @param firstName the first name to be assigned to the patient
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the surname of the patient.
     * @return the surname of the patient
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the patient.
     * @param surname the surname to be assigned to the patient
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the age of the patient.
     * @return the age of the patient
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the patient.
     * @param age the age to be assigned to the patient
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the phone number of the patient.
     * @return the phone number of the patient
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the patient.
     * @param phoneNumber the phone number to be assigned to the patient
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email of the patient.
     * @return the email of the patient
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the patient.
     * @param email the email to be assigned to the patient
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
