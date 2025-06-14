package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.request.DeliveryAddressSaveRO;
import ynu.jackielinn.xhs_springboot3.dto.request.DeliveryAddressUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.DeliveryAddressVO;
import ynu.jackielinn.xhs_springboot3.entity.po.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService extends IService<DeliveryAddress> {

    List<DeliveryAddressVO> listDeliveryAddressByUserId(Long uid);

    Integer saveDeliveryAddress(DeliveryAddressSaveRO ro);

    Integer updateDeliveryAddress(DeliveryAddressUpdateRO ro);

    Integer deleteDeliveryAddress(Long did);
}
