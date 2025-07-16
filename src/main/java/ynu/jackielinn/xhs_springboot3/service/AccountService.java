package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import ynu.jackielinn.xhs_springboot3.dto.request.ConfirmResetRO;
import ynu.jackielinn.xhs_springboot3.dto.request.EmailRegisterRO;
import ynu.jackielinn.xhs_springboot3.dto.request.EmailResetRO;
import ynu.jackielinn.xhs_springboot3.dto.response.AccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {

    Account findAccountByUsernameOrEmail(String text);

    String registerEmailVerifyCode(String type, String email, String ip);

    String registerEmailAccount(EmailRegisterRO ro);

    String resetConfirm(ConfirmResetRO ro);

    String resetEmailAccountPassword(EmailResetRO ro);

    AccountVO getAccountByUid(Long uid);

    Boolean pay(Long uid, Double price);

    List<AccountVO> getRandomUnfollowedUsers(Long uid);

    void addLike(Long id);

    void deleteLike(Long id);
}
