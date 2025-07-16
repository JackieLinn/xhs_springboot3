package ynu.jackielinn.xhs_springboot3.utils;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ynu.jackielinn.xhs_springboot3.dto.response.*;
import ynu.jackielinn.xhs_springboot3.entity.po.*;
import ynu.jackielinn.xhs_springboot3.service.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * VO对象转换工具
 */
@Component
public class Proxy implements ApplicationContextAware {

    private static ProductService productService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        productService = applicationContext.getBean(ProductService.class);
    }

    public static ProductVO product2VO(Product product) {
        if (product == null) return null;
        return product.asViewObject(ProductVO.class);
    }

    public static MerchantVO merchant2VO(Merchant merchant) {
        if (merchant == null) return null;
        return merchant.asViewObject(MerchantVO.class, vo ->
                vo.setSold(productService.getTotalSoldByMid(merchant.getId())));
    }

    public static ProductSelectionVO product2SelectionVO(Product product, 
            List<Map<String, List<OptionVO>>> categories, Double spread) {
        if (product == null) return null;
        return product.asViewObject(ProductSelectionVO.class, vo -> {
            vo.setCategories(categories);
            // 使用BigDecimal进行四舍五入，保留两位小数
            BigDecimal price = BigDecimal.valueOf(product.getPrice());
            BigDecimal spreadValue = BigDecimal.valueOf(spread);
            BigDecimal totalPrice = price.add(spreadValue).setScale(2, RoundingMode.HALF_UP);
            vo.setPrice(totalPrice.doubleValue());
        });
    }

    public static DeliveryAddressVO  deliveryAddress2VO(DeliveryAddress deliveryAddress) {
        if (deliveryAddress == null) return null;
        return deliveryAddress.asViewObject(DeliveryAddressVO.class);
    }

    public static AccountVO account2VO(Account account) {
        if (account == null) return null;
        return account.asViewObject(AccountVO.class);
    }

    public static CartVO cart2VO(Cart cart, Product product, List<String> attributes) {
        return product.asViewObject(CartVO.class, vo -> {
            vo.setCid(cart.getId());
            vo.setPrice(cart.getPrice());
            vo.setQuantity(cart.getQuantity());
            vo.setAttributes(attributes);
        });
    }

    public static OrdersVO orders2VO(Orders order, CartVO cartVO) {
        return order.asViewObject(OrdersVO.class, vo -> {
            vo.setOid(order.getId());
            vo.setCartVO(cartVO);
            vo.setDate(order.getDate());
            vo.setPrice(order.getPrice());
            vo.setStatus(order.getStatus());
        });
    }
}
