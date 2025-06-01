package ynu.jackielinn.xhs_springboot3;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductSelectionVO;
import ynu.jackielinn.xhs_springboot3.service.VariantService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestVariantService {

    @Resource
    private VariantService variantService;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    void testGetProductSelection() throws Exception {
        // 测试获取商品1的选择信息
        Long productId = 1L;
        ProductSelectionVO selection = variantService.getProductSelection(productId);
        
        // 打印结果
        System.out.println("商品选择信息：");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(selection));
    }

    @Test
    void testCalculatePriceByOptions() {
        // 测试计算商品1的价格
        Long productId = 1L;
        // 假设用户选择了颜色ID为1，大小ID为2的选项
        List<Long> selectedOptions = Arrays.asList(1L, 8L);
        
        Double price = variantService.calculatePriceByOptions(productId, selectedOptions);
        
        // 打印结果
        System.out.println("计算后的价格：");
        System.out.println("商品ID: " + productId);
        System.out.println("选择的选项ID: " + selectedOptions);
        System.out.println("最终价格: " + price);
    }
}
