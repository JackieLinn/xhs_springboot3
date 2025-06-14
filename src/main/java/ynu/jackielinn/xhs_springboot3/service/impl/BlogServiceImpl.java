package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.jackielinn.xhs_springboot3.dto.request.BlogCreateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.entity.po.Liked;
import ynu.jackielinn.xhs_springboot3.mapper.BlogMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.BlogImagesService;
import ynu.jackielinn.xhs_springboot3.service.BlogService;
import ynu.jackielinn.xhs_springboot3.service.LikedService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Resource
    private AccountService  accountService;

    @Resource
    private BlogImagesService blogImagesService;

    @Resource
    private LikedService likedService;

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = blogMapper.getBlogById(id);
        Account account = accountService.getById(blog.getUid());
        BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
        blog.setUser(blogAccountVO);
        if(blog.getIsVideo()==false){
            blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
        }
        return blog;
    }

    @Override
    public List<Blog> getBlogByUid(Long uid) {
        List<Blog> blogs = blogMapper.getBlogByUid(uid);
        for(Blog blog : blogs){
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
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
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
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

    @Override
    public void createBlog(BlogCreateRO dto) {
        Blog blog = new Blog();
//        blog.setTitle(dto.getTitle());
//        blog.setContent(dto.getContent());
//        blog.setVisibility(dto.getVisibility());
//        blog.setImage(dto.getImage());
//        blog.setCreateTime(LocalDateTime.now());
//        blog.setUpdateTime(LocalDateTime.now());
//        blog.setUid(AuthUtils.getCurrentUserId()); // 获取当前登录用户 ID

        blogMapper.insert(blog); // 使用 MyBatis 插入
    }

    @Override
    public List<Blog> getFollowingBlogs(Long uid) {
        List<Blog> blogs = blogMapper.getFollowingBlogs(uid);
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
        }
        return blogs;
    }

    @Transactional
    @Override
    public void addLike(Integer id) {
        blogMapper.addLike(id);
        Blog blog = blogMapper.getBlogById(id);
        Account account = accountService.getById(blog.getUid());
        Liked liked = new Liked();
        liked.setUid(account.getId());
        liked.setBid(id);
        likedService.save(liked);
        accountService.addLike(account.getId());
    }

    @Transactional
    @Override
    public void deleteLike(Integer id) {
        blogMapper.deleteLike(id);
        Blog blog = blogMapper.getBlogById(id);
        Account account = accountService.getById(blog.getUid());
        Liked liked = new Liked();
        liked.setUid(account.getId());
        liked.setBid(id);
        likedService.removeCall(liked);
        accountService.deleteLike(account.getId());
    }

    private BlogAccountVO convertToBlogAccountVO(Account account) {
        BlogAccountVO blogAccountVO = new BlogAccountVO();
        blogAccountVO.setId(account.getId());
        blogAccountVO.setUsername(account.getUsername());
        blogAccountVO.setAvatar(account.getAvatar());
        return blogAccountVO;
    }

}
