package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;
import ynu.jackielinn.xhs_springboot3.service.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth/comments")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping ("/bid")
    public RestBean<List<Comment>> getCommentByBlogId(@RequestParam(name = "blog_id") Integer blog_id, @RequestParam(name = "uid") Long uid) {
        return RestBean.success(commentService.getCommentsByBlogId(blog_id, uid));
    }

    @GetMapping ("/uid")
    public RestBean<List<Comment>> getCommentByUid(@RequestParam(name = "uid") Long uid, @RequestParam(name = "currentUid") Long currentUid) {
        return RestBean.success(commentService.getCommentsByUid(uid, currentUid));
    }

    @PostMapping("/upload")
    public RestBean<String> uploadComment(@RequestBody Comment comment) {
        log.error("上传评论：{}", comment);
        if (commentService.save(comment)) {
            return RestBean.success("评论成功");
        }
        return RestBean.failure(500, "评论失败");
    }

    @PostMapping("/addLike/{id}")
    public RestBean<Void> addLike(@RequestParam(name = "uid") Long uid, @PathVariable(name = "id") Integer id) {
        commentService.addLike(uid, id);
        return RestBean.success();
    }

    @PostMapping("/deleteLike/{id}")
    public RestBean<Void> deleteLike(@RequestParam(name = "uid") Long uid, @PathVariable(name = "id") Integer id) {
        commentService.deleteLike(uid, id);
        return RestBean.success();
    }

}
