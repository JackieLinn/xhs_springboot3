package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("detail_images")
@AllArgsConstructor
public class DetailImages {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("pid")
    Long pid;
    @TableField("image")
    String image;
}
