package com.deloitte.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.deloitte.entity.Appointment;

import jakarta.persistence.Entity;

public interface AppointmentService {

  
    public void add(Appointment Appointment) throws Exception;


    public void update(Appointment Appointment) throws Exception;

    public void delete(String id) throws Exception;


    public Entity findById(String id) throws Exception;

    public Collection<Appointment> findByName(String name) throws Exception;


    public Collection<Appointment> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;


    public void produceAppointmentOrderEvent(Appointment Appointment) throws Exception;

}
