package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.Liked;
import ynu.jackielinn.xhs_springboot3.mapper.LikedMapper;
import ynu.jackielinn.xhs_springboot3.service.LikedService;

@Service
public class LikedServiceImpl extends ServiceImpl<LikedMapper, Liked> implements LikedService {

    @Resource
    private LikedMapper likedMapper;
    @Override
    public void removeCall(Liked liked) {
        likedMapper.removeCall(liked);
    }
}
