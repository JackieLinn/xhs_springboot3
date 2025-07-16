package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.request.BlogCreateRO;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    Blog getBlogById(Integer id, Long uid);

    List<Blog> getBlogByUid(Long targetUid, Long uid);

    List<Blog> getRandomBlogs(int page, int size, Long uid);

    List<Blog> getBlogsByKeyword(String keyword, Long uid);

    void createBlog(BlogCreateRO blogDTO);

    List<Blog> getFollowingBlogs(Long uid, Long currentUid);

    void addLike(Long uid,Integer id);

    void deleteLike(Long uid,Integer id);

    List<Blog> getRandomVideoBlogs(Long uid);

    List<Blog> getLikedBlogs(Long uid);

    List<Blog> getFavoriteBlogs(Long uid);
}
