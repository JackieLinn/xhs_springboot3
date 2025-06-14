package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.request.DeliveryAddressSaveRO;
import ynu.jackielinn.xhs_springboot3.dto.request.DeliveryAddressUpdateRO;
import ynu.jackielinn.xhs_springboot3.dto.response.DeliveryAddressVO;
import ynu.jackielinn.xhs_springboot3.entity.po.DeliveryAddress;
import ynu.jackielinn.xhs_springboot3.mapper.DeliveryAddressMapper;
import ynu.jackielinn.xhs_springboot3.service.DeliveryAddressService;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressServiceImpl extends ServiceImpl<DeliveryAddressMapper, DeliveryAddress>
        implements DeliveryAddressService {

    /**
     * 根据用户ID查询并返回该用户的所有配送地址
     *
     * @param uid 用户ID
     * @return 该用户的所有配送地址列表
     */
    @Override
    public List<DeliveryAddressVO> listDeliveryAddressByUserId(Long uid) {
        QueryWrapper<DeliveryAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        List<DeliveryAddress> deliveryAddresses = baseMapper.selectList(queryWrapper);
        return deliveryAddresses.stream().
                map(Proxy::deliveryAddress2VO)
                .collect(Collectors.toList());
    }

    /**
     * 保存配送地址
     *
     * @param ro 包含配送地址信息的对象
     * @return 插入操作影响的行数
     */
    @Override
    public Integer saveDeliveryAddress(DeliveryAddressSaveRO ro) {
        DeliveryAddress deliveryAddress = new DeliveryAddress(null, ro.getName(), ro.getSex(),
                ro.getPhone(), ro.getAddress(), ro.getUid());
        return this.baseMapper.insert(deliveryAddress);
    }

    /**
     * 更新配送地址
     *
     * @param ro 包含配送地址信息的对象
     * @return 更新操作影响的行数
     */
    @Override
    public Integer updateDeliveryAddress(DeliveryAddressUpdateRO ro) {
        UpdateWrapper<DeliveryAddress> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("did", ro.getDid());
        updateWrapper.set("name", ro.getName());
        updateWrapper.set("sex", ro.getSex());
        updateWrapper.set("phone", ro.getPhone());
        updateWrapper.set("address", ro.getAddress());
        updateWrapper.set("uid", ro.getUid());
        return this.baseMapper.update(null, updateWrapper);
    }

    /**
     * 根据配送地址ID删除该配送地址
     *
     * @param did 配送地址ID
     * @return 删除操作影响的行数
     */
    @Override
    public Integer deleteDeliveryAddress(Long did) {
        return this.baseMapper.deleteById(did);
    }
}
