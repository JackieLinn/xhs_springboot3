package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.ProductClassification;
import ynu.jackielinn.xhs_springboot3.service.ProductClassificationService;

import java.util.List;

@RestController
@RequestMapping("/api/product-classification")
@Tag(name = "商品分类接口", description = "与商品分类相关的操作接口")
public class ProductClassificationController {

    @Resource
    ProductClassificationService productClassificationService;

    @GetMapping("/get-all-categories")
    public RestBean<List<ProductClassification>> getAllCategories() {
        return RestBean.success(productClassificationService.getAllCategories());
    }
}
