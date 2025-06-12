package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.History;
import ynu.jackielinn.xhs_springboot3.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/auth/history")
public class HistoryController {
    @Resource
    private HistoryService historyService;
    @GetMapping("/{uid}")
    public RestBean<List<History>> getHistoryByUid(@PathVariable(name = "uid") Long uid) {
        List<History> historyList = historyService.getHistoryByUid(uid);
        return RestBean.success(historyList);
    }

    @PostMapping("/upload")
    public RestBean<String> uploadHistory(@RequestBody History history) {
        boolean result = historyService.save(history);
        return result ? RestBean.success("上传成功") : RestBean.failure(500, "上传失败");
    }
}
