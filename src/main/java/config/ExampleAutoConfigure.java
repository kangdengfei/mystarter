package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ExampleService;
import service.MyService;

/**
 * created  by KDF on 2018/6/13.
 */

@Configuration
@ConditionalOnClass(ExampleService.class)
@EnableConfigurationProperties(ExampleServiceProperties.class)
public class ExampleAutoConfigure {

    private final ExampleServiceProperties properties;

    @Autowired
    public ExampleAutoConfigure(ExampleServiceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(ExampleService.class)
    @ConditionalOnProperty(prefix = "example.service", value = "enabled",havingValue = "true")
    ExampleService exampleService (){
        return  new ExampleService(properties.getPrefix(),properties.getSuffix());
    }


    @Bean
    @ConditionalOnMissingBean(MyService.class)
//    @ConditionalOnProperty(prefix = "example.service", value = "enabled",havingValue = "true")
    MyService MyService (){
        return  new MyService();
    }

} 