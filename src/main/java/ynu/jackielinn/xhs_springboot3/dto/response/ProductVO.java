package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "商品对象")
public class ProductVO {
    @Schema(description = "商品ID")
    Long id;
    @Schema(description = "商品名")
    String name;
    @Schema(description = "商品图片")
    String image;
    @Schema(description = "商品活动")
    String activity;
    @Schema(description = "商品价格")
    Double price;
    @Schema(description = "商品购买人数")
    Integer payers;
}
