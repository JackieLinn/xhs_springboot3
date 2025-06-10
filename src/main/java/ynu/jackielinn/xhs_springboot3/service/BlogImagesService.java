package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.BlogImages;

import java.util.List;

public interface BlogImagesService extends IService<BlogImages> {
    List<BlogImages> getImagesByBlogId(Integer blogId);
}
