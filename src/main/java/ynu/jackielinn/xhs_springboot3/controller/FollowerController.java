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
        // 检查是否已经关注
        if (followerService.isFollowing(follow.getFollower(), follow.getUid())) {
            return RestBean.failure(400, "已经关注过了");
        }
        followerService.follow(follow);
        return RestBean.success("添加成功");
    }
    @PostMapping("/remove")
    public RestBean<String> removeFollower(@RequestParam(name = "follower") Long follower, @RequestParam(name = "uid") Long uid){
        followerService.unfollow(follower, uid);
        return RestBean.success("取消关注成功");
    }
}
