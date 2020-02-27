package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.AddressDao;
import com.example.demo.model.Address;
import com.example.demo.model.LogisticRequest;
import com.example.demo.service.AddressService;
import com.example.demo.utils.*;
import com.example.demo.vo.Query;
import com.example.demo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class AddressController {

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo authorize(Address addr) {
        try {
            if (addr.getId() != null && addr.getId() > 0) {
                Address address = addressDao.getOne(addr.getId());
                address.setReceiver(addr.getReceiver());
                address.setTelephone(addr.getTelephone());
                address.setProvince(addr.getProvince());
                address.setCity(addr.getCity());
                address.setZone(addr.getZone());
                address.setDetail(addr.getDetail());
                addr = address;
            } else {
                addr.setUserid(-1);
                addr.setStatus(0);
            }
            addressDao.save(addr);

            return ResultVoUtils.success(addr.getId());
        } catch (Exception e) {
            return ResultVoUtils.failed(999, "添加失败::: " + e);
        }
    }

    @GetMapping("")
    public String index() {
        return "address";
    }

    @RequestMapping("/address")
    public String address() {
        return "address";
    }

    @RequestMapping("/address/list")
    public String list() {
        return "address/list";
    }

    @RequestMapping("/address/pageList")
    @ResponseBody
    public PageUtils list(final @RequestParam Map<String, Object> params) {
        try {
            Query query = new Query(params);
            return new PageUtils(addressService.list(query), addressService.count(query));
        } catch (Exception e) {
            log.error("查询失败::: " + e);
            return new PageUtils(null, 0);
        }
    }

    @RequestMapping(value = "/address/detail")
    public String detail() {
        return "address/detail";
    }

    @RequestMapping(value = "/address/detail", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo detail(Integer id) {
        try {
            Address address = addressDao.getOne(id);
            if (address != null && !StringUtils.isEmpty(address.getReceiver())) {
                Address addr = new Address();
                BeanUtils.copyProperties(address, addr);

                return ResultVoUtils.success(addr);
            } else {
                return ResultVoUtils.failed(999, "地址不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtils.failed(999, "系统异常");
        }
    }

    @RequestMapping(value = "/eorder/create", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo createEorder(LogisticRequest request, Integer id) {
        if (!EqualsUtils.isNotEmpty(request.getShipperCode(), request.getPayType(), request.getGoodsName(), request.getGoodsQuantity())) {
            return ResultVoUtils.failed(999, "参数不正确");
        }

        Address address = addressDao.getOne(id);
        if (address != null && !StringUtils.isEmpty(address.getReceiver())) {
            request.setCompany("魏先生");
            request.setSendName("魏先生");
            request.setMobile("189****889");
            request.setSdProvinceName("江苏省");
            request.setSdCityName("南京市");
            request.setSdExpAreaName("玄武区");
            request.setSdAddress("珠江路利际大厦1楼17室");

            request.setName(address.getReceiver());
            request.setTel(address.getTelephone());
            request.setProvinceName(address.getProvince());
            request.setCityName(address.getCity());
            request.setExpAreaName(address.getZone());
            request.setAddress(address.getDetail());

            request.setRemark("");

//            request.setShipperCode("SF");
//            request.setPayType(2);
//            request.setMonthCode("");
//            request.setGoodsName("电子产品");
//            request.setGoodsQuantity(1);

            try {
                String result = KdApiEOrder.getOrderTracesByJson(request);
//                String result = "{  \"EBusinessID\": \"1274925\",  \"Success\": true,  \"Order\": {    \"OrderCode\": \"d348160ad94e\",    \"ShipperCode\": \"SF\",    \"LogisticCode\": \"619442637525\",    \"OriginCode\": \"025\",    \"DestinatioCode\": \"517\",    \"KDNOrderCode\": \"KDN1804082300000469\"  },  \"Reason\": \"成功\",  \"ResultCode\": \"100\"}";
                log.info("生成电子面单，返回 result: {}", JSON.toJSONString(result));

                Map<String, Object> map = JSON.parseObject(result, Map.class);
                if (kdnIsSuccess(map)) {
                    map = JSON.parseObject(map.get("Order").toString(), Map.class);

                    Map<String, Object> resultMap = new HashMap<>();
                    // 快递单号
                    resultMap.put("logisticCode", map.get("LogisticCode"));
                    // 目的代码
                    resultMap.put("originCode", map.get("DestinatioCode"));
                    // 订单编号
                    resultMap.put("orderCode", map.get("OrderCode"));
                    // 日期
                    resultMap.put("today", DateUtils.getTodayTimeNos());

                    return ResultVoUtils.success(resultMap);
                } else {
                    return ResultVoUtils.failed(999, ObjectUtils.ObjectToString(map.get("Reason")));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResultVoUtils.failed(999, "生成电子面单错误");
            }
        } else {
            return ResultVoUtils.failed(999, "地址不存在");
        }
    }

    @RequestMapping(value = "/address/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo cancel(LogisticRequest logisticRequest) {
        try {
            if (logisticRequest != null) {
                String result = KdApiEOrder.cancelOrder(logisticRequest);
                System.out.println(result);
                Map<String, Object> map = JSON.parseObject(result, Map.class);
                if (kdnIsSuccess(map)) {
                    return ResultVoUtils.success("取消成功");
                } else {
                    return ResultVoUtils.failed(999, ObjectUtils.ObjectToString(map.get("Reason")));
                }
            } else {
                return ResultVoUtils.failed(999, "参数不能为空");
            }
        } catch (Exception e) {
            return ResultVoUtils.failed(999, "查询失败::: " + e);
        }
    }

    @RequestMapping(value = "/address/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo delete(Integer id) {
        try {
            if (id != null) {
                addressService.delete(id);
                return ResultVoUtils.success("删除成功");
            } else {
                return ResultVoUtils.failed(999, "参数不能为空");
            }
        } catch (Exception e) {
            return ResultVoUtils.failed(999, "查询失败::: " + e);
        }
    }

    private boolean kdnIsSuccess(Map<String, Object> result) {
        if (result != null) {
            if (EqualsUtils.ObjectEquals(result.get("Success"), "true") &&
                    EqualsUtils.ObjectEquals(result.get("ResultCode"), "100")) {
                return true;
            }
        }

        return false;
    }

}
