package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.dto.response.BlogAccountVO;
import ynu.jackielinn.xhs_springboot3.entity.po.Account;
import ynu.jackielinn.xhs_springboot3.entity.po.History;
import ynu.jackielinn.xhs_springboot3.mapper.HistoryMapper;
import ynu.jackielinn.xhs_springboot3.service.AccountService;
import ynu.jackielinn.xhs_springboot3.service.BlogService;
import ynu.jackielinn.xhs_springboot3.service.HistoryService;

import java.util.List;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private BlogService blogService;
    @Override
    public List<History> getHistoryByUid(Long uid) {
        List<History> historyList = historyMapper.getHistoryByUid(uid);
        for(History history : historyList){
            history.setBlog(blogService.getBlogById(history.getBlogId(),uid));
        }
        return historyList;
    }
}
