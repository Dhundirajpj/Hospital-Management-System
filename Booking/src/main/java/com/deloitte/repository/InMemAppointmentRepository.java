package com.deloitte.repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.springframework.stereotype.Repository;

import com.deloitte.entity.Appointment;

@Repository("AppointmentRepository")
public class InMemAppointmentRepository implements AppointmentRepository<Appointment, String> {

    private Map<String, Appointment> entities;

    public InMemAppointmentRepository() {
//        public Appointment(String id, String name, String HospitalId, String PatientId, LocalDate date, LocalTime time, String roomId) {
        entities = new HashMap();
        Appointment Appointment = new Appointment("1", "Appointment 1", "1", "1",  LocalDate.now(), LocalTime.now(),"1");
        entities.put("1", Appointment);
        Appointment Appointment2 = new Appointment("2", "Appointment 2", "2", "2",  LocalDate.now(), LocalTime.now(),"2");
        entities.put("2", Appointment2);
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
    public void add11(Appointment entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    @Override
    public void update(Appointment entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }


    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Appointment get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<Appointment> getAll() {
        return entities.values();
    }

    @Override
    public Collection<Appointment> findByName(String name) throws Exception {
        Collection<Appointment> Appointments = new ArrayList();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                Appointments.add(v);
            }
        });
        return Appointments;
    }

    @Override
    public Collection<Appointment> findByHospitalId(String id) throws Exception {
        Collection<Appointment> Appointments = new ArrayList();
        entities.forEach((k,v) -> {
            if(v.getHospitalId().equals(id)){
                Appointments.add(v);
            }
        });
        return Appointments;
    }

    @Override
    public Collection<Appointment> findByPatientId(String id) throws Exception {
        Collection<Appointment> Appointments = new ArrayList();
        entities.forEach((k,v) -> {
            if(v.getPatientId().equals(id)){
                Appointments.add(v);
            }
        }) ;
        return Appointments;
    }

    @Override
    public Collection<Appointment> findByRoomId(String id) throws Exception {
        Collection<Appointment> Appointments = new ArrayList();
        entities.forEach((k,v) -> {
            if(v.getRoomId().equals(id)){
                Appointments.add(v);

            }
        });
        return Appointments;
    }

	@Override
	public void add(Appointment entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Appointment entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeClass(JavaClass clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeClass(JavaClass clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JavaClass findClass(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaClass loadClass(String className) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaClass loadClass(Class clazz) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
