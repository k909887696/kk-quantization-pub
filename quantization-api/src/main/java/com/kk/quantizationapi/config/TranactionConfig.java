package com.kk.quantizationapi.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;

@Aspect
@Configuration
public class TranactionConfig {
    //指定事务处理范围
    private static final String AOP_POINTCUT_EXCEPTION = "execution(* com.kk..service.*.*(..))";
    @Resource
    private TransactionManager transactionManager;
    @Bean
    public TransactionInterceptor txAdvice() {
        //设置事务的传播机制 行为 比如什么时候回滚 隔离级别
        DefaultTransactionAttribute transactionAttribute_REQUIRED = new DefaultTransactionAttribute();
        transactionAttribute_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionAttribute_REQUIRED.rollbackOn(new Throwable());//当出现异常就回滚

        DefaultTransactionAttribute transactionAttribute_NESTED = new DefaultTransactionAttribute();
        transactionAttribute_NESTED.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
        transactionAttribute_NESTED.rollbackOn(new Throwable());//当出现异常就回滚

        DefaultTransactionAttribute transactionAttribute_REQUIRES_NEW = new DefaultTransactionAttribute();
        transactionAttribute_REQUIRES_NEW.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionAttribute_REQUIRES_NEW.rollbackOn(new Throwable());//当出现异常就回滚
        //transactionAttribute.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);//上一次事务执行完才执行
        // 只读事务
        DefaultTransactionAttribute transactionAttribute_readonly = new DefaultTransactionAttribute();
        transactionAttribute_readonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        transactionAttribute_readonly.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        //通配方法

        source.addTransactionalMethod("search*",transactionAttribute_readonly);
        source.addTransactionalMethod("get*",transactionAttribute_readonly);
        source.addTransactionalMethod("query*",transactionAttribute_readonly);
        source.addTransactionalMethod("find*",transactionAttribute_readonly);
        source.addTransactionalMethod("list*",transactionAttribute_readonly);
        source.addTransactionalMethod("count*",transactionAttribute_readonly);
        source.addTransactionalMethod("netsed*",transactionAttribute_NESTED);
        source.addTransactionalMethod("stand*",transactionAttribute_REQUIRES_NEW);
        source.addTransactionalMethod("*",transactionAttribute_REQUIRED);
        return  new TransactionInterceptor(transactionManager,source);
    }
    //开启事务
    @Bean
    public Advisor txtAdviceAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXCEPTION);
        return new DefaultPointcutAdvisor(pointcut,txAdvice());
    }
}
