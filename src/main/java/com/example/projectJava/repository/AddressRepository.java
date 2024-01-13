package com.example.projectJava.repository;

import com.example.projectJava.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByStreet(String street);

    List<Address> findByStreetAndStreetNo(String street, Integer streetNo);


}
