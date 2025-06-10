package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/auth/search")
public class SearchController {
    @Resource
    private BlogService blogService;

    @GetMapping("/keyword")
    public RestBean<List<Blog>> searchBlogByKeyword(String keyword){
        return RestBean.success(blogService.getBlogsByKeyword(keyword));
    }

}
