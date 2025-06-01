package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

@Data
@TableName("delivery_address")
@AllArgsConstructor
public class DeliveryAddress implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("name")
    String name;
    @TableField("sex")
    Integer sex;
    @TableField("phone")
    String phone;
    @TableField("address")
    String address;
    @TableField("uid")
    Long uid;
}
