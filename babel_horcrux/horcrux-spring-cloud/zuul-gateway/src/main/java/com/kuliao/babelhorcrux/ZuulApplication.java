package com.kuliao.babelhorcrux;

import com.kuliao.babelhorcrux.filter.MyFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableOAuth2Sso
@EnableZuulProxy
//public class ZuulApplication extends WebSecurityConfigurerAdapter {
public class ZuulApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
        FilterProcessor.setProcessor(new MyFilterProcessor());

    }

    //需要在启动类里实例化具体的过滤器
    //也可以在过滤器类加上@Compoment注解
//    @Bean
//    public AccessFilter accessFilter() {
//        return new AccessFilter();
//
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated();
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ZuulApplication.class);
    }
}
