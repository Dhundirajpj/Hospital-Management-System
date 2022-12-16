package com.deloitte.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.hb.Patient.domain.model.entity.Entity;
import com.project.hb.Patient.domain.model.entity.Patient;
import com.project.hb.Patient.domain.service.PatientService;
import com.project.hb.Patient.domain.valueobject.PatientVO;
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

@RestController
@RequestMapping("/v1/Patients")
public class PatientController {

    protected Logger logger = Logger.getLogger(PatientController.class.getName());

    protected PatientService PatientService;

  
    @Autowired
    public PatientController(PatientService PatientService) {
        this.PatientService = PatientService;
    }

    @HystrixCommand(fallbackMethod = "defaultPatients")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Patient>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("Patient-service findByName() invoked:%s for %s ", PatientService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Patient> Patients;
        try {
            Patients = PatientService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Patients.size() > 0 ? new ResponseEntity<>(Patients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defaultPatient")
    public ResponseEntity<Entity> findById(@PathVariable("id") String id) {
        logger.info(String.format("Patient-service findById() invoked:%s for %s ", PatientService.getClass().getName(), id));
        id = id.trim();
        Entity Patient;
        try {
            Patient = PatientService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Patient != null ? new ResponseEntity<>(Patient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Patient> add(@RequestBody PatientVO PatientVO) {
        logger.info(String.format("Patient-service add() invoked: %s for %s", PatientService.getClass().getName(), PatientVO.getName()));
        System.out.println(PatientVO);
        Patient Patient = new Patient(null, null, null, null, null);
        BeanUtils.copyProperties(PatientVO, Patient);
        try {
            PatientService.add(Patient);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Patient REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<Entity> defaultPatient(String input) {
        logger.warning("Fallback method for Patient-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Collection<Patient>> defaultPatients(String input) {
        logger.warning("Fallback method for Patient-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
