package com.wei.controller;

import com.wei.drools.ActivityService;
import com.wei.mapper.RuleMapper;
import com.wei.uniform.entity.Activity;
import com.wei.uniform.entity.ActivityResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ActivityController {

    @Resource
    private RuleMapper ruleMapper;
    @Resource
    private ActivityService activityService;

    @PostMapping("/getActivity")
    public ResponseEntity<ActivityResult> getActivity(@RequestBody Activity activity) {
        ActivityResult activityResult = null;
        try {
            activityResult = activityService.getActivity(activity);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(activityResult, HttpStatus.NOT_EXTENDED);
        }
        return new ResponseEntity<>(activityResult, HttpStatus.OK);
    }

}
