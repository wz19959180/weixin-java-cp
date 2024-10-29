package com.github.binarywang.demo.wx.cp.service.impl;

import com.github.binarywang.demo.wx.cp.model.Person;
import com.github.binarywang.demo.wx.cp.mapper.PersonMapper;
import com.github.binarywang.demo.wx.cp.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.自己的工具包.RetJson;
//import com.自己的工具包.RetCode;
//import com.自己的工具包.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author wz
* @since 2024-08-10
*/
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

//@Autowired
//private PageUtils pageUtils;

/**
* 分页列表
* @param param 根据需要进行传值
* @return

@Override
public IPage<Person> page(Person param) {

QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
queryWrapper.lambda()
 // 人员id
   .eq(param.getId() != null, Person::getId, param.getId())
 // 微信Id
   .eq(!StringUtils.isEmpty(param.getWxId()), Person::getWxId, param.getWxId())
 // 人员姓名
   .eq(!StringUtils.isEmpty(param.getName()), Person::getName, param.getName())
 // 人员编码
   .eq(!StringUtils.isEmpty(param.getCode()), Person::getCode, param.getCode())
 // 证件类型
   .eq(param.getPaperType() != null, Person::getPaperType, param.getPaperType())
 // 证件号码
   .eq(!StringUtils.isEmpty(param.getPaperNumber()), Person::getPaperNumber, param.getPaperNumber())
 // 证件地址
   .eq(!StringUtils.isEmpty(param.getPaperAddress()), Person::getPaperAddress, param.getPaperAddress())
 // 部门ID
   .eq(param.getDepartmentId() != null, Person::getDepartmentId, param.getDepartmentId())
 // 人员类型, 默认0
   .eq(param.getPersonType() != null, Person::getPersonType, param.getPersonType())
 // 有效刷卡次数 仅来宾用户有效
   .eq(param.getAvailableTimes() != null, Person::getAvailableTimes, param.getAvailableTimes())
 // 电话
   .eq(!StringUtils.isEmpty(param.getPhone()), Person::getPhone, param.getPhone())
 // 电子邮箱
   .eq(!StringUtils.isEmpty(param.getEmail()), Person::getEmail, param.getEmail())
 // 年龄
   .eq(param.getAge() != null, Person::getAge, param.getAge())
 // 生日
   .eq(param.getBirthday() != null, Person::getBirthday, param.getBirthday())
 // 性别
   .eq(!StringUtils.isEmpty(param.getSex()), Person::getSex, param.getSex())
 // 国家
   .eq(!StringUtils.isEmpty(param.getCountry()), Person::getCountry, param.getCountry())
 // 民族
   .eq(param.getNation() != null, Person::getNation, param.getNation())
 // 民族名称
   .eq(!StringUtils.isEmpty(param.getNationName()), Person::getNationName, param.getNationName())
 // 人员自编号
   .eq(!StringUtils.isEmpty(param.getSelfCode()), Person::getSelfCode, param.getSelfCode())
 // 组织编码
   .eq(!StringUtils.isEmpty(param.getOwnerCode()), Person::getOwnerCode, param.getOwnerCode())
 // 权限有效期开始日期
   .eq(param.getValidStartTime() != null, Person::getValidStartTime, param.getValidStartTime())
 // 权限有效期结束日期
   .eq(param.getValidEndTime() != null, Person::getValidEndTime, param.getValidEndTime())
 // 人员密码密文
   .eq(!StringUtils.isEmpty(param.getPassword()), Person::getPassword, param.getPassword())
 // 私钥key值
   .eq(!StringUtils.isEmpty(param.getPasswordKey()), Person::getPasswordKey, param.getPasswordKey())
 // 业务类型
   .eq(!StringUtils.isEmpty(param.getBusinessType()), Person::getBusinessType, param.getBusinessType())
;

IPage<Person> page = page(pageUtils.page(), queryWrapper);

return page;
}
*/

/**
* 详情
* @param id
* @return
*/
@Override
public Person info(Long id) {

return getById(id);
}

/**
* 新增
* @param param 根据需要进行传值
* @return
*/
@Override
public void add(Person param) {

save(param);
}

/**
* 修改
* @param param 根据需要进行传值
* @return
*/
@Override
public void modify(Person param) {

updateById(param);
}

/**
* 删除(单个条目)
* @param id
* @return
*/
@Override
public void remove(Long id) {
removeById(id);
}

/**
* 删除(多个条目)
* @param ids
* @return
*/
@Override
public void removes(List<Long> ids) {

 removeByIds(ids);
 }
 }
