package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.dto.response.AccountVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@Tag(name = "用户相关接口", description = "与用户相关的操作接口")
public class AccountController {

    @Resource
    AccountService accountService;

    @Operation(summary = "通过用户编号获取用户信息", description = "通过用户编号获取用户信息")
    @GetMapping("/get-account-by-userId")
    public RestBean<AccountVO> getAccountByUserId(Long uid) {
        return RestBean.success(accountService.getAccountByUid(uid));
    }

    @Operation(summary = "随机获取未关注用户列表", description = "随机推荐若干个当前登录用户未关注的其他用户")
    @GetMapping("/random-unfollowed")
    public RestBean<List<AccountVO>> getRandomUnfollowedUsers(@RequestParam Long uid) {
        return RestBean.success(accountService.getRandomUnfollowedUsers(uid));
    }


}
