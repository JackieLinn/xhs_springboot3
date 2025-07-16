package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.request.CartListOneRO;
import ynu.jackielinn.xhs_springboot3.dto.request.CartUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.CartVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Cart;

import java.util.List;

public interface CartService extends IService<Cart> {

    CartVO getCartVO(CartListOneRO ro);

    Integer updateCart(CartUpdateRO ro);

    List<CartVO> getAllCarts(Long uid);
}
