package com.project.hb.Hospital.domain.service;

import com.project.hb.Hospital.domain.model.entity.Entity;
import com.project.hb.Hospital.domain.model.entity.Hospital;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface HospitalService {

    public void add(Hospital Hospital) throws Exception;

    public void update(Hospital Hospital) throws Exception;

    public void delete(String id) throws Exception;

    public Entity findById(String HospitalId) throws Exception;

    public Collection<Hospital> findByName(String name) throws Exception;

    public Collection<Hospital> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

    public Collection<Hospital> getAll();
}
