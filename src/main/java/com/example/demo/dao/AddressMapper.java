package com.example.demo.dao;

import com.example.demo.model.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {

    List<Address> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int delete(Integer id);
}
