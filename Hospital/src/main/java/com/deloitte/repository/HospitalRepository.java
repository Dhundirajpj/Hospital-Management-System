package com.deloitte.repository;

import java.util.Collection;



public interface HospitalRepository<Hospital, String> extends Repository<Hospital, String> {

    boolean containsName(String name);

    public Collection<Hospital> findByName(String name) throws Exception;

    public Collection<Hospital> findByLocation(String location) throws Exception;

    public Collection<Hospital> findAll();
}
