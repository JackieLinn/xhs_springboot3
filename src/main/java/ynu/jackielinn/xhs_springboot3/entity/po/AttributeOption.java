package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("attribute_option")
@AllArgsConstructor
public class AttributeOption {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("aid")
    Long aid;
    @TableField("content")
    String content;
}
