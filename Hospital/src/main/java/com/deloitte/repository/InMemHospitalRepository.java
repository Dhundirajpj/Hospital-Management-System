package com.deloitte.repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.project.hb.Hospital.domain.model.entity.Hospital;
import org.springframework.stereotype.Repository;

@Repository("HospitalRepository")
public class InMemHospitalRepository implements HospitalRepository<Hospital, String> {

    private Map<String, Hospital> entities;


    public InMemHospitalRepository() {
        entities = new HashMap();
        //public Hospital(String name, String id, String location, List<Room> rooms) {
        Hospital Hospital = new Hospital("Le Meurice","1","Paris",null);
        entities.put("1", Hospital);
        Hospital = new Hospital("L'Ambroisie","2","Helsinki",null);
        entities.put("2", Hospital);
        Hospital = new Hospital("Arpège","3","Praha",null);
        entities.put("3", Hospital);
        Hospital = new Hospital("Alain Ducasse au Plaza Athénée","4","Zurich",null);
        entities.put("4", Hospital);
        Hospital = new Hospital("Pavillon LeDoyen","5","Berlin",null);
        entities.put("5", Hospital);
        Hospital = new Hospital("Pierre Gagnaire","6","Frankfurt",null);
        entities.put("6", Hospital);
        Hospital = new Hospital("Guy Savoy","7","NewYork",null);
        entities.put("7", Hospital);
        Hospital = new Hospital("Pré Catelan","8","Chicago",null);
        entities.put("8", Hospital);
        Hospital = new Hospital("L'Astrance","9","HoChiMinh",null);
        entities.put("9", Hospital);
        Hospital = new Hospital("Le Bristol","10","California",null);
        entities.put("10", Hospital);
    }

    @Override
    public Collection<Hospital> findAll() {
        return entities.values();
    }

    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }


    @Override
    public void add(Hospital entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    @Override
    public void update(Hospital entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hospital get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<Hospital> getAll() {
        return entities.values();
    }


    @Override
    public Collection<Hospital> findByName(String name) throws Exception {
        Collection<Hospital> Hospitals = new ArrayList();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                Hospitals.add(v);
            }
        });
        return Hospitals;
    }

    @Override
    public Collection<Hospital> findByLocation(String location) throws Exception {
        Collection<Hospital> Hospitals = new ArrayList<>();
        entities.forEach((k,v) -> {
            if(v.getLocation().trim().equals(location.trim())){
                Hospitals.add(v);

            }

        });
        return Hospitals;
    }
}
