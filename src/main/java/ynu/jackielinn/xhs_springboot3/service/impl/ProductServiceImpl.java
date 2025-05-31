package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Product;
import ynu.jackielinn.xhs_springboot3.mapper.ProductMapper;
import ynu.jackielinn.xhs_springboot3.service.ProductService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public List<ProductVO> getRecommendProducts() {
        List<Product> allProducts = this.list();
        java.util.Collections.shuffle(allProducts);
        return allProducts.stream()
                .limit(6)
                .map(Proxy::product2VO)
                .toList();
    }

    @Override
    public List<ProductVO> getAllProducts() {
        return this.list().stream()
                .map(Proxy::product2VO)
                .toList();
    }

    @Override
    public List<ProductVO> getProductsByType(Integer type) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getType, type);
        return this.list(wrapper).stream()
                .map(Proxy::product2VO)
                .toList();
    }

    @Override
    public ProductVO getProductById(Long id) {
        Product product = this.getById(id);
        return Proxy.product2VO(product);
    }

    @Override
    public Integer getTotalSoldByMid(long mid) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getMid, mid);
        return this.list(wrapper).stream()
                .mapToInt(Product::getPayers)
                .sum();
    }

    @Override
    public List<ProductVO> getProductsByMid(long mid) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getMid, mid);
        return this.list(wrapper).stream()
                .map(Proxy::product2VO)
                .toList();
    }
}
