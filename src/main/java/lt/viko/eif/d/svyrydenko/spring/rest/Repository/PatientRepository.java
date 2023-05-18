package lt.viko.eif.d.svyrydenko.spring.rest.Repository;

import lt.viko.eif.d.svyrydenko.spring.rest.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The PatientRepository interface is responsible for managing patients in the system.
 * It extends the JpaRepository interface to inherit common CRUD operations and additional methods.
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
