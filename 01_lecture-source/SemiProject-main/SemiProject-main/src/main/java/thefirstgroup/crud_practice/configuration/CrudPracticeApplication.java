package thefirstgroup.crud_practice.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "thefirstgroup.crud_practice", annotationClass = Mapper.class)
public class CrudPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudPracticeApplication.class, args);
    }

}
