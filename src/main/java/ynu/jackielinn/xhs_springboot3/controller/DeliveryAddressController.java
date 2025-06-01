package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.dto.request.DeliveryAddressSaveRO;
import ynu.jackielinn.xhs_springboot3.dto.request.DeliveryAddressUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.DeliveryAddressVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.DeliveryAddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@Tag(name = "送货地址相关接口", description = "与送货地址相关的操作接口")
public class DeliveryAddressController {

    @Resource
    DeliveryAddressService deliveryAddressService;

    @Operation(summary = "获取用户所有地址信息", description = "获取用户所有地址信息")
    @GetMapping("/get-all-address")
    public RestBean<List<DeliveryAddressVO>> getAllAddress(Long uid) {
        return RestBean.success(deliveryAddressService.listDeliveryAddressByUserId(uid));
    }

    @Operation(summary = "保存地址信息", description = "保存地址信息")
    @PostMapping("/save-address")
    public RestBean<Integer> saveAddress(@RequestBody DeliveryAddressSaveRO ro) {
        return RestBean.success(deliveryAddressService.saveDeliveryAddress(ro));
    }

    @Operation(summary = "更新地址信息", description = "更新地址信息")
    @PostMapping("/update-address")
    public RestBean<Integer> updateAddress(@RequestBody DeliveryAddressUpdateRO ro) {
        return RestBean.success(deliveryAddressService.updateDeliveryAddress(ro));
    }

    @Operation(summary = "移除地址信息", description = "移除地址信息")
    @PostMapping("/remove-address")
    public RestBean<Integer> removeAddress(Long daId) {
        return RestBean.success(deliveryAddressService.deleteDeliveryAddress(daId));
    }
}
