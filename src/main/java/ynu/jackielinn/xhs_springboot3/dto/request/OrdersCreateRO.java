package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "创建订单对象")
public class OrdersCreateRO {
    @Schema(description = "购物车ID")
    Long cid;
    @Schema(description = "用户ID")
    Long uid;
    @Schema(description = "单价")
    Double price;
    @Schema(description = "数量")
    Integer quantity;
}
