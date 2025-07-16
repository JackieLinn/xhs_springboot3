package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ynu.jackielinn.xhs_springboot3.dto.response.AccountVO;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("history")
public class History {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("uid")
    private Long uid;
    @TableField("blog_id")
    private Integer blogId;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField(exist = false)
    private Blog blog;
}
