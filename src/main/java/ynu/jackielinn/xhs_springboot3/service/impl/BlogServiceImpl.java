package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.mapper.BlogMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.BlogService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Resource
    private AccountService  accountService;
    @Override
    public Blog getBlogById(Long id) {
        return null;
    }

    @Override
    public List<Blog> getBlogByUid(Long uid) {
        List<Blog> blogs = blogMapper.getBlogByUid(uid);
        for(Blog blog : blogs){
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
        }
        return blogs;
    }

    @Override
    @Transactional
    public List<Blog> getRandomBlogs(int page, int size) {
        PageHelper.startPage(page, size);
        List<Blog> blogs = blogMapper.getRandomBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        for (Blog blog : pageInfo.getList()) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
        }

        return pageInfo.getList();
    }

    @Override
    public List<Blog> getBlogsByKeyword(String keyword) {
        List<Blog> blogs = blogMapper.getBlogsByKeyword(keyword);
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
        }
        return blogs;
    }

    private BlogAccountVO convertToBlogAccountVO(Account account) {
        BlogAccountVO blogAccountVO = new BlogAccountVO();
        blogAccountVO.setId(account.getId());
        blogAccountVO.setUsername(account.getUsername());
        blogAccountVO.setAvatar(account.getAvatar());
        return blogAccountVO;
    }
}
