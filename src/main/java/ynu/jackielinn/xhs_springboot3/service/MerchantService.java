package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.response.MerchantVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Merchant;

import java.util.List;

public interface MerchantService extends IService<Merchant> {

    Merchant getMerchantById(Long id);

    List<MerchantVO> getAllMerchants();

    MerchantVO getMerchantVOByPid(Long pid);
}
