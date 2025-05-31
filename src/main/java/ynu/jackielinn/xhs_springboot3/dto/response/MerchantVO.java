package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "商家对象")
public class MerchantVO {
    @Schema(description = "商家ID")
    Long id;
    @Schema(description = "商家名")
    String name;
    @Schema(description = "商家图片")
    String image;
    @Schema(description = "商家粉丝")
    Integer fans;
    @Schema(description = "商家总共售出的数量")
    Integer sold;
}
