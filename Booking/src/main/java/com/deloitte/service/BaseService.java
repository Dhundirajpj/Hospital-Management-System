package com.deloitte.service;

import java.util.Collection;

import com.deloitte.repository.AppointmentRepository;

public abstract class BaseService<TE, T> extends ReadOnlyBaseService<TE, T> {

    private AppointmentRepository<TE, T> _repository;


    public void add(TE entity) throws Exception {
        _repository.add1(entity);
    }

    public Collection<TE> getAll() {
        return _repository.getAll();
    }
}
