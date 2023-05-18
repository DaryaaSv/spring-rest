package lt.viko.eif.d.svyrydenko.spring.rest.Models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

/**
 * Appointment class represents a medical appointment made by a patient to see a doctor or specialist.
 * It contains information about the appointment such as its unique id, type, date, and result.
 * The class also has a Patient object that represents the patient who made the appointment.
 */
@Entity
public class Appointment extends RepresentationModel<Appointment> {

    /**
     * The unique id of the appointment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The type of the appointment, e.g. "checkup", "surgery", "consultation", etc.
     */
    private String type;

    /**
     * The date of the appointment.
     */
    private Date date;

    /**
     * The Patient object representing the patient who made the appointment.
     */
    @OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
    private Patient patient;

    /**
     * The Doctor object representing the doctor who checked the patient.
     */
    @OneToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
    private Doctor doctor;

    /**
     * Constructs an empty Appointment object.
     */
    public Appointment() {
    }

    /**
     * Constructs a new Appointment with the specified parameters.
     * @param type    the type of the appointment, e.g. 'Routine Check', 'Emergency', etc.
     * @param date    the date and time of the appointment.
     * @param patient the Patient object for the person who has the appointment.
     * @param doctor  the Doctor object for the doctor who will be seeing the patient.
     */
    public Appointment(String type, Date date, Patient patient, Doctor doctor) {
        this.type = type;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    /**
     * Returns the unique id of the appointment.
     * @return the unique id of the appointment
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique id of the appointment.
     * @param id the unique id of the appointment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the type of the appointment.
     * @return the type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     * @param type the type of the appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the date of the appointment.
     * @return the date of the appointment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the appointment.
     * @param date the date of the appointment
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the Patient object representing the patient who made the appointment.
     * @return the Patient object representing the patient who made the appointment
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the Patient object representing the patient who made the appointment.
     * @param patient the Patient object representing the patient who made the appointment
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the doctor for this appointment.
     * @return the doctor for this appointment.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor for this appointment.
     * @param doctor the new Doctor object for the doctor who will be seeing the patient.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}