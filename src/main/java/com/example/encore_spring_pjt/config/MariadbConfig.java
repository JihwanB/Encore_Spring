package com.example.encore_spring_pjt.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
// import org.apache.ibatis.session.Configuration;

import javax.sql.DataSource;

/*
@Component : 객체생성과 라이프사이클 관리를 Spring Container 에게 위임하겠다.
Inversion of Control(IOC) : 제어의 역행
-- IOC (Dependency Injection (DI) , Dependency Lookup(DL) )
-- @Controller
-- @Service
-- @Repository
-- @Bean

환경설정에 관련된 객체는
-- @Configuration

Dependency Injection 관련 어노테이션으로
spring container (ApplicationContext) 관리하는 객체를 가져다 쓸 수 있음
-- Autowired
-- Inject
-- Resource
-- Qualified
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class MariadbConfig {

    // DI 구현
    @Autowired
    private ApplicationContext context;

    // hikariConfig
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    // hikari CP를 만듬
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    // CP를 관리하는 팩토리
    // 여기에서는 Mybatis 와 연결될 수 있도록
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        // Mapper 관련 추가 작업이 필요
        factory.setMapperLocations(context.getResources("classpath:/mapper/**/*Mapper.xml"));
        factory.setConfiguration(myConfiguration());
        return factory.getObject();
    }

    // 이름을 명시적으로 지정할 수 있음
    // 명시적으로 안했을 시, sqlSessionTemplate 으로 첫 글자는 소문자로 됨
    @Bean("encore")
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration myConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

}
