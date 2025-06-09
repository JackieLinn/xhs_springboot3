package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/auth/blogs")
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("/{id}")
    public RestBean<Blog> getBlogById(@PathVariable(name = "id") Long id) {
        return RestBean.success(blogService.getBlogById(id));
    }

    @GetMapping("/{uid}")
    public RestBean<List<Blog>> getBlogByUid(@PathVariable(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getBlogByUid(uid);
        return RestBean.success(blogs);
    }

    @GetMapping("/random")
    public List<Blog> getRandomBlogs(@RequestParam(name = "page", defaultValue = "1") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        return blogService.getRandomBlogs(page, size);
    }

}
