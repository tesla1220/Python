package com.ohgiraffers.springdatajpa.menu.model.dao;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    // Native Query 를 사용 시에는 Native Query 를 사용한다는 사실을 알려야 오류 안생김 => nativeQuery = true
    @Query(value = "SELECT category_code, category_name, ref_category_code FROM tbl_category ORDER BY category_code"
            , nativeQuery = true)
    List<Category> findAllCategory();

}
