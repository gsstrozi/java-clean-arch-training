package br.com.training.cleanarch.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "dbDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dbDataSource")
    @ConfigurationProperties("spring.datasource.configuration")
    public DataSource ff4jDataSource(@Qualifier("dbDataSourceProperties") DataSourceProperties dbDataSourceProperties) {

        DataSourceBuilder<HikariDataSource> dataSourceBuilder = dbDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class);
        return dataSourceBuilder
                .build();
    }

    @Primary
    @Bean("dbJdbcTemplate")
    public NamedParameterJdbcTemplate dbJdbcTemplate(@Qualifier("dbDataSource") DataSource dbDataSource) {
        return new NamedParameterJdbcTemplate(dbDataSource);
    }
}
