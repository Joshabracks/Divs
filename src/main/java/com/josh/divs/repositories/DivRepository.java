package com.josh.divs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.josh.divs.models.Div;

public interface DivRepository extends CrudRepository<Div, Long>{
	List<Div> findByIdContaining(Long search);
}
