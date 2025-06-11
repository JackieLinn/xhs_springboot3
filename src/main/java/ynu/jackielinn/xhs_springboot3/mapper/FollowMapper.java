package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ynu.jackielinn.xhs_springboot3.entity.po.Follow;

import java.util.List;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    @Select("select * from follower where uid = #{uid}")
    List<Follow> getFollows(Long uid);


    @Update("insert into follower(follower, uid) values(#{follower}, #{uid})")
    void addFollow(Follow follow);

}
