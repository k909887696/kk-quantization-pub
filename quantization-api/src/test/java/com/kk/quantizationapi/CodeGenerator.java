package com.kk.quantizationapi;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import com.baomidou.mybatisplus.generator.config.*;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {

    @Test
    public void autoMgb35Plus()
    {
        List<String> tables = new ArrayList<>();
        tables.add("daily");
//        tables.add("p_question");
//        tables.add("p_answer");
//        tables.add("p_correct");

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/quantization?serverTimezone=GMT","root","-random8201227")
                .globalConfig(builder -> {
                    builder.author("kk")               //作者
                            .outputDir(System.getProperty("user.dir")+"\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("com.kk")
                            .moduleName("quanzationapi")
                            .entity("dao.entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("dao.mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            //.addTablePrefix("p_")
                            .serviceBuilder()
                            //.formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            //.enableLombok()
                            //.logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    /*@Test
    public void run() {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");  // 项目路径
        gc.setOutputDir(projectPath + "/src/main/java"); // 生成的文件路径
        gc.setAuthor("kk");
        gc.setOpen(false); // 是否打开生成的目录
        gc.setFileOverride(false); // 是否覆盖已有文件, 默认false
        //gc.setServiceName("%sService"); // 自动生成的Service类前面会自动加前缀I, 取消I前缀
        //gc.setIdType(IdType.AUTO); // 生成主键得id类型
        gc.setDateType(DateType.ONLY_DATE); // 数据库中的时间类型对应的java类, 此设置表示Date类, 默认是java8的时间类

         gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/quantization?serverTimezone=GMT");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("-random8201227");
        dsc.setDbType(DbType.MYSQL); // 设置数据库连接的类型
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null); // 模块路径(子路径)
        pc.setParent("com.kk.quanzationapi"); // 生存的代码的父路径
        pc.setEntity("entity"); // 生存实体类所在的包名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("daily"); // 要生成的表在数据库中的名称, 每张表名以英文逗号隔开
        strategy.setNaming(NamingStrategy.underline_to_camel); // 表名转换方式: 数据库中的下划线转成java驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 列名转换方式
       // strategy.setEntityLombokModel(true); // 自动加上lombok注解
        strategy.setRestControllerStyle(true); // 加上@RestController注解
        strategy.setEntityTableFieldAnnotationEnable(true); // 为实体类的类上加@TableName, 所有字段上加注解
        strategy.setControllerMappingHyphenStyle(false); // RequestMapping种的驼峰是否转成用"-"连接, 默认是false
       // strategy.setTablePrefix("t_"); // 按照表名生成实体类时去掉表名前面的"t_"前缀
        // strategy.setLogicDeleteFieldName("deleted"); // 数据库中表示逻辑删除的字段名
        // strategy.setVersionFieldName("version"); // 数据库中表示乐观锁版本号的字段名

        // 自动填充配置: 插入时间, 最后一次更新时间
        ArrayList<TableFill> tableFills = new ArrayList<>();
        TableFill gmtCreate = new TableFill("created", FieldFill.INSERT); // 插入时改变的时间纪录, created为表的字段名
        TableFill gmtModified = new TableFill("updated", FieldFill.INSERT_UPDATE); // 最后一次更新时updated表字段的时间记录
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);


        // 自定义配置, 将Mapper.xml文件生成到resources目录下
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        mpg.execute();

    }*/

}
