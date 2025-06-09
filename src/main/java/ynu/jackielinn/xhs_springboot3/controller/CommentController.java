package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;
import ynu.jackielinn.xhs_springboot3.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/auth/comments")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping ("/bid")
    public RestBean<List<Comment>> getCommentByBlogId(@RequestParam(name = "blog_id") Integer blog_id) {
        return RestBean.success(commentService.getCommentsByBlogId(blog_id));
    }

    @GetMapping ("/uid")
    public RestBean<List<Comment>> getCommentByUid(@RequestParam(name = "uid") Long uid) {
        return RestBean.success(commentService.getCommentsByUid(uid));
    }
}
