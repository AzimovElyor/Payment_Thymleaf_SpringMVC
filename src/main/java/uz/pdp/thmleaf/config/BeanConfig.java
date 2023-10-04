package uz.pdp.thmleaf.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import uz.pdp.thmleaf.filter.AdminFilter;
import uz.pdp.thmleaf.filter.UserFilter;


import java.util.List;

@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder()
                .build();
    }
    @Bean
    public FilterRegistrationBean<UserFilter> userFilter(){
        FilterRegistrationBean<UserFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserFilter());
        registrationBean.setUrlPatterns(List.of("/user/*"));

        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter(){
        FilterRegistrationBean<AdminFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.setUrlPatterns(List.of("/admin/*"));
        return registrationBean;
    }
}
