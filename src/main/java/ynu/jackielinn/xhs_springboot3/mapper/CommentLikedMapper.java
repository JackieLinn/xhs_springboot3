package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import ynu.jackielinn.xhs_springboot3.entity.po.CommentLiked;

@Mapper
public interface CommentLikedMapper extends BaseMapper<CommentLiked> {
    @Select("SELECT COUNT(*) FROM comment_liked WHERE uid = #{uid} AND cid = #{cid}")
    int existsByUidAndCid(Long uid, Integer cid);

    @Delete("DELETE FROM comment_liked WHERE uid = #{uid} AND cid = #{cid}")
    void removeByUidAndCid(Long uid, Integer cid);
}