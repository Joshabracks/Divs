package com.josh.divs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.josh.divs.models.Thing;

public interface ThingRepository extends CrudRepository<Thing, Long>{

}
