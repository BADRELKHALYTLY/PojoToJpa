package com.generic.kafka.GenericProducer.repository;


import java.util.List;

import com.generic.kafka.GenericProducer.pojo.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>{

    Address save(Address person);
    List<Address> findAll();

}
