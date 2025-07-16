package ynu.jackielinn.xhs_springboot3.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "送货地址对象")
public class DeliveryAddressVO {
    @Schema(description = "送餐地址ID")
    Long id;
    @Schema(description = "联系人姓名")
    String name;
    @Schema(description = "联系人性别")
    Integer sex;
    @Schema(description = "联系人电话")
    String phone;
    @Schema(description = "地址")
    String address;
}
