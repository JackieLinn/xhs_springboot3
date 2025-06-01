package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.jackielinn.xhs_springboot3.dto.request.CartListOneRO;
import ynu.jackielinn.xhs_springboot3.dto.request.CartUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.CartVO;
import ynu.jackielinn.xhs_springboot3.entity.po.*;
import ynu.jackielinn.xhs_springboot3.mapper.*;
import ynu.jackielinn.xhs_springboot3.service.CartService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
        implements CartService {

    @Resource
    ProductMapper productMapper;
    
    @Resource
    AttributeOptionMapper attributeOptionMapper;

    @Resource
    CartOptionMapper cartOptionMapper;

    @Override
    @Transactional
    public CartVO getCartVO(CartListOneRO ro) {
        // 1. 创建Cart记录
        Product product = productMapper.selectById(ro.getPid());
        Cart cart = new Cart(null, ro.getUid(), product.getMid(), ro.getPid(),
                ro.getPrice(), ro.getQuantity(), 0);
        save(cart);

        // 2. 创建CartOption记录
        List<CartOption> cartOptions = ro.getAoids().stream()
                .map(aoid -> {
                    AttributeOption attributeOption = attributeOptionMapper.selectById(aoid);
                    return new CartOption(null, cart.getId(), attributeOption.getAid(), aoid);
                })
                .toList();
        cartOptions.forEach(cartOption -> cartOptionMapper.insert(cartOption));

        // 3. 构建返回的VO对象
        List<String> attributes = ro.getAoids().stream()
                .map(aoid -> attributeOptionMapper.selectById(aoid).getContent())
                .toList();
        return Proxy.cart2VO(cart, product, attributes);
    }

    @Override
    @Transactional
    public Integer updateCart(CartUpdateRO ro) {
        // 1. 获取购物车记录
        Cart cart = getById(ro.getCid());
        if (cart == null) return 0;

        // 2. 根据type进行相应操作
        if (ro.getType() == 1) {
            // 增加数量
            cart.setQuantity(cart.getQuantity() + 1);
            return updateById(cart) ? 1 : 0;
        } else if (ro.getType() == 0) {
            // 减少数量
            if (cart.getQuantity() == 1) cart.setStatus(1);
            cart.setQuantity(cart.getQuantity() - 1);
            return updateById(cart) ? 1 : 0;
        }
        return 0;
    }

    @Override
    public List<CartVO> getAllCarts(Long uid) {
        // 1. 查询用户所有状态为0的购物车记录
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUid, uid)
                .eq(Cart::getStatus, 0);
        List<Cart> carts = list(wrapper);

        // 2. 转换为VO对象
        return carts.stream().map(cart -> {
            Product product = productMapper.selectById(cart.getPid());
            if (product == null) return null;

            List<String> attributes = cartOptionMapper.selectList(
                    new LambdaQueryWrapper<CartOption>()
                            .eq(CartOption::getCid, cart.getId())
            ).stream()
                    .map(cartOption -> attributeOptionMapper.selectById(cartOption.getAoid()).getContent())
                    .toList();

            return Proxy.cart2VO(cart, product, attributes);
        }).filter(Objects::nonNull).toList();
    }
}
