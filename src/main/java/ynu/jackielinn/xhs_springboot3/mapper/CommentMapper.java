package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comments where blog_id = #{blogId}")
    List<Comment> getByBlogId(Integer blogId);

    @Select("select * from comments where uid = #{uid}")
    List<Comment> getByUid(Long uid);
}
