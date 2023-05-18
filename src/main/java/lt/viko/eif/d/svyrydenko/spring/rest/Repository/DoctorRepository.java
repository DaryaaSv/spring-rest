package lt.viko.eif.d.svyrydenko.spring.rest.Repository;

import lt.viko.eif.d.svyrydenko.spring.rest.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The DoctorRepository interface is responsible for managing doctors in the system.
 * It extends the JpaRepository interface to inherit common CRUD operations and additional methods.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}