package ynu.jackielinn.xhs_springboot3;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ynu.jackielinn.xhs_springboot3.dto.response.ProductSelectionVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.entity.po.Comment;
import ynu.jackielinn.xhs_springboot3.service.CommentService;
import ynu.jackielinn.xhs_springboot3.service.VariantService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestComment {

    @Resource
    private CommentService commentService;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    void testGetComment() throws Exception {
        List<Comment> comments = commentService.getCommentsByBlogId(1);
        for (Comment comment : comments){
            System.out.println(objectMapper.writeValueAsString(comment));
        }
    }

}
