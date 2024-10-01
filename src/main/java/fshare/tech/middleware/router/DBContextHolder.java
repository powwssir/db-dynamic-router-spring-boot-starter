package fshare.tech.middleware.router;



import fshare.tech.middleware.router.constants.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * 动态数据源上下文持有器
 */
public class DBContextHolder {
    /**
     * 存放数据源的key 例如 ds_1、ds_2、ds_default
     */
    private static final Set<String> DB_KEYS = new HashSet<>();

    private static final ThreadLocal<String> DBKEY_HOLDER = new ThreadLocal<>();

    /**
     * 在数据源配置时 加入已配置的数据源的key
     *
     * @param dbKeys
     */
    public static void init(Set<String> dbKeys) {
        DB_KEYS.addAll(dbKeys);
    }
    public static void setDBKey(String dbKey) {
        if (!DB_KEYS.contains(Constants.DBKEY_PREFIX + dbKey)) {
            throw new IllegalArgumentException("dbKey is not exist,please check!");
        }
        DBKEY_HOLDER.set(dbKey);
    }

    public static String getDBKey() {
        return DBKEY_HOLDER.get();
    }

    public static void clearDBKey() {
        DBKEY_HOLDER.remove();
    }
}