package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.DetailImages;
import ynu.jackielinn.xhs_springboot3.mapper.DetailImagesMapper;
import ynu.jackielinn.xhs_springboot3.service.DetailImagesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailImagesServiceImpl extends ServiceImpl<DetailImagesMapper, DetailImages>
        implements DetailImagesService {
    @Override
    public List<String> getImagesByPid(Long pid) {
        LambdaQueryWrapper<DetailImages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DetailImages::getPid, pid);
        return this.list(wrapper).stream()
                .map(DetailImages::getImage)
                .collect(Collectors.toList());
    }
}
