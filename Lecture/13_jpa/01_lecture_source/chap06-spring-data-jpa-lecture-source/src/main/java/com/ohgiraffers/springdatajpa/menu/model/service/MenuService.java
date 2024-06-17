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

        // 현재 여기까지 반환타입은 엔티티인 상태

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
                .collect(Collectors.toList());      // 현재 엔티티를 리스트 타입으로 바꿔줘


    }

    /* 페이징 처리를 한 메뉴 전체 조회
            이 메소드는 페이지네이션을 이용하여 데이터베이스에서 메뉴 항목을 조회하고,
            조회된 각 항목을 MenuDTO 객체로 변환하여 Page<MenuDTO> 형태로 반환합니다.
            이를 통해 화면에 표시할 페이지별 메뉴 항목 목록을 효과적으로 관리하고 제어할 수 있습니다  */

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
//          Sort.by("menuPrice") 의 "menuPrice" 는 Menu 엔티티 내 menuPrice.

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

    @Transactional      // 수정이기 때문
    public void modifyMenu(MenuDTO modifyMenuDTO) {

        Menu menuEntityResult = repository.findById(modifyMenuDTO.getMenuCode()).orElseThrow(IllegalArgumentException::new);
        // repository 가 메뉴코드로 식별한 값 => 메뉴 엔티티

        /* 1. setter 사용  - setter 사용은 지양한다. */
//        menuEntity.setMenuName(modifyMenuDTO.getMenuName());

        // 이 후 Menu 엔티티 클래스에 setMenuName 추가해줌. -> 2번 진행 위해 주석처리


        /* 2. @Builder */
//        menuEntityResult = menuEntityResult.toBuilder().menuName(modifyMenuDTO.getMenuName()).build();
//        repository.save(menuEntityResult);


        /* 3. Entity 클래스 내부에서 Builder 패턴을 사용해서 구현하기 */
        menuEntityResult = menuEntityResult.menuName(modifyMenuDTO.getMenuName()).builder();
        repository.save(menuEntityResult);


    }

    @Transactional
    public void deleteMenu(int menuCode) {

        repository.deleteById(menuCode);

    }
}


/* 🟠 orElseThrow(IllegalArgumentException::new)
    Java 8의 Optional 클래스에서 제공하는 메서드입니다. 이 코드는 Optional 객체가 비어 있을 때 예외를 던지는 방식으로 처리합니다.

    Context
        menuRepository.findById(menuCode)
            menuCode에 해당하는 엔티티를 데이터베이스에서 찾는 메서드입니다. 이 메서드는 Optional<Menu>를 반환합니다.
            Optional은 값이 존재할 수도 있고, 존재하지 않을 수도 있는 컨테이너 클래스입니다.
            값이 존재할 때는 해당 값을 반환하고, 존재하지 않을 때는 적절한 처리를 할 수 있도록 도와줍니다.

        orElseThrow
            Optional 클래스의 메서드로, Optional 객체가 비어 있을 경우 지정된 예외를 던집니다.

        IllegalArgumentException::new
            IllegalArgumentException 예외를 생성하는 생성자 참조입니다. :: 연산자는 메서드 참조 또는 생성자 참조를 나타냅니다.

    의미
        menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new)

        menuRepository.findById(menuCode)
            menuCode에 해당하는 Menu 엔티티를 데이터베이스에서 찾으려고 시도합니다.
             Optional<Menu>가 반환됩니다. 만약 menuCode에 해당하는 Menu가 존재하면 Optional은 값을 포함하고, 존재하지 않으면 비어 있게 됩니다.

        orElseThrow(IllegalArgumentException::new)
            Optional이 비어 있는 경우, 즉 menuCode에 해당하는 Menu가 존재하지 않는 경우 IllegalArgumentException을 던집니다.

    정리
        menuRepository.findById(menuCode):
            menuCode에 해당하는 Menu를 찾습니다. 결과는 Optional<Menu>입니다.
        orElseThrow(IllegalArgumentException::new):
            만약 Optional<Menu>가 비어 있으면 IllegalArgumentException 예외를 던집니다. 그렇지 않으면 Optional에 들어 있는 Menu 객체를 반환합니다.

   ▶️ 이 코드의 목적은 menuCode에 해당하는 Menu가 반드시 존재해야 한다는 것을 보장하는 것입니다. 존재하지 않을 경우 예외를 통해 문제를 알립니다.


    */

    /* .map(menu -> modelMapper.map(menu, MenuDTO.class))
            menuList의 각 Menu 객체를 MenuDTO 객체로 변환
            modelMapper는 객체 간 변환을 지원하는 라이브러리
            map() 메소드는 각 요소를 변환하고, Menu 객체를 MenuDTO 객체로 매핑하는 데 사용

        .collect(Collectors.toList())
            스트림의 각 요소를 리스트로 수집. 따라서 최종적으로 List<MenuDTO> 형태로 변환된 메뉴 항목 목록을 반환

 */



/* pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1

     pageable 객체에서 현재 페이지 번호를 가져옵니다. 페이지 번호가 0 이하일 경우 0으로 설정하고, 그렇지 않으면 현재 페이지 번호에서 1을 빼서 페이지 번호를 설정합니다.
     이렇게 함으로써 첫 페이지에서 이전 페이지로 넘어갈 때 예외를 처리합니다.


    pageable.getPageSize()
        pageable 객체에서 페이지 크기를 가져옵니다. 한 페이지에 표시할 항목의 최대 개수입니다.

    Sort.by("menuCode").ascending()
        menuCode 필드를 기준으로 오름차순으로 정렬합니다. 정렬 방식을 지정합니다.

    PageRequest.of(...)
        위에서 계산한 페이지 번호, 페이지 크기, 정렬 방식을 이용하여 새로운 PageRequest 객체를 생성합니다.
        이 객체는 실제 데이터베이스 쿼리에 사용될 페이지네이션 정보를 포함합니다.

    repository.findAll(pageable)
        데이터베이스에서 지정된 페이지네이션 정보(pageable)에 따라 메뉴 항목을 조회합니다.
        repository는 Spring Data JPA에서 제공하는 메뉴 엔티티를 관리하는 레포지토리 인터페이스일 것입니다.

    menuList.map(menu -> modelMapper.map(menu, MenuDTO.class))
        menuList는 Page<Menu> 타입의 객체로, 데이터베이스에서 조회한 메뉴 항목들을 포함합니다.
        map() 메소드를 사용하여 각 Menu 객체를 MenuDTO 객체로 변환합니다. modelMapper를 사용하여 엔티티 객체를 DTO 객체로 변환하는 데 사용됩니다.

    return
        최종적으로 변환된 Page<MenuDTO> 객체를 반환합니다. 이 객체는 페이지네이션된 메뉴 항목 목록을 나타내며, MenuDTO 객체들을 포함합니다.


*/