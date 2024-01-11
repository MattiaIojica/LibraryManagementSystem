package com.example.projectJava.repository;

import com.example.projectJava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    List<User> findAllByAddress_Street(String street);

    @Query( nativeQuery = true, value = "select * from user where firstName = :name")
    User findUserByFirstNameWithNativeQuery(String name);

    Optional<User> findByEmail(String email);
}
