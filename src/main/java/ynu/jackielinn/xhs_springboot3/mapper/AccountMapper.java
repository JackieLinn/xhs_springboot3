package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.Follow;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    @Select("SELECT * FROM account WHERE id NOT IN (SELECT follower FROM follower WHERE uid = #{uid}) AND id NOT IN (SELECT uid FROM follower WHERE follower = #{uid})")
    List<Account> selectRandomUnfollowedUsers(Long uid, List<Long> followedIds);

    // 将用户 uid 的关注数 +1
    @Update("UPDATE account SET follow = follow + 1 WHERE id = #{uid}")
    void addFollow(Long uid);

    // 将用户 follower 的粉丝数 +1
    @Update("UPDATE account SET fans = fans + 1 WHERE id = #{follower}")
    void addFan(Long follower);

    @Update("UPDATE account SET likes = likes + 1 WHERE id = #{id}")
    void addLike(Long id);

    @Update("UPDATE account SET likes = likes - 1 WHERE id = #{id}")
    void deleteLike(Long id);
<<<<<<< HEAD

    @Update("UPDATE account SET follow = follow - 1 WHERE id = #{uid}")
    void deleteFollow(Long uid);

    @Update("UPDATE account SET fans = fans - 1 WHERE id = #{follower}")
    void deleteFan(Long follower);
=======
>>>>>>> e16626736a1a2a2b85c66b347a7b6244025690c9
}
