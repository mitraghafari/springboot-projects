package com.example.repository;


import com.example.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cars")
public interface CarRepository extends CrudRepository<Car,Long> {

    //endpoint it makes:
    //http://localhost:5050/cars   get
//    http://localhost:5050/cars/2  + body + post  -->  create body with id=2
//    http://localhost:5050/cars/2  + body + put   -->  update id=2 with body
//    http://localhost:5050/cars/2      + delete   -->  delete id=2

    @Override
    Iterable<Car> findAll();

}
