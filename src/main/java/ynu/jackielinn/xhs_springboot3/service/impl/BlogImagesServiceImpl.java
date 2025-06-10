package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.BlogImages;
import ynu.jackielinn.xhs_springboot3.mapper.BlogImagesMapper;
import ynu.jackielinn.xhs_springboot3.service.BlogImagesService;

import java.util.List;

@Service
public class BlogImagesServiceImpl extends ServiceImpl<BlogImagesMapper, BlogImages> implements BlogImagesService {
    @Resource
    private BlogImagesMapper blogImagesMapper;

    @Override
    public List<BlogImages> getImagesByBlogId(Integer blogId){
        return blogImagesMapper.getImagesByBlogId(blogId);
    }
}
