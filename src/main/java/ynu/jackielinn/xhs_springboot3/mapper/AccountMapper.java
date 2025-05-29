package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
