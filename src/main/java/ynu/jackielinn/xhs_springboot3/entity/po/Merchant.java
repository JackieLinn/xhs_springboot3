package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

@Data
@TableName("merchant")
@AllArgsConstructor
public class Merchant implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("name")
    String name;
    @TableField("address")
    String address;
    @TableField("introduction1")
    String introduction1;
    @TableField("introduction2")
    String introduction2;
    @TableField("image")
    String image;
    @TableField("follow")
    Integer follow;
    @TableField("fans")
    Integer fans;
    @TableField("likes")
    Integer likes;
}
