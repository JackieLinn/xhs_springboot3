package ynu.jackielinn.xhs_springboot3.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "订单列表对象")
public class OrdersVO {
    @Schema(description = "订单ID")
    Long oid;
    @Schema(description = "订单信息")
    CartVO cartVO;
    @Schema(description = "订单日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date date;
    @Schema(description = "订单价格")
    Double price;
    @Schema(description = "订单状态")
    Integer status;
}
