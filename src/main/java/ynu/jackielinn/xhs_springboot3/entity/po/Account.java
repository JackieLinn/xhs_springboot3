package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

@Data
@TableName("account")
@AllArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("username")
    String username;
    @TableField("password")
    String password;
    @TableField("sex")
    Integer sex;
    @TableField("phone")
    String phone;
    @TableField("email")
    String email;
    @TableField("avatar")
    String avatar;
    @TableField("balance")
    Double balance;
    @TableField("introduction")
    String introduction;
    @TableField("follow")
    Integer follow;
    @TableField("fans")
    Integer fans;
    @TableField("likes")
    Integer likes;
}
