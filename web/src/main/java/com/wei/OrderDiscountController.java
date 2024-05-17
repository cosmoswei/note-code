package com.wei;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import com.wei.drools.OrderDiscount;
import com.wei.drools.OrderDiscountService;
import com.wei.drools.OrderRequest;
import com.wei.entity.Rule;
import com.wei.mapper.RuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class OrderDiscountController {

    @Resource
    private RuleMapper ruleMapper;
    @Resource
    private OrderDiscountService orderDiscountService;

    @PostMapping("/getDiscount/v1")
    public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
        OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @PostMapping("/getDiscount/v2")
    public ResponseEntity<OrderDiscount> getDiscount2(@RequestBody OrderRequest orderRequest) {
        OrderDiscount discount = orderDiscountService.getDiscount2(orderRequest);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(HttpServletRequest request) {
        // 规则名称
        String name = request.getParameter("name");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 规则文件
        MultipartFile multipartFile = multipartRequest.getFile("file");
        Rule rule = new Rule();
        SeataIdWorker seataIdWorker = new SeataIdWorker((long) WorkIdGenerator.getHostWorkId());
        rule.setId(String.valueOf(seataIdWorker.nextId()));
        rule.setName(name);
        rule.setContent(getContent(multipartFile));
        rule.setCreateBy("huangxuwei");
        rule.setUpdateBy("huangxuwei");
        ruleMapper.delete(name);
        ruleMapper.save(rule);
        return Dict.create().set("msg", "添加成功。");
    }

    private String getContent(MultipartFile multipartFile) {
        String content = null;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            content = IoUtil.read(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("读取文件内容失败，异常信息：", e);
        }
        return content;
    }
}
