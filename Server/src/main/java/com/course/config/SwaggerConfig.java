package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //表示这是一个配置类,通过@Configuration注解，让Spring来加载该类配置
@EnableSwagger2 //@EnableSwagger2注解来启用Swagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2) //主要api配置机制初始化为swagger规范2.0
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))  //过滤的接口
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("UseManager service API")  // 标题
                .contact(new Contact("xiaohei", "", "123456@qq.com"))  // 联系方式
                .description("this is UseManager service API")  // 描述信息
                .version("1.0") //版本号
                .build();
    }
}
