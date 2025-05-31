package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.ProductClassification;
import ynu.jackielinn.xhs_springboot3.mapper.ProductClassificationMapper;
import ynu.jackielinn.xhs_springboot3.service.ProductClassificationService;

import java.util.List;

@Service
public class ProductClassificationServiceImpl extends ServiceImpl<ProductClassificationMapper, ProductClassification>
        implements ProductClassificationService {
    @Override
    public List<ProductClassification> getAllCategories() {
        return this.list();
    }
}
