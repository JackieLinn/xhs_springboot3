package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.CommentLiked;

public interface CommentLikedService extends IService<CommentLiked> {
    boolean existsByUidAndCid(Long uid, Integer cid);
    void removeByUidAndCid(Long uid, Integer cid);
}