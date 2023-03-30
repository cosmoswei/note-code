package com.wei.exclude;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcludeSpringMng {

    public static void main(String[] args) throws IOException {
        //加载MyBatis配置，获得SqlSessionFactory
        String url = "mybatis-config.xml";
        InputStream config = Resources.getResourceAsStream(url);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        //获取mapper对象
        SqlSession sqlSession = null;
        try {
            sqlSession = factory.openSession(ExecutorType.BATCH);
            DepartmentEmployeesMapper mapper = sqlSession.getMapper(DepartmentEmployeesMapper.class);
            //调用方法
            List<Long> ids = Stream.of(1L, 2L, 4L, 5L, 6L).collect(Collectors.toList());
            List<DepartmentEmployees> entities = mapper.selectByIds(ids);
            // 处理查询结果
            for (DepartmentEmployees entity : entities) {
                System.out.println(entity);
            }
        } finally {
            assert sqlSession != null;
            sqlSession.close();
        }
    }

    public static void test1(String[] args) {

        // 创建数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydatabase");
        dataSource.setUsername("myuser");
        dataSource.setPassword("mypassword");

        // 创建事务管理器
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // 创建Environment对象
        Environment environment = new Environment.Builder("development")
                .dataSource(dataSource)
                .transactionFactory(transactionFactory)
                .build();

        // 创建MyBatis配置对象
        Configuration configuration = new Configuration();
        configuration.setEnvironment(environment);
        // 配置MyBatis，例如添加Mapper类
        configuration.addMapper(DepartmentEmployeesMapper.class);

        // 创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 获取SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取Mapper接口实例
//            DepartmentEmployeesMapper mapper = sqlSession.getMapper(DepartmentEmployeesMapper.class);
//            // 调用Mapper方法执行SQL语句
//            List<Long> ids = Stream.of(1L, 2L, 4L, 5L, 6L).collect(Collectors.toList());
//            List<DepartmentEmployees> entities = mapper.selectByIds(ids);
//            // 处理查询结果
//            for (DepartmentEmployees entity : entities) {
//                System.out.println(entity);
//            }
        }
    }

}
