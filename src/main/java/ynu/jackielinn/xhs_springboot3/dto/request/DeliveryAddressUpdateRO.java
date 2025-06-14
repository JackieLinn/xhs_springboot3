package ynu.jackielinn.xhs_springboot3.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "送货地址更新对象")
public class DeliveryAddressUpdateRO {
    @Schema(description = "送货地址ID")
    Long did;
    @Schema(description = "联系人姓名")
    String name;
    @Schema(description = "联系人性别")
    Integer sex;
    @Schema(description = "联系人电话")
    String phone;
    @Schema(description = "地址")
    String address;
    @Schema(description = "用户ID")
    Long uid;
}
