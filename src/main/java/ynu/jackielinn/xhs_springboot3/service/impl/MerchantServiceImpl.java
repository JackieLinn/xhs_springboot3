package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.MerchantVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Merchant;
import ynu.jackielinn.xhs_springboot3.mapper.MerchantMapper;
import ynu.jackielinn.xhs_springboot3.service.MerchantService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.List;

@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant>
        implements MerchantService {

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
}
