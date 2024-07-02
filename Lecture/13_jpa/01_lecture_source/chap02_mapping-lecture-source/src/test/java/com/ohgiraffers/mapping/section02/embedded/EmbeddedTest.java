package com.ohgiraffers.mapping.section02.embedded;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
public class EmbeddedTest {

    /* @Autowired: 이 애너테이션은 BookRegistService 의존성을 테스트 클래스에 주입하는 데 사용됩니다.
                   테스트 실행 시 Spring이 자동으로 BookRegistService 인스턴스를 제공합니다. */
    @Autowired
    private BookRegistService bookRegistService;

    /*
    * private static: 이 메서드가 클래스 자체에 속하며 인스턴스가 아닌 정적 메서드임을 나타냅니다.

    * Stream<Arguments> getBook(): 이 메서드는 Arguments 스트림을 반환합니다.
    Arguments는 JUnit 5 라이브러리의 클래스로, 매개변수화된 테스트에서 사용할 매개변수를 담고 있습니다.
    */
    private static Stream<Arguments> getBook() {

        /* 테스트 데이터 생성

        * Stream.of(...): 매개변수의 스트림을 생성합니다. Stream은 일련의 요소들로, 순차 및 병렬 집계 작업을 지원합니다.
        * Arguments.of(...): 제공된 매개변수로 Arguments 인스턴스를 생성합니다. 이 매개변수들은 테스트에서 사용할 책의 데이터를 나타냅니다. */
        return Stream.of(
                Arguments.of(
                        "조평훈의 JPA 정석",
                        "조평훈",
                        "에이콘",
                        LocalDate.now(),
                        43000,
                        0.1
                )
        );
    }

    /*
     @ParameterizedTest: 이 애너테이션은 메서드가 매개변수화된 테스트임을 나타냅니다.
     @MethodSource("getBook"): 이 애너테이션은 getBook 메서드가 테스트에 필요한 매개변수를 제공한다고 명시합니다.
     */
    @DisplayName("Embedded 테스트")
    @ParameterizedTest
    @MethodSource("getBook")
    void testCreateEmbeddedPriceOfBook(String bookTitle, String author, String publisher,
                                       LocalDate publishedDate, int regularPrice, double discountRate) {

        /* BookRegistDTO newBook = new BookRegistDTO(...): 주어진 매개변수를 사용하여 BookRegistDTO 객체를 생성합니다.  */
        BookRegistDTO newBook = new BookRegistDTO(
                bookTitle,
                author,
                publisher,
                publishedDate,
                regularPrice,
                discountRate
        );

        /*
        * Assertions.assertDoesNotThrow(...): JUnit의 단언문으로, 주어진 람다 표현식이 예외를 던지지 않는지 확인합니다.

        * () -> bookRegistService.registBook(newBook): 람다 표현식으로 bookRegistService의 registBook 메서드를 호출합니다.
                                                       이 메서드는 newBook 객체를 사용하여 책을 등록하는 역할을 합니다.

        * bookRegistService.registBook(newBook): BookRegistService 클래스의 메서드로, 책 등록 로직을 실행합니다.       */

        Assertions.assertDoesNotThrow(
                () -> bookRegistService.registBook(newBook)
        );

    }

}
