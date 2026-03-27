package edu.pe.cibertec.clinica;

import edu.pe.cibertec.clinica.model.Patient;
import edu.pe.cibertec.clinica.repository.PatientRepository;
import edu.pe.cibertec.clinica.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PatientsServiceImpl -Unit test with Mockito")
public class PatientServiceTest {

    @Mock
    private PatientRepository repository;

    @InjectMocks
    private PatientServiceImpl service;


    ///  getAllPatients
    @Test
    @DisplayName("Return all patients from repository")
    void givenPatientExist_whenGetAllPatients_thenReturnList(){
        List<Patient> patients = List.of(
                new Patient(1L, "Paula Perez", "96325814", "987456123","paola@gmail.com"),
                new Patient(2L, "Luis Ramos", "40205187", "963852741","luis@gmail.com")
        );

        when(repository.findAll()).thenReturn(patients);
        ///cuando alguien llame repository.findAll(), no vayas a la base de datos real,
        ///devuelve esta lista falsa (patients) que yo preparé"

        List<Patient> result = service.getAllPatients();
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    ///getPatientsById
    @Test
    @DisplayName("Return patients when ID exists")
    void givenExistingId_whenGetPatientById_thenReturnPatient (){
        Patient patient= new Patient(1L, "Marco Polo", "40207845", "963852700","marco@gmail.com");

        when(repository.findById(1L)).thenReturn(Optional.of(patient));
        Patient result = service.getPatientById(1L);
        assertEquals("Marco Polo", result.getFullName());
        verify(repository, times(1)).findById(1L);

    }

    ///update patient
    @Test
    @DisplayName("Updates patients when ID exists")
    void givenExistingID_whenUpdatePatient_thenReturnUpdatedPatient (){
        Patient existing = new Patient(1L, "Ana Torres", "12345678","987456123","anatorres@gmail.com");
        Patient updated = new Patient(1L, "Ana Torres Perez", "12345678","999888777","anatorres@gmail.com");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(updated);

        Patient result = service.updatePatient(1L,updated);

        assertEquals("Ana Torres Perez", result.getFullName());
        assertEquals("999888777", result.getPhone());

        verify(repository,times(1)).findById(1L);
        verify(repository, times(1)).save(existing);
    }


}
