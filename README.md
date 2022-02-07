# Springboot+JPA实现读写分离

方案
----
利用AbstractRoutingDataSource动态切换到主库或者从库

原理
-------
1.添加注解
```
@TargetDataSource(value = DataSourceNames.slave)
```
2.切换相应的数据（此时只配置了一个名称）

以下代码就做一件事情：给RoutingDataSource的CONTEXT_HOLDER赋值。
```
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
    
```
3.选择数据库（通过第二步的名称）

AbstractRoutingDataSource会根据RoutingDataSource的CONTEXT_HOLDER赋值选择数据库
```
    这里是AbstractRoutingDataSource的代码，RoutingDataSource重写了this.determineCurrentLookupKey()方法。
    protected DataSource determineTargetDataSource() {
        Assert.notNull(this.resolvedDataSources, "DataSource router not initialized");
        Object lookupKey = this.determineCurrentLookupKey();
        DataSource dataSource = (DataSource)this.resolvedDataSources.get(lookupKey);
        if (dataSource == null && (this.lenientFallback || lookupKey == null)) {
            dataSource = this.resolvedDefaultDataSource;
        }

        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        } else {
            return dataSource;
        }
    }
```
JPA在执行sql之前都会调用determineTargetDataSource方法，这就实现的动态的切换数据库。

----------
fancie 杭州
