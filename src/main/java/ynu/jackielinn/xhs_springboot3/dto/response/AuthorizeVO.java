package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "用户验证的对象")
public class AuthorizeVO {
    @Schema(description = "用户ID")
    Long id;
    @Schema(description = "用户名")
    String username;
    @Schema(description = "用户角色")
    String role;
    @Schema(description = "生成的token")
    String token;
    @Schema(description = "token过期时间")
    Date expire;
}
