package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/* 필기
    @Entity
*       클래스 선언부 위에 위치해서 JPA 에서 사용되는 엔티티 클래스임을 표시한다.
*       이는 해당 클래스와 데이터베이스의 테이블 매핑을 의미한다.
*       - 필수 작성 사항 : 기본 생성자
*       - 사용불가 : final 클래스, enum, interface, inner class
*       - 주의사항 : 저장할 필드에 final 을 사용하면 안됨  */



    /* @Entity 는 클래스를 JPA 엔티티로 지정합니다.
        @Table 은 엔티티와 매핑될 데이터베이스 테이블을 지정합니다.
        @UniqueConstraint 는 지정된 컬럼 조합이 데이터베이스 내에서 유일함을 보장합니다.*/

@Entity(name = "entityMember")
@Table(name = "tbl_member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "phone"})
})
public class Member {

    /* 필기
    *   @Column 속성
    *       - name : 매핑할 테이블의 컬럼 이름
    *       - nullable : null 값 허용 여부 설정 = not null 제약 조건에 해당. (기본 설정 default 는 true, 즉 null 값 허용)
    *       - unique : 컬럼의 유일성 제약 조건 (기본값 false).
    *                  두 개 이상의 컬럼에 unique 제약 조건을 설정하기 위해서는 @Table 어노테이션 속성에 uniqueConstrains 속성을 추가해야한다.
    *       - columnDefinition : 직접 컬럼의 DDL 을 지정할 수 있다.
    *       - length: 문자열의 길이 = String 타입에서만 사용 (default: 255)
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private int memberNo;

    // memberId가 null 이면 안되기 때문에 여기선 nullable 을 false 로 지정
    @Column(name = "member_id", nullable = false, unique = true, columnDefinition = "varchar(10)")
    private String memberId;

    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "phone", unique = true)
    private String phone;

    // 글자 900 자까지 허용
    @Column(name = "address", length = 900)
    private String address;

    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    /* 필기
    *   @Enumerated
    *   - enum 타입을 매핑하기 위해서 사용
    *   - ORDINAL : Enum 타입을 순서로 매핑.
    *               (Enum 클래스에 속성 기재한 순서대로 자동으로 숫자 매겨짐)
    *               장점 : 데이터 베이스에 저장되는 데이터의 크기가 작음 (문자열로 작성했을 시 공간 더 많이 차지하기 때문)
    *   - STRING : Enum 타입을 문자열로 매핑
    *   */

    /* enum은 열거형(enumeration)으로, 상수의 집합을 정의하는 특수한 클래스입니다.
    Java에서 enum은 서로 관련된 상수들을 그룹화하는 데 사용됩니다.
    이는 가독성을 높이고 코드에서 특정 값들만 허용하도록 제한할 수 있습니다. */

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)    //  enum 값을 문자열로 데이터베이스에 저장
    private MemberRole memberRole;

    // status 필드를 status 컬럼에 매핑하며, char(1) 타입이고 기본값을 'Y'로 설정
    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;

    // protected 로 기본 생성자의 접근 범위를 제한
    protected Member() {}

    // 모든 필드를 초기화하는 생성자 . (memberNo는 generated value= auto increment 설정했으므로 안만들어줌)
    public Member(String memberId, String memberPwd, String memberName, String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }
}
