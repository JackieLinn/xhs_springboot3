package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.MultipartInputStreamFileResource;
import ynu.jackielinn.xhs_springboot3.utils.AliOSSUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth/upload")
public class UploadController {

    private static final String TOKEN_API = "https://picui.cn/api/v1/images/tokens";
    private static final String UPLOAD_API = "https://picui.cn/api/v1/upload";
    // 使用你提供的API Key
    private static final String API_KEY = "1214|KrNZbShUIrUhv2YJaNaGRrKhYxzzMJttx2XWalxd";

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/image")
    public RestBean<String> uploadImage(@RequestPart("file") MultipartFile file) {
        try {
            // 1. 获取上传 token
            Map<String, Object> tokenReq = Map.of("num", 1, "seconds",2626560);
            HttpHeaders tokenHeaders = new HttpHeaders();
            tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
            // 添加认证头 - 使用Bearer Token方式
            tokenHeaders.set("Authorization", "Bearer " + API_KEY);
            tokenHeaders.set("Accept", "application/json");
            HttpEntity<?> tokenEntity = new HttpEntity<>(tokenReq, tokenHeaders);

            ResponseEntity<Map> tokenResp = restTemplate.postForEntity(TOKEN_API, tokenEntity, Map.class);
            if (tokenResp.getStatusCode() != HttpStatus.OK || !(Boolean) tokenResp.getBody().get("status")) {
                log.error("获取token失败：状态码 = {}, 响应 = {}", tokenResp.getStatusCode(), tokenResp.getBody());
                return RestBean.failure(500, "获取上传 token 失败: " + tokenResp.getBody().get("message"));
            }

            Map tokenData = ((List<Map>) ((Map) tokenResp.getBody().get("data")).get("tokens")).get(0);
            String token = (String) tokenData.get("token");
            String expiredAt = (String) tokenData.get("expired_at");

            // 2. 上传图片到图床
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            formData.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            formData.add("token", token);
            formData.add("permission", "1");
            formData.add("expired_at", expiredAt);

            HttpHeaders uploadHeaders = new HttpHeaders();
            uploadHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            // 上传时也需要认证头
            uploadHeaders.set("Authorization", "Bearer " + API_KEY);
            uploadHeaders.set("Accept", "application/json");

            HttpEntity<MultiValueMap<String, Object>> uploadEntity = new HttpEntity<>(formData, uploadHeaders);
            ResponseEntity<Map> uploadResp = restTemplate.postForEntity(UPLOAD_API, uploadEntity, Map.class);

            if (uploadResp.getStatusCode() != HttpStatus.OK || !(Boolean) uploadResp.getBody().get("status")) {
                log.error("上传失败：响应内容 = {}", uploadResp.getBody());
                return RestBean.failure(500, "上传失败: " + uploadResp.getBody().get("message"));
            }

            // 根据API响应结构，图片URL在 data.links.url 路径下
            Map data = (Map) uploadResp.getBody().get("data");
            Map links = (Map) data.get("links");
            String imageUrl = (String) links.get("url");
            return RestBean.success(imageUrl);

        } catch (Exception e) {
            log.error("上传图片异常", e);
            return RestBean.failure(500, "服务器异常：" + e.getMessage());
        }
    }


    @Resource
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/video")
    public RestBean<String> upload(MultipartFile image) throws IOException {
        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        //将图片上传完成后的url返回，用于浏览器回显展示
        return RestBean.success(url);
    }
}

