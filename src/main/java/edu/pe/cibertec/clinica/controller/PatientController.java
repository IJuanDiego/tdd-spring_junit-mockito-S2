package edu.pe.cibertec.clinica.controller;

import edu.pe.cibertec.clinica.model.Patient;
import edu.pe.cibertec.clinica.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll(){
        return ResponseEntity.ok(service.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<Patient> register(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerPatient(patient));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient){
        return ResponseEntity.ok(service.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
