package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Favorite;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;

import java.util.List;

public interface FavoriteService extends IService<Favorite> {
    boolean existsByUidAndBid(Long uid, Integer bid);
    void removeByUidAndBid(Long uid, Integer bid);
    List<Blog> getFavoriteBlogs(Long uid);
}