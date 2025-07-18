package ynu.jackielinn.xhs_springboot3.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class BlogCreateRO {
    private Long uid;
    private String title;
    private String content;
    private String image;
    private Boolean draft;
    private Boolean isVideo;
    private List<String> imageUrls;
}
