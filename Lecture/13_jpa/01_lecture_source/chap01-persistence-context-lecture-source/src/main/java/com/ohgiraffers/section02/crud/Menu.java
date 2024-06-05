package com.ohgiraffers.section02.crud;

import jakarta.persistence.*;

@Entity(name = "section02Menu")     // 보통 이름 지정 안해줌. Entity는 하나이기 때문에
@Table(name = "tbl_menu")
public class Menu {

    // pk -> (pk 기본 조건) not null, unique -> (데이터 베이스 설정하는 부분) auto increment
    @Id // not null, unique 갖고 있는 상태
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk에 auto increment 설정, mySQL은 Identity, Oracle은 Sequence 사용
    /* strategy 속성
    *   AUTO : 우리가 사용하는 DB 에 따라 자동 설정되지만 되도록 정확하게 명시해주는 것을 권장
    *   IDENTITY / SEQUENCE : mysql -> auto_increment 사용. oracle -> sequence 사용 */
    @Column(name = "menu_code")     // 컬럼명도 지정 가능!
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    // 이 엔티티들  JPA 에게 알려주기 위해 xml 파일에서 내용 작성

    // protected : 같은 패키지 내에 있는 녀석들만 사용 가능. 여기에선 crud 에서만 사용 가능
    protected Menu() {}

    public Menu(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }


    public int getMenuCode() {

        return this.menuCode;
    }

    public void setMenuName(String menuName) {
        // 업데이트 할 때 전달한 값을 필드에 매칭
        this.menuName = menuName;
    }

    public String getMenuName() {

        return menuName;
    }
}
