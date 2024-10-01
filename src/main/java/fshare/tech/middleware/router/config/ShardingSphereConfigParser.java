package fshare.tech.middleware.router.config;

import org.apache.shardingsphere.driver.api.yaml.YamlJDBCConfiguration;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.driver.ShardingSphereURLManager;
import org.apache.shardingsphere.infra.util.yaml.YamlEngine;
import org.apache.shardingsphere.infra.yaml.config.swapper.resource.YamlDataSourceConfigurationSwapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author: XiaoYe
 * @create: 2024-10-01 16:04
 * @description: TODO
 */
@Component
public class ShardingSphereConfigParser {

    @Value("${spring.datasource.url}")
    private String url;

    private final String urlPrefix = "jdbc:shardingsphere:";

    private static final YamlDataSourceConfigurationSwapper DATA_SOURCE_SWAPPER = new YamlDataSourceConfigurationSwapper();


    /**
     * 获取ShardingSphere数据源配置
     */
    public DataSource parseShardingSphereDataSource() throws SQLException, IOException {
        // 该方法参考至 ShardingSpher 中 的 DriverDataSourceCache # createDataSource
        return YamlShardingSphereDataSourceFactory.createDataSource(ShardingSphereURLManager.getContent(url, urlPrefix));
    }

    /**
     * 解析ShardingSphere数据源配置文件中的所有数据源 用于 动态数据源切换
     */
    public Map<String, DataSource> parseIndividualDataSources() throws IOException {
        byte[] yamlBytes = ShardingSphereURLManager.getContent(url, urlPrefix);
        YamlJDBCConfiguration rootConfig = YamlEngine.unmarshal(yamlBytes, YamlJDBCConfiguration.class);
        // 示例
        // ds_default -> {HikariDataSource@2207} "HikariDataSource (null)"
        // ds_1 -> {HikariDataSource@2209} "HikariDataSource (null)"
        // ds_0 -> {HikariDataSource@2211} "HikariDataSource (null)"
        Map<String, DataSource> dataSources = DATA_SOURCE_SWAPPER.swapToDataSources(rootConfig.getDataSources());
        return dataSources;
    }
}