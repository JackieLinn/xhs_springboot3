package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.Role;
import ynu.jackielinn.xhs_springboot3.mapper.RoleMapper;
import ynu.jackielinn.xhs_springboot3.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public Role findRoleByRID(long rid) {
        return this.query()
                .eq("id", rid)
                .one();
    }
}
