package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

@Data
@TableName("cart")
@AllArgsConstructor
public class Cart implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("uid")
    Long uid;
    @TableField("mid")
    Long mid;
    @TableField("pid")
    Long pid;
    @TableField("price")
    Double price;
    @TableField("quantity")
    Integer quantity;
    @TableField("status")
    Integer status;
}
