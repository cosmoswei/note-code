package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @TableName department_employees_simple
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("department_employees_simple")
@Entity(name = "department_employees_simple")
@Getter
@Setter
public class DepartmentEmployeesSimple extends Model<DepartmentEmployeesSimple> {

    /**
     * 员工唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Long id;

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

    @Override
    public Serializable pkVal() {
        return id;
    }

}