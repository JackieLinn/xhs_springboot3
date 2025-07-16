package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.dto.request.OrdersCreateRO;
import ynu.jackielinn.xhs_springboot3.dto.request.PaymentRO;
import ynu.jackielinn.xhs_springboot3.dto.response.OrdersVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Orders;

import java.util.List;

public interface OrdersService extends IService<Orders> {

    Long createOrders(OrdersCreateRO ro);

    Boolean updateAddress(Long oid, Long did);

    Boolean payment(PaymentRO ro);

    List<OrdersVO> getOrdersList(Long uid, Integer status);
}
