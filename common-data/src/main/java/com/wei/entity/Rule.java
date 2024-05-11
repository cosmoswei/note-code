package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_rule")
public class Rule implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * ID
   */
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  /**
   * 规则名称
   */
  private String name;
  /**
   * 规则内容
   */
  private String content;
  /**
   * 创建人
   */
  private String createBy;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 更新人员
   */
  private String updateBy;
  /**
   * 更新时间
   */
  private Date updateTime;
}
