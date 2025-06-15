package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import ynu.jackielinn.xhs_springboot3.entity.po.Liked;

@Mapper
public interface LikedMapper extends BaseMapper<Liked> {
    @Delete("delete from liked where uid = #{uid} and bid = #{blogId}")
    void removeCall(Liked liked);
}
