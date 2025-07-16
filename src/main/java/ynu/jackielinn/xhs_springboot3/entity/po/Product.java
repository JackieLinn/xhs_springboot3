package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

@Data
@TableName("product")
@AllArgsConstructor
public class Product implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("name")
    String name;
    @TableField("image")
    String image;
    @TableField("activity")
    String activity;
    @TableField("price")
    Double price;
    @TableField("payers")
    Integer payers;
    @TableField("type")
    Integer type;
    @TableField("mid")
    Long mid;
}
