package ynu.jackielinn.xhs_springboot3.utils;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ynu.jackielinn.xhs_springboot3.dto.response.MerchantVO;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Merchant;
import ynu.jackielinn.xhs_springboot3.entity.po.Product;
import ynu.jackielinn.xhs_springboot3.service.ProductService;

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
        ProductVO productVO = new ProductVO();
        productVO.setId(product.getId());
        productVO.setName(product.getName());
        productVO.setImage(product.getImage());
        productVO.setActivity(product.getActivity());
        productVO.setPrice(product.getPrice());
        productVO.setPayers(product.getPayers());
        return productVO;
    }

    public static MerchantVO merchant2VO(Merchant merchant) {
        if (merchant == null) return null;
        MerchantVO merchantVO = new MerchantVO();
        merchantVO.setId(merchant.getId());
        merchantVO.setName(merchant.getName());
        merchantVO.setImage(merchant.getImage());
        merchantVO.setFans(merchant.getFans());
        merchantVO.setSold(productService.getTotalSoldByMid(merchant.getId()));
        return merchantVO;
    }
}
