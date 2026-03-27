package edu.pe.cibertec.clinica.service.impl;

import edu.pe.cibertec.clinica.exception.PatientNotFoundException;
import edu.pe.cibertec.clinica.model.Patient;
import edu.pe.cibertec.clinica.repository.PatientRepository;
import edu.pe.cibertec.clinica.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return repository.findById(id).
                orElseThrow(()-> new PatientNotFoundException(id));
    }

    @Override
    public Patient registerPatient(Patient patient) {
        repository.findByDni(patient.getDni())
                .ifPresent(p -> {
                        throw new IllegalArgumentException("DNI already exists: "+patient.getDni());
                });
        return repository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Patient existing = repository.findById(id).
                orElseThrow(()-> new PatientNotFoundException(id));

        existing.setFullName(patient.getFullName());
        existing.setPhone(patient.getPhone());
        existing.setEmail(patient.getEmail());

        return repository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new PatientNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
