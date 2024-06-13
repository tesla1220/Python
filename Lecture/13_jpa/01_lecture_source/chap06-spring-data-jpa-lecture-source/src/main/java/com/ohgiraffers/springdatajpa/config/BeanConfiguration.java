package com.ohgiraffers.springdatajpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 이 클래스는 dependencies 에 의존성 추가(여기선 modelMapper) 하고 나서 빈 추가하기 위해(즉, 객체 등록하기 위해) 생성 */

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        /* 필기 : Model Mapper
            entity 클래스에서 setter 지양 -> 그럼 값을 어떻게 넣을까?
            값을 넣으려면 필드에 접근해야한다. 그러나 모든 필드들 지금 private 상태
            private 필드에 접근할 수 있도록 설정 필요.
            modelMapper 는 entity 와 DTO 간의 변환을 용이하게 만들도록 도와주는 라이브러리다
        *   */

        ModelMapper modelMapper = new ModelMapper();


        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)        // Private 까지 접근 가능하도록 설정
                .setFieldMatchingEnabled(true);     // field 끼리 매칭 가능하도록 설정

        return modelMapper;

    }

    // 이 빈 생성 후 Service 에 생성자 생성해줌


    //package com.ohgiraffers.springdatajpa.config;
//
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
///* 필기
//*   어플리케이션 클래스는 본인 포함 상위 카테고리를 읽는데, config 경로 밑의 파일은 모두 읽을 수가 없다. 이 때문에 ContextConfiguration 클래스를 생성해
//*   @Configuarion 을 만들고 @ComponentScan 으로 패키지 위치 설정해줌 => 어플리케이션 클래스에다 애노테이션 추가해도 상관없음(동일하게 작동) */
//
//@Configuration
//@ComponentScan(basePackages = "com.ohgiraffers.springdatajpa")
//public class ContextConfiguration {
//}
}
