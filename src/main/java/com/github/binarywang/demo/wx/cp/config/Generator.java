package com.github.binarywang.demo.wx.cp.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class Generator {
    @Test
    public void run() {
        FastAutoGenerator.create("jdbc:mysql://27.25.158.123:3306/wxicc?useSSL=FALSE&serverTimezone=UTC", "wxicc", "nbcb,111")
                .globalConfig(builder -> {
                    builder.author("wz") // 设置作者 baomidou 默认值:作者
                            .enableSwagger() // 开启 swagger 模式 默认值:false
//                            .fileOverride() // 覆盖已生成文件 默认值:false
                            .disableOpenDir()//禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd")// 注释日期
                            .dateType(DateType.ONLY_DATE)//定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录 /opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
                    //System.getProperty("user.dir")为你当前模块的绝对路径
                })
                .packageConfig(builder -> {
                    builder.parent("com.github.binarywang.demo.wx.cp") // 父包模块名 默认值:com.baomidou
                            .controller("controller")//Controller 包名 默认值:controller
                            .entity("model")//Entity 包名 默认值:entity
                            .service("service")//Service 包名 默认值:service
                            .serviceImpl("service.impl")
                            .mapper("mapper")//Mapper 包名 默认值:mapper
//                            .moduleName("educms") // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+ "/src/main/resources/mapper")); // 设置mapperXml生成路径
                    //默认存放在mapper的xml下
                })

                .strategyConfig(builder -> {
                    builder.addInclude("person") // 设置需要生成的表名 可边长参数“user”, “user1”
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .serviceBuilder().enableFileOverride()//service策略配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder().enableFileOverride()
                            .controllerBuilder().enableFileOverride()
                            .entityBuilder().enableFileOverride()
                            .entityBuilder()// 实体类策略配置
                            .idType(IdType.ASSIGN_ID)//主键策略  雪花算法自动生成的id
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT)) // 自动填充配置
                            .addTableFills(new Property("gmtModified", FieldFill.INSERT_UPDATE))
                            .enableLombok() //开启lombok
                            .logicDeleteColumnName("deleted")// 说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()// 属性加上注解说明
                            .controllerBuilder() //controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle() // 开启RestController注解
                            .mapperBuilder()// mapper策略配置
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//@mapper注解开启
                            .formatXmlFileName("%sMapper");
                })
                // 模板配置
                .templateConfig(
                        builder -> {
                            builder.controller("/template/controller.java" )
                                    .entity("/template/entity.java")
                                    .service("/template/service.java")
                                    .mapper("/template/mapper.java")
                                    .xml("/template/mapper.xml")
                                    .serviceImpl("/template/serviceImpl.java");
                        }
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
    @Test
    public void test2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 获取当前日期和时间
        Date now = new Date();

        // 将日期格式化为字符串
        String formattedDate = sdf.format(now);
        System.out.println(formattedDate);
    }
}
