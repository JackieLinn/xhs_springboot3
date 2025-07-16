package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comment_liked")
public class CommentLiked {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("uid")
    private Long uid;
    @TableField("cid")
    private Integer cid;
}