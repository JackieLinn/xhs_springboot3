package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "重置邮件对象")
public class EmailResetRO {
    @Email
    @Schema(description = "邮箱")
    String email;
    @Length(min = 6, max = 6)
    @Schema(description = "邮箱验证码")
    String code;
    @Length(min = 6, max = 20)
    @Schema(description = "密码")
    String password;
}
