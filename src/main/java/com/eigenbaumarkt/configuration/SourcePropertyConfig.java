package com.eigenbaumarkt.configuration;

import com.eigenbaumarkt.source.SuckSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:datasource.properties")
public class SourcePropertyConfig {

    @Autowired
    Environment env;

    // Parameter @Value: Spring Expression Language (SpEL)
    @Value("${mssql.url}")
    String url;

    @Value("${mssql.port}")
    String port;

    @Value("${mssql.user}")
    String user;

    @Value("${mssql.password}")
    String password;

    // get values from externalized properties into spring framework
    @Bean
    public SuckSource suckSource(){
        SuckSource suckSource = new SuckSource();

            suckSource.setDatasource(url);
            suckSource.setPort(port);
            suckSource.setUser(user);
            suckSource.setPassword( env.getProperty("PASSWORD") != null ? env.getProperty("PASSWORD") : password );

            return suckSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {

        // enables to wire up by @Value
        // PrSPC is reading the file given by @PropertySource("classpath:file")
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;

    }



}
