package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.dto.request.CartListOneRO;
import ynu.jackielinn.xhs_springboot3.dto.request.CartUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.CartVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "购物车相关接口", description = "与购物车相关的操作接口")
public class CartController {

    @Resource
    CartService  cartService;

    @Operation(summary = "保存并获取单个购物车信息", description = "保存并获取单个购物车信息")
    @PostMapping("/get-cart")
    public RestBean<CartVO> getCartListOneVO(@RequestBody CartListOneRO ro) {
        return RestBean.success(cartService.getCartVO(ro));
    }

    @Operation(summary = "更新购物车信息", description = "更新购物车信息")
    @PostMapping("/update-cart")
    public RestBean<Integer> updateCart(@RequestBody CartUpdateRO ro) {
        return RestBean.success(cartService.updateCart(ro));
    }

    @Operation(summary = "获取所有购物车信息", description = "获取所有购物车信息")
    @GetMapping("/get-all-carts")
    public RestBean<List<CartVO>> getAllCarts(@RequestParam Long uid) {
        return RestBean.success(cartService.getAllCarts(uid));
    }
}
