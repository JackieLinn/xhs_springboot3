package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "单个购物车列表对象")
public class CartListOneRO {
    @Schema(description = "用户ID")
    Long uid;
    @Schema(description = "商品ID")
    Long pid;
    @Schema(description = "商品价格")
    Double price;
    @Schema(description = "商品数量")
    Integer quantity;
    @Schema(description = "商品属性")
    List<Long> aoids;
}
