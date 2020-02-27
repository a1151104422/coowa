package com.example.demo.vo;

import lombok.Data;

import java.util.List;

/**
 * 接口返回对象
 *
 * @param <T>
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private String message;
    // 返回的数据
    private T data;
}