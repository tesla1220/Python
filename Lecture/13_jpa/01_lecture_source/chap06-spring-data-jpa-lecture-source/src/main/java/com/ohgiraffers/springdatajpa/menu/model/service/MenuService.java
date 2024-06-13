package com.ohgiraffers.springdatajpa.menu.model.service;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.dao.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository repository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    // 생성자 주입 방식의 장점 - final 키워드를 사용할 수 있다.=> 안정적으로 사용 가능
    @Autowired
    public MenuService(MenuRepository repository, ModelMapper modelMapper, CategoryRepository categoryRepository){
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;

    }

    /* 1. findById() */
    public MenuDTO findMenuByMenuCode(int menuCode) {

        // findById(menuCode) :  Repository에 이미 Menu 엔티티를 지정해줬기 때문에 엔티티 넣을 필요 없음
        Menu foundMenu = repository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
        // 자료형 Optional -> null 값 체크
        // orElseThrow : 예외 처리

        // 현재까지 반환타입은 엔티티


        // Entity 를 DTO 로 변환해주는 dependency (modelmapper) 추가함


        // map(Object Type, Class<MenuDTO>
        return modelMapper.map(foundMenu, MenuDTO.class);

    }

    /* 페이징 처리를 안한 메뉴 전체 조회 */
    public List<MenuDTO> findMenuList() {

        List<Menu> menuList = repository.findAll(Sort.by("menuCode").ascending());

        // 현재 반환값 엔티티형. DTO 로 변환해 컨트롤러로 보내줘야함

        // stream -> 해당 데이터를 쫙 펼쳐준다
        // (menu -> modelMapper.map(menu, MenuDTO.class)) : 변수 하나를 MenuDTO 로 바꿔줘

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class)) // map 안에 변수(여기선 menu) 선언 / 지금 스트림으로 펼쳐진 상태
                .collect(Collectors.toList());      // 현재 자료형인 스트림 타입을 리스트 타입으로 바꿔줘


    }

    /* 페이징 처리를 한 메뉴 전체 조회 */

    // Overloading : 메소드명 중복더라도 변수가 다르면 사용 가능
    public Page<MenuDTO> findMenuList(Pageable pageable){

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1,
                                  pageable.getPageSize(),
                                  Sort.by("menuCode").ascending());

        // 위에서 만들은 페이지에 대한 정보 담기
        Page<Menu> menuList = repository.findAll(pageable);

        // DTO 타입으로 바꿔서 컨트롤러로 반환
        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }


    /* Query 메소드를 사용해서 조회하기 */
    public List<MenuDTO> findByMenuPrice(int menuPrice) {

//        List<Menu> menuList = repository.findByMenuPriceGreaterThan(menuPrice);
//        List<Menu> menuList = repository.findByMenuPriceGreaterThanOrderByMenuPriceMenuPrice(menuPrice);
        List<Menu> menuList = repository.findByMenuPriceGreaterThan(menuPrice, Sort.by("menuPrice").ascending());

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    /* @Query : JPQL 과 Native Query */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    // save() 등록 관련 메소드
    @Transactional
    public void registNewMenu(MenuDTO menuDTO) {

        // 엔티티 타입으로 써야하므로 modelMapper 사용
         repository.save(modelMapper.map(menuDTO, Menu.class));
        // menuDTO 를 Menu 엔티티 클래스로 바꿔줘

    }
}
