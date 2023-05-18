package lt.viko.eif.d.svyrydenko.spring.rest.Repository;

import lt.viko.eif.d.svyrydenko.spring.rest.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The AppointmentRepository interface is responsible for managing appointments in the system.
 * It extends the JpaRepository interface to inherit common CRUD operations and additional methods.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}