package com.project.hb.Hospital.domain.service;

import com.project.hb.Hospital.domain.model.entity.Entity;
import com.project.hb.Hospital.domain.model.entity.Hospital;

import com.project.hb.Hospital.domain.repository.HospitalRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HospitalService")
public class HospitalServiceImpl extends BaseService<Hospital, String>
        implements HospitalService {

    private HospitalRepository<Hospital, String> HospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository<Hospital, String> HospitalRepository) {
        super(HospitalRepository);
        this.HospitalRepository = HospitalRepository;
    }

    @Override
    public void add(Hospital Hospital) throws Exception {
        if (HospitalRepository.containsName(Hospital.getName())) {
            throw new Exception(String.format("There is already a Hospital with the name - %s", Hospital.getName()));
        }

        if (Hospital.getName() == null || "".equals(Hospital.getName())) {
            throw new Exception("Hospital name cannot be null or empty string.");
        }
        super.add(Hospital);
    }

    @Override
    public Collection<Hospital> findByName(String name) throws Exception {
        return HospitalRepository.findByName(name);
    }

    @Override
    public void update(Hospital Hospital) throws Exception {
        HospitalRepository.update(Hospital);
    }

    @Override
    public void delete(String id) throws Exception {
        HospitalRepository.remove(id);
    }


    @Override
    public Entity findById(String id) throws Exception {
        return HospitalRepository.get(id);
    }

    @Override
    public Collection<Hospital> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Hospital> getAll() {
        return HospitalRepository.getAll();
    }
}
