package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.jackielinn.xhs_springboot3.entity.po.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
