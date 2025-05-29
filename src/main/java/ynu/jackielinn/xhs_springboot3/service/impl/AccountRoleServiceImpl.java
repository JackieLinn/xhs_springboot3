package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.AccountRole;
import ynu.jackielinn.xhs_springboot3.mapper.AccountRoleMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountRoleService;

@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole>
        implements AccountRoleService {
    @Override
    public AccountRole findRIDByUID(long uid) {
        return this.query()
                .eq("id", uid)
                .one();
    }

    @Override
    public boolean registerAccountRole(long uid) {
        AccountRole accountRole = new AccountRole(null, uid, 3L);
        return this.save(accountRole);
    }
}
