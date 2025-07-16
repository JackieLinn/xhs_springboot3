package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.MerchantVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Merchant;
import ynu.jackielinn.xhs_springboot3.mapper.MerchantMapper;
import ynu.jackielinn.xhs_springboot3.service.MerchantService;
import ynu.jackielinn.xhs_springboot3.service.ProductService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.List;

@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant>
        implements MerchantService {

    @Resource
    private ProductService productService;

    @Override
    public Merchant getMerchantById(Long id) {
        return getById(id);
    }

    @Override
    public List<MerchantVO> getAllMerchants() {
        return this.list().stream()
                .map(Proxy::merchant2VO)
                .toList();
    }

    @Override
    public MerchantVO getMerchantVOByPid(Long pid) {
        var product = productService.getById(pid);
        if (product == null) return null;
        var merchant = getById(product.getMid());
        if (merchant == null) return null;
        return Proxy.merchant2VO(merchant);
    }
}
