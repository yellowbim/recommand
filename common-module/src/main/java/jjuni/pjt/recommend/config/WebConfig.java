package jjuni.pjt.recommend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("~~");
        // 화면단 CORS 해제
        registry.addMapping("/**").allowedOrigins("http://localhost:3000");
        // 이 한줄을 작성안하면 모든 도메인 허용하는 듯하다
    }

}
