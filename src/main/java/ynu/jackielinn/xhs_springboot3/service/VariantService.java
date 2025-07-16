package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Variant;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductSelectionVO;

import java.util.List;

public interface VariantService extends IService<Variant> {

    /**
     * 获取商品选择信息
     * @param pid 商品ID
     * @return 商品选择信息
     */
    ProductSelectionVO getProductSelection(Long pid);

    /**
     * 根据用户选择的选项计算价格
     * @param pid 商品ID
     * @param optionIds 用户选择的选项ID列表
     * @return 计算后的价格
     */
    Double calculatePriceByOptions(Long pid, List<Long> optionIds);
}
