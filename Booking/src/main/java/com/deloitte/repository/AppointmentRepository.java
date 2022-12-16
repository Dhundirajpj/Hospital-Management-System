package com.deloitte.repository;

import java.awt.print.Book;
import java.nio.charset.CodingErrorAction;
import java.util.Collection;

import org.aspectj.apache.bcel.util.Repository;


public interface AppointmentRepository<Appointment, String> extends Repository {


    boolean containsName(String name);


    public Collection<Appointment> findByName(String name) throws Exception;



    public Collection<Appointment> findByPatientId(String id) throws Exception;

    public Collection<Appointment> findByHospitalId(String id) throws Exception;

    public Collection<Appointment> findByRoomId(String id) throws Exception;
}
