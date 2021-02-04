/*
package it.restclienttemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${datasource.connection}")
    private String datasourceConnection;

    @Value("${datasource.user}")
    private String dsUser;

    @Value("${datasource.pass}")
    private String dsPass;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url( datasourceConnection + "?useLegacyDatetimeCode=false&serverTimezone=Europe/Rome&useSSL=false");
        dataSourceBuilder.username(dsUser);
        dataSourceBuilder.password(dsPass);
        return dataSourceBuilder.build();
    }
}
*/
