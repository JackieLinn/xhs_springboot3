package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("liked")
public class Liked {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("uid")
    private Long uid;
    @TableField("bid")
    private Integer bid;
    private Blog blog;
}
