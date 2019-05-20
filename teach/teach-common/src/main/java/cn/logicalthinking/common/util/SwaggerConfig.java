package cn.logicalthinking.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@EnableSwagger2  
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	@Bean
    public Docket getApiInfo() {
		ParameterBuilder tokenPar = new ParameterBuilder();
    	List<Parameter> pars = Lists.newArrayList();
    	tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
    	pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .produces(Sets.newHashSet("application/json;charset=UTF-8"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(outApiInfo())
                .globalOperationParameters(pars);

    }


	private ApiInfo outApiInfo() {
		 ApiInfo apiInfo = new ApiInfoBuilder()
	         .title("测试数据相关接口")
	         .version("V1.0")
	         .description("主要是测试数据的相关接口")
	         .build();
		 return apiInfo;
	}
}	
