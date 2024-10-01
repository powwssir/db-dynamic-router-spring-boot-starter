原理:

DynamicDataSource 继承 AbstractRoutingDataSource 重写 determineCurrentLookupKey 方法
如果 determineCurrentLookupKey 方法返回值不为空，则使用 determineCurrentLookupKey 方法返回值作为数据源名称，否则使用默认数据源

以下配置中，默认数据源为 shardingSphereDataSource，其他数据源为 individualDataSources

数据源使用方式：
1、如果需要切换数据源
1、1 使用场景
    比如说 sharding Sphere Jdbc 失效 或者不方便使用
    例如 task 表分库 使用 user_id 作为分片键,但是执行定时任务扫描任务表时 sql语句查询条件不包含 user_id 并且 需要并行扫描提高效率时（需要指定数据源）
    如果使用 sharding Sphere Jdbc 会两个表一起执行 但是最终真正只扫描其中一个库
1、2 使用方式 (两种方式)：
    1）在方法上添加 @DBRouter 注解，指定数据源名称 示例： @DBRouter("1") 对应 ds_0 ; @DBRouter("default") 对应 ds_default
    2）使用 IDBRouterStrategy 默认为 DefaultDBRouterStrategy 在需要切换数据源的场景，使用 IDBRouterStrategy 切换数据源
    示例：
    @Resource
    IDBRouterStrategy dbRouterStrategy;
    dbRouterStrategy.setDBKey("1");
    userOrderDao.queryOrderByOrderId("1");
    dbRouterStrategy.clearDBKey();
    或者直接使用DBContextHolder（不推荐）
2、如果不切换数据源，则使用默认数据源 也就是 shardingSphereDataSource 这时 可以正常使用 sharding Sphere Jdbc 相关功能
