package sj.simpleboard;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sj.simpleboard.filter.LogFilter;
import sj.simpleboard.filter.LoginCheckFilter;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginCheckFilter() { //스프링 부트가 WAS띄울때 필터를 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter()); //만든 필터를 넣어준다
        filterRegistrationBean.setOrder(1); //순서를 정해줘야함
        filterRegistrationBean.addUrlPatterns("/*"); //어떤 패턴에 적용할것인가 /* 모든 url

        return filterRegistrationBean;
    }
}
