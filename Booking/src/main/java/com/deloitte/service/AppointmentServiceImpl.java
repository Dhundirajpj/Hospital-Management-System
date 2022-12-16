package com.deloitte.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.entity.Appointment;
import com.deloitte.repository.AppointmentRepository;

@Service("AppointmentService")
public class AppointmentServiceImpl extends BaseService<Appointment, String>
        implements AppointmentService {

    private static final Logger LOG = LoggerFactory.getLogger(AppointmentServiceImpl.class);
    private AppointmentRepository<Appointment, String> AppointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository<Appointment, String> AppointmentRepository) {
        super(AppointmentRepository);
        this.AppointmentRepository = AppointmentRepository;
    }

    @Override
    public void add(Appointment Appointment) throws Exception {
        if (AppointmentRepository.containsName(Appointment.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", Appointment.getName()));
        }

        if (Appointment.getName() == null || "".equals(Appointment.getName())) {
            throw new Exception("Appointment name cannot be null or empty string.");
        }
        super.add(Appointment);
        produceAppointmentOrderEvent(Appointment);
    }


    @Override
    public Collection<Appointment> findByName(String name) throws Exception {
        return AppointmentRepository.findByName(name);
    }


    @Override
    public void update(Appointment Appointment) throws Exception {
        AppointmentRepository.update(Appointment);
    }


    @Override
    public void delete(String id) throws Exception {
        AppointmentRepository.remove(id);
    }


    @Override
    public Entity findById(String id) throws Exception {
        return AppointmentRepository.get(id);
    }


    @Override
    public void produceAppointmentOrderEvent(Appointment Appointment) throws Exception {
        final AppointmentOrder.Builder boBuilder = AppointmentOrder.newBuilder();
        boBuilder.setId(Appointment.getId());
        boBuilder.setName(Appointment.getName());
        boBuilder.setHospitalId(Appointment.getHospitalId());
        boBuilder.setTableId(Appointment.getRoomId());
        boBuilder.setPATIENTId(Appointment.getPATIENTId());
        boBuilder.setDate(Appointment.getDate().toString());
        boBuilder.setTime(Appointment.getTime().toString());
        AppointmentOrder bo = boBuilder.build();
        final Message<AppointmentOrder> message = MessageBuilder.withPayload(bo).build();
        AppointmentMessageChannels.AppointmentOrderOutput().send(message);
        LOG.info("sending AppointmentOrder: {}", Appointment);
    }

    @Override
    public Collection<Appointment> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void add(Appointment Appointment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Appointment Appointment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Appointment> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void produceAppointmentOrderEvent(Appointment Appointment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Appointment> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
