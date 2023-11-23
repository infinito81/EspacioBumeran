package com.espacio.bumeran.aula.config;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.espacio.bumeran.aula.mapper.CoursesMapper;

@Configuration
@EnableTransactionManagement
public class DataSourceBean {
	
	public static final String POSTGRE_SESSION_FACTORY = "postgreSessionFactory";
	public static final String POSTGRE_DATASOURCE = "postgreDatasource";
	public static final String POSTGRE_TX_MANAGER = "postgreTransactionManager";

	@Autowired
	private Environment env;
	
    @Bean(name = POSTGRE_DATASOURCE, destroyMethod = "")
    @Primary
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
        System.out.println("datasource " + dataSourceBuilder.toString());
        return dataSourceBuilder.build();
    }
	
    /*@Bean(name = POSTGRE_DATASOURCE, destroyMethod = "")
    @ConfigurationProperties( prefix="spring.datasource")    
    //@Primary
    public DataSource postgreDataSource() {
    	//log.debug("db2datasource() main en Db2Config");
        return DataSourceBuilder.create().build();
    }*/	
    
    @Bean(name = POSTGRE_TX_MANAGER)
    @Primary
    public PlatformTransactionManager postgreTxManager() {
        return new DataSourceTransactionManager(getDataSource());
    }    
    
    @Primary
    @Bean(name = POSTGRE_SESSION_FACTORY, destroyMethod = "")
    public SqlSessionFactoryBean postgreSqlSessionFactory(@Qualifier(POSTGRE_DATASOURCE) final DataSource dataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        
         
		//sqlSessionFactoryBean.setDataSource(oneDataSource);
		SqlSessionFactory sqlSessionFactory;
		sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CoursesMapper.class);

		
		System.out.println("SQLSession configured");
       
        return sqlSessionFactoryBean;
    }
       
    
    
    
    
	@Bean
	public MapperFactoryBean<CoursesMapper> transactionsMapper(@Named(POSTGRE_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
		MapperFactoryBean<CoursesMapper> factoryBean = new MapperFactoryBean<>(CoursesMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		return factoryBean;
	}
}