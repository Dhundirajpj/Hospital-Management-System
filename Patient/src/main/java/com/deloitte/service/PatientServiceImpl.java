package com.deloitte.service;

import com.deloittey.Entity;
import com.deloitte.Patient;
import com.deloitte.PatientRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PatientService")
public class PatientServiceImpl extends BaseService<Patient, String>
        implements PatientService {

    private PatientRepository<Patient, String> PatientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository<Patient, String> PatientRepository) {
        super(PatientRepository);
        this.PatientRepository = PatientRepository;
    }

    @Override
    public void add(Patient Patient) throws Exception {
        if (PatientRepository.containsName(Patient.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", Patient.getName()));
        }

        if (Patient.getName() == null || "".equals(Patient.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        super.add(Patient);
    }

    @Override
    public Collection<Patient> findByName(String name) throws Exception {
        return PatientRepository.findByName(name);
    }


    @Override
    public void update(Patient Patient) throws Exception {
        PatientRepository.update(Patient);
    }

    @Override
    public void delete(String id) throws Exception {
        PatientRepository.remove(id);
    }


    @Override
    public Entity findById(String hotelId) throws Exception {
        return PatientRepository.get(hotelId);
    }


    @Override
    public Collection<Patient> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
