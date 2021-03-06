package com.hidiu.multids.annotation;

import java.lang.annotation.*;

/**
 * @author fancie
 * @title: TargetDataSource
 * @projectName multi-datasource
 * @description: TODO
 * @date 2022/2/7 上午11:38
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
