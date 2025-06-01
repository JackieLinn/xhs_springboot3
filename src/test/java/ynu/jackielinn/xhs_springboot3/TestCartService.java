package ynu.jackielinn.xhs_springboot3;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ynu.jackielinn.xhs_springboot3.dto.request.CartListOneRO;
import ynu.jackielinn.xhs_springboot3.dto.request.CartUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.CartVO;
import ynu.jackielinn.xhs_springboot3.service.CartService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestCartService {

    @Resource
    CartService cartService;

    @Test
    public void testGetCartVO() {
        // 创建测试数据
        CartListOneRO ro = new CartListOneRO();
        ro.setUid(1L);  // 假设用户ID为1
        ro.setPid(1L);  // 假设商品ID为1
        ro.setPrice(32.99);  // 设置价格
        ro.setQuantity(2);  // 设置数量
        ro.setAoids(Arrays.asList(1L, 6L));  // 假设选择了ID为1和6的选项

        // 调用测试方法
        CartVO vo = cartService.getCartVO(ro);

        // 打印结果
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vo);
            System.out.println("测试结果：");
            System.out.println(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateCart() {
        CartUpdateRO updateRO = new CartUpdateRO();
        updateRO.setCid(1L);
        updateRO.setType(1);
        Integer result = cartService.updateCart(updateRO);
        System.out.println("增加数量结果：" + result);

        updateRO.setType(0);
        result = cartService.updateCart(updateRO);
        System.out.println("减少数量结果：" + result);

        result = cartService.updateCart(updateRO);
        System.out.println("删除结果：" + result);
    }

    @Test
    public void testGetAllCarts() {
        Long uid = 1L;
        List<CartVO> carts = cartService.getAllCarts(uid);
        System.out.println("用户" + uid + "的购物车列表：");
        for (CartVO cart : carts) {
            System.out.println(cart);
        }
    }
}
