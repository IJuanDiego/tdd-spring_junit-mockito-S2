package edu.pe.cibertec.clinica.repository;

import edu.pe.cibertec.clinica.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByDni(String dni);
}
