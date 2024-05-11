package com.wei.drools;

import lombok.Data;@Data
public class ExamData {
  /**
   * BMI的测量值
   */
  private Double bmi;
  /**
   * 规则名称
   */
  private String ruleName;
  /**
   * 结论
   */
  private String conclusion;
}
