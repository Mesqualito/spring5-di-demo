package com.eigenbaumarkt.configuration;

import com.eigenbaumarkt.source.FakeJmsBroker;
import com.eigenbaumarkt.source.SuckSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
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

    @Value("${jms.mssql.url}")
    String jmsUrl;

    @Value("${jms.mssql.port}")
    String jmsPort;

    @Value("${jms.mssql.user}")
    String jmsUser;

    @Value("${jms.mssql.password}")
    String jmsPassword;


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
    public FakeJmsBroker fakeJmsBroker(){
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();

        fakeJmsBroker.setDatasource(jmsUrl);
        fakeJmsBroker.setPort(jmsPort);
        fakeJmsBroker.setUser(jmsUser);
        fakeJmsBroker.setPassword(jmsPassword);

        return fakeJmsBroker;

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {

        // enables to wire up by @Value
        // PrSPC is reading the file given by @PropertySource("classpath:file")
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;

    }



}
