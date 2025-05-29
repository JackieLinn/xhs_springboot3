package ynu.jackielinn.xhs_springboot3.controller.exception;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;

@Slf4j
@RestControllerAdvice
@Tag(name = "参数校验相关接口", description = "与参数校验相关的操作接口")
public class ValidationController {

    /**
     * 与SpringBoot保持一致，校验不通过则打印警告信息，而不是直接抛出异常
     *
     * @param exception 验证异常
     * @return 校验结果
     */
    @ExceptionHandler(ValidationException.class)
    public RestBean<Void> validateError(ValidationException exception) {
        log.warn("Resolved [{}: {}]", exception.getClass().getName(), exception.getMessage());
        return RestBean.failure(400, "请求参数有误");
    }
}
