package com.wei.service.impl;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BatchExecutorUpdate {

    TransactionTemplate transactionTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void batchExecutorUpdate(List<DepartmentEmployees> list) {

        transactionTemplate.execute(status -> null);

        //加载MyBatis配置，获得SqlSessionFactory
        String url = "mybatis-config.xml";
        InputStream config = null;
        try {
            config = Resources.getResourceAsStream(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        //获取mapper对象
        SqlSession sqlSession = null;
        try {
            sqlSession = factory.openSession(ExecutorType.BATCH);
            DepartmentEmployeesMapper mapper = sqlSession.getMapper(DepartmentEmployeesMapper.class);
            //调用方法
            list.forEach(mapper::updateByPrimaryKey);
            // 处理查询结果
        } finally {
            assert sqlSession != null;
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
