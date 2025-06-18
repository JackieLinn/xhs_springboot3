package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.dto.request.BlogCreateRO;
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
    public RestBean<Blog> getBlogById(@PathVariable(name = "id") Integer id) {
        return RestBean.success(blogService.getBlogById(id));
    }

    @GetMapping("/uid")
    public RestBean<List<Blog>> getBlogByUid(@RequestParam(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getBlogByUid(uid);
        return RestBean.success(blogs);
    }

    @GetMapping("/random")
    public RestBean<List<Blog>> getRandomBlogs(@RequestParam(name = "page", defaultValue = "1") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Blog> blogs = blogService.getRandomBlogs(page, size);
        return RestBean.success(blogs);
    }

    @PostMapping("/publish")
    public RestBean<Void> publishBlogWithImages(@RequestBody BlogCreateRO blogDTO) {
        blogService.createBlog(blogDTO);
        return RestBean.success();
    }

    @GetMapping("/following")
    public RestBean<List<Blog>> getFollowingBlogs(@RequestParam(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getFollowingBlogs(uid);
        return RestBean.success(blogs);
    }

    @PostMapping("/addLike/{id}")
    public RestBean<Void> addLike(@PathVariable(name = "id") Integer id) {
        blogService.addLike(id);
        return RestBean.success();
    }

    @DeleteMapping("/deleteLike/{id}")
    public RestBean<Void> deleteLike(@PathVariable(name = "id") Integer id) {
        blogService.deleteLike(id);
        return RestBean.success();
    }
}
