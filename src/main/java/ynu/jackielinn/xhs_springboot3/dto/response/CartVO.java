package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "单个购物车列表对象")
public class CartVO {
    @Schema(description = "购物车ID")
    Long cid;
    @Schema(description = "商品图片")
    String image;
    @Schema(description = "商品名称")
    String name;
    @Schema(description = "属性列表")
    List<String> attributes;
    @Schema(description = "商品价格")
    Double price;
    @Schema(description = "购买数量")
    Integer quantity;
}
