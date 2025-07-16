package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.jackielinn.xhs_springboot3.entity.po.Follow;
import ynu.jackielinn.xhs_springboot3.mapper.AccountMapper;
import ynu.jackielinn.xhs_springboot3.mapper.FollowMapper;
import ynu.jackielinn.xhs_springboot3.service.FollowerService;

@Service
public class FollowerServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowerService {

    @Resource
    private FollowMapper followMapper;

    @Resource
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public void follow(Follow follow) {
        followMapper.addFollow(follow);
        accountMapper.addFollow(follow.getFollower());
        accountMapper.addFan(follow.getUid());
    }

    @Override
    public boolean isFollowing(Long follower, Long uid) {
        return this.lambdaQuery()
                .eq(Follow::getFollower, follower)
                .eq(Follow::getUid, uid)
                .exists();
    }

    @Override
    @Transactional
    public void unfollow(Long follower, Long uid) {
        // 删除关注记录
        this.lambdaUpdate()
                .eq(Follow::getFollower, follower)
                .eq(Follow::getUid, uid)
                .remove();
        
        // 更新关注数和粉丝数
        accountMapper.deleteFollow(follower);
        accountMapper.deleteFan(uid);
    }
}
