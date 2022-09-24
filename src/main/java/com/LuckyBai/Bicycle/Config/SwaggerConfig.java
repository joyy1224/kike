package com.LuckyBai.Bicycle.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//配置了Swagger的Docket的bean实例
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
          //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");
        //获取项目中的环境;通过environment.acceptsProfiles来判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("LuckyBai")
                .enable(flag)//enable时启动Swagger，如果是false。那么Swagger不能再浏览器中访问
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.LuckyBai.Bicycle.Controller"))
                .build();
    }
}
