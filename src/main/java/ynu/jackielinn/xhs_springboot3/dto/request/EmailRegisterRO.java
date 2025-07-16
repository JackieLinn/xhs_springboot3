package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "邮箱注册对象")
public class EmailRegisterRO {
    @Schema(description = "电话号码")
    String phone;
    @Email
    @Schema(description = "邮箱")
    String email;
    @Length(min = 6, max = 6)
    @Schema(description = "邮箱验证码")
    String code;
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$")
    @Length(min = 1, max = 30)
    @Schema(description = "用户名")
    String username;
    @Length(min = 6, max = 20)
    @Schema(description = "密码")
    String password;
}
