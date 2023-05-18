package lt.viko.eif.d.svyrydenko.spring.rest.Controllers;

import lt.viko.eif.d.svyrydenko.spring.rest.Models.Appointment;
import lt.viko.eif.d.svyrydenko.spring.rest.Repository.AppointmentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Rest Controller for handling appointment related operations.
 */
@RestController
class AppointmentController {

    private final AppointmentRepository repository;

    /**
     * Constructor for the AppointmentController.
     * @param repository The repository used for appointment data operations.
     */
    AppointmentController(AppointmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all the appointments.
     * @return A collection model of all appointments.
     */
    @GetMapping("/appointments")
    CollectionModel<EntityModel<Appointment>> all() {
        List<EntityModel<Appointment>> appointments = repository.findAll().stream()
                .map(appointment -> EntityModel.of(appointment,
                        linkTo(methodOn(AppointmentController.class).one(appointment.getId())).withSelfRel(),
                        linkTo(methodOn(AppointmentController.class).all()).withRel("appointments")))
                .collect(Collectors.toList());

        return CollectionModel.of(appointments, linkTo(methodOn(AppointmentController.class).all()).withSelfRel());
    }

    /**
     * Create a new appointment.
     * @param newAppointment The appointment to be created.
     * @return The created appointment.
     */
    @PostMapping("/appointments")
    Appointment newAppointment(@RequestBody Appointment newAppointment) {
        return repository.save(newAppointment);
    }

    /**
     * Get a single appointment by its id.
     * @param id The id of the desired appointment.
     * @return The desired appointment.
     */
    @GetMapping("/appointments/{id}")
    EntityModel<Appointment> one(@PathVariable Integer id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found: " + id));

        return EntityModel.of(appointment,
                linkTo(methodOn(AppointmentController.class).one(id)).withSelfRel(),
                linkTo(methodOn(AppointmentController.class).all()).withRel("appointments"));
    }

    /**
     * Update an existing appointment or create a new one if it doesn't exist.
     * @param newAppointment The appointment to be created or updated.
     * @param id The id of the appointment to be updated.
     * @return The created or updated appointment.
     */
    @PutMapping("/appointments/{id}")
    Appointment replaceAppointment(@RequestBody Appointment newAppointment, @PathVariable Integer id) {
        return repository.findById(id)
                .map(appointment -> {
                    appointment.setType(newAppointment.getType());
                    appointment.setDate(newAppointment.getDate());
                    appointment.setPatient(newAppointment.getPatient());
                    appointment.setDoctor(newAppointment.getDoctor());
                    return repository.save(appointment);
                })
                .orElseGet(() -> {
                    newAppointment.setId(id);
                    return repository.save(newAppointment);
                });
    }

    /**
     * Delete an appointment by its id.
     * @param id The id of the appointment to be deleted.
     */
    @DeleteMapping("/appointments/{id}")
    void deleteAppointment(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
