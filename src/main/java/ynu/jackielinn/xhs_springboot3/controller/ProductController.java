package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Tag(name = "商品接口", description = "与商品相关的操作接口")
public class ProductController {

    @Resource
    ProductService productService;

    @GetMapping("/get-recommend-products")
    public RestBean<List<ProductVO>> getRecommendProducts() {
        return RestBean.success(productService.getRecommendProducts());
    }

    @GetMapping("/get-all-products")
    public RestBean<List<ProductVO>> getAllProducts() {
        return RestBean.success(productService.getAllProducts());
    }

    @GetMapping("/get-products-by-type")
    public RestBean<List<ProductVO>> getProductsByType(@RequestParam Integer type) {
        return RestBean.success(productService.getProductsByType(type));
    }
}
