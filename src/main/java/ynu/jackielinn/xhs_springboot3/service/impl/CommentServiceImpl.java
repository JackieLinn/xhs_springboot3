package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.CommentAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;
import ynu.jackielinn.xhs_springboot3.entity.po.CommentLiked;
import ynu.jackielinn.xhs_springboot3.mapper.CommentMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.BlogService;
import ynu.jackielinn.xhs_springboot3.service.CommentLikedService;
import ynu.jackielinn.xhs_springboot3.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private BlogService blogService;

    @Resource
    private CommentLikedService commentLikedService;

    @Override
    public List<Comment> getCommentsByBlogId(Integer blogId, Long uid) {
        List<Comment> comments = commentMapper.getByBlogId(blogId);
        for(Comment c : comments){
            Account account = accountService.getById(c.getUid());
            c.setUser(convertToBlogAccountVO(account));
            c.setLiked(uid != null && commentLikedService.existsByUidAndCid(uid, c.getId()));
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsByUid(Long uid, Long currentUid) {
        List<Comment> comments = commentMapper.getByUid(uid);
        for(Comment c : comments){
            Account account = accountService.getById(c.getUid());
            Blog blog = blogService.getBlogById(c.getBlogId(),uid);
            c.setBlog(blog);
            c.setUser(convertToBlogAccountVO(account));
            c.setLiked(currentUid != null && commentLikedService.existsByUidAndCid(currentUid, c.getId()));
        }
        return comments;
    }

    @Override
    public void addLike(Long uid, Integer id) {
        if (!commentLikedService.existsByUidAndCid(uid, id)) {
            CommentLiked liked = new CommentLiked();
            liked.setUid(uid);
            liked.setCid(id);
            commentLikedService.save(liked);
            commentMapper.addLike(id);
        }
    }

    @Override
    public void deleteLike(Long uid, Integer id) {
        if (commentLikedService.existsByUidAndCid(uid, id)) {
            commentLikedService.removeByUidAndCid(uid, id);
            commentMapper.deleteLike(id);
        }
    }

    private CommentAccountVO convertToBlogAccountVO(Account account) {
        CommentAccountVO commentAccountVO = new CommentAccountVO();
        commentAccountVO.setId(account.getId());
        commentAccountVO.setUsername(account.getUsername());
        commentAccountVO.setAvatar(account.getAvatar());
        return commentAccountVO;
    }
}
