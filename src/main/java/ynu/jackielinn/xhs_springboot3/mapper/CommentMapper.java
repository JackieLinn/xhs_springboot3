package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Update;
=======
>>>>>>> e16626736a1a2a2b85c66b347a7b6244025690c9
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comments where blog_id = #{blogId}")
    List<Comment> getByBlogId(Integer blogId);

    @Select("select * from comments where uid = #{uid}")
    List<Comment> getByUid(Long uid);
<<<<<<< HEAD

    @Update("UPDATE comments SET likes = likes + 1 WHERE id = #{id}")
    void addLike(Integer id);

    @Update("UPDATE comments SET likes = likes - 1 WHERE id = #{id}")
    void deleteLike(Integer id);
=======
>>>>>>> e16626736a1a2a2b85c66b347a7b6244025690c9
}
