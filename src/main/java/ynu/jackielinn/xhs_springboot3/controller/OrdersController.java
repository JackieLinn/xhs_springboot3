package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.dto.request.OrdersCreateRO;
import ynu.jackielinn.xhs_springboot3.dto.request.PaymentRO;
import ynu.jackielinn.xhs_springboot3.dto.response.OrdersVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "订单相关接口", description = "与订单相关的操作接口")
public class OrdersController {

    @Resource
    OrdersService ordersService;

    @Operation(summary = "创建订单", description = "创建订单")
    @PostMapping("/create-orders")
    public RestBean<Long> createOrders(@RequestBody OrdersCreateRO ro) {
        return RestBean.success(ordersService.createOrders(ro));
    }

    @Operation(summary = "更改订单地址", description = "更改订单地址")
    @PostMapping("/update-address")
    public RestBean<Boolean> updateAddress(@RequestParam Long oid, @RequestParam Long did) {
        return RestBean.success(ordersService.updateAddress(oid, did));
    }

    @Operation(summary = "支付操作", description = "支付操作")
    @PostMapping("/payment")
    public RestBean<Boolean> ordersPayment(@RequestBody PaymentRO ro) {
        return RestBean.success(ordersService.payment(ro));
    }

    @Operation(summary = "获取订单列表", description = "获取订单列表")
    @GetMapping("/get-orders-list")
    public RestBean<List<OrdersVO>> getOrdersList(@RequestParam Long uid, @RequestParam Integer status) {
        return RestBean.success(ordersService.getOrdersList(uid, status));
    }
}
