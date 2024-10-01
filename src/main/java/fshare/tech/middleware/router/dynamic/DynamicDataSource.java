package fshare.tech.middleware.router.dynamic;

import fshare.tech.middleware.router.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 动态数据源获取，每当切换数据源，都要从这个里面进行获取
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    //    @Override
//    protected Object determineCurrentLookupKey() {
//        return "db" + DBContextHolder.getDBKey();
//    }
    @Override
    protected Object determineCurrentLookupKey() {
        return "ds_" + DBContextHolder.getDBKey();
    }

}
