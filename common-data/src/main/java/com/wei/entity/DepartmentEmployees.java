package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName department_employees
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("department_employees")
public class DepartmentEmployees extends Model<DepartmentEmployees>{
    /**
     * 员工唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工所属部门唯一标识符
     */
    @TableField(value = "departmentId")
    private Integer departmentId;

    /**
     * 员工姓名
     */
    @TableField(value = "employeeName")
    private String employeeName;

    /**
     * 员工职位
     */
    @TableField(value = "employeeTitle")
    private String employeeTitle;

    /**
     * 员工薪水
     */
    @TableField(value = "employeeSalary")
    private BigDecimal employeeSalary;

    /**
     * 员工年龄
     */
    @TableField(value = "employeeAge")
    private Integer employeeAge;

    /**
     * 员工性别
     */
    @TableField(value = "employeeGender")
    private String employeeGender;

    /**
     * 员工地址
     */
    @TableField(value = "employeeAddress")
    private String employeeAddress;

    /**
     * 员工电话号码
     */
    @TableField(value = "employeePhone")
    private String employeePhone;

    /**
     * 员工电子邮件地址
     */
    @TableField(value = "employeeEmail")
    private String employeeEmail;

    /**
     * 员工入职日期
     */
    @TableField(value = "employeeStartDate")
    private Date employeeStartDate;

    /**
     * 员工离职日期
     */
    @TableField(value = "employeeEndDate")
    private Date employeeEndDate;

    /**
     * 员工状态（在职/离职）
     */
    @TableField(value = "employeeStatus")
    private String employeeStatus;

    /**
     * 员工绩效评估
     */
    @TableField(value = "employeePerformance")
    private String employeePerformance;

    /**
     * 员工备注信息
     */
    @TableField(value = "employeeNotes")
    private String employeeNotes;

    private static final long serialVersionUID = 1L;

    @Override
    public Serializable pkVal() {
        return id;
    }

}