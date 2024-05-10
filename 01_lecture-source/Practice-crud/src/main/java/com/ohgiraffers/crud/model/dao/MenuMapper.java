package com.ohgiraffers.crud.model.dao;

import com.ohgiraffers.crud.model.dto.CategoryDTO;
import com.ohgiraffers.crud.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu();

    void registNewMenu(MenuDTO menuDTO);

}
