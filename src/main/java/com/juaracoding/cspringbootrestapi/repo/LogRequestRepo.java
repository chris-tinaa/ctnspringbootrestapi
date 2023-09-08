package com.juaracoding.cspringbootrestapi.repo;


import com.juaracoding.cspringbootrestapi.model.LogRequest;
import org.springframework.data.repository.CrudRepository;

public interface LogRequestRepo extends CrudRepository<LogRequest,Long> {

}
