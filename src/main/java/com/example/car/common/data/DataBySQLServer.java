/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DataBySQLServer
 * Author:   冷酷的苹果
 * Date:     2020/6/19 19:13
 * Description: 核心配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈核心配置〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/19
 * @since 1.0.0
 */
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.car.mapper.sqlserver"},sqlSessionFactoryRef = "otherSqlSessionFactory")
//这里一定要注意，这个basePackages是你的mapper接口及service所在的包名，而下面的红线所标注的classpath是mapper.xml所在的位置，这个xml是配置文件，处在resources里，他的路径也要格外区分开。

public class DataBySQLServer {
    @Bean(name="otherDataSource")
    //下面的注解作用就是从application.properties中读取以这个字符串开头的那些配置，设置为数据源的配置
    @ConfigurationProperties(prefix ="spring.datasource.other")
    public DataSource testDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name="otherSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("otherDataSource") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/sqlserver/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "otherTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("otherDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name="otherSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("otherSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
