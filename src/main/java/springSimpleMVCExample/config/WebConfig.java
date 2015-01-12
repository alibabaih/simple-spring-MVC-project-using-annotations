package springSimpleMVCExample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration //annotation says that this class is the Java Configuration
@EnableWebMvc //this annotation allows our project using MVC
@ComponentScan("springSimpleMVCExample") //аналогично  mvc-dispatcher-servlet.xml, говорит, где искать компоненты проект
public class WebConfig extends WebMvcConfigurerAdapter { //унаследовавшись от этого класса мы получим возможность сконфигурировать ResourceLocations

    @Override //переопределив данный метод мы сможем указать где будут лежать ресурсы нашего проекта, такие как css, image, js и другие.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }

    @Bean //указывает на то что это инициализация бина, и он будет создан с помощью DI
    public InternalResourceViewResolver setupViewResolver() { //аналогичная конфигурация с mvc-dispatcher-servlet.xml
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    //Теперь нужно зарегистрировать конфигурацию в Spring Context это нам позволит сделать наш класс AppInit
}