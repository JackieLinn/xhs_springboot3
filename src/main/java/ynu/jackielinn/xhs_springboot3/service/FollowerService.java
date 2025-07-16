package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Follow;

public interface FollowerService extends IService<Follow> {
    void follow(Follow follow);
    boolean isFollowing(Long follower, Long uid);
    void unfollow(Long follower, Long uid);
}
