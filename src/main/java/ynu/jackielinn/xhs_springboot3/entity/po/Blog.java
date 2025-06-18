package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blogs")
public class Blog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField ("uid")
    private Long uid;
    private BlogAccountVO user;
    @TableField ("title")
    private String title;
    @TableField ("content")
    private String content;
    @TableField ("likes")
    private Integer likes;
    @TableField("draft")
    private Boolean draft;
    @TableField("is_video")
    private Boolean isVideo;
    @TableField("video_url")
    private String videoUrl;
    private List<BlogImages> images;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
