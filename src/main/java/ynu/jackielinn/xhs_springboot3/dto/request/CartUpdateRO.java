package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "购物车更新对象")
public class CartUpdateRO {
    @Schema(description = "购物车ID")
    Long cid;
    @Schema(description = "操作类型")
    Integer type;
}
