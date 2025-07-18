package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.Favorite;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.mapper.FavoriteMapper;
import ynu.jackielinn.xhs_springboot3.service.FavoriteService;

import java.util.List;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public boolean existsByUidAndBid(Long uid, Integer bid) {
        return favoriteMapper.existsByUidAndBid(uid, bid) > 0;
    }

    @Override
    public void removeByUidAndBid(Long uid, Integer bid) {
        favoriteMapper.removeByUidAndBid(uid, bid);
    }

    @Override
    public List<Blog> getFavoriteBlogs(Long uid) {
        return favoriteMapper.getFavoriteBlogs(uid);
    }
}