package com.deloitte.controller;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.entity.Appointment;
import com.deloitte.service.AppointmentService;

import jakarta.persistence.Entity;


@RestController
@RequestMapping("/v1/Appointment")
public class AppointmentController {

    protected static final Logger logger = Logger.getLogger(AppointmentController.class.getName());


    protected AppointmentService AppointmentService;

    @Autowired
    public AppointmentController(AppointmentService AppointmentService) {
        this.AppointmentService = AppointmentService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Appointment>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("Appointment-service findByName() invoked:{} for {} ", AppointmentService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Appointment> Appointments;
        try {
            Appointments = AppointmentService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Appointments.size() > 0 ? new ResponseEntity<>(Appointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("id") String id) {
        logger.info(String.format("Appointment-service findById() invoked:{} for {} ", AppointmentService.getClass().getName(), id));
        id = id.trim();
        Entity Appointment;
        try {
            Appointment = AppointmentService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Appointment != null ? new ResponseEntity<>(Appointment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Appointment> add(@RequestBody Appointment Appointment) {
        logger.info(String.format("Appointment-service add() invoked: %s for %s", AppointmentService.getClass().getName(), AppointmentVO.getName()));
        System.out.println(Appointment);
        Appointment Appointment1 = new Appointment(null, null, null, null, null, null, null);
        BeanUtils.copyProperties(Appointment1, Appointment1);
        try {
            AppointmentService.add(Appointment1);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Appointment REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}