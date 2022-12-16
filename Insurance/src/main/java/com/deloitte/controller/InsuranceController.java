package com.deloitte.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.service.InsuranceService;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.List;


@RestController
public class InsuranceController {
    private InsuranceService InsuranceService;

    @Autowired
    public InsuranceController(InsuranceService InsuranceService) {
        this.InsuranceService = InsuranceService;
    }

    @RequestMapping("/findallInsurance")
    private List<Feature> findedAllInsurance() {
        return InsuranceService.findedAllInsurance();
    }
}

