package com.example.repository;

import com.example.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, BigInteger> {

    User findTopByName();
    List<User> findUserByIdOrNameOrFamily(BigInteger id,String name,String family);

}
