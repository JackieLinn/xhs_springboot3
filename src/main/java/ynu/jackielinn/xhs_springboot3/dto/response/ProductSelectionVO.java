package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Schema(description = "商品选择对象")
public class ProductSelectionVO {
    @Schema(description = "商品ID")
    Long id;
    @Schema(description = "商品价格")
    Double price;
    @Schema(description = "商品图片")
    String image;
    @Schema(description = "商品分类")
    List<Map<String, List<OptionVO>>> categories;
}
