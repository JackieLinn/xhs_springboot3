package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.jackielinn.xhs_springboot3.dto.request.OrdersCreateRO;
import ynu.jackielinn.xhs_springboot3.dto.request.PaymentRO;
import ynu.jackielinn.xhs_springboot3.dto.response.CartVO;
import ynu.jackielinn.xhs_springboot3.dto.response.OrdersVO;
import ynu.jackielinn.xhs_springboot3.entity.po.*;
import ynu.jackielinn.xhs_springboot3.mapper.*;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.OrdersService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService {

    @Resource
    DeliveryAddressMapper deliveryAddressMapper;

    @Resource
    CartMapper cartMapper;

    @Resource
    AccountService accountService;

    @Resource
    ProductMapper productMapper;

    @Resource
    CartOptionMapper cartOptionMapper;

    @Resource
    AttributeOptionMapper attributeOptionMapper;

    @Override
    @Transactional
    public Long createOrders(OrdersCreateRO ro) {
        // 1. 获取用户默认收货地址
        Long did = deliveryAddressMapper.selectList(
                new LambdaQueryWrapper<DeliveryAddress>()
                        .eq(DeliveryAddress::getUid, ro.getUid())
                        .last("LIMIT 1")
        ).stream()
                .findFirst()
                .map(DeliveryAddress::getId)
                .orElse(1L);  // 如果没有收货地址，默认使用ID为1的地址

        // 2. 创建订单
        Orders order = new Orders(null, ro.getCid(), ro.getUid(), did,
                ro.getPrice() * ro.getQuantity(), new Date(), 0);

        // 3. 保存订单
        save(order);

        // 4. 更新购物车状态
        Cart cart = cartMapper.selectById(ro.getCid());
        if (cart != null) {
            cart.setStatus(1);
            cartMapper.updateById(cart);
        }

        return order.getId();
    }

    @Override
    public Boolean updateAddress(Long oid, Long did) {
        // 1. 获取订单信息
        Orders order = getById(oid);
        if (order == null) return false;

        // 2. 验证新的收货地址是否存在
        DeliveryAddress address = deliveryAddressMapper.selectById(did);
        if (address == null) return false;

        // 3. 更新订单的收货地址
        order.setDid(did);
        return updateById(order);
    }

    @Override
    @Transactional
    public Boolean payment(PaymentRO ro) {
        Orders order = getById(ro.getOid());
        if (order == null) return false;
        Boolean payResult = accountService.pay(ro.getUid(), order.getPrice());
        if (!payResult) return false;
        order.setStatus(1);
        boolean updatedOrder = updateById(order);
        if (!updatedOrder) return false;
        Long cid = order.getCid();
        List<Cart> carts = cartMapper.selectList(
                new QueryWrapper<Cart>().eq("id", cid)
        );
        for (Cart cart : carts) {
            Long pid = cart.getPid();
            Product prod = productMapper.selectById(pid);
            if (prod != null) {
                prod.setPayers(prod.getPayers() + 1);
                productMapper.updateById(prod);
            }
        }
        return true;
    }

    @Override
    public List<OrdersVO> getOrdersList(Long uid, Integer status) {
        // 1. 查询指定状态的订单
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUid, uid)
                .eq(Orders::getStatus, status);
        List<Orders> orders = list(wrapper);

        // 2. 转换为VO对象
        return orders.stream().map(order -> {
            // 获取购物车信息
            Cart cart = cartMapper.selectById(order.getCid());
            if (cart == null) return null;

            // 获取商品信息
            Product product = productMapper.selectById(cart.getPid());
            if (product == null) return null;

            // 获取购物车项的属性选项内容
            List<String> attributes = cartOptionMapper.selectList(
                    new LambdaQueryWrapper<CartOption>()
                            .eq(CartOption::getCid, cart.getId())
            ).stream()
                    .map(cartOption -> attributeOptionMapper.selectById(cartOption.getAoid()).getContent())
                    .toList();

            // 构建CartVO
            CartVO cartVO = Proxy.cart2VO(cart, product, attributes);

            // 构建OrdersVO
            return Proxy.orders2VO(order, cartVO);
        }).filter(Objects::nonNull).toList();
    }
}
