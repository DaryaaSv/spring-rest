package lt.viko.eif.d.svyrydenko.spring.rest.Models;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;

/**
 * Represents a doctor in a medical application. Doctors can have appointments and are identified by their unique ID.
 * This class is an entity that is mapped to the "doctor" table in a database. It contains
 * attributes such as name, specialty, salary, and appointments.
 */
@Entity
public class Doctor extends RepresentationModel<Doctor> {
    /**
     * The unique identifier for this doctor.
     * This field is the primary key of the entity and is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The first name of this doctor.
     */
    private String firstName;

    /**
     * The surname of this doctor.
     */
    private String surname;

    /**
     * The phone number of this doctor.
     */
    private String phoneNumber;

    /**
     * The specialty of this doctor (e.g. cardiology, pediatrics).
     */
    private String specialty;

    /**
     * Creates a new doctor with default values for all fields.
     */
    public Doctor() {
    }

    /**
     * Constructs a new Doctor with the specified parameters.
     * @param firstName   the first name of the doctor.
     * @param surname     the surname of the doctor.
     * @param phoneNumber the phone number of the doctor.
     * @param specialty   the specialty field of the doctor, e.g. 'Cardiology', 'Neurology', etc.
     */
    public Doctor(String firstName, String surname, String phoneNumber, String specialty) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
    }

    /**
     * Returns the unique identifier of the doctor.
     * @return the unique identifier of the doctor
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the doctor to the specified value.
     * @param id the new unique identifier of the doctor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first name of the doctor.
     * @return the first name of the doctor
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the doctor to the specified value.
     * @param firstName the new first name of the doctor
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the phone number of the doctor.
     * @return the phone number of the doctor
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the doctor to the specified value.
     * @param phoneNumber the new phone number of the doctor
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the surname of the doctor.
     * @return the surname of the doctor
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the doctor to the specified value.
     * @param surname the new surname of the doctor
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the specialty of the doctor.
     * @return the specialty of the doctor
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Sets the specialty of the doctor to the specified value.
     * @param specialty the new specialty of the doctor
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
