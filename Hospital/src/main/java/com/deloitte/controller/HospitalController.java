package com.deloitte.controller;


import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deloitte.service.HospitalService;

import jakarta.persistence.Entity;

import com.deloitte.entity.Hospital;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/v1/Hospitals")
public class HospitalController {

  protected Logger logger = Logger.getLogger(HospitalController.class.getName());

  @Autowired
  protected HospitalService HospitalService;

  @Autowired
  DiscoveryClient client;

  @RequestMapping("/services")
  public List<String> home() {
      return client.getServices();
  }


  @HystrixCommand(fallbackMethod = "defaultHospitals")
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Hospital>> findByName(@RequestParam("name") String name) {
      logger.info(String.format("Hospital-service findByName() invoked:{} for {} ", HospitalService.getClass().getName(), name));
      name = name.trim().toLowerCase();
      Collection<Hospital> Hospitals;
      try {
          Hospitals = HospitalService.findByName(name);
      } catch (Exception ex) {
          logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return Hospitals.size() > 0 ? new ResponseEntity<>(Hospitals, HttpStatus.OK)
              : new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/all",method = RequestMethod.GET)
  public ResponseEntity<Collection<Hospital>> findAll() {

      Collection<Hospital> Hospitals;
      try {
          Hospitals = HospitalService.getAll();
      } catch (Exception ex) {

          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity<>(Hospitals, HttpStatus.OK);

  }


  @HystrixCommand(fallbackMethod = "defaultHospital")
  @RequestMapping(value = "/{Hospital_id}", method = RequestMethod.GET)
  public ResponseEntity<Entity> findById(@PathVariable("Hospital_id") String id) {
      logger.info(String.format("Hospital-service findById() invoked:{} for {} ", HospitalService.getClass().getName(), id));
      id = id.trim();
      Entity Hospital;
      try {
          Hospital = HospitalService.findById(id);
      } catch (Exception ex) {
          logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return Hospital != null ? new ResponseEntity<>(Hospital, HttpStatus.OK)
              : new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Hospital> add(@RequestBody HospitalDto HospitalDto) {
      logger.info(String.format("Hospital-service add() invoked: %s for %s", HospitalService.getClass().getName(), HospitalDto.getName()));

      Hospital Hospital = new Hospital(null, null, null, null);
      BeanUtils.copyProperties(HospitalDto, Hospital);
      try {
          HospitalService.add(Hospital);
      } catch (Exception ex) {
          logger.log(Level.WARNING, "Exception raised add Hospital REST Call {0}", ex);
          return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
      }
      return new ResponseEntity<>(HttpStatus.CREATED);
  }


  public ResponseEntity<Entity> defaultHospital(String input) {
      logger.warning("Fallback method for Hospital-service is being used.");
      return new ResponseEntity<>( HttpStatus.NO_CONTENT);
  }


  public ResponseEntity<Collection<Hospital>> defaultHospitals(String input) {
      logger.warning("Fallback method for user-service is being used.");
      return new ResponseEntity<>( HttpStatus.NO_CONTENT);
  }
}
