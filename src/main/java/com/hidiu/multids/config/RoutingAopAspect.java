package com.hidiu.multids.config;

import com.hidiu.multids.annotation.TargetDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fancie
 * @title: RoutingAopAspect
 * @projectName multi-datasource
 * @description: TODO
 * @date 2022/2/7 上午11:40
 */
@Order(1)
@Aspect
@Component
public class RoutingAopAspect {

    @Around("@annotation(targetDataSource)")
    public Object routingWithDataSource(ProceedingJoinPoint joinPoint, TargetDataSource targetDataSource) throws Throwable {
        if (targetDataSource == null) {
            RoutingDataSource.setDataSource(DataSourceNames.master);
        } else {
            RoutingDataSource.setDataSource(targetDataSource.value());
        }
        try {
            return joinPoint.proceed();
        } finally {
            RoutingDataSource.clearDataSource();
        }
    }

}
