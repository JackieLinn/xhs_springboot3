package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Variant;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductSelectionVO;

public interface VariantService extends IService<Variant> {

    /**
     * 获取商品选择信息
     * @param pid 商品ID
     * @return 商品选择信息
     */
    ProductSelectionVO getProductSelection(Long pid);
}
