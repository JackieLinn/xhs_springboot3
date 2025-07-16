package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.jackielinn.xhs_springboot3.entity.po.Liked;

@Mapper
public interface LikedMapper extends BaseMapper<Liked> {
    @Delete("delete from liked where uid = #{uid} and bid = #{bid}")
    void removeCall(Liked liked);

    /**
     * 判断用户是否已点赞某blog
     */
    @Select("SELECT COUNT(*) FROM liked WHERE uid = #{uid} AND bid = #{bid}")
    int existsByUidAndBid(Long uid, Integer bid);
}
