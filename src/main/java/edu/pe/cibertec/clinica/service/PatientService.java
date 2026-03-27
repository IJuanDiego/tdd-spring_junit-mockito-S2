package edu.pe.cibertec.clinica.service;

import edu.pe.cibertec.clinica.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient registerPatient(Patient patient);
    void deletePatient (Long id);
}
