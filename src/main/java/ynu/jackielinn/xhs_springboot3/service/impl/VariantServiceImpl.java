package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductSelectionVO;
import ynu.jackielinn.xhs_springboot3.entity.po.*;
import ynu.jackielinn.xhs_springboot3.mapper.*;
import ynu.jackielinn.xhs_springboot3.service.VariantService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品变体服务实现类
 */
@Service
public class VariantServiceImpl extends ServiceImpl<VariantMapper, Variant>
        implements VariantService {

    @Resource
    private ProductAttributeMapper productAttributeMapper;

    @Resource
    private AttributeMapper attributeMapper;

    @Resource
    private AttributeOptionMapper attributeOptionMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public ProductSelectionVO getProductSelection(Long pid) {
        // 1. 获取商品基本信息
        Product product = productMapper.selectById(pid);
        if (product == null) return null;

        // 2. 获取商品的所有属性关联
        List<ProductAttribute> productAttributes = getProductAttributes(pid);
        if (productAttributes.isEmpty()) return null;

        // 3. 构建分类信息和计算差价
        List<Map<String, List<String>>> categories = new ArrayList<>();
        Map<String, List<String>> categoryMap = new HashMap<>();
        Double totalSpread = 0.0;

        for (ProductAttribute pa : productAttributes) {
            // 获取属性信息
            Attribute attribute = attributeMapper.selectById(pa.getAid());
            if (attribute == null) continue;

            // 获取该商品实际拥有的属性选项
            List<AttributeOption> options = getProductAttributeOptions(pid, attribute.getId());
            if (!options.isEmpty()) {
                // 将选项内容转换为列表
                List<String> optionContents = options.stream()
                        .map(AttributeOption::getContent)
                        .collect(Collectors.toList());

                // 添加到分类映射
                categoryMap.put(attribute.getName(), optionContents);

                // 获取第一个选项的变体差价
                AttributeOption firstOption = options.get(0);
                Variant firstVariant = getVariantByOptionId(pid, firstOption.getId());
                if (firstVariant != null) {
                    totalSpread += firstVariant.getSpread();
                }
            }
        }
        categories.add(categoryMap);

        // 4. 使用Proxy转换VO
        return Proxy.product2SelectionVO(product, categories, totalSpread);
    }

    /**
     * 获取商品的所有属性关联
     */
    private List<ProductAttribute> getProductAttributes(Long pid) {
        LambdaQueryWrapper<ProductAttribute> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductAttribute::getPid, pid);
        return productAttributeMapper.selectList(wrapper);
    }

    /**
     * 获取商品实际拥有的属性选项
     */
    private List<AttributeOption> getProductAttributeOptions(Long pid, Long attributeId) {
        // 获取该商品的所有变体
        LambdaQueryWrapper<Variant> variantWrapper = new LambdaQueryWrapper<>();
        variantWrapper.eq(Variant::getPid, pid);
        List<Variant> variants = this.list(variantWrapper);
        
        // 获取这些变体对应的选项ID
        Set<Long> optionIds = variants.stream()
                .map(Variant::getAoid)
                .collect(Collectors.toSet());

        // 获取这些选项的详细信息，并按照数据库中的顺序排序
        LambdaQueryWrapper<AttributeOption> aoWrapper = new LambdaQueryWrapper<>();
        aoWrapper.eq(AttributeOption::getAid, attributeId)
                .in(AttributeOption::getId, optionIds)
                .orderByAsc(AttributeOption::getId);
        return attributeOptionMapper.selectList(aoWrapper);
    }

    /**
     * 根据商品ID和选项ID获取变体
     */
    private Variant getVariantByOptionId(Long pid, Long optionId) {
        LambdaQueryWrapper<Variant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Variant::getPid, pid)
                .eq(Variant::getAoid, optionId);
        return this.getOne(wrapper);
    }
}
