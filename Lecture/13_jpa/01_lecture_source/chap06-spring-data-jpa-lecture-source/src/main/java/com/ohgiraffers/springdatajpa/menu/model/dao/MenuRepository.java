package com.ohgiraffers.springdatajpa.menu.model.dao;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 필기
*   JPA Repository 를 상속받을 거임(<내가 사용할 엔티티, 해당 클래스의 Id 자료형>     interface 는 상속받을 떄 'extends' 사용
*   (부모) Repository <상속< CrudRepository <상속< PagingAndSortingRepository <상속< JpaRepository (제일 자식)
*   - EntityManagerFactory, EntityManager, EntityTransaction 자동 구현   */

// JPA 는 변경이 감지되면 자동으로 업데이트해주기 때문에 위 나열한 상위 상속 클래스들에는 update 에 관련된 구현 기능은 없다.


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    // <T, ID> T ->엔티티 클래스의 정보 (여기선 menu)   ID -> 자료형 : Id 애노테이션이 붙은 거에 wrapper 클래스 형 (여기선 Integer)

    List<Menu> findByMenuPriceGreaterThan(int menuPrice);

    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(int menuPrice);

    List<Menu> findByMenuPriceGreaterThan(int menuPrice, Sort menuPrice1);
}
