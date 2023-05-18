package lt.viko.eif.d.svyrydenko.spring.rest.Controllers;

import lt.viko.eif.d.svyrydenko.spring.rest.Models.Patient;
import lt.viko.eif.d.svyrydenko.spring.rest.Repository.PatientRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Rest Controller for handling patient related operations.
 */
@RestController
class PatientController {

    private final PatientRepository repository;

    /**
     * Constructor for the PatientController.
     * @param repository The repository used for patient data operations.
     */
    PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all the patients.
     * @return A collection model of all patients.
     */
    @GetMapping("/patients")
    CollectionModel<EntityModel<Patient>> all() {
        List<EntityModel<Patient>> patients = repository.findAll().stream()
                .map(patient -> EntityModel.of(patient,
                        linkTo(methodOn(PatientController.class).one(patient.getId())).withSelfRel(),
                        linkTo(methodOn(PatientController.class).all()).withRel("patients")))
                .collect(Collectors.toList());

        return CollectionModel.of(patients, linkTo(methodOn(PatientController.class).all()).withSelfRel());
    }

    /**
     * Create a new patient.
     * @param newPatient The patient to be created.
     * @return The created patient.
     */
    @PostMapping("/patients")
    Patient newPatient(@RequestBody Patient newPatient) {
        return repository.save(newPatient);
    }

    /**
     * Get a single patient by their id.
     * @param id The id of the desired patient.
     * @return The desired patient.
     */
    @GetMapping("/patients/{id}")
    EntityModel<Patient> one(@PathVariable Integer id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + id));

        return EntityModel.of(patient,
                linkTo(methodOn(PatientController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PatientController.class).all()).withRel("patients"));
    }

    /**
     * Update an existing patient or create a new one if it doesn't exist.
     * @param newPatient The patient to be created or updated.
     * @param id The id of the patient to be updated.
     * @return The created or updated patient.
     */
    @PutMapping("/patients/{id}")
    Patient replacePatient(@RequestBody Patient newPatient, @PathVariable Integer id) {
        return repository.findById(id)
                .map(patient -> {
                    patient.setFirstName(newPatient.getFirstName());
                    patient.setSurname(newPatient.getSurname());
                    patient.setAge(newPatient.getAge());
                    patient.setPhoneNumber(newPatient.getPhoneNumber());
                    patient.setEmail(newPatient.getEmail());
                    return repository.save(patient);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return repository.save(newPatient);
                });
    }

    /**
     * Delete a patient by their id.
     * @param id The id of the patient to be deleted.
     */
    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
