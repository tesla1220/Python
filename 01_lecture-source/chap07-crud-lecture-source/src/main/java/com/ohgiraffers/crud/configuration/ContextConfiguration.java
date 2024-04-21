package com.ohgiraffers.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/*
@ComponentScan (basePackages의 패키지부터 컴포넌트를 스캔하도록 지시.
스프링은 이 패키지와 그 하위 패키지를 스캔하여 애노테이션이 붙은 클래스를 찾아 스프링 애플리케이션 컨텍스트에 등록
 */

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
public class ContextConfiguration {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // messageSource() 메서드는 ReloadableResourceBundleMessageSource 객체를 생성하여 스프링 빈으로 등록

        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* ReloadableResourceBundleMessageSource 객체는
        스프링에서 메시지를 관리하고 다국어 지원을 제공하는 데 사용.
        메시지의 기본 인코딩은 UTF-8로 설정되고,
        메시지 파일의 위치는 "classpath:/messages/message"로 지정
         */

        /* 제공하지 않는 언어로 요청 시 MessageSource에서 사용할 default 언어 한국 */
        Locale.setDefault(Locale.KOREA);
        return source;
    }
}
