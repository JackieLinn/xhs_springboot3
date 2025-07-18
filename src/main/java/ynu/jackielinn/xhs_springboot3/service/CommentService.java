package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<Comment> getCommentsByBlogId(Integer blogId, Long uid);

    List<Comment> getCommentsByUid(Long uid, Long currentUid);

    void addLike(Long uid, Integer id);

    void deleteLike(Long uid, Integer id);
}
