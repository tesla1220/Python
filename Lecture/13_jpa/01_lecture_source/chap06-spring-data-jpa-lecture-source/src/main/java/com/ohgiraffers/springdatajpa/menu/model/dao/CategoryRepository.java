package com.ohgiraffers.springdatajpa.menu.model.dao;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// JpaRepository 상속받고, < 1 , 2 > 1에 엔티티 클래스 이름, 2에

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    // Native Query 를 사용 시에는 Native Query 를 사용한다는 사실을 알려야 오류 안생김 => nativeQuery = true
    @Query(value = "SELECT category_code, category_name, ref_category_code FROM tbl_category ORDER BY category_code"
            , nativeQuery = true)
    List<Category> findAllCategory();




    /* JPQL 사용 시 :
    nativeQuery= true 가 아닐 시(JPQL 사용 시)에는 아래처럼 작성. 더불어, 엔티티명 작성하지 않았을 때는 맨 앞의 단어 자동 소문자로 변환됨
    @Query(value = "SELECT m FROM categoryEntity m ORDER BY m.categoryCode") */

}
