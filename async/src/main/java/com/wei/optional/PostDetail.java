package com.wei.optional;

import lombok.Data;

import java.util.Date;
import java.util.Optional;

/**
 * 帖子详情
 *
 * @author 架构悟道
 * @since 2022/7/14
 */
@Data
public class PostDetail {
    private String title;
    private User postUser;
    private String content;
    private Optional<Date> lastModifyTime;
    private Optional<Attachment> attachment;
}
