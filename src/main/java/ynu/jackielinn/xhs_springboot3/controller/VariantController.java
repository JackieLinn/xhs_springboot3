package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductSelectionVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.VariantService;

@RestController
@RequestMapping("/api/variant")
@Tag(name = "商品选择接口", description = "与商品选择相关的操作接口")
public class VariantController {

    @Resource
    VariantService variantService;

    @GetMapping ("/get-product-selection")
    public RestBean<ProductSelectionVO>  getProductSelection(@RequestParam Long pid) {
        return RestBean.success(variantService.getProductSelection(pid));
    }
}
