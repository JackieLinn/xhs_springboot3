package ynu.jackielinn.xhs_springboot3.utils;

import ynu.jackielinn.xhs_springboot3.dto.response.ProductVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Product;

/**
 * VO对象转换工具
 */
public class Proxy {

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
}
