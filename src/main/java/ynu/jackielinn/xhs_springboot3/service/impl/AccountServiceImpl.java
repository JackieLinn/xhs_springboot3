package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.request.ConfirmResetRO;
import ynu.jackielinn.xhs_springboot3.dto.request.EmailRegisterRO;
import ynu.jackielinn.xhs_springboot3.dto.request.EmailResetRO;
import ynu.jackielinn.xhs_springboot3.dto.response.AccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.Follow;
import ynu.jackielinn.xhs_springboot3.entity.po.Role;
import ynu.jackielinn.xhs_springboot3.mapper.AccountMapper;
import ynu.jackielinn.xhs_springboot3.mapper.FollowMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountRoleService;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.RoleService;
import ynu.jackielinn.xhs_springboot3.utils.Const;
import ynu.jackielinn.xhs_springboot3.utils.FlowUtils;
import ynu.jackielinn.xhs_springboot3.utils.Proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    FlowUtils flowUtils;

    @Resource
    AmqpTemplate amqpTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    PasswordEncoder encoder;

    @Resource
    AccountRoleService accountRoleService;

    @Resource
    RoleService roleService;

    @Resource
    FollowMapper followMapper;

    @Resource
    AccountMapper accountMapper;

    /**
     * 根据用户名或邮箱查找账户
     *
     * @param text 用户名或邮箱
     * @return 找到的账户，如果没有找到则返回 null
     */
    @Override
    public Account findAccountByUsernameOrEmail(String text) {
        return this.query()
                .eq("username", text).or()
                .eq("email", text)
                .one();
    }

    /**
     * 注册或重置密码时，生成并发送电子邮件验证码
     *
     * @param type 操作类型，"register" 表示注册，"reset" 表示重置密码
     * @param email 要发送验证码的电子邮件地址
     * @param ip 发送验证码请求的 IP 地址，用于限制请求频率
     * @return 如果操作成功，返回 null；如果请求过于频繁，返回错误信息
     */
    @Override
    public String registerEmailVerifyCode(String type, String email, String ip) {
        synchronized (ip.intern()) {
            if (!this.verifyLimit(ip)) {
                return "请求频繁，请稍后再试";
            }
            Random random = new Random();
            int code = random.nextInt(899999) + 100000;
            Map<String, Object> data = Map.of("type", type, "email", email, "code", code);
            amqpTemplate.convertAndSend("XHSEmail", data);
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA + email, String.valueOf(code), 3, TimeUnit.MINUTES);
            return null;
        }
    }

    /**
     * 邮件验证码注册账号操作，需要检查验证码是否正确以及邮箱、用户名是否存在重名
     *
     * @param ro 注册基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String registerEmailAccount(EmailRegisterRO ro) {
        String email = ro.getEmail();
        String username = ro.getUsername();
        String phone = ro.getPhone();
        String key = Const.VERIFY_EMAIL_DATA + email;
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) return "请先获取验证码";
        if (!code.equals(ro.getCode())) return "验证码错误，请重新输入";
        if (this.existsAccountByEmail(email)) return "此电子邮件已被其他用户注册";
        if (this.existsAccountByUsername(username)) return "此用户名已被其他人注册，请更换一个新的用户名";
        String password = encoder.encode(ro.getPassword());
        Account account = new Account(null, username, password, 1, phone, email, "https://www.12339.gov.cn/preview/20250518_b5669d8531_jubao.jpg",
                1000.00, "这个人很懒，什么也没留下 ~", 0, 0, 0);
        if (this.save(account)) {
            if (accountRoleService.registerAccountRole(account.getId())) {
                stringRedisTemplate.delete(key);
                return null;
            } else {
                return "内部错误，请联系管理员";
            }
        } else {
            return "内部错误，请联系管理员";
        }
    }

    /**
     * 重置密码确认操作，验证验证码是否正确
     *
     * @param ro 验证基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String resetConfirm(ConfirmResetRO ro) {
        String email = ro.getEmail();
        String code = stringRedisTemplate.opsForValue().get(Const.VERIFY_EMAIL_DATA + email);
        if (code == null) return "请先获取验证码";
        if (!code.equals(ro.getCode())) return "验证码错误，请重新输入";
        return null;
    }

    /**
     * 邮件验证码重置密码操作，需要检查验证码是否正确
     *
     * @param ro 重置基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String resetEmailAccountPassword(EmailResetRO ro) {
        String verify = resetConfirm(new ConfirmResetRO(ro.getEmail(), ro.getCode()));
        if (verify != null) return verify;
        String email = ro.getEmail();
        String password = encoder.encode(ro.getPassword());
        boolean update = this.update().eq("email", email).set("password", password).update();
        if (update) {
            stringRedisTemplate.delete(Const.VERIFY_EMAIL_DATA + email);
        }
        return update ? null : "更新失败，请联系管理员";
    }

    /**
     * 根据用户ID获取用户账户信息
     *
     * @param uid 用户ID
     * @return 用户账户信息，如果用户ID为空或用户不存在，则返回null
     * @throws IllegalArgumentException 如果用户ID为空
     */
    @Override
    public AccountVO getAccountByUid(Long uid) {
        if (uid == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        Account account = baseMapper.selectById(uid);
        if (account == null) return null;

        return Proxy.account2VO(account);
    }

    /**
     * 充值操作，更新账户余额
     *
     * @param uid 用户ID
     * @param price 充值金额
     * @return 操作结果，true表示成功，false表示失败
     */
    @Override
    public Boolean pay(Long uid, Double price) {
        Account account = baseMapper.selectById(uid);
        if (account == null) return false;
        if (account.getBalance() < price) return false;
        Double newBalance = account.getBalance() - price;
        account.setBalance(newBalance);
        int result = baseMapper.updateById(account);
        return result > 0;
    }

    @Override
    public List<AccountVO> getRandomUnfollowedUsers(Long uid) {
        List<Follow> followed = followMapper.getFollows(uid); // 获取已关注用户ID列表
        List<Long> followedIds = followed.stream().map(Follow::getUid).toList();
        // 查询未关注的用户（排除自己和已关注用户）
        List<Account> candidates = accountMapper.selectRandomUnfollowedUsers(uid, followedIds);
        List<AccountVO> res = new ArrayList<>();
        for(Account account: candidates){
            res.add(Proxy.account2VO(account));
        }
        return res;
    }

    @Override
    public void addLike(Long id) {
        accountMapper.addLike(id);
    }

    @Override
    public void deleteLike(Long id) {
        accountMapper.deleteLike(id);
    }

    /**
     * 从数据库中通过用户名或邮箱查找用户详细信息
     *
     * @param username 用户名
     * @return 用户详细信息
     * @throws UsernameNotFoundException 如果用户未找到则抛出此异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByUsernameOrEmail(username);
        if (account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        Role role = roleService.findRoleByRID(accountRoleService.findRIDByUID(account.getId()).getRid());
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(role.getRolename())
                .build();
    }

    /**
     * 验证指定 IP 在一定时间内是否已经达到了发送邮件的次数限制
     *
     * @param ip 要验证的 IP 地址
     * @return 如果未达到限制，返回 true；如果已经达到限制，返回 false
     */
    private boolean verifyLimit(String ip) {
        String key = Const.VERIFY_EMAIL_LIMIT + ip;
        return flowUtils.limitOnceCheck(key, 60);
    }

    /**
     * 查询指定邮箱的用户是否已经存在
     *
     * @param email 邮箱
     * @return 是否存在
     */
    private boolean existsAccountByEmail(String email) {
        return this.baseMapper.exists(Wrappers.<Account>query().eq("email", email));
    }

    /**
     * 查询指定用户名的用户是否已经存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    private boolean existsAccountByUsername(String username) {
        return this.baseMapper.exists(Wrappers.<Account>query().eq("username", username));
    }
}
