package com.eigenbaumarkt;

import com.eigenbaumarkt.source.SuckSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    private static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(DiDemoApplication.class, args);
        displayAllBeans();

        SuckSource suckSource = (SuckSource) ctx.getBean(SuckSource.class);
        System.out.println(suckSource.getUser() + "@" + suckSource.getDatasource() + ":" + suckSource.getPort());

    }

    public static void displayAllBeans() {
        String[] allBeanNames = ctx.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }


}
