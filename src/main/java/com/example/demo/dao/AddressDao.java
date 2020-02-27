package com.example.demo.dao;

import com.example.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Integer>
{

}
