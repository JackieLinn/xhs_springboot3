package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "选项对象")
public class OptionVO {
    @Schema(description = "选项ID")
    Long id;
    @Schema(description = "选项内容")
    String content;
}
