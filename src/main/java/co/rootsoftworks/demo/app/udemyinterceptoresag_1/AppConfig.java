package co.rootsoftworks.demo.app.udemyinterceptoresag_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("interceptorTiempo")
    private HandlerInterceptor loadingTimInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       
        registry.addInterceptor(loadingTimInterceptor).addPathPatterns("/app/foo");
    }
    
    
}
