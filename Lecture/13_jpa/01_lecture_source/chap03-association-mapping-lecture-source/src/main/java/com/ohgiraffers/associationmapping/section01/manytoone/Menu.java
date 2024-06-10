package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.*;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;


    /* 카테고리 코드에 123을 임의로 만들음.
    그러나 카테고리에는 123 카테고리번호가 존재하지 않기 때문에 우리가 해당 내용 담아 작성한 Insert 테스트가 실행되지 않는 게 맞다. */

    /* 필기
    *   영속성 전이
    *       : 특정 엔티티를 영속화(등록)할 때, 연관된 엔티티도 함꼐 영속화한다는 의미다.
    *       : 즉, Menu 엔티티를 영속화할 때, 이와 연관된 Category 도 같이 영속화 시킨다. */
    @ManyToOne(cascade = CascadeType.PERSIST)      // 하나의 카테고리 코드는 많은 메뉴코드를 갖고 카테고리가 Many. 메뉴가 One. 하단 해석 참고
    @JoinColumn(name = "category_code")     // 데이터테이블에서 외래키 열의 이름 지정
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected Menu() {}

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    // 엔티티는 getter 만 추가함!

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName=" + menuName +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}

/* 1) @ManyToOne

    설명: 이 어노테이션은 Menu 엔터티와 Category 엔터티 사이의 관계가 다대일(Many-to-One)임을 나타냅니다.
    즉, 여러 개의 Menu 엔터티가 하나의 Category 엔터티와 연결될 수 있습니다.
    사용: JPA는 @ManyToOne 어노테이션을 통해 두 엔터티 간의 관계를 설정합니다.


    2) cascade = CascadeType.PERSIST

    설명: 이 속성은 영속성 전이(Cascade) 동작을 정의합니다. CascadeType.PERSIST는 Menu 엔터티가 영속 상태로 전이될 때 연관된 Category 엔터티도 함께 영속 상태로 전이됨을 의미합니다.
    사용 예: 새로운 Menu 엔터티를 저장할 때, 연관된 Category 엔터티가 이미 영속화되지 않은 경우, 해당 Category 엔터티도 함께 영속화됩니다.


    3) @JoinColumn(name = "category_code")

    설명: 이 어노테이션은 외래 키 열을 정의합니다. category_code는 Menu 테이블에서 Category 테이블을 참조하는 외래 키 열입니다.
        - 속성 : name -> 데이터베이스 테이블에서 외래 키 열의 이름을 지정합니다.
    사용: JPA는 @JoinColumn 어노테이션을 통해 두 엔터티 간의 조인(Join) 관계를 정의하고, 데이터베이스에서 외래 키 제약 조건을 설정합니다.*/