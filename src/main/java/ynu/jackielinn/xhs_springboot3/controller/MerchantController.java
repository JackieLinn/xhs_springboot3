package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.dto.response.MerchantVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Merchant;
import ynu.jackielinn.xhs_springboot3.service.MerchantService;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
@Tag(name = "商家接口", description = "与商家相关的操作接口")
public class MerchantController {

    @Resource
    MerchantService merchantService;

    @GetMapping("/get-merchant-by-id")
    public RestBean<Merchant> getMerchantById(Long id) {
        return RestBean.success(merchantService.getMerchantById(id));
    }

    @GetMapping("/get-all-merchants")
    public RestBean<List<MerchantVO>> getAllMerchants() {
        return RestBean.success(merchantService.getAllMerchants());
    }

    @GetMapping("/get-merchant-by-pid")
    public RestBean<MerchantVO> getMerchantVOByPid(Long pid) {
        return RestBean.success(merchantService.getMerchantVOByPid(pid));
    }
}
