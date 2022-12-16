package com.deloitte.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.repository.InsurancesRepository;
import com.fasterxml.jackson.annotation.JsonFormat.Insurance;

import java.util.List;



@Service
public class InsurancesService {
    private final InsurancesRepository InsurancesRepository;
    @Autowired
    public InsurancesService(InsurancesRepository InsurancesRepository) {
        this.InsurancesRepository = InsurancesRepository;
    }

    public List<Insurance> findedAllInsurances() {
        return InsurancesRepository.findAll();
    }
}
