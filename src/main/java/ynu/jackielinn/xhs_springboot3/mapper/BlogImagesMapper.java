package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.jackielinn.xhs_springboot3.entity.po.BlogImages;

import java.util.List;

@Mapper
public interface BlogImagesMapper extends BaseMapper<BlogImages> {
    @Select("SELECT * FROM blog_images WHERE bid = #{blogId}")
    List<BlogImages> getImagesByBlogId(Integer blogId);
}
