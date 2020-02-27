package com.example.demo.utils;


import com.example.demo.vo.ResultVo;

public class ResultVoUtils {

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMessage("ok");
        resultVo.setData(object);

        return resultVo;
    }

    public static ResultVo success(String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMessage(message);

        return resultVo;
    }

    public static ResultVo failed() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(9999);
        resultVo.setMessage("failed");

        return resultVo;
    }

    public static ResultVo failed(Integer code, String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(message);

        return resultVo;
    }

    public static ResultVo failed(Integer code, String message, Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        resultVo.setData(object);

        return resultVo;
    }
}