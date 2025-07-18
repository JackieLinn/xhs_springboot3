package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.dto.request.BlogCreateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.service.BlogService;
import ynu.jackielinn.xhs_springboot3.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/auth/blogs")
public class BlogController {
    @Resource
    private BlogService blogService;

    @Resource
    private FavoriteService favoriteService;

    @GetMapping("/{id}")
    public RestBean<Blog> getBlogById(@PathVariable(name = "id") Integer id, @RequestParam(name = "uid") Long uid) {
        return RestBean.success(blogService.getBlogById(id, uid));
    }

    @GetMapping("/uid")
    public RestBean<List<Blog>> getBlogByUid(@RequestParam(name = "uid") Long targetUid, @RequestParam(name = "currentUid") Long uid) {
        List<Blog> blogs = blogService.getBlogByUid(targetUid, uid);
        return RestBean.success(blogs);
    }

    @GetMapping("/random")
    public RestBean<List<Blog>> getRandomBlogs(@RequestParam(name = "page", defaultValue = "1") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                              @RequestParam(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getRandomBlogs(page, size, uid);
        return RestBean.success(blogs);
    }

    @GetMapping("/random/video")
    public RestBean<List<Blog>> getRandomVideoBlogs(@RequestParam(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getRandomVideoBlogs(uid);
        return RestBean.success(blogs);
    }

    @PostMapping("/publish")
    public RestBean<Void> publishBlogWithImages(@RequestBody BlogCreateRO blogDTO) {
        blogService.createBlog(blogDTO);
        return RestBean.success();
    }

    @GetMapping("/following")
    public RestBean<List<Blog>> getFollowingBlogs(@RequestParam(name = "uid") Long uid, @RequestParam(name = "currentUid") Long currentUid) {
        List<Blog> blogs = blogService.getFollowingBlogs(uid, currentUid);
        return RestBean.success(blogs);
    }

    @PostMapping("/addLike/{id}")
    public RestBean<Void> addLike(@RequestParam(name = "uid") Long uid,@PathVariable(name = "id") Integer id) {
        blogService.addLike(uid,id);
        return RestBean.success();
    }

    @PostMapping("/deleteLike/{id}")
    public RestBean<Void> deleteLike(@RequestParam(name = "uid") Long uid,@PathVariable(name = "id") Integer id) {
        blogService.deleteLike(uid,id);
        return RestBean.success();
    }

    @GetMapping("/like")
    public  RestBean<List<Blog>> getLikedBlogs(@RequestParam(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getLikedBlogs(uid);
        return RestBean.success(blogs);
    }

    @PostMapping("/addFavorite/{id}")
    public RestBean<Void> addFavorite(@RequestParam(name = "uid") Long uid, @PathVariable(name = "id") Integer id) {
        if (!favoriteService.existsByUidAndBid(uid, id)) {
            ynu.jackielinn.xhs_springboot3.entity.po.Favorite favorite = new ynu.jackielinn.xhs_springboot3.entity.po.Favorite();
            favorite.setUid(uid);
            favorite.setBid(id);
            favoriteService.save(favorite);
        }
        return RestBean.success();
    }

    @PostMapping("/deleteFavorite/{id}")
    public RestBean<Void> deleteFavorite(@RequestParam(name = "uid") Long uid, @PathVariable(name = "id") Integer id) {
        if (favoriteService.existsByUidAndBid(uid, id)) {
            favoriteService.removeByUidAndBid(uid, id);
        }
        return RestBean.success();
    }

    @GetMapping("/favorite")
    public RestBean<List<Blog>> getFavoriteBlogs(@RequestParam(name = "uid") Long uid) {
        List<Blog> blogs = blogService.getFavoriteBlogs(uid);
        return RestBean.success(blogs);
    }
}
