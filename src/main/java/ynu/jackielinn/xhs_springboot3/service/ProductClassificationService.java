package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.ProductClassification;

import java.util.List;

public interface ProductClassificationService extends IService<ProductClassification> {

    List<ProductClassification> getAllCategories();
}
