package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户对象")
public class AccountVO {
    @Schema(description = "用户ID")
    Long id;
    @Schema(description = "用户名")
    String username;
    @Schema(description = "用户性别")
    Integer sex;
    @Schema(description = "用户电话")
    String phone;
    @Schema(description = "用户邮箱")
    String email;
    @Schema(description = "用户头像")
    String avatar;
    @Schema(description = "用户余额")
    Double balance;
    @Schema(description = "用户简介")
    String introduction;
    @Schema(description = "关注数")
    Integer follow;
    @Schema(description = "粉丝数")
    Integer fans;
    @Schema(description = "获赞数")
    Integer likes;
}
