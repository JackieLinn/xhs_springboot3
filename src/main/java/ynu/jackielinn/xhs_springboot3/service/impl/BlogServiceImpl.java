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
import ynu.jackielinn.xhs_springboot3.entity.po.BlogImages;
import ynu.jackielinn.xhs_springboot3.entity.po.Liked;
import ynu.jackielinn.xhs_springboot3.mapper.BlogMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.BlogImagesService;
import ynu.jackielinn.xhs_springboot3.service.BlogService;
import ynu.jackielinn.xhs_springboot3.service.LikedService;
import ynu.jackielinn.xhs_springboot3.service.FavoriteService;
import ynu.jackielinn.xhs_springboot3.service.FollowerService;

import java.util.Date;
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

    @Resource
    private FavoriteService favoriteService;

    @Resource
    private FollowerService followerService;

    @Override
    public Blog getBlogById(Integer id, Long uid) {
        Blog blog = blogMapper.getBlogById(id);
        Account account = accountService.getById(blog.getUid());
        BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
        blog.setUser(blogAccountVO);
        if(blog.getIsVideo()==false){
            blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
        }
        blog.setLiked(uid != null && likedService.existsByUidAndBid(uid, blog.getId()));
        blog.setFavorited(uid != null && favoriteService.existsByUidAndBid(uid, blog.getId()));
        blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
        return blog;
    }

    @Override
    public List<Blog> getBlogByUid(Long targetUid, Long uid) {
        List<Blog> blogs = blogMapper.getBlogByUid(targetUid);
        for(Blog blog : blogs){
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
            blog.setLiked(uid != null && likedService.existsByUidAndBid(uid, blog.getId()));
            blog.setFavorited(uid != null && favoriteService.existsByUidAndBid(uid, blog.getId()));
            blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
        }
        return blogs;
    }

    @Override
    @Transactional
    public List<Blog> getRandomBlogs(int page, int size, Long uid) {
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
            blog.setLiked(uid != null && likedService.existsByUidAndBid(uid, blog.getId()));
            blog.setFavorited(uid != null && favoriteService.existsByUidAndBid(uid, blog.getId()));
            blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
        }
        return pageInfo.getList();
    }

    @Override
    public List<Blog> getBlogsByKeyword(String keyword, Long uid) {
        List<Blog> blogs = blogMapper.getBlogsByKeyword(keyword);
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
            blog.setLiked(uid != null && likedService.existsByUidAndBid(uid, blog.getId()));
            blog.setFavorited(uid != null && favoriteService.existsByUidAndBid(uid, blog.getId()));
            blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
        }
        return blogs;
    }

    @Override
    @Transactional
    public void createBlog(BlogCreateRO blogDTO) {
        Blog blog = new Blog();
        System.out.println(blogDTO.getUid());
        blog.setUid(blogDTO.getUid());
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setDraft(blogDTO.getDraft() != null ? blogDTO.getDraft() : false);
        blog.setIsVideo(blogDTO.getIsVideo() != null ? blogDTO.getIsVideo() : false);
        Date now = new Date();
        blog.setCreateTime(now);
        blog.setLikes(0);
        
        blogMapper.insert(blog);
        
        // 如果有图片URL，保存到blog_images表
        if (blogDTO.getImageUrls() != null && !blogDTO.getImageUrls().isEmpty()) {
            for (String imageUrl : blogDTO.getImageUrls()) {
                BlogImages blogImage = new BlogImages();
                blogImage.setBid(blog.getId());
                blogImage.setUrl(imageUrl);
                blogImagesService.save(blogImage);
            }
        }
    }

    @Override
    public List<Blog> getFollowingBlogs(Long uid, Long currentUid) {
        List<Blog> blogs = blogMapper.getFollowingBlogs(uid);
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
            blog.setLiked(currentUid != null && likedService.existsByUidAndBid(currentUid, blog.getId()));
            blog.setFavorited(currentUid != null && favoriteService.existsByUidAndBid(currentUid, blog.getId()));
            blog.setFollowed(currentUid != null && !currentUid.equals(blog.getUid()) && followerService.isFollowing(currentUid, blog.getUid()));
        }
        return blogs;
    }

    @Transactional
    @Override
    public void addLike(Long uid,Integer id) {
        if (likedService.existsByUidAndBid(uid, id)) {
            return;
        }
        blogMapper.addLike(uid,id);
        Blog blog = blogMapper.getBlogById(id);
        Liked liked = new Liked();
        liked.setUid(uid);
        liked.setBid(id);
        likedService.save(liked);
        accountService.addLike(blog.getUid());
    }

    @Transactional
    @Override
    public void deleteLike(Long uid,Integer id) {
        blogMapper.deleteLike(uid,id);
        Blog blog = blogMapper.getBlogById(id);
        Liked liked = new Liked();
        liked.setUid(uid);
        liked.setBid(id);
        likedService.removeCall(liked);
        accountService.deleteLike(blog.getUid());
    }

    @Override
    public List<Blog> getRandomVideoBlogs(Long uid) {
        List<Blog> blogs = blogMapper.getRandomVideoBlogs();
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            blog.setLiked(uid != null && likedService.existsByUidAndBid(uid, blog.getId()));
            blog.setFavorited(uid != null && favoriteService.existsByUidAndBid(uid, blog.getId()));
            blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
        }
        return blogs;
    }

    @Override
    public List<Blog> getLikedBlogs(Long uid) {
        List<Blog> blogs = blogMapper.getLikedBlogs(uid);
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if(blog.getIsVideo()==false){
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
            blog.setLiked(true); // 已点赞
            blog.setFavorited(uid != null && favoriteService.existsByUidAndBid(uid, blog.getId()));
            blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
        }
        return blogs;
    }

    @Override
    public List<Blog> getFavoriteBlogs(Long uid) {
        List<Blog> blogs = favoriteService.getFavoriteBlogs(uid);
        for (Blog blog : blogs) {
            Account account = accountService.getById(blog.getUid());
            BlogAccountVO blogAccountVO = convertToBlogAccountVO(account);
            blog.setUser(blogAccountVO);
            if (blog.getIsVideo() == false) {
                blog.setImages(blogImagesService.getImagesByBlogId(blog.getId()));
            }
            blog.setLiked(uid != null && likedService.existsByUidAndBid(uid, blog.getId()));
            blog.setFavorited(true);
            blog.setFollowed(uid != null && !uid.equals(blog.getUid()) && followerService.isFollowing(uid, blog.getUid()));
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
