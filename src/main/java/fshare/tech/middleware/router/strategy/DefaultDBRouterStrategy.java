package fshare.tech.middleware.router.strategy;

import fshare.tech.middleware.router.DBContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: XiaoYe
 * @create: 2024-10-01 18:20
 * @description: 默认路由 直接使用数据源
 */
public class DefaultDBRouterStrategy implements IDBRouterStrategy {

    private Logger logger = LoggerFactory.getLogger(DefaultDBRouterStrategy.class);

    @Override
    public void setDBKey(String dbKey) {
        if (dbKey == null) {
            throw new IllegalArgumentException("dbKey is not be null");
        }
        DBContextHolder.setDBKey(dbKey);
    }

    @Override
    public void clearDBKey() {
        DBContextHolder.clearDBKey();
    }
}
