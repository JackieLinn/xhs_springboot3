package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.jackielinn.xhs_springboot3.entity.po.ProductClassification;

@Mapper
public interface ProductClassificationMapper extends BaseMapper<ProductClassification> {
}
