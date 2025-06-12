package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Follow;
import ynu.jackielinn.xhs_springboot3.service.FollowerService;

@RestController
@RequestMapping("/api/follower")
public class FollowerController {
    @Resource
    private FollowerService followerService;
    @PostMapping("/add")
    public RestBean<String> addFollower(@RequestBody Follow follow){
        followerService.follow(follow);
        return RestBean.success("添加成功");
    }
}
