package flow.assignment.config;

import flow.assignment.interceptor.ExtensionHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 웹 서비스 관련 Config
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    private final ExtensionHandlerInterceptor extensionHandlerInterceptor;

    /**
     * 인터셉터를 InterceptorRegistry에 추가하는 메서드
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(extensionHandlerInterceptor).addPathPatterns("/file/upload");
    }

    /**
     * cors 매핑 정보를 CorsRegistry에 추가하는 메서드
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
