package org.secretdemo.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
public class TokenFeignConfig {
    /**
     * token 传递的 key
     */
    public static final String HEAD_TOKEN_KEY = "dh-token";

    @Bean
    public RequestInterceptor requestInterceptor() {
        log.info("TokenFeignConfig requestInterceptor");

        return requestTemplate -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request;
            if (attr != null) {
                request = attr.getRequest();
                String token = request.getHeader(HEAD_TOKEN_KEY);
                requestTemplate.header(HEAD_TOKEN_KEY, token);
            }
        };
    }
}