package com.github.binarywang.demo.wx.cp.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wz
 * @since 2024-08-10
 */
@Getter
@Setter
@TableName("person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    //("人员id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //("微信Id")
    @TableField("wxId")
    private String wxId;

    //("人员姓名")
    @TableField("name")
    private String name;

    //("人员编码")
    @TableField("code")
    private String code;

    //("证件类型")
    @TableField("paper_type")
    private Integer paperType;

    //("证件号码")
    @TableField("paper_number")
    private String paperNumber;

    //("证件地址")
    @TableField("paper_address")
    private String paperAddress;

    //("部门ID")
    @TableField("department_id")
    private Long departmentId;

    //("人员类型, 默认0")
    @TableField("person_type")
    private Integer personType;

    //("有效刷卡次数 仅来宾用户有效")
    @TableField("available_times")
    private Integer availableTimes;

    //("电话")
    @TableField("phone")
    private String phone;

    //("电子邮箱")
    @TableField("email")
    private String email;

    //("年龄")
    @TableField("age")
    private Integer age;

    //("生日")
    @TableField("birthday")
    private Date birthday;

    //("性别")
    @TableField("sex")
    private String sex;

    //("国家")
    @TableField("country")
    private String country;

    //("民族")
    @TableField("nation")
    private Integer nation;

    //("民族名称")
    @TableField("nation_name")
    private String nationName;

    //("人员自编号")
    @TableField("self_code")
    private String selfCode;

    //("组织编码")
    @TableField("owner_code")
    private String ownerCode;

    //("权限有效期开始日期")
    @TableField("valid_start_time")
    private Date validStartTime;

    //("权限有效期结束日期")
    @TableField("valid_end_time")
    private Date validEndTime;

    //("人员密码密文")
    @TableField("password")
    private String password;

    //("私钥key值")
    @TableField("password_key")
    private String passwordKey;

    //("业务类型")
    @TableField("business_type")
    private String businessType;
}
