package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Getter
@Setter
public class Query extends LinkedHashMap<String, Object> {
    //
    private int offset;
    // 每页条数
    private int limit;

    public Query(Map<String, Object> params) {
        if (params != null) {
            this.putAll(params);
            // 分页参数
            this.offset = Integer.parseInt(params.get("offset").toString());
            this.limit = Integer.parseInt(params.get("limit").toString());
            this.put("offset", offset);
            this.put("page", offset / limit + 1);
            this.put("limit", limit);
        }
    }
}
