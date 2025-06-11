package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.request.BlogCreateRO;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    Blog getBlogById(Integer id);

    List<Blog> getBlogByUid(Long uid);

    List<Blog> getRandomBlogs(int page, int size);

    List<Blog> getBlogsByKeyword(String keyword);

    void createBlog(BlogCreateRO blogDTO);

    List<Blog> getFollowingBlogs(Long uid);
}
