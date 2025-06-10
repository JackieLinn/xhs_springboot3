package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;
import ynu.jackielinn.xhs_springboot3.dto.response.CommentAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;
import ynu.jackielinn.xhs_springboot3.mapper.CommentMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private AccountService accountService;
    @Override
    public List<Comment> getCommentsByBlogId(Integer blogId) {
        List<Comment> comments = commentMapper.getByBlogId(blogId);
        for(Comment c : comments){
            Account account = accountService.getById(c.getUid());
            c.setUser(convertToBlogAccountVO(account));
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsByUid(Long uid) {
        List<Comment> comments = commentMapper.getByUid(uid);
        for(Comment c : comments){
            Account account = accountService.getById(c.getUid());
            c.setUser(convertToBlogAccountVO(account));
        }
        return comments;
    }

    private CommentAccountVO convertToBlogAccountVO(Account account) {
        CommentAccountVO commentAccountVO = new CommentAccountVO();
        commentAccountVO.setId(account.getId());
        commentAccountVO.setUsername(account.getUsername());
        commentAccountVO.setAvatar(account.getAvatar());
        return commentAccountVO;
    }
}
