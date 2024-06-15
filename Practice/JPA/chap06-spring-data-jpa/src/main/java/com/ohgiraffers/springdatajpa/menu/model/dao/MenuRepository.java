package com.ohgiraffers.springdatajpa.menu.model.dao;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository <Menu,Integer> {


    List<Menu> findMenuByMenuPrice(int menuPrice);
}


