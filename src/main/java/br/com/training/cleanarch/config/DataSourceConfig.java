package br.com.training.cleanarch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("dbJdbcTemplate")
    public NamedParameterJdbcTemplate dbJdbcTemplate(DataSource dbDataSource) {
        return new NamedParameterJdbcTemplate(dbDataSource);
    }
}
