package com.deloitte.repository;

import java.util.Collection;

public interface UserRepository<Booking, String> extends Repository<Booking, String> {


    boolean containsName(String name);

    public Collection<Booking> findByName(String name) throws Exception;
}
