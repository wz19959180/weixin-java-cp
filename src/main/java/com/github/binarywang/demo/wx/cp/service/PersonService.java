package com.github.binarywang.demo.wx.cp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.demo.wx.cp.model.Person;

import java.util.List;

/**
* <p>
 *  服务类
 * </p>
*
* @author wz
* @since 2024-08-10
*/
public interface PersonService extends IService<Person> {

/**
* 分页列表
* @param param 根据需要进行传值
* @return

IPage<Person> page(Person param);
*/

/**
* 详情
* @param id
* @return
*/
Person info(Long id);

/**
* 新增
* @param param 根据需要进行传值
* @return
*/
void add(Person param);

/**
* 修改
* @param param 根据需要进行传值
* @return
*/
void modify(Person param);

/**
* 删除(单个条目)
* @param id
* @return
*/
void remove(Long id);

/**
* 删除(多个条目)
* @param ids
* @return
*/
void removes(List<Long> ids);
 }
