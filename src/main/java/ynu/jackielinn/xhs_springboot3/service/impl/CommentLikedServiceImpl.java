package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.CommentLiked;
import ynu.jackielinn.xhs_springboot3.mapper.CommentLikedMapper;
import ynu.jackielinn.xhs_springboot3.service.CommentLikedService;

@Service
public class CommentLikedServiceImpl extends ServiceImpl<CommentLikedMapper, CommentLiked> implements CommentLikedService {
    @Resource
    private CommentLikedMapper commentLikedMapper;

    @Override
    public boolean existsByUidAndCid(Long uid, Integer cid) {
        return commentLikedMapper.existsByUidAndCid(uid, cid) > 0;
    }

    @Override
    public void removeByUidAndCid(Long uid, Integer cid) {
        commentLikedMapper.removeByUidAndCid(uid, cid);
    }
}