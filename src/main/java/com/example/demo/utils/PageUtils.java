package com.example.demo.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageUtils {
    private int total;
    private List<?> rows;

    public PageUtils(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }
}
