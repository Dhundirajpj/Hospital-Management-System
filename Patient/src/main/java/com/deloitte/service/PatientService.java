package com.deloitte.service;

import com.deloitte.entity.Patient;
import com.deloitte.entity.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public interface PatientService {

    public void add(Patient booking) throws Exception;

    public void update(Patient booking) throws Exception;

    public void delete(String id) throws Exception;

    public Entity findById(String hotelId) throws Exception;

    public Collection<Patient> findByName(String name) throws Exception;

    public Collection<Patient> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
