package com.example.demo.service;

import com.example.demo.model.Address;

import java.util.List;
import java.util.Map;

public interface AddressService {
    List<Address> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int delete(Integer id);
}
