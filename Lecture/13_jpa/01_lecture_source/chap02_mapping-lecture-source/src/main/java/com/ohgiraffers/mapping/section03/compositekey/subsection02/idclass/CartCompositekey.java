package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

/*
    * 복합 키 클래스:
    CartCompositekey는 복합 키를 정의하는 클래스입니다. 복합 키 클래스를 정의하려면, 두 가지 주요 요구 사항을 충족해야 합니다:

    1. Serializable 인터페이스를 구현해야 합니다.
    2. 기본 생성자, equals, hashCode 메서드를 구현해야 합니다.
*/

public class CartCompositekey {

    private int cartOwner;

    private int addedBook;

    protected CartCompositekey() {}

    public CartCompositekey(int cartOwner, int addedBook ){
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
    }
}

/* @IdClass(CartCompositekey.class)는 JPA(Entity)에서 복합 키(Composite Key)를 정의하기 위해 사용하는 어노테이션입니다.
    복합 키란 하나의 엔티티(Entity)에서 여러 개의 필드를 합쳐서 하나의 기본 키(Primary Key)로 사용하는 것을 말합니다.
    이를 통해 데이터베이스 테이블의 행을 고유하게 식별할 수 있습니다.       */
