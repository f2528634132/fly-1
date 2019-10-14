package com.fly.fankun.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * swagger地址
     */
    @Value("${swagger.request.url}")
    private String requestUrl;
    /**
     * 扫描路径包
     */
    @Value("${swagger.request.package}")
    private String basePackage;
    /**
     * 非开发环境配置代理地址
     */
    @Value("${swagger.request.ngnix}")
    private String nginxPath;

    /**
     * 是否开启swagger，正式环境一般是需要关闭的，可根据多环境配置进行设置
     */
    @Value(value = "${swagger.enabled}")
    private Boolean swaggerEnabled;

    /**
     * 页面标题名称
     */
    @Value("${swagger.api.title}")
    private String title;

    private static final String AUTHORIZATION = "Authorization";

    @Bean
    public Docket createRestApi() {
        StringBuilder sb = new StringBuilder();
        sb.append(requestUrl).append(nginxPath);
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(swaggerEnabled)
            .apiInfo(apiInfo())
            .host(sb.toString())
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage(basePackage))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
    }

    /**
     * 构建 api文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(title)
            .build();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<ApiKey> securitySchemes() {
        return new ArrayList( Arrays.asList(new ApiKey(AUTHORIZATION, AUTHORIZATION, "header")));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<SecurityContext> securityContexts() {
        return new ArrayList(
            Arrays.asList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build())
        );
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new ArrayList( Arrays.asList(new SecurityReference(AUTHORIZATION, authorizationScopes)));
    }
}
