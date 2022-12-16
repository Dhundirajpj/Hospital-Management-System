package com.deloitte.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat.Insurance;

import java.util.List;

@Repository
public interface InsurancesRepository extends CrudRepository<Insurance, Long> {
    List<Insurance> findAll();
}