package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Product;

import java.util.List;

public interface ProductService extends IService<Product> {

    List<ProductVO> getRecommendProducts();

    List<ProductVO> getAllProducts();

    List<ProductVO> getProductsByType(Integer type);

    ProductVO getProductById(Long id);
}
