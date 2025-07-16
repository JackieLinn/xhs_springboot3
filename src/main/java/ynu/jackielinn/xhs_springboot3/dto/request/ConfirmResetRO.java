package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@Schema(description = "密码重置确认对象")
public class ConfirmResetRO {
    @Email
    @Schema(description = "邮箱")
    String email;
    @Length(min = 6, max = 6)
    @Schema(description = "邮箱验证码")
    String code;
}
