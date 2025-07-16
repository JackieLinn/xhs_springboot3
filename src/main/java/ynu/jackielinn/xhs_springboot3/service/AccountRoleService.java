package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.AccountRole;

public interface AccountRoleService extends IService<AccountRole> {

    AccountRole findRIDByUID(long uid);

    boolean registerAccountRole(long uid);
}
