package ynu.jackielinn.xhs_springboot3.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.service.DetailImagesService;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@Tag(name = "商品详情图片接口", description = "与商品详情图片相关的操作接口")
public class DetailImagesController {

    @Resource
    DetailImagesService detailImagesService;

    @GetMapping("/get-images-by-pid")
    public RestBean<List<String>> getImagesByPid(Long pid) {
        return RestBean.success(detailImagesService.getImagesByPid(pid));
    }
}
