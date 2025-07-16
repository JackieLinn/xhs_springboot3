package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Liked;

public interface LikedService extends IService<Liked> {
    void removeCall(Liked liked);
    boolean existsByUidAndBid(Long uid, Integer bid);
}
