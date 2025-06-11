package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    @Select("SELECT * FROM blogs ORDER BY RAND()")  // Fetch random blogs
    List<Blog> getRandomBlogs();  // Get random blogs (no need for user info here)

    @Select("SELECT * FROM blogs WHERE uid = #{uid}")
    List<Blog> getBlogByUid(Long uid);

    @Select(
            "SELECT b.* " +
                    "FROM blogs b " +
                    "LEFT JOIN account a ON b.uid = a.id " +
                    "WHERE b.title   LIKE CONCAT('%', #{keyword}, '%') " +
                    "   OR b.content LIKE CONCAT('%', #{keyword}, '%') " +
                    "   OR a.username LIKE CONCAT('%', #{keyword}, '%')"
    )
    List<Blog> getBlogsByKeyword(String keyword);

    @Select(
            "SELECT b.* " +
                    "FROM blogs b " +
                    "LEFT JOIN follower f ON b.uid = f.uid " +
                    "WHERE f.follower = #{uid}"
    )
    List<Blog> getFollowingBlogs(Long uid);
}
