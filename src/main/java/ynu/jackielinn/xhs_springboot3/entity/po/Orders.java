package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

import java.util.Date;

@Data
@TableName("orders")
@AllArgsConstructor
public class Orders implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("cid")
    Long cid;
    @TableField("uid")
    Long uid;
    @TableField("did")
    Long did;
    @TableField("price")
    Double price;
    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date date;
    @TableField("status")
    Integer status;
}
