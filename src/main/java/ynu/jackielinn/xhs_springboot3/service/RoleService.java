package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.Role;

public interface RoleService extends IService<Role> {

    Role findRoleByRID(long rid);
}
