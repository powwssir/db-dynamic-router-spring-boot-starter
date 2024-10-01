package fshare.tech.middleware.router.strategy;

/**
 * @author: XiaoYe
 * @create: 2024-10-01 18:20
 * @description: 路由策略
 */
public interface IDBRouterStrategy {

    /**
     * 手动方式设置分库路由
     *
     * @param dbKey
     */
    void setDBKey(String dbKey);

    /**
     * 清除分库路由
     */
    void clearDBKey();

}
