package com.example.demo.service.impl;

import com.example.demo.dao.AddressMapper;
import com.example.demo.model.Address;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> list(Map<String, Object> map) {
        return addressMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return addressMapper.count(map);
    }

    @Override
    public int delete(Integer id) {
        return addressMapper.delete(id);
    }
}
