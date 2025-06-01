package ynu.jackielinn.xhs_springboot3.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.jackielinn.xhs_springboot3.entity.BaseData;

@Data
@TableName("variant")
@AllArgsConstructor
public class Variant implements BaseData {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("pid")
    Long pid;
    @TableField("aoid")
    Long aoid;
    @TableField("spread")
    Double spread;
}
