package lt.viko.eif.d.svyrydenko.spring.rest.Controllers;

import lt.viko.eif.d.svyrydenko.spring.rest.Models.Doctor;
import lt.viko.eif.d.svyrydenko.spring.rest.Repository.DoctorRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Rest Controller for handling doctor related operations.
 */
@RestController
class DoctorController {

    private final DoctorRepository repository;

    /**
     * Constructor for the DoctorController.
     * @param repository The repository used for doctor data operations.
     */
    DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all the doctors.
     * @return A collection model of all doctors.
     */
    @GetMapping("/doctors")
    CollectionModel<EntityModel<Doctor>> all() {
        List<EntityModel<Doctor>> doctors = repository.findAll().stream()
                .map(doctor -> EntityModel.of(doctor,
                        linkTo(methodOn(DoctorController.class).one(doctor.getId())).withSelfRel(),
                        linkTo(methodOn(DoctorController.class).all()).withRel("doctors")))
                .collect(Collectors.toList());

        return CollectionModel.of(doctors, linkTo(methodOn(DoctorController.class).all()).withSelfRel());
    }

    /**
     * Create a new doctor.
     * @param newDoctor The doctor to be created.
     * @return The created doctor.
     */
    @PostMapping("/doctors")
    Doctor newDoctor(@RequestBody Doctor newDoctor) {
        return repository.save(newDoctor);
    }

    /**
     * Get a single doctor by their id.
     * @param id The id of the desired doctor.
     * @return The desired doctor.
     */
    @GetMapping("/doctors/{id}")
    EntityModel<Doctor> one(@PathVariable Integer id) {
        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + id));

        return EntityModel.of(doctor,
                linkTo(methodOn(DoctorController.class).one(id)).withSelfRel(),
                linkTo(methodOn(DoctorController.class).all()).withRel("doctors"));
    }

    /**
     * Update an existing doctor or create a new one if it doesn't exist.
     * @param newDoctor The doctor to be created or updated.
     * @param id The id of the doctor to be updated.
     * @return The created or updated doctor.
     */
    @PutMapping("/doctors/{id}")
    Doctor replaceDoctor(@RequestBody Doctor newDoctor, @PathVariable Integer id) {
        return repository.findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctor.getFirstName());
                    doctor.setSurname(newDoctor.getSurname());
                    doctor.setPhoneNumber(newDoctor.getPhoneNumber());
                    doctor.setSpecialty(newDoctor.getSpecialty());
                    return repository.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctor.setId(id);
                    return repository.save(newDoctor);
                });
    }

    /**
     * Delete a doctor by their id.
     * @param id The id of the doctor to be deleted.
     */
    @DeleteMapping("/doctors/{id}")
    void deleteDoctor(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}